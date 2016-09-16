'use strict';

angular.module('Administration')
.controller('AdministrationPageController', ['$scope', '$rootScope', '$mdDialog', 'AdministrationService',
    function($scope, $rootScope, $mdDialog, AdministrationService) {
		$scope.tabs = ['modules/administration/administration.worklog.html', 'modules/administration/administration.absence.html'];
		$scope.currentTab = $scope.tabs[0];
		$scope.isActiveTab = function(tabUrl) {
			return tabUrl == $scope.currentTab;
		};
		$scope.onClickTab = function(tab) {
			if(tab === 'Worklog'){
				$scope.currentTab = $scope.tabs[0];
			} else {
				$scope.currentTab = $scope.tabs[1];
			}
		};
		$scope.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		$scope.selectedDateFilterAbsence = "All";
		$scope.selectedDateFilterWorklog = "All";

		$scope.employees = [];
		$scope.selectedEmployeeAbsence = "";
		$scope.selectedEmployeeWorklog = "";
		$scope.selectedEmployeeEdit = '';
		$scope.emptyEmployeeList = false;

		$scope.employeeWorklogs = [];
		$scope.emptyWorklogList = false;
		$scope.employeeAbsences = [];
		$scope.emptyAbsenceList = false;

		$scope.listDailyWorkHour = false;
		$scope.listNotAccepted 	 = false;

		$scope.newFirstName = "";
		$scope.newLastName = "";
		$scope.newPosition = "";
		$scope.newEmailAddress = "";
		$scope.newDailyWorkHourTotal = "";
		
		$scope.init = function() {
			AdministrationService.GetEmployees($rootScope.userData.workerId).then(
					function(result) {
						if(result.length > 0) {
							result.forEach(function(employee) {
								$scope.employees.push(employee.firstName + ' ' + employee.lastName); 
							});
							$rootScope.emptyEmployeeList = $scope.emptyEmployeeList = false;
							$rootScope.employees = $scope.employees;
						} else {
							$rootScope.emptyEmployeeList = $scope.emptyEmployeeList = true;
						}
					},
					function(error) {
					}
				);
		};

		$scope.filterWorklog = function() {
			if($scope.selectedEmployeeWorklog !== "") {
				var splitted = $scope.selectedEmployeeWorklog.split(" ");
				AdministrationService.GetWorklogsByEmployee(splitted[0], splitted[1], $scope.selectedDateFilterWorklog, $scope.listDailyWorkHour).then(
						function(result) {
							$scope.employeeWorklogs = [];
							$scope.employeeWorklogs = result;
							if($scope.employeeWorklogs.length > 0) {
								$scope.emptyWorklogList = false;
							} else {
								$scope.emptyWorklogList = true;
							}
						},
						function(error) {
						}
					);
			}
		};
		$scope.filterAbsence = function() {
			if($scope.selectedEmployeeAbsence !== "") {
				var splitted = $scope.selectedEmployeeAbsence.split(" ");
				AdministrationService.GetAbsencesByEmployee(splitted[0], splitted[1], $scope.selectedDateFilterAbsence, $scope.listNotAccepted).then(
						function(result) {
							$scope.employeeAbsences = [];
							$scope.employeeAbsences = result;
							if($scope.employeeAbsences.length > 0) {
								$scope.emptyAbsenceList = false;
							} else {
								$scope.emptyAbsenceList = true;
							}
						},
						function(error) {
						}
					);
			}
		};
		$scope.acceptEmployeeAbsence = function(ev, absence) {

			var confirm = $mdDialog.confirm().title('Approve Selected Absence')
											 .clickOutsideToClose(true)
										     .htmlContent('<div><p>Are you sure about approve the below Absence?<br>Begin Date: '+absence.beginDate+'<br>End Date: '+absence.endDate+'<br>Absence Type: '+absence.absenceType+'</p></div>')
										     .targetEvent(ev)
										     .ok('Yes')
										     .cancel('No');
			$mdDialog.show(confirm).then(function() { // Yes
				AdministrationService.AcceptEmployeeAbsence(absence.id).then(
					function(result) {
					},
					function(error) {
					}
				);
				for(var i = 0; i < $scope.employeeAbsences.length; i++) {
					if($scope.employeeAbsences[i].id === absence.id) {
						$scope.employeeAbsences[i].status = 'APPROVE';
						break;
					}
				}
			}, function() { // No
			});	
		};
		$scope.SelectEmployeeForEdit = function() {
			AdministrationService.GetEmployeeWorkerData($rootScope.userData.workerId).then(
				function(result) {
					$scope.newFirstName = result.firstName;
					$scope.newLastName = result.lastName;
					$scope.newPosition = result.position;
					$scope.newEmailAddress = result.emailAddress;
					$scope.newDailyWorkHourTotal = result.dailyWorkHourTotal;
				},
				function(error) {
				}
			);
		};
		$scope.ChangeEmployeeData = function() {
			AdministrationService.EditEmployeeWorkerData($scope.newFirstName, $scope.newLastName, $scope.newPosition, $scope.newEmailAddress, $scope.newDailyWorkHourTotal).then(
				function(result) {
				},
				function(error) {
				}
			);
		};

		$scope.sortType = "BeginDate";
		$scope.sortReverse = false;
		$scope.searchQuery = "";
		$scope.showDownCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && !$scope.sortReverse);
		};
		$scope.showUpCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && $scope.sortReverse);
		};
		$scope.setSearchTypeOrReverse = function(tableHeader) {
			if($scope.sortType == tableHeader){
				$scope.sortReverse = !$scope.sortReverse;
			} else {
				$scope.sortType = tableHeader;
			}
		};
    }]);
'use strict';

angular.module('Administration')
.controller('AdministrationPageController', ['$scope', '$rootScope', 'AdministrationService',
    function($scope, $rootScope, AdministrationService) {
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
		$scope.selectedEmployee = "";
		$scope.emptyEmployeeList = false;
		
		$scope.employeeWorklogs = [];
		$scope.emptyWorklogList = false;
		$scope.employeeAbsences = [];
		$scope.emptyAbsenceList = false;

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
			if($scope.selectedEmployee !== "") {
				var splitted = $scope.selectedEmployee.split(" ");
				AdministrationService.GetWorklogsByEmployee(splitted[0], splitted[1], $scope.selectedDateFilterWorklog).then(
						function(result) {
							$scope.employeeWorklogs = [];
							$scope.employeeWorklogs = result;
							if($scope.employeeWorklogs.length > 0) {
								$scope.emptyWorklogList = false;
								$scope.dateFormatter();
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
			if($scope.selectedEmployee !== "") {
				var splitted = $scope.selectedEmployee.split(" ");
				AdministrationService.GetWorklogsByEmployee(splitted[0], splitted[1], $scope.selectedDateFilterAbsence).then(
						function(result) {
							$scope.employeeAbsences = [];
							$scope.employeeAbsences = result;
							if($scope.employeeAbsences.length > 0) {
								$scope.emptyAbsenceList = false;
								$scope.dateFormatter();
							} else {
								$scope.emptyAbsenceList = true;
							}
						},
						function(error) {
						}
					);				
			}
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
		$scope.worklogDateFormatter = function() {
			if (!(typeof $scope.employeeWorklogs) || $scope.employeeWorklogs.length !== 0) {
				$scope.employeeWorklogs.forEach(function(employeeWorklog) {
					employeeWorklog.beginDate = moment(employeeWorklog.beginDate).format('YYYY.MM.DD');
				});
			}
		};
    }]);
'use strict';

angular.module('Absence')
.controller('AbsenceController', ['$scope', '$rootScope', '$mdDialog', 'AbsenceService',
    function ($scope, $rootScope, $mdDialog, AbsenceService) {

		$scope.absences = [];
		$scope.absenceDatas = [];
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
			if($scope.sortType == tableHeader) {
				$scope.sortReverse = !$scope.sortReverse;
			} else {
				$scope.sortType = tableHeader;
			}
		};
		$scope.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		$scope.selectedDateFilter = "All";

		$scope.absenceType = "PAYED";
		$scope.beginDate = "";
		$scope.endDate = "";

		$scope.showStatus = function(result) {
			var textContent = '';
			if(result === -2) {
				textContent = 'The saving is unsuccesfull, the Begin or End Date is in the range of an already exist absence!';
			} else {
				textContent = 'The saving is succesfull!';
			}
			alert = $mdDialog.alert({
				title: 'Absence Adding',
				textContent: textContent,
				clickOutsideToClose: true,
				ok: 'Close'
			});
		    $mdDialog.show(alert)
					 .finally(function() {
						alert = undefined;
					 });
		};
		$scope.AddAbsence = function() {
			//for(var i = 0; i < $scope.worklogs.length; i++) {
				//if(moment($scope.worklogs[i].beginDate).isBetween($scope.beginDate, $scope.endDate) ||
				//	 moment($scope.worklogs[i].endDate).isBetween($scope.beginDate, $scope.endDate)) {
				//	$scope.showStatus(-1);
				//}
			//}
			var dateBegin = moment($scope.beginDate).format('YYYY.MM.DD');
			var dateEnd = moment($scope.endDate).format('YYYY.MM.DD');
			AbsenceService.AddAbsence(dateBegin, dateEnd, $rootScope.userData.workerId, $scope.absenceType).then(
				function(result) {
					$scope.absences.push({
						beginDate: dateBegin,
						endDate: dateEnd,
						absenceType: $scope.absenceType
					});
					$scope.absenceType = "PAYED";
					$scope.beginDate = "";
					$scope.endDate = "";
					$scope.showStatus(result);
				},
				function(error) {
				}
			);
		};
		$scope.initAbsence = function() {
			if((typeof $scope.absences || $scope.absences.length === 0) && 
			   (typeof $scope.absenceDatas || $scope.absenceDatas.length === 0)) {
				$scope.GetAbsences();
				$scope.GetAbsenceDatas();
			}
		};
		$scope.GetAbsences = function() {
			AbsenceService.GetAbsence($rootScope.userData.workerId, $scope.selectedDateFilter).then(
				function(result) {
					$scope.absences = [];
					$scope.absences = result;
				},
				function(error) {
				}
			);
		};
		$scope.GetAbsenceDatas = function() {
			AbsenceService.GetAbsenceData($rootScope.userData.workerId).then(
				function(result) {
					$scope.absenceDatas = result;
				},
				function(error) {
				}
			);
		};
		$scope.DeleteAbsence = function(ev, absence) {
			var confirm = $mdDialog.confirm().title('Absence Delete')
											 .clickOutsideToClose(true)
										     .htmlContent('<div><p>Are you sure about delete the below Absence?<br>Begin Date: '+absence.beginDate+'<br>End Date: '+absence.endDate+'<br>Absence Type: '+absence.absenceType+'</p></div>')
										     .targetEvent(ev)
										     .ok('Yes')
										     .cancel('No');
			$mdDialog.show(confirm).then(function() { // Yes
				AbsenceService.DeleteAbsence(absence.id).then(
					function(result) {
					},
					function(error) {
					}
				)
				for(var i = 0; i < $scope.absences.length; i++) {
					if($scope.absences[i].id === absence.id) {
						$scope.absences.splice(i, 1);
						break;
					} 
				}
			}, function() { // No
			});
		};
		$scope.EditAbsence = function(ev, absence) {
			$rootScope.selectedAbsence = absence;
			$mdDialog.show({
				locals: { absenceData: absence },
				templateUrl: 'modules/absence/absence.edit.html',
				clickOutsideToClose: true,
				bindToController: true,
				controller: 'AbsenceEditController',
				parent: angular.element(document.body),
				targetEvent: ev
			}).then(function(answer) {
				for(var i = 0; i < $scope.absences.length; i++) {
					if($scope.absences[i].id === absence.id) {
						$scope.absences[i].beginDate = answer.beginDate;
						$scope.absences[i].endDate = answer.endDate;
						$scope.absences[i].absenceType = answer.absenceType;
						break;
					}
				}
			}, function() {
			});
		};
		$scope.ExportAbsence = function(excelType) {
			
			var excelTypeStr = ((excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
			var excelFileName = 'ExportAbsence.xls' + ((excelType === 1) ? '' : 'x');

			AbsenceService.ExportAbsence($rootScope.userData.workerId, $scope.selectedDateFilter, excelType).then(
				function(result) {
					var blob = new Blob([result], {type: excelTypeStr});
					saveAs(blob, excelFileName);
				},
				function(error) {
				}
			);
		};
    }]);
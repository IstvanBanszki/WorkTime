(function() {
	'use strict';

	angular
		.module('Absence')
		.controller('AbsenceController', AbsenceController);

	AbsenceController.$inject =	['$rootScope', '$mdDialog', 'AbsenceService', 'StatusLogService'];

	function AbsenceController($rootScope, $mdDialog, AbsenceService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.absences = [];
		vm.absenceDatas = [];
		vm.sortType = "BeginDate";
		vm.sortReverse = false;
		vm.searchQuery = "";
		vm.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		vm.selectedDateFilter = "All";
		vm.absenceType = "PAYED";
		vm.beginDate = "";
		vm.endDate = "";
		//Bindable functions
		vm.showDownCaret = showDownCaret;
		vm.showUpCaret = showUpCaret;
		vm.setSearchTypeOrReverse = setSearchTypeOrReverse;
		vm.addAbsence = addAbsence;
		vm.getAbsences = getAbsences;
		vm.getAbsenceDatas = getAbsenceDatas;
		vm.deleteAbsence = deleteAbsence;
		vm.editAbsence = editAbsence;
		vm.exportAbsence = exportAbsence;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			if((typeof vm.absences || vm.absences.length === 0) && 
			   (typeof vm.absenceDatas || vm.absenceDatas.length === 0)) {
				getAbsences();
				getAbsenceDatas();
			}
		};
		function showDownCaret(tableHeader) {
			return (vm.sortType == tableHeader && !vm.sortReverse);
		}

		function showUpCaret(tableHeader) {
			return (vm.sortType == tableHeader && vm.sortReverse);
		}

		function setSearchTypeOrReverse(tableHeader) {
			if(vm.sortType == tableHeader) {
				vm.sortReverse = !vm.sortReverse;
			} else {
				vm.sortType = tableHeader;
			}
		}

		function createExcelFileName(excelType) {
			var employeeName = $rootScope.profileData.firstName+$rootScope.profileData.lastName;
			return employeeName+'-'+moment(new Date()).format('YYYYMMDDHHhhmmss')+'-ExportAbsence.xls'+((excelType === 1) ? '' : 'x');
		}

		function addAbsence() {
			//for(var i = 0; i < $scope.worklogs.length; i++) {
				//if(moment($scope.worklogs[i].beginDate).isBetween($scope.beginDate, $scope.endDate) ||
				//	 moment($scope.worklogs[i].endDate).isBetween($scope.beginDate, $scope.endDate)) {
				//	$scope.showStatus(-1);
				//}
			//}
			var dateBegin = moment(vm.beginDate).format('YYYY.MM.DD');
			var dateEnd = moment(vm.endDate).format('YYYY.MM.DD');
			AbsenceService.addAbsence(dateBegin, dateEnd, $rootScope.userData.workerId, vm.absenceType).then(
				function(result) {
					StatusLogService.showStatusLog(result.status, 'Absence Adding');
					vm.absences.push({
						id: result.newId,
						beginDate: dateBegin,
						endDate: dateEnd,
						absenceType: vm.absenceType,
						status: 'NOT_APPROVE'
					});
					vm.absenceType = "PAYED";
					vm.beginDate = "";
					vm.endDate = "";
				},
				function(error) {
				}
			);
		}

		function getAbsences() {
			AbsenceService.getAbsence($rootScope.userData.workerId, vm.selectedDateFilter).then(
				function(result) {
					vm.absences = [];
					vm.absences = result;
				},
				function(error) {
				}
			);
		}

		function getAbsenceDatas() {
			AbsenceService.getAbsenceData($rootScope.userData.workerId).then(
				function(result) {
					vm.absenceDatas = result;
				},
				function(error) {
				}
			);
		}

		function deleteAbsence(ev, absence) {
			var confirm = $mdDialog.confirm().title('Absence Delete')
											 .clickOutsideToClose(true)
											 .htmlContent('<div><p>Are you sure about delete the below Absence?<br>Begin Date: '+absence.beginDate+'<br>End Date: '+absence.endDate+'<br>Absence Type: '+absence.absenceType+'</p></div>')
											 .targetEvent(ev)
											 .ok('Yes')
											 .cancel('No');
			$mdDialog.show(confirm).then(function() { // Yes
				AbsenceService.deleteAbsence(absence.id).then(
					function(result) {
						StatusLogService.showStatusLog(result, 'Absence Remove');
						for(var i = 0; i < vm.absences.length; i++) {
							if(vm.absences[i].id === absence.id) {
								vm.absences.splice(i, 1);
								break;
							}
						}
					},
					function(error) {
					}
				)
			}, function() { // No
			});
		}

		function editAbsence(ev, absence) {
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
				for(var i = 0; i < vm.absences.length; i++) {
					if(vm.absences[i].id === absence.id) {
						vm.absences[i].beginDate = answer.beginDate;
						vm.absences[i].endDate = answer.endDate;
						vm.absences[i].absenceType = answer.absenceType;
						break;
					}
				}
				StatusLogService.showStatusLog(answer.status, 'Absence Edit');
			}, function() {
			});
		}

		function exportAbsence(excelType) {

			var excelTypeStr = ((excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
			var excelFileName = createExcelFileName(excelType);

			AbsenceService.exportAbsence($rootScope.userData.workerId, vm.selectedDateFilter, excelType).then(
				function(result) {
					var blob = new Blob([result], {type: excelTypeStr});
					saveAs(blob, excelFileName);
				},
				function(error) {
				}
			);
		}
	}
})();
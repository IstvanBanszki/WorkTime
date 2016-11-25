(function() {
	'use strict';

	const templateLoc = ['modules',
						 'absence',
						 'absence-table',
						 'absence-table.html'].join('/');

	angular
		.module('Absence')
		.component('absenceTable', Component());

	function Component() {
		return {
			templateUrl: templateLoc,
			controller: Controller,
			controllerAs: 'vm'
		}
	}

	Controller.$inject = ['$rootScope', '$mdDialog', 'AbsenceService', 'StatusLogService', 'ResponseWatcherService'];

	function Controller($rootScope, $mdDialog, AbsenceService, StatusLogService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.absences = [];
		vm.sortType = "BeginDate";
		vm.sortReverse = false;
		vm.searchQuery = "";
		vm.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		vm.selectedDateFilter = "All";
		//Bindable functions
		vm.showDownCaret = showDownCaret;
		vm.showUpCaret = showUpCaret;
		vm.setSearchTypeOrReverse = setSearchTypeOrReverse;
		vm.getAbsences = getAbsences;
		vm.deleteAbsence = deleteAbsence;
		vm.editAbsence = editAbsence;
		vm.exportAbsence = exportAbsence;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			if (typeof vm.absences || vm.absences.length === 0) {
				vm.getAbsences();
			}
		}

		function showDownCaret(tableHeader) {
			return (vm.sortType == tableHeader && !vm.sortReverse);
		}

		function showUpCaret(tableHeader) {
			return (vm.sortType == tableHeader && vm.sortReverse);
		}

		function setSearchTypeOrReverse(tableHeader) {
			if (vm.sortType == tableHeader) {
				vm.sortReverse = !vm.sortReverse;
			} else {
				vm.sortType = tableHeader;
			}
		}

		function createExcelFileName(excelType) {
			var employeeName = $rootScope.profileData.firstName+$rootScope.profileData.lastName;
			return employeeName+'-'+moment(new Date()).format('YYYYMMDDHHhhmmss')+'-ExportAbsence.xls'+((excelType === 1) ? '' : 'x');
		}

		function getAbsences() {
			AbsenceService.getAbsence($rootScope.userData.workerId, vm.selectedDateFilter).then(
				function(result) {
					vm.absences = [];
					vm.absences = result;
				},
				function(error) {
					StatusLogService.showStatusLog(-1, 'Filter Absence');
					ResponseWatcherService.checkHttpStatus(error.status);
				}
			);
		}

		function deleteAbsence(ev, absence) {
			var confirm = $mdDialog.confirm().title('Delete Absence')
											 .clickOutsideToClose(true)
											 .htmlContent('<div><p>Are you sure about delete the below Absence?<br>Begin Date: '+absence.beginDate+'<br>End Date: '+absence.endDate+'<br>Absence Type: '+absence.absenceType+'</p></div>')
											 .targetEvent(ev)
											 .ok('Yes')
											 .cancel('No');
			$mdDialog.show(confirm).then(function() { // Yes
				AbsenceService.deleteAbsence(absence.id).then(
					function(result) {
						StatusLogService.showStatusLog(result, 'Delete Absence');
						for (var i = 0; i < vm.absences.length; i++) {
							if (vm.absences[i].id === absence.id) {
								vm.absences.splice(i, 1);
								break;
							}
						}
					},
					function(error) {
						StatusLogService.showStatusLog(-1, 'Delete Absence');
						ResponseWatcherService.checkHttpStatus(error.status);
					}
				)
			}, function() { // No
			});
		}

		function editAbsence(ev, absence) {
			$rootScope.selectedAbsence = absence;
			$mdDialog.show({
				templateUrl: 'modules/absence/edit-form/edit-form.html',
				clickOutsideToClose: true,
				bindToController: true,
				controller: 'AbsenceEditController',
				parent: angular.element(document.body),
				targetEvent: ev
			}).then(function(answer) {
				for (var i = 0; i < vm.absences.length; i++) {
					if (vm.absences[i].id === absence.id) {
						vm.absences[i].beginDate = answer.beginDate;
						vm.absences[i].endDate = answer.endDate;
						vm.absences[i].absenceType = answer.absenceType;
						break;
					}
				}
				StatusLogService.showStatusLog(answer.status, 'Edit Absence');
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
					StatusLogService.showStatusLog(1, 'Export Absence');
				},
				function(error) {
					StatusLogService.showStatusLog(-1, 'Export Absence');
					ResponseWatcherService.checkHttpStatus(error.status);
				}
			);
		}
	}
})();
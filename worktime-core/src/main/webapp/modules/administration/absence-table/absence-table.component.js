(function() {
	'use strict';

	const templateLoc = ['modules',
						 'administration',
						 'absence-table',
						 'absence-table.html'].join('/');

	angular
		.module('Administration')
		.component('administrationAbsenceTable', Component());

	function Component() {
		return {
			templateUrl : templateLoc,
			controller  : Controller,
			controllerAs: 'vm',
			bindings    : {
				employees : '='
			}
		}
	}

	Controller.$inject = ['$rootScope', '$mdDialog', 'AdministrationService', 'StatusLogService', 'ResponseWatcherService'];

    function Controller($rootScope, $mdDialog, AdministrationService, StatusLogService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.sortType = "BeginDate";
		vm.sortReverse = false;
		vm.searchQuery = "";
		vm.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		vm.selectedDateFilter = "All";
		vm.employees = [];
		vm.selectedEmployeeAbsence = "";
		vm.emptyEmployeeList = false;
		vm.employeeAbsences = [];
		vm.emptyAbsenceList = false;
		vm.listNotAccepted 	 = false;
		//Bindable functions
		vm.showDownCaret = showDownCaret;
		vm.showUpCaret = showUpCaret;
		vm.setSearchTypeOrReverse = setSearchTypeOrReverse;
		vm.filterAbsence = filterAbsence;
		vm.acceptEmployeeAbsence = acceptEmployeeAbsence;
		vm.updateAbsenceNote = updateAbsenceNote;
		vm.exportAdminAbsence = exportAdminAbsence;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
		}

		function showDownCaret(tableHeader) {
			return (vm.sortType == tableHeader && !vm.sortReverse);
		}

		function showUpCaret(tableHeader) {
			return (vm.sortType == tableHeader && vm.sortReverse);
		}

		function setSearchTypeOrReverse(tableHeader) {
			if(vm.sortType == tableHeader){
				vm.sortReverse = !vm.sortReverse;
			} else {
				vm.sortType = tableHeader;
			}
		}

		function createExcelFileName(excelType) {
			var employeeName = '';
			vm.employees.forEach(function(employee) {
				if(employee.id === vm.selectedEmployeeWorklog) {
					employeeName = employee.firstName+employee.lastName;
				}
			});
			return employeeName+'-'+moment(new Date()).format('YYYYMMDDHHhhmmss')+'-ExportWorklog.xls'+((excelType === 1) ? '' : 'x');
		}

		function filterAbsence() {
			if(vm.selectedEmployeeAbsence !== "") {
				AdministrationService.getAbsencesByEmployee(vm.selectedEmployeeAbsence, vm.selectedDateFilter, vm.listNotAccepted).then(
						function(result) {
							vm.employeeAbsences = [];
							vm.employeeAbsences = result;
							if(vm.employeeAbsences.length > 0) {
								vm.emptyAbsenceList = false;
							} else {
								vm.emptyAbsenceList = true;
							}
						},
						function(error) {
							StatusLogService.showStatusLog(-1, 'Filter Absence');
							ResponseWatcherService.checkHttpStatus(error.status);
						}
					);
			}
		}

		function acceptEmployeeAbsence(ev, absence) {

			var confirm = $mdDialog.confirm().title('Approve Selected Absence')
											 .clickOutsideToClose(true)
										     .htmlContent('<div><p>Are you sure about approve the below Absence?<br>Begin Date: '+absence.beginDate+'<br>End Date: '+absence.endDate+'<br>Absence Type: '+absence.absenceType+'</p></div>')
										     .targetEvent(ev)
										     .ok('Yes')
										     .cancel('No');
			$mdDialog.show(confirm).then(function() { // Yes
				AdministrationService.acceptEmployeeAbsence(absence.id).then(
					function(result) {
						StatusLogService.showStatusLog(result.status, 'Accept Empolyee Absence');
						for(var i = 0; i < vm.employeeAbsences.length; i++) {
							if(vm.employeeAbsences[i].id === absence.id) {
								vm.employeeAbsences[i].status = 'APPROVE';
								break;
							}
						}
					},
					function(error) {
						StatusLogService.showStatusLog(-1, 'Accept Empolyee Absence');
						ResponseWatcherService.checkHttpStatus(error.status);
					}
				);
			}, function() { // No
			});	
		}

		function exportAdminAbsence(excelType) {
			if(vm.selectedEmployeeAbsence !== "") {

				var excelTypeStr = ((excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
				var excelFileName = createExcelFileName(excelType);

				AdministrationService.exportEmployeeAbsences(vm.selectedEmployeeAbsence, excelType, vm.selectedDateFilter, vm.listNotAccepted).then(
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

		function updateAbsenceNote(ev, absence) {
			$rootScope.selectedAbsence = absence;
			$mdDialog.show({
				templateUrl: 'modules/administration/update-note/update-note.html',
				clickOutsideToClose: true,
				bindToController: true,
				controller: 'UpdateNoteController',
				parent: angular.element(document.body),
				targetEvent: ev
			}).then(function(answer) {
				for(var i = 0; i < vm.employeeAbsences.length; i++) {
					if (vm.employeeAbsences[i].id === absence.id) {
						vm.employeeAbsences[i].note = answer.note;
						break;
					}
				}
				StatusLogService.showStatusLog(answer.status, 'Update Absence Note');
			}, function() {
			});
		}
    }
})();
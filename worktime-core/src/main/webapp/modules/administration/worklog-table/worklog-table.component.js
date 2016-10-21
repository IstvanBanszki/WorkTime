(function() {
	'use strict';

	const templateLoc = ['modules',
						 'administration',
						 'worklog-table',
						 'worklog-table.html'].join('/');

	angular
		.module('Administration')
		.component('administrationWorklogTable', Component());

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

	Controller.$inject = ['$rootScope', '$mdDialog', 'AdministrationService', 'StatusLogService'];

    function Controller($rootScope, $mdDialog, AdministrationService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.sortType = "BeginDate";
		vm.sortReverse = false;
		vm.searchQuery = "";
		vm.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		vm.selectedDateFilter = "All";
		vm.employees = [];
		vm.selectedEmployeeWorklog = "";
		vm.selectedEmployeeEdit = '';
		vm.emptyEmployeeList = false;
		vm.employeeWorklogs = [];
		vm.emptyWorklogList = false;
		vm.listDailyWorkHour = false;
		//Bindable functions
		vm.showDownCaret = showDownCaret;
		vm.showUpCaret = showUpCaret;
		vm.setSearchTypeOrReverse = setSearchTypeOrReverse;
		vm.filterWorklog = filterWorklog;
		vm.updateWorklogNote = updateWorklogNote;
		vm.exportAdminWorklog = exportAdminWorklog;

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

		function filterWorklog() {
			if(vm.selectedEmployeeWorklog !== "") {
				AdministrationService.getWorklogsByEmployee(vm.selectedEmployeeWorklog, vm.selectedDateFilter, vm.listDailyWorkHour).then(
						function(result) {
							vm.employeeWorklogs = [];
							vm.employeeWorklogs = result;
							if(vm.employeeWorklogs.length > 0) {
								vm.emptyWorklogList = false;
							} else {
								vm.emptyWorklogList = true;
							}
						},
						function(error) {
							StatusLogService.showStatusLog(-1, 'Filter Woklog');
						}
					);
			}
		}

		function exportAdminWorklog(excelType) {
			if(vm.selectedEmployeeWorklog !== "") {

				var excelTypeStr = ((excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
				var excelFileName = createExcelFileName(excelType);

				AdministrationService.exportEmployeeWorklogs(vm.selectedEmployeeWorklog, excelType, vm.selectedDateFilter, vm.listDailyWorkHour).then(
					function(result) {
						var blob = new Blob([result], {type: excelTypeStr});
						saveAs(blob, excelFileName);
						StatusLogService.showStatusLog(1, 'Export Woklog');
					},
					function(error) {
						StatusLogService.showStatusLog(-1, 'Export Woklog');
					}
				);
			}
		}
		
		function updateWorklogNote(ev, worklog) {
			$rootScope.selectedWorklog = worklog;
			$mdDialog.show({
				templateUrl: 'modules/administration/update-note/update-note.html',
				clickOutsideToClose: true,
				bindToController: true,
				controller: 'UpdateNoteController',
				parent: angular.element(document.body),
				targetEvent: ev
			}).then(function(answer) {
				for(var i = 0; i < vm.employeeWorklogs.length; i++) {
					if (vm.employeeWorklogs[i].id === worklog.id) {
						vm.employeeWorklogs[i].note = answer.note;
						break;
					}
				}
				StatusLogService.showStatusLog(answer.status, 'Update Woklog Note');
			}, function() {
			});
		}

    }
})();
(function() {
	'use strict';

	angular
		.module('Administration')
		.controller('AdministrationController', AdministrationController);

	AdministrationController.$inject = ['$rootScope', '$mdDialog', 'AdministrationService'];

    function AdministrationController($rootScope, $mdDialog, AdministrationService) {

		var vm = this;
		//Bindable variables
		vm.sortType = "BeginDate";
		vm.sortReverse = false;
		vm.searchQuery = "";
		vm.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		vm.selectedDateFilterAbsence = "All";
		vm.selectedDateFilterWorklog = "All";
		vm.employees = [];
		vm.selectedEmployeeAbsence = "";
		vm.selectedEmployeeWorklog = "";
		vm.selectedEmployeeEdit = '';
		vm.emptyEmployeeList = false;
		vm.employeeWorklogs = [];
		vm.emptyWorklogList = false;
		vm.employeeAbsences = [];
		vm.emptyAbsenceList = false;
		vm.listDailyWorkHour = false;
		vm.listNotAccepted 	 = false;
		vm.newFirstName = "";
		vm.newLastName = "";
		vm.newPosition = "";
		vm.newEmailAddress = "";
		vm.newDailyWorkHourTotal = "";
		//Bindable functions
		vm.init = init;
		vm.showDownCaret = showDownCaret;
		vm.showUpCaret = showUpCaret;
		vm.setSearchTypeOrReverse = setSearchTypeOrReverse;
		vm.filterWorklog = filterWorklog;
		vm.filterAbsence = filterAbsence;
		vm.acceptEmployeeAbsence = acceptEmployeeAbsence;
		vm.selectEmployeeForEdit = selectEmployeeForEdit;
		vm.changeEmployeeData = changeEmployeeData;
		vm.exportAdminAbsence = exportAdminAbsence;
		vm.exportAdminWorklog = exportAdminWorklog;

		// *********************** //
		// Function implementation //
		// *********************** //
		function init() {
			AdministrationService.getEmployees($rootScope.userData.workerId).then(
					function(result) {
						if(result.length > 0) {
							result.forEach(function(employee) {
								vm.employees.push({
									id: employee.id,
									lastName: employee.lastName,
									firstName: employee.firstName,
									name: employee.firstName + ' ' + employee.lastName
								});
							});
							$rootScope.emptyEmployeeList = vm.emptyEmployeeList = false;
						} else {
							$rootScope.emptyEmployeeList = vm.emptyEmployeeList = true;
						}
					},
					function(error) {
					}
				);
		};

		function showDownCaret(tableHeader) {
			return (vm.sortType == tableHeader && !vm.sortReverse);
		};

		function showUpCaret(tableHeader) {
			return (vm.sortType == tableHeader && vm.sortReverse);
		};

		function setSearchTypeOrReverse(tableHeader) {
			if(vm.sortType == tableHeader){
				vm.sortReverse = !vm.sortReverse;
			} else {
				vm.sortType = tableHeader;
			}
		};

		function createExcelFileName(excelType) {
			var employeeName = '';
			vm.employees.forEach(function(employee) {
				if(employee.id === vm.selectedEmployeeWorklog) {
					employeeName = employee.firstName+employee.lastName;
				}
			});
			return employeeName+'-'+moment(new Date()).format('YYYYMMDDHHhhmmss')+'-ExportWorklog.xls'+((excelType === 1) ? '' : 'x');
		};

		function filterWorklog() {
			if(vm.selectedEmployeeWorklog !== "") {
				AdministrationService.getWorklogsByEmployee(vm.selectedEmployeeWorklog, vm.selectedDateFilterWorklog, vm.listDailyWorkHour).then(
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
						}
					);
			}
		};
		function filterAbsence() {
			if(vm.selectedEmployeeAbsence !== "") {
				AdministrationService.getAbsencesByEmployee(vm.selectedEmployeeAbsence, vm.selectedDateFilterAbsence, vm.listNotAccepted).then(
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
						}
					);
			}
		};
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
					},
					function(error) {
					}
				);
				for(var i = 0; i < vm.employeeAbsences.length; i++) {
					if(vm.employeeAbsences[i].id === absence.id) {
						vm.employeeAbsences[i].status = 'APPROVE';
						break;
					}
				}
			}, function() { // No
			});	
		};
		function selectEmployeeForEdit() {
			if(vm.selectedEmployeeEdit !== "") {
				AdministrationService.getEmployeeWorkerData(vm.selectedEmployeeEdit).then(
					function(result) {
						vm.newFirstName = result.firstName;
						vm.newLastName = result.lastName;
						vm.newPosition = result.position;
						vm.newEmailAddress = result.emailAddress;
						vm.newDailyWorkHourTotal = result.dailyWorkHourTotal;
					},
					function(error) {
					}
				);
			}
		};
		function changeEmployeeData() {
			AdministrationService.editEmployeeWorkerData(vm.newFirstName, vm.newLastName, vm.newPosition, vm.newEmailAddress, vm.newDailyWorkHourTotal).then(
				function(result) {
				},
				function(error) {
				}
			);
		};
		function exportAdminWorklog(excelType) {
			if(vm.selectedEmployeeWorklog !== "") {

				var excelTypeStr = ((excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
				var excelFileName = createExcelFileName(excelType);

				AdministrationService.exportEmployeeWorklogs(vm.selectedEmployeeWorklog, excelType, vm.selectedDateFilterWorklog, vm.listDailyWorkHour).then(
					function(result) {
						var blob = new Blob([result], {type: excelTypeStr});
						saveAs(blob, excelFileName);
					},
					function(error) {
					}
				);
			}
		};
		function exportAdminAbsence(excelType) {
			if(vm.selectedEmployeeAbsence !== "") {

				var excelTypeStr = ((excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
				var excelFileName = createExcelFileName(excelType);

				AdministrationService.exportEmployeeAbsences(vm.selectedEmployeeAbsence, excelType, vm.selectedDateFilterAbsence, vm.listNotAccepted).then(
					function(result) {
						var blob = new Blob([result], {type: excelTypeStr});
						saveAs(blob, excelFileName);
					},
					function(error) {
					}
				);
			}
		};
    }
})();
(function() {
	'use strict';

	const templateLoc = ['modules',
						 'administration',
						 'employee-form',
						 'employee-form.html'].join('/');

	angular
		.module('Administration')
		.component('administrationEmployeeForm', Component());

	function Component() {
		return {
			templateUrl : templateLoc,
			controller  : Controller,
			controllerAs: 'vm'
		}
	}

	Controller.$inject = ['$rootScope', 'AdministrationService', 'StatusLogService'];

    function Controller($rootScope, AdministrationService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.employees = [];
		vm.selectedEmployeeEdit = '';
		vm.emptyEmployeeList = false;
		vm.newFirstName = "";
		vm.newLastName = "";
		vm.newPosition = "";
		vm.newEmailAddress = "";
		vm.newDailyWorkHourTotal = "";
		//Bindable functions
		vm.selectEmployeeForEdit = selectEmployeeForEdit;
		vm.changeEmployeeData = changeEmployeeData;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
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
		}

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
		}

		function changeEmployeeData() {
			AdministrationService.editEmployeeWorkerData(vm.newFirstName, vm.newLastName, vm.newPosition, vm.newEmailAddress, vm.newDailyWorkHourTotal).then(
				function(result) {
					StatusLogService.showStatusLog(result, 'Change Empolyee Data');
				},
				function(error) {
				}
			);
		}
    }
})();
(function() {
	'use strict';

	const templateLoc = ['modules',
						 'administration',
						 'employee-form',
						 'employee-form.html'].join('/');

	angular
		.module('Administration')
		.component('employeeForm', Component());

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

	Controller.$inject = ['$rootScope', 'AdministrationService', 'StatusLogService', 'ResponseWatcherService'];

    function Controller($rootScope, AdministrationService, StatusLogService, ResponseWatcherService) {

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
						ResponseWatcherService.checkHttpStatus(error.status);
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
					StatusLogService.showStatusLog(-1, 'Change Empolyee Data');
					ResponseWatcherService.checkHttpStatus(error.status);
				}
			);
		}
    }
})();
(function() {
	'use strict';

	const templateLoc = ['modules',
						 'addition',
						 'department-save-form',
						 'department-save-form.html'].join('/');

	angular
		.module('Addition')
		.component('departmentSaveForm', Component());

	function Component() {
		return {
			templateUrl : templateLoc,
			controller  : Controller,
			controllerAs: 'vm',
			bindings    : {
				offices	   : '=',
				departments: '='
			}
		}
	}

	Controller.$inject = ['$rootScope', '$attrs', 'AdditionService', 'StatusLogService'];

    function Controller($rootScope, $attrs, AdditionService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.departmentNameForCreation = "";
		vm.departmentDateOfFoundtationForCreation = "";
		vm.selectedOfficeIdForCreation = -1;
		//Bindable functions
		vm.createNewDepartment = createNewDepartment;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
		}

		function createNewDepartment() {

			var newDate = moment(vm.departmentDateOfFoundtationForCreation).format('YYYY.MM.DD');
			AdditionService.saveDepartment(vm.departmentNameForCreation, newDate, vm.selectedOfficeIdForCreation).then(
				function(result) {
					StatusLogService.showStatusLog(result.status, 'Create New Department!');
					if (result.status === 1) {
						vm.departments.push({
							id: result.newId,
							name: vm.departmentNameForCreation,
							dateOfFoundation: newDate,
							officeId: vm.selectedOfficeIdForCreation
						});
					}
				},
				function(error) {
				}
			);
		}
    }
})();
(function() {
	'use strict';

	const templateLoc = ['modules',
						 'addition',
						 'office-save-form',
						 'office-save-form.html'].join('/');

	angular
		.module('Addition')
		.component('officeSaveForm', Component());

	function Component() {
		return {
			templateUrl : templateLoc,
			controller  : Controller,
			controllerAs: 'vm',
			bindings    : {
				offices: '='
			}
		}
	}

	Controller.$inject = ['$rootScope', '$attrs', 'AdditionService', 'StatusLogService'];

    function Controller($rootScope, $attrs, AdditionService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.officesCopy;
		vm.officeNameForCreation = "";
		vm.officeAddressForCreation = "";
		vm.officeDateOfFoundtationForCreation = "";
		//Bindable functions
		vm.createNewOffice = createNewOffice;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
		}

		function createNewOffice() {
			var newDate = moment(vm.officeDateOfFoundtationForCreation).format('YYYY.MM.DD');
			AdditionService.saveOffice(vm.officeNameForCreation, vm.officeAddressForCreation, newDate).then(
				function(result) {
					StatusLogService.showStatusLog(result.status, 'Create New Office!');
					if (result.status === 1) {
						vm.offices.push({
							id: result.newId,
							name: vm.officeNameForCreation,
							address: vm.officeAddressForCreation,
							dateOfFoundation: newDate
						});
					}
				},
				function(error) {
				}
			);
		}
    }
})();
(function() {
	'use strict';

	angular
		.module('Addition')
		.controller('OfficeEditFormController', Controller);

	Controller.$inject = ['$rootScope', '$mdDialog', 'AdditionService'];

    function Controller($rootScope, $mdDialog, AdditionService) {

		var vm = this;
		//Bindable variables
		vm.officeId = -1;
		vm.officeName = "";
		vm.officeAddress = "";
		vm.officeDateOfFoundtation = "";
		vm.error = false;
		//Bindable functions
		vm.editOffice = editOffice;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			vm.officeId = $rootScope.selectedOffice.id;
			vm.officeName = $rootScope.selectedOffice.name;
			vm.officeAddress = $rootScope.selectedOffice.address;
			vm.officeDateOfFoundtation = moment($rootScope.selectedOffice.dateOfFoundtation, 'YYYY.MM.DD', false).toDate();
		}

		function editOffice() {
			var newDate = moment(vm.officeDateOfFoundtation).format('YYYY.MM.DD');
			AdditionService.editOffice(vm.officeId, vm.officeName, vm.officeAddress, newDate).then(
				function(result) {
					if (result === 1) {
						answer({
							name: vm.officeName,
							address: vm.officeAddress,
							dateOfFoundtation: vm.officeDateOfFoundtation,
							dateOfFoundtationStr: newDate,
							status: result
						});
					} else {
						vm.error = true;
					}
				},
				function(error) {
					cancel();
				}
			);
		}

		function answer(answer) {
			$rootScope.selectedOffice = {};
			$mdDialog.hide(answer);
		}

		function cancel() {
			$rootScope.selectedOffice = {};
			$mdDialog.cancel();
		}

    }
})();
(function() {
	'use strict';

	angular
		.module('Addition')
		.controller('DepartmentEditFormController', Controller);

	Controller.$inject = ['$rootScope', '$mdDialog', 'AdditionService', 'ResponseWatcherService'];

    function Controller($rootScope, $mdDialog, AdditionService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.departmentId = -1;
		vm.departmentName = "";
		vm.departmentDateOfFoundtation = "";
		vm.departmentOfficeId = -1;
		vm.error = false;
		//Bindable functions
		vm.editDepartment = editDepartment;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			vm.departmentId = $rootScope.selectedDepartment.id;
			vm.departmentName = $rootScope.selectedDepartment.name;
			vm.departmentDateOfFoundtation = moment($rootScope.selectedDepartment.dateOfFoundtation, 'YYYY.MM.DD', false).toDate();
			vm.departmentOfficeId = $rootScope.selectedDepartment.officeId;
		}

		function editDepartment() {
			
			var newDate = moment(vm.departmentDateOfFoundtation).format('YYYY.MM.DD');
			AdditionService.editDepartment(vm.departmentId, vm.departmentName, newDate, vm.departmentOfficeId).then(
				function(result) {
					if (result === 1) {
						answer({
							name: vm.officeName,
							dateOfFoundtation: vm.officeDateOfFoundtation,
							dateOfFoundtationStr: newDate,
							officeId: vm.departmentOfficeId,
							status: result
						});
					} else {
						vm.error = true;
					}
				},
				function(error) {
					ResponseWatcherService.checkHttpStatus(error.status);
				}
			);
		}

		function answer(answer) {
			$rootScope.selectedDepartment = {};
			$mdDialog.hide(answer);
		}

		function cancel() {
			$rootScope.selectedDepartment = {};
			$mdDialog.cancel();
		}

    }
})();
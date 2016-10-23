(function() {
	'use strict';

	const templateLoc = ['modules',
						 'profile',
						 'update-password',
						 'update-password.html'].join('/');

	angular
		.module('Profile')
		.component('updatePassword', Component());

	function Component() {
		return {
			templateUrl: templateLoc,
			controller: Controller,
			controllerAs: 'vm'
		}
	}

	Controller.$inject = ['$rootScope', 'ProfileService', 'StatusLogService', 'ResponseWatcherService'];

	function Controller($rootScope, ProfileService, StatusLogService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.oldPassword = "";
		vm.newPassword = "";
		vm.newPasswordSecond = "";
		vm.showDuplicateErrorMessage = false;
		vm.showErrorMessage = false;
		//Bindable functions
		vm.changePassword = changePassword;
		vm.clearPasswords = clearPasswords;

		// *********************** //
		// Function implementation //
		// *********************** //
		function changePassword() {
			if (vm.newPassword == vm.newPasswordSecond) {
				if (vm.newPassword != vm.oldPassword) {
					ProfileService.changePassword($rootScope.userData.loginName, vm.oldPassword, vm.newPassword).then(
						function(result) {
							StatusLogService.showStatusLog(result, 'Password change');
							if (result === 1) {
								vm.showDuplicateErrorMessage = false;
								vm.showErrorMessage = false;	
							} else {
								vm.showErrorMessage = true;	
							}
						},
						function(error) {
							vm.showErrorMessage = true;
							ResponseWatcherService.checkHttpStatus(error.status);
						}
					)
				}
				vm.clearPasswords();
			} else {
				vm.showDuplicateErrorMessage = true;
				vm.clearPasswords();
			}
		}

		function clearPasswords() {
			vm.oldPassword = "";
			vm.newPassword = "";
			vm.newPasswordSecond = "";
		}

	}

})();
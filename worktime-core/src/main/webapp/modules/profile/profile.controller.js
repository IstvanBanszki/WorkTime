(function() {
	'use strict';

	angular
		.module('Profile')
		.controller('ProfileController', ProfileController);

	ProfileController.$inject = ['$rootScope', '$mdDialog', 'ProfileService', 'StatusLogService']

	function ProfileController($rootScope, $mdDialog, ProfileService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.oldPassword = "";
		vm.newPassword = "";
		vm.newPasswordSecond = "";
		vm.loginName = "";
		vm.dateOfRegistration = "";
		vm.role = "";
		vm.firstName = "";
		vm.lastName = "";
		vm.gender = "";
		vm.dateOfBirth = "";
		vm.nationality = "";
		vm.position = "";
		vm.emailAddress = "";
		vm.dailyWorkHourTotal = "";
		vm.departmentName = "";
		vm.officeName = "";
		vm.showDuplicateErrorMessage = false;
		vm.showErrorMessage = false;
		//Bindable functions
		vm.changePassword = changePassword;
		vm.clearPasswords = clearPasswords;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			ProfileService.profile($rootScope.userData.workerId).then(
				function(result) {
					ProfileService.setProfileData(result);
					// Account Tab - initial value
					vm.loginName = $rootScope.userData.loginName;
					vm.dateOfRegistration = moment(result.dateOfRegistration).format('YYYY.MM.DD HH:mm:ss');
					vm.role = $rootScope.userData.roleName;
					// Personal Tab - initial value
					vm.firstName = result.firstName;
					vm.lastName = result.lastName;
					vm.gender = result.gender;
					vm.dateOfBirth = result.dateOfBirth;
					vm.nationality = result.nationality ;
					// Work Tab - initial value
					vm.position = result.position;
					vm.emailAddress = result.emailAddress;
					vm.dailyWorkHourTotal = result.dailyWorkHourTotal;
					vm.departmentName = result.departmentName;
					vm.officeName = result.officeName;
				},
				function(error) {
					vm.error = true;
					ProfileService.removeProfileData();
					vm.errorMessage = error.status;
				}
			)
		}

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
	};
})();
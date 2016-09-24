(function() {
	'use strict';

	angular
		.module('Profile')
		.controller('ProfileController', ProfileController);

	ProfileController.$inject = ['$rootScope', '$mdDialog', 'ProfileService']

	function ProfileController($rootScope, $mdDialog, ProfileService) {

		var vm = this;

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

		vm.changePassword = changePassword;
		vm.clearPasswords = clearPasswords;
		vm.init = init;
		vm.showStatus = showStatus;
		vm.clearPasswords = clearPasswords;

		// *********************** //
		// Function implementation //
		// *********************** //
		function changePassword () {
			if(vm.newPassword == vm.newPasswordSecond) {
				ProfileService.changePassword(vm.userData.loginName, vm.oldPassword, vm.newPassword).then(
					function(result) {
						vm.showStatus(result);
					},
					function(error) {
					}
				)
				vm.clearPasswords();
			} else {
				vm.showStatus(-2);
				vm.clearPasswords();
			}
		};
		function init () {
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
		};
		function showStatus (result) {
			var textContent = '';
			if (result === -2) {
				textContent = 'The newly entered passwords are not the same!';
			} else if (result === 2) {
				textContent = 'Wrong old password typed!';
			} else if (result === 1) {
				textContent = 'Change is succesfull!';
			} else {
				textContent = 'Unexpected Error! Result - '+result;
			}
			alert = $mdDialog.alert({
				title: 'Password Change',
				textContent: textContent,
				clickOutsideToClose: true,
				ok: 'Close'
			});
			$mdDialog.show(alert)
					 .finally(function() {
						alert = undefined;
					});
		};	
		function clearPasswords () {
			vm.oldPassword = "";
			vm.newPassword = "";
			vm.newPasswordSecond = "";
		};
	};
})();
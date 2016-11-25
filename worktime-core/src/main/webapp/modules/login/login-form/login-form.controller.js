(function() {
	'use strict';

	angular
		.module('Login')
		.controller('LoginController', Controller);

	Controller.$inject = ['$location', 'LoginService', 'ProfileService']

    function Controller($location, LoginService, ProfileService) {

		var vm = this;
		//Bindable variables
		vm.loginName = "";
		vm.password = "";
		vm.error = false;
		//Bindable functions
		vm.login = login;
		vm.logout = logout;
		vm.getProfileData = getProfileData;

		// *********************** //
		// Function implementation //
		// *********************** //
		function login() {
			LoginService.getLogin(vm.loginName, vm.password).then(
				function(loginResult) {
					if(loginResult !== "") {
						LoginService.getToken(vm.loginName).then(
							function(result) {
								if(result !== "") {
									LoginService.setUserData(loginResult, result.token, vm.loginName, vm.password);
									vm.getProfileData(loginResult.workerId);
									$location.path('/home');
								} else {
									vm.error = true;
									vm.errorMessage = error.status;
								}
							},
							function(error) {
								vm.error = true;
								vm.errorMessage = error.status;
							}
						);
					} else {
						vm.error = true;
					}
				},
				function(error) {
					vm.error = true;
					vm.errorMessage = error.status;
				}
			);
		}

		function logout() {
			LoginService.removeUserData();
			$location.path('/login');
		}

		function getProfileData(workerId) {
			ProfileService.getProfile(workerId).then(
				function(result) {
					ProfileService.setProfileData(result);
					},
				function(error) {
				}
			)
		}

    }

})();
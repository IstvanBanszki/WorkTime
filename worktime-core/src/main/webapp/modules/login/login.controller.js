(function() {
	'use strict';

	angular
		.module('Login')
		.controller('LoginController', LoginController);

	LoginController.$inject = ['$location', 'LoginService']
	
    function LoginController($location, LoginService) {

		var vm = this;
		//Bindable variables
		vm.loginName = "";
		vm.password = "";
		vm.error = false;
		//Bindable functions
		vm.login = login;
		vm.logout = logout;

		// *********************** //
		// Function implementation //
		// *********************** //
		function login() {
			LoginService.login(vm.loginName, vm.password)
				.then(
					function(result) {
						if(result !== "") {
							LoginService.setUserData( result, vm.loginName, vm.password );
							$location.path('/home');
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
    };
})();
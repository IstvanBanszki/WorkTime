(function() {
	'use strict';

	angular
		.module('Login')
		.controller('LoginController', LoginController);

	LoginController.$inject = ['$location', 'LoginService']
	
    function LoginController($location, LoginService) {

		var vm = this;

		vm.loginName = "";
		vm.password = "";
		vm.error = false;

		vm.login = login;
		vm.logout = logout;

		// *********************** //
		// Function implementation //
		// *********************** //
		function login () {
			LoginService.Login(vm.loginName, vm.password)
				.then(
					function(result) {
						if(result !== "") {
							LoginService.SetUserData( result, vm.loginName, vm.password );
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
		function logout () {
			LoginService.RemoveUserData();
			$location.path('/login');
		}
    };
})();
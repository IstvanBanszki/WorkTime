'use strict';

angular.module('Login')
.controller('LoginController', ['$scope', '$location', 'LoginService',
    function ($scope, $location, LoginService) {
		$scope.loginName = "";
		$scope.password = "";
		$scope.error = false;
		$scope.login = function(){
			LoginService.Login($scope.loginName, $scope.password)
				.then(
					function(result) {
						if(result !== "") {
							LoginService.SetUserData( result, $scope.loginName, $scope.password );
							$location.path('/home');
						} else {
							$scope.error = true;
						}
					},
					function(error) {
						
						$scope.error = true;
						$scope.errorMessage = error.status;
					}
				);
		}
		$scope.logout = function(){
			LoginService.RemoveUserData();
			$location.path('/login');
		}
    }]);
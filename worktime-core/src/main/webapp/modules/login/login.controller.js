'use strict';

angular.module('Login')
.controller('LoginController', ['$scope', '$http', '$location', 'LoginService',
    function ($scope, $http, $location, LoginService) {
		$scope.loginName = "";
		$scope.password = "";
		$scope.error = false;
		$scope.login = function(){
			LoginService.Login($scope.loginName, $scope.password)
				.then(
					function( result ){
						LoginService.SetUserData( result );
						$location.path('/home');
					},
					function( error ){
						
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
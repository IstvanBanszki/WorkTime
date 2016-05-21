'use strict';

angular.module('Login')
.controller('LoginController', ['$scope', '$http', '$location', 'LoginService',
    function ($scope, $http, $location, LoginService) {
		$scope.loginName = "";
		$scope.password = "";
		$scope.login = function(){
			var data = LoginService.Login($scope.loginName, $scope.password);
			if( data ){
				LoginService.SetUserData(data);
				$location.path('/home');
			}
		}
    }]);
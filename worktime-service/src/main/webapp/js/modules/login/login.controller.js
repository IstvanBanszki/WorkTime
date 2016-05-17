angular.module('Login')
.controller('LoginController', ['$scope', '$http', 'LoginService',
    function ($scope, $http, LoginService) {
		$scope.loginName = "";
		$scope.password = "";
		$scope.login = function(){
			LoginService.Login($scope.loginName, $scope.password);
		}
    }]);
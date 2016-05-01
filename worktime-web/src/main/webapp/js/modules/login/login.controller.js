angular.module('Login')
.controller('LoginController', ['$scope', '$http', 'LoginService',
    function ($scope, $http, LoginService) {
		$scope.userName = "";
		$scope.password = "";
		$scope.login = function(){
			LoginService.Login($scope.userName, $scope.password);
		}
    }]);
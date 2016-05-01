angular.module('myApp')
.controller('LoginController', ['$scope',
    function ($scope) {
		$scope.userName = "";
		$scope.password = "";
    }]);
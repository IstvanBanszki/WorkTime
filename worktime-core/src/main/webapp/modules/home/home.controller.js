'use strict';

angular.module('Home')
.controller('HomeController', ['$scope', '$rootScope', '$location',
    function ($scope, $rootScope, $location) {
		$rootScope.needToShow = ($location.path() !== '/login');
    }]);
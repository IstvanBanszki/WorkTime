
// declare modules
angular.module('myApp', ['ngRoute'])        
	.config(['$routeProvider', function ($routeProvider) {
		$routeProvider
			.when('/login', {
				controller: 'LoginController',
				templateUrl: 'js/modules/login/login.html'
			})
			.otherwise({ redirectTo: '/login' });
	}])
	.run(['$rootScope', function ($rootScope) {
		
	}])
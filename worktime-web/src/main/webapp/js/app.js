
// declare modules
angular.module('loginModule', []);
angular.module('myApp', ['ngRoute'])        
	.config(['$routeProvider', function ($routeProvider) {
		$routeProvider
			.when('/login', {
				controller: 'LoginController',
				templateUrl: 'modules/login/views/login.html'
			})
			.otherwise({ redirectTo: '/login' });
	}])
	.run(['$rootScope', function ($rootScope) {
		
	}])
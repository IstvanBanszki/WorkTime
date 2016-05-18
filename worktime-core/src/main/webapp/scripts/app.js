angular.module('Login', [])
angular.module('myApp', ['Login', 'ngRoute'])        
	.config(['$routeProvider', function ($routeProvider, $httpProvider) {
		$routeProvider
			.when('/login', {
				controller: 'LoginController',
				templateUrl: 'modules/login/login.html'
			})
			.when('/home', {
				controller: 'HomeController',
				templateUrl: 'modules/home/home.html'
			})
			.otherwise('/login');
	}])
	.run(['$rootScope', function ($rootScope, $location) {
	}])
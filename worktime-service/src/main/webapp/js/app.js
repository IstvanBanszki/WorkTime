angular.module('Login', [])
angular.module('myApp', ['Login', 'ngRoute'])        
	.config(['$routeProvider', function ($routeProvider, $httpProvider) {
		$routeProvider
			.when('/login', {
				controller: 'LoginController',
				templateUrl: 'js/modules/login/login.html'
			})
			.when('/home', {
				controller: 'HomeController',
				templateUrl: 'js/modules/home/home.html'
			})
			.otherwise('/login');
	}])
	.run(['$rootScope', function ($rootScope, $location) {
	}])
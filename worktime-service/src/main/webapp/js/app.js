angular.module('Login', [])
angular.module('myApp', ['Login', 'ngRoute'])        
	.config(['$routeProvider', function ($routeProvider, $httpProvider) {
		$routeProvider
			.when('/login', {
				controller: 'LoginController',
				templateUrl: 'js/modules/login/login.html'
			})
			.otherwise('/login');
	}])
	.run(['$rootScope', function ($rootScope) {
		
	}])
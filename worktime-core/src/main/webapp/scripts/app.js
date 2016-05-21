'use strict';

angular.module('Login', [])
angular.module('Home', [])
angular.module('myApp', ['Login', 'Home', 'ngRoute', 'ngCookies'])        
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
	.run(['$rootScope', '$cookies', '$location', '$http', function ($rootScope, $cookies, $location, $http) {
        $rootScope.userData = $cookies.getObject('data');
		if( $rootScope.userData ){
			$http.defaults.headers.common['Authorization'] = $rootScope.userData.secret;			
		}
		$rootScope.$on( "$locationChangeStart", function(event, next, current) {
			if( $location.path() !== '/login' && !$rootScope.userData ){
                $location.path('/login');
            }
        });
	}])
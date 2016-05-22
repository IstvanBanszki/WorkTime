'use strict';

angular.module('Login', [])
angular.module('Home', [])
angular.module('myApp', ['Login', 'Home', 'ngRoute', 'ngCookies'])        
	.config(['$routeProvider', function ($routeProvider, $httpProvider) {
		$routeProvider
			.when('/login', {
				controller : 'LoginController',
				templateUrl: 'modules/login/login.html',
				title	   : 'WorkTime - Login'
			})
			.when('/home', {
				controller : 'HomeController',
				templateUrl: 'modules/home/home.page.html',
				title	   : 'WorkTime - Home'
			})
			.otherwise('/login');
	}])
	.run(['$rootScope', '$cookies', '$location', '$http', function ($rootScope, $cookies, $location, $http) {
        $rootScope.userData = $cookies.getObject('data');
		if( $rootScope.userData ){
			$http.defaults.headers.common['Authorization'] = $rootScope.userData.secret;			
		}
		$rootScope.$on("$locationChangeStart", function(event, next, current) {
			if( $location.path() !== '/login' && !$rootScope.userData ){
                $location.path('/login');
            }
        });
		$rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
			if( current.hasOwnProperty('$$route') ){
				$rootScope.title = current.$$route.title;
			}
		});
	}])
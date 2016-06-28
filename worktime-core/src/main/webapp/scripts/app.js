'use strict';

angular.module('Login', [])
angular.module('Home', [])
angular.module('Profile', [])
angular.module('Worklog', [])
angular.module('Absence', [])
angular.module('Administration', [])
angular.module('myApp', ['Login', 'Home', 'Profile', 'Worklog', 'Absence', 'Administration', 'ngRoute', 'ngCookies', "ngAnimate", "ngAria", 'ngMaterial', 'ngMessages', 'material.svgAssetsCache'])
	.config(['$routeProvider', '$mdDateLocaleProvider', function ($routeProvider, $mdDateLocaleProvider) {
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
			.when('/profile', {
				controller : 'ProfileController',
				templateUrl: 'modules/profile/profile.page.html',
				title	   : 'WorkTime - Profile'
			})
			.when('/worklog', {
				controller : 'WorklogController',
				templateUrl: 'modules/worklog/worklog.page.html',
				title	   : 'WorkTime - Worklog'
			})
			.when('/absence', {
				controller : 'AbsenceController',
				templateUrl: 'modules/absence/absence.page.html',
				title	   : 'WorkTime - Absence'
			})
			.when('/administration', {
				controller : 'AdministrationController',
				templateUrl: 'modules/administration/administration.page.html',
				title	   : 'WorkTime - Administration'
			})
			.otherwise('/login');
		$mdDateLocaleProvider.formatDate = function(date) {
			return date ? moment(date).format('YYYY-MM-DD') : '';
		};
		$mdDateLocaleProvider.parseDate = function(dateString) {
			var m = moment(dateString, 'YYYY-MM-DD', true);
			return m.isValid() ? m.toDate() : new Date(NaN);
		};
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
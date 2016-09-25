'use strict';

angular.module('Login', [])
angular.module('Home', [])
angular.module('Profile', [])
angular.module('Worklog', [])
angular.module('Absence', [])
angular.module('Administration', [])
angular.module('Addition', [])
angular.module('myApp', ['Login', 'Home', 'Profile', 'Worklog', 'Absence', 'Administration', 'Addition', 'ngRoute', 'ngCookies', "ngAnimate", "ngAria", 'ngMaterial', 'ngMessages', 'ngSanitize', 'material.svgAssetsCache'])
	.config(['$routeProvider', '$mdDateLocaleProvider', function ($routeProvider, $mdDateLocaleProvider) {
		$routeProvider
			.when('/login', {
				controller : 'LoginController as login',
				templateUrl: 'modules/login/login.html',
				title	   : 'WorkTime - Login'
			})
			.when('/home', {
				controller : 'HomeController',
				templateUrl: 'modules/home/home.page.html',
				title	   : 'WorkTime - Home'
			})
			.when('/profile', {
				controller : 'ProfileController as profile',
				templateUrl: 'modules/profile/profile.page.html',
				title	   : 'WorkTime - Profile'
			})
			.when('/worklog', {
				controller : 'WorklogController as worklogC',
				templateUrl: 'modules/worklog/worklog.page.html',
				title	   : 'WorkTime - Worklog'
			})
			.when('/absence', {
				controller : 'AbsenceController as absenceC',
				templateUrl: 'modules/absence/absence.page.html',
				title	   : 'WorkTime - Absence'
			})
			.when('/administration', {
				controller : 'AdministrationController as administration',
				templateUrl: 'modules/administration/administration.page.html',
				title	   : 'WorkTime - Administration'
			})
			.when('/addition', {
				controller : 'AdditionController as addition',
				templateUrl: 'modules/addition/addition.page.html',
				title	   : 'WorkTime - Addition'
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
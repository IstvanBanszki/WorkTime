(function() {
	'use strict';

	angular.module('WorkTime', [
				'Login', 
				'Home', 
				'Profile', 
				'Worklog', 
				'Absence', 
				'Administration', 
				'Addition',
				'Utility',
				'ngCookies',
				'ui.router'])
		.config(routingConfig)
		.run(runner);

	routingConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider'];

	function routingConfig($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {

		$httpProvider.defaults.headers.common.Authorization = 'Basic ';

		$locationProvider.html5Mode({
			enabled: true,
			requireBase: false
		});

		$urlRouterProvider.otherwise("/login");
      
		$stateProvider
			.state('Login', {
				url			 : '/login',
				templateUrl  : 'modules/login/login-form/login-form.html',
				controller   : 'LoginController',
				controllerAs : 'vm',
				title	     : 'WorkTime - Login'
			})
			.state('Home', {
				url			 : '/home',
				templateUrl  : 'modules/home/home-page.html',
				title	     : 'WorkTime - Home'
			})
			.state('Profile', {
				url			 : '/profile',
				templateUrl  : 'modules/profile/profile-page.html',
				controller   : 'ProfileController',
				controllerAs : 'vm',
				title	     : 'WorkTime - Profile'
			})
			.state('Worklog', {
				url			 : '/worklog',
				templateUrl  : 'modules/worklog/worklog-page.html',
				controller   : 'WorklogController',
				controllerAs : 'vm',
				title	     : 'WorkTime - Worklog'
			})
			.state('Absence', {
				url			 : '/absence',
				templateUrl  : 'modules/absence/absence-page.html',
				controller   : 'AbsenceController',
				controllerAs : 'vm',
				title	     : 'WorkTime - Absence'
			})
			.state('Administration', {
				url			 : '/administration',
				templateUrl  : 'modules/administration/administration-page.html',
				controller   : 'AdministrationController',
				controllerAs : 'vm',
				title	     : 'WorkTime - Administration'
			})
			.state('Addition', {
				url			 : '/addition',
				templateUrl  : 'modules/addition/addition-page.html',
				controller   : 'AdditionController',
				controllerAs : 'vm',
				title	     : 'WorkTime - Addition'
			});
	}

	runner.$inject = ['$rootScope', '$cookies', '$location', '$http'];

	function runner($rootScope, $cookies, $location, $http) {
        $rootScope.userData = $cookies.getObject('data');
		if ($rootScope.userData) {
			$http.defaults.headers.common.Authorization = $rootScope.userData.secret;
		} else {
			$http.defaults.headers.common.Authorization = 'Basic ';
		}
		$rootScope.$on("$locationChangeStart", function(event, next, current) {
			if ($location.path() !== '/login' && !$rootScope.userData) {
                $location.path('/login');
            }
        });
		$rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
			if (current.hasOwnProperty('$$route')) {
				$rootScope.title = current.$$route.title;
			}
		});
	}
})();
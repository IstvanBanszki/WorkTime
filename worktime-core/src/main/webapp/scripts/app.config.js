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
				'ngRoute',
				'ngCookies'])
		.config(routingConfig)
		.run(runner);

	routingConfig.$inject = ['$routeProvider'];

	function routingConfig($routeProvider) {
		$routeProvider
			.when('/login', {
				controller : 'LoginController as login',
				templateUrl: 'modules/login/login.header.html',
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
	}

	runner.$inject = ['$rootScope', '$cookies', '$location', '$http'];

	function runner($rootScope, $cookies, $location, $http) {
        $rootScope.userData = $cookies.getObject('data');
		if ($rootScope.userData){
			$http.defaults.headers.common['Authorization'] = $rootScope.userData.secret;
		}
		$rootScope.$on("$locationChangeStart", function(event, next, current) {
			if ($location.path() !== '/login' && !$rootScope.userData){
                $location.path('/login');
            }
        });
		$rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
			if (current.hasOwnProperty('$$route')){
				$rootScope.title = current.$$route.title;
			}
		});
	}
})();
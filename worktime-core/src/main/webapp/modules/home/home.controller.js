'use strict';

angular.module('Home')
.controller('HomeController', ['$scope', '$rootScope',
    function ($scope, $rootScope) {
		$scope.links = [{
			title: 'Home', url: '#home'
		}, {
			title: 'Personal', url: '#profile'
		}, {
			title: 'Worlog', url: '#worklog'
		}, {
			title: 'Absence', url: '#absence'
		}];

		if( $rootScope.userData.roleName === 'COMPANY-ADMIN-ROLE' ){
			$scope.links.push({ title: 'Worklog Administration', url: '#worklog.administration' });
			$scope.links.push({ title: 'Absence Administration', url: '#absence.administration' });
		}
    }]);
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
			$scope.links.push({ title: 'Worklog Administration', url: '#worklogadministration' });
			$scope.links.push({ title: 'Absence Administration', url: '#absenceadministration' });
		}

		$scope.currentLink = '#home';
		$scope.isActiveLink = function(url) {
			return url == $scope.currentLink;
		}
		$scope.onClickLink = function(link) {
			$scope.currentLink = link.url;
		}
    }]);
'use strict';

angular.module('Home')
.controller('HomeController', ['$scope', '$rootScope',
    function ($scope, $rootScope) {
		$scope.links = [{
			title: 'Home', url: '#home'
		}, {
			title: 'Personal', url: '#profile'
		}, {
			title: 'Worklog', url: '#worklog'
		}, {
			title: 'Absence', url: '#absence'
		}];
		if( $rootScope.userData.roleName === 'COMPANY-ADMIN-ROLE' ){
			$scope.links.push({ title: 'Administration', url: '#administration' });
		}

		$scope.currentLink = '#home';
		$scope.isActiveLink = function(url) {
			return url == $scope.currentLink;
		}
		$scope.onClickLink = function(link) {
			$scope.currentLink = link.url;
		}
    }]);
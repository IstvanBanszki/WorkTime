'use strict';

angular.module('Profile')
.controller('ProfileController', ['$scope', 'ProfileService',
    function ($scope, ProfileService) {
		$scope.tabs = [{
            title: 'Account',
            url: 'modules/profile/profile.account.html'
        }, {
            title: 'Personal',
            url: 'modules/profile/profile.personal.html'
        }, {
            title: 'Work',
            url: 'modules/profile/profile.work.html'
		}];
		$scope.currentTab = 'profile.account.html';
		$scope.onClickTab = function (tab) {
			$scope.currentTab = tab.url;
		}
		$scope.isActiveTab = function(tabUrl) {
			return tabUrl == $scope.currentTab;
		}
		// Account Tab - initial value
		$scope.loginName = "";
		$scope.dateOfRegistration = ";
		$scope.role = "";
		// Personal Tab - initial value
		$scope.firstName = "";
		$scope.lastName = "";
		$scope.gender = "";
		$scope.dateOfBirth = "";
		$scope.nationality = "";
		// Work Tab - initial value
		$scope.position = "";
		$scope.emailAddress = "";
		$scope.dailyWorkHour = "";
		$scope.superior = "";
    }]);
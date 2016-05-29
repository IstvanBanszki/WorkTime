'use strict';

angular.module('Worklog')
.controller('WorklogController', ['$scope', '$rootScope', 'WorklogService',
    function ($scope, $rootScope, WorklogService) {
		$scope.tabs = [{
            title: 'Add',
            url: 'modules/worklog/worklog.add.html'
        }, {
            title: 'Show',
            url: 'modules/worklog/worklog.show.html'
        }];
		$scope.currentTab = 'modules/worklog/worklog.add.html';

		$scope.isActiveTab = function(tabUrl) {
			return tabUrl == $scope.currentTab;
		}
		$scope.onClickTab = function(tab) {
			$scope.currentTab = tab.url;
		}
		$scope.addWorklog = function(begin, end, description) {
			WorklogService.WorklogService
		}
    }]);
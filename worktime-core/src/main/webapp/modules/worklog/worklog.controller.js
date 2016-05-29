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
		$scope.description = "";
		$scope.begin = "";
		$scope.end = "";
		$scope.worklogs = [];

		$scope.addWorklog = function() {
			WorklogService.AddWorklog($scope.description, $scope.begin, $scope.end, $rootScope.userData.workerId).then(
				function( result ){
					
				},
				function( error ){
					
				}
			)
		}
		$scope.initWorklog = function(){
			if( typeof $scope.worklogs || $scope.worklogs.length === 0 ){
				WorklogService.GetWorklog($rootScope.userData.workerId).then(
					function( result ){
						$scope.worklogs = result;
					},
					function( error ){
						
					}
				)
			}
		}
    }]);
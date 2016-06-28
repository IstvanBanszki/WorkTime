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
		};
		$scope.onClickTab = function(tab) {
			$scope.currentTab = tab.url;
		};

		$scope.worklogs = [];
		$scope.sortType = "Begin";
		$scope.sortReverse = false;
		$scope.searchQuery = "";
		$scope.showDownCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && !$scope.sortReverse);
		};
		$scope.showUpCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && $scope.sortReverse);
		};
		$scope.setSearchTypeOrReverse = function(tableHeader) {
			if($scope.sortType == tableHeader){
				$scope.sortReverse = !$scope.sortReverse;
			} else {
				$scope.sortType = tableHeader;
			}
		};

		$scope.begin = "";
		$scope.workHour = 0;

		$scope.addWorklog = function() {
			WorklogService.AddWorklog($scope.begin, $scope.workHour, $rootScope.userData.workerId).then(
				function( result ){
					$scope.GetWorklogs();
				},
				function( error ){
					
				}
			)
		};
		$scope.initWorklog = function(){
			if( typeof $scope.worklogs || $scope.worklogs.length === 0 ){
				$scope.GetWorklogs();
			}
		};
		$scope.GetWorklogs = function(){
			WorklogService.GetWorklog($rootScope.userData.workerId).then(
				function( result ){
					$scope.worklogs = result;
					$scope.dateFormatter();
				},
				function( error ){
				}
			)
		}
		$scope.timeValue = function(value){
			return value < 10 ? '0'+value : value+'';
		};
		$scope.range = function(count){
			return new Array(count);
		};
		$scope.dateFormatter = function(){
			$scope.worklogs.forEach(function(worklog) {
				worklog.begin = moment(worklog.begin).format('YYYY.MM.DD');
			});
		};
    }]);
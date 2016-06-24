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

		$scope.beginDate = new Date();
		$scope.begin = {
			hour: 0,
			min: 0
		};
		$scope.end = {
			hour: 0,
			min: 0
		};
		$scope.addWorklog = function() {
			var alma = $scope.dateBuilder($scope.beginDate, $scope.begin.hour, $scope.begin.min);
			var korte = $scope.dateBuilder($scope.beginDate, $scope.end.hour, $scope.end.min);
			var almaFormatted = moment(alma).format('YYYY-MM-DD hh:mm:ss');
			var korteFormatted = moment(korte).format('YYYY-MM-DD hh:mm:ss');
			/*WorklogService.AddWorklog($scope.begin.date, $scope.end.date, $rootScope.userData.workerId).then(
				function( result ){
					
				},
				function( error ){
					
				}
			)*/
		};
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
		};
		$scope.timeValue = function(value){
			return value < 10 ? '0'+value : value+'';
		}
		$scope.dateBuilder = function(date, hour, min){
			var localDate = new Date(date);
			localDate.setHours(hour);
			localDate.setMinutes(min);
			return localDate;
		};
		$scope.range = function(count){
			return new Array(count);
		};
    }]);
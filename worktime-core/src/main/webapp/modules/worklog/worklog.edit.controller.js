'use strict';

angular.module('Worklog')
.controller('WorklogEditController', ['$scope', '$rootScope', '$mdDialog', 'worklogData', 'WorklogService',
    function ($scope, $rootScope, $mdDialog, worklogData, WorklogService) {
		$scope.newBegin = new Date(worklogData.begin.substring(0,4)+'-'+worklogData.begin.substring(5,7)+'-'+worklogData.begin.substring(8,10))
		$scope.newWorkHour = worklogData.workHour;
		$scope.id = worklogData.id;
		
		$scope.editWorklog = function() {
			
			WorklogService.EditWorklog($scope.id, $scope.newBegin, $scope.newWorkHour).then(
				function(result) {
					var editedWorklog = {
						begin: new Date(worklogData.begin.substring(0,4), worklogData.begin.substring(5,7), worklogData.begin.substring(8,10)),
						workHour: worklogData.workHour,
						id: worklogData.id
					};
					$scope.answer(editedWorklog);
				},
				function(error) {
					$scope.cancel();
				}
			)
		};

		$scope.answer = function(answer) {
		  $mdDialog.hide(answer);
		};
		$scope.cancel = function() {
		  $mdDialog.cancel();
		};
		$scope.range = function(count) {
			return new Array(count);
		};
    }]);
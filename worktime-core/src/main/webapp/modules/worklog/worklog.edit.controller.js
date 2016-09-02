'use strict';

angular.module('Worklog')
.controller('WorklogEditController', ['$scope', '$rootScope', '$mdDialog', 'WorklogService',
    function ($scope, $rootScope, $mdDialog, WorklogService, worklogData) {
		$scope.newBeginDate = new Date(worklogData.beginDate.substring(0,4)+'-'+worklogData.beginDate.substring(5,7)+'-'+worklogData.beginDate.substring(8,10))
		$scope.newWorkHour = worklogData.workHour;
		$scope.id = worklogData.id;

		$scope.editWorklog = function() {

			WorklogService.EditWorklog($scope.id, $scope.newBegin, $scope.newWorkHour).then(
				function(result) {
					$scope.answer({
						beginDate: $scope.newBeginDate,
						workHour: $scope.newWorkHour,
						id: $scope.id
					});
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
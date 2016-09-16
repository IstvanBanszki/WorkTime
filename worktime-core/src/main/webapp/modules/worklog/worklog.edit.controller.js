'use strict';

angular.module('Worklog')
.controller('WorklogEditController', ['$scope', '$rootScope', '$mdDialog', 'WorklogService',
    function ($scope, $rootScope, $mdDialog, WorklogService) {

		$scope.localBegin = $rootScope.selectedWorklog.beginDate;

		$scope.newBeginDate = new Date($scope.localBegin.substring(0,4)+'-'+$scope.localBegin.substring(5,7)+'-'+$scope.localBegin.substring(8,10))
		$scope.newWorkHour = $rootScope.selectedWorklog.workHour;
		$scope.id = $rootScope.selectedWorklog.id;

		$scope.error = false;

		$scope.editWorklog = function() {
			var newDate = moment($scope.newBeginDate).format('YYYY.MM.DD');
			WorklogService.EditWorklog($scope.id, newDate, $scope.newWorkHour).then(
				function(result) {
					if(result === 1) {
						$scope.answer({
							beginDate: newDate,
							workHour: $scope.newWorkHour
						});
					} else {
						$scope.error = true;
					}
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
'use strict';

angular.module('Absence')
.controller('AbsenceEditController', ['$scope', '$rootScope', '$mdDialog', 'AbsenceService',
    function ($scope, $rootScope, $mdDialog, AbsenceService) {

		$scope.localBegin = $rootScope.selectedAbsence.beginDate;
		$scope.localEnd = $rootScope.selectedAbsence.endDate;

		$scope.determineType = function(typeStr) {
			if(typeStr === 'UNPAYED') {
				return 2;
			}else if(typeStr === 'SICK_PAY') {
				return 3;
			}else if(typeStr === 'VERIFIED') {
				return 4;
			}else {
				return 1;
			}
		};

		$scope.newBeginDate =  new Date($scope.localBegin.substring(0,4)+'-'+$scope.localBegin.substring(5,7)+'-'+$scope.localBegin.substring(8,10));
		$scope.newEndDate = new Date($scope.localEnd.substring(0,4)+'-'+$scope.localEnd.substring(5,7)+'-'+$scope.localEnd.substring(8,10));
		$scope.newAbsenceType = $scope.determineType($rootScope.selectedAbsence.absenceType);
		$scope.id = $rootScope.selectedAbsence.id;

		$scope.editWorklog = function() {
			WorklogService.EditWorklog($scope.id, $scope.newBegin, $scope.newEndDate, $scope.newAbsenceType).then(
				function(result) {
					$scope.answer({
						beginDate: $scope.newBeginDate,
						endDate: $scope.newEndDate,
						absenceType: $scope.newAbsenceType,
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
    }]);
'use strict';

angular.module('Absence')
.controller('AbsenceEditController', ['$scope', '$rootScope', '$mdDialog', 'AbsenceService',
    function ($scope, $rootScope, $mdDialog, AbsenceService) {

		$scope.localBegin = $rootScope.selectedAbsence.beginDate;
		$scope.localEnd = $rootScope.selectedAbsence.endDate;

		$scope.newBeginDate =  new Date($scope.localBegin.substring(0,4)+'-'+$scope.localBegin.substring(5,7)+'-'+$scope.localBegin.substring(8,10));
		$scope.newEndDate = new Date($scope.localEnd.substring(0,4)+'-'+$scope.localEnd.substring(5,7)+'-'+$scope.localEnd.substring(8,10));
		$scope.newAbsenceType = $rootScope.selectedAbsence.absenceType;
		$scope.id = $rootScope.selectedAbsence.id;

		$scope.error = false;

		$scope.editAbsence = function() {
			var answerBeginDate = moment($scope.newBeginDate).format('YYYY.MM.DD');
			var answerEndDate = moment($scope.newEndDate).format('YYYY.MM.DD');
			AbsenceService.EditAbsence($scope.id, answerBeginDate, answerEndDate, $scope.newAbsenceType).then(
				function(result) {
					if (result === 1) {
						$scope.answer({
							beginDate: answerBeginDate,
							endDate: answerEndDate,
							absenceType: $scope.newAbsenceType
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
		$scope.answer = function(answer) {
		  $mdDialog.hide(answer);
		};
		$scope.cancel = function() {
		  $mdDialog.cancel();
		};
    }]);
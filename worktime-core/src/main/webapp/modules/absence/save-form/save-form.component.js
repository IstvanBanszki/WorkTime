(function() {
	'use strict';

	const templateLoc = ['modules',
						 'absence',
						 'save-form',
						 'save-form.html'].join('/');

	angular
		.module('Absence')
		.component('absenceForm', Component());

	function Component() {
		return {
			templateUrl: templateLoc,
			controller: Controller,
			controllerAs: 'vm'
		}
	}

	Controller.$inject = ['$rootScope', 'AbsenceService', 'StatusLogService'];

	function Controller($rootScope, AbsenceService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.absenceType = "PAYED";
		vm.beginDate = "";
		vm.endDate = "";
		//Bindable functions
		vm.addAbsence = addAbsence;

		// *********************** //
		// Function implementation //
		// *********************** //

		function addAbsence() {
			//for(var i = 0; i < $scope.worklogs.length; i++) {
				//if(moment($scope.worklogs[i].beginDate).isBetween($scope.beginDate, $scope.endDate) ||
				//	 moment($scope.worklogs[i].endDate).isBetween($scope.beginDate, $scope.endDate)) {
				//	$scope.showStatus(-1);
				//}
			//}
			var dateBegin = moment(vm.beginDate).format('YYYY.MM.DD');
			var dateEnd = moment(vm.endDate).format('YYYY.MM.DD');
			AbsenceService.addAbsence(dateBegin, dateEnd, $rootScope.userData.workerId, vm.absenceType).then(
				function(result) {
					StatusLogService.showStatusLog(result.status, 'Create New Absence');
					vm.absences.push({
						id: result.newId,
						beginDate: dateBegin,
						endDate: dateEnd,
						absenceType: vm.absenceType,
						status: 'NOT_APPROVE'
					});
					vm.absenceType = "PAYED";
					vm.beginDate = "";
					vm.endDate = "";
				},
				function(error) {
					StatusLogService.showStatusLog(-1, 'Create New Absence');
				}
			);
		}
	}
})();
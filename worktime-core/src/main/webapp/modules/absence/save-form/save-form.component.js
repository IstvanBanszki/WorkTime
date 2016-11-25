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

	Controller.$inject = ['$rootScope', 'AbsenceService', 'StatusLogService', 'ResponseWatcherService'];

	function Controller($rootScope, AbsenceService, StatusLogService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.absenceType = "PAYED";
		vm.beginDate = "";
		vm.endDate = "";
		vm.multyDayEntry = false;
		//Bindable functions
		vm.addAbsence = addAbsence;

		// *********************** //
		// Function implementation //
		// *********************** //

		function addAbsence() {
			var dateBegin = moment(vm.beginDate).format('YYYY.MM.DD');
			var dateEnd = vm.multyDayEntry ? moment(vm.endDate).format('YYYY.MM.DD') : moment(vm.beginDate).format('YYYY.MM.DD');
			AbsenceService.addAbsence(dateBegin, dateEnd, $rootScope.userData.workerId, vm.absenceType).then(
				function(result) {
					StatusLogService.showStatusLog(result.status, 'Create New Absence');
					vm.absenceType = "PAYED";
					vm.beginDate = "";
					vm.endDate = "";
				},
				function(error) {
					StatusLogService.showStatusLog(-1, 'Create New Absence');
					ResponseWatcherService.checkHttpStatus(error.status);
				}
			);
		}
	}
})();
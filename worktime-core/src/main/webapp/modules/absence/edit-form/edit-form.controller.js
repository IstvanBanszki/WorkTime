(function() {
	'use strict';

	angular
		.module('Absence')
		.controller('AbsenceEditController', Controller);

	Controller.$inject = ['$rootScope', '$mdDialog', 'AbsenceService', 'ResponseWatcherService'];

    function Controller($rootScope, $mdDialog, AbsenceService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.id = -1;
		vm.newBeginDate = "";
		vm.newEndDate = "";
		vm.newAbsenceType = "";
		vm.error = false;
		//Bindable functions
		vm.editAbsence = editAbsence;
		vm.range = range;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			var localBegin = $rootScope.selectedAbsence.beginDate;
			var localEnd = $rootScope.selectedAbsence.endDate;

			vm.newBeginDate = new Date(localBegin.substring(0,4)+'-'+localBegin.substring(5,7)+'-'+localBegin.substring(8,10));
			vm.newEndDate = new Date(localEnd.substring(0,4)+'-'+localEnd.substring(5,7)+'-'+localEnd.substring(8,10));
			vm.newAbsenceType = $rootScope.selectedAbsence.absenceType;
			vm.id = $rootScope.selectedAbsence.id;
		}
		
		function editAbsence() {
			var answerBeginDate = moment(vm.newBeginDate).format('YYYY.MM.DD');
			var answerEndDate = moment(vm.newEndDate).format('YYYY.MM.DD');
			AbsenceService.editAbsence(vm.id, answerBeginDate, answerEndDate, vm.newAbsenceType).then(
				function(result) {
					if (result === 1) {
						answer({
							beginDate: answerBeginDate,
							endDate: answerEndDate,
							absenceType: vm.newAbsenceType,
							status: result
						});
					} else {
						vm.error = true;
					}
				},
				function(error) {
					ResponseWatcherService.checkHttpStatus(error.status);
					cancel();
				}
			)
		}

		function range(count) {
			return new Array(count);
		}

		function answer(answer) {
			$rootScope.selectedAbsence = {};
			$mdDialog.hide(answer);
		}

		function cancel() {
			$rootScope.selectedAbsence = {};
			$mdDialog.cancel();
		}

    }
})();
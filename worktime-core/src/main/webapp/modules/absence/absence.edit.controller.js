(function() {
	'use strict';

	angular
		.module('Absence')
		.controller('AbsenceEditController', AbsenceEditController);

	AbsenceEditController.$inject = ['$rootScope', '$mdDialog', 'AbsenceService'];

    function AbsenceEditController($rootScope, $mdDialog, AbsenceService) {

		var vm = this;
		//Bindable variables
		vm.localBegin = $rootScope.selectedAbsence.beginDate;
		vm.localEnd = $rootScope.selectedAbsence.endDate;
		vm.newBeginDate =  new Date(vm.localBegin.substring(0,4)+'-'+vm.localBegin.substring(5,7)+'-'+vm.localBegin.substring(8,10));
		vm.newEndDate = new Date(vm.localEnd.substring(0,4)+'-'+vm.localEnd.substring(5,7)+'-'+vm.localEnd.substring(8,10));
		vm.newAbsenceType = $rootScope.selectedAbsence.absenceType;
		vm.id = $rootScope.selectedAbsence.id;
		vm.error = false;
		//Bindable functions
		vm.editAbsence = editAbsence;
		vm.range = range;

		// *********************** //
		// Function implementation //
		// *********************** //
		function editAbsence() {
			var answerBeginDate = moment(vm.newBeginDate).format('YYYY.MM.DD');
			var answerEndDate = moment(vm.newEndDate).format('YYYY.MM.DD');
			AbsenceService.editAbsence(vm.id, answerBeginDate, answerEndDate, vm.newAbsenceType).then(
				function(result) {
					if (result === 1) {
						answer({
							beginDate: answerBeginDate,
							endDate: answerEndDate,
							absenceType: vm.newAbsenceType
						});
					} else {
						vm.error = true;
					}
				},
				function(error) {
					cancel();
				}
			)
		};
		function answer(answer) {
		  $mdDialog.hide(answer);
		};
		function answer(answer) {
		  $mdDialog.hide(answer);
		};
		function cancel() {
		  $mdDialog.cancel();
		};
    };
})();
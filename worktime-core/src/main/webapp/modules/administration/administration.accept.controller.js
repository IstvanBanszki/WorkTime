(function() {
	'use strict';

	angular
		.module('Administration')
		.controller('AdministrationAcceptController', AdministrationAcceptController);

	AdministrationAcceptController.$inject = ['$rootScope', '$mdDialog', 'AbsenceService'];

    function AdministrationAcceptController($rootScope, $mdDialog, AbsenceService) {

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

		// *********************** //
		// Function implementation //
		// *********************** //
		function editAbsence() {
			AbsenceService.EditAbsence(vm.id, vm.newBeginDate, vm.newEndDate, vm.newAbsenceType).then(
				function(result) {
					if(result === 1) {
						answer({
							beginDate: vm.newBeginDate,
							endDate: vm.newEndDate,
							absenceType: vm.newAbsenceType
						});
					}else {
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
    }
})();
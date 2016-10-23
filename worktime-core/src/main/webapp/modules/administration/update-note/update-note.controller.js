(function() {
	'use strict';

	angular
		.module('Administration')
		.controller('UpdateNoteController', Controller);

	Controller.$inject = ['$rootScope', '$mdDialog', 'AdministrationService', 'ResponseWatcherService'];

    function Controller($rootScope, $mdDialog, AdministrationService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.wId = -1;
		vm.aId = -1;
		vm.note = "";
		vm.error = false;
		//Bindable functions
		vm.updateNote = updateNote;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			if ($rootScope.selectedWorklog !== undefined) {
				vm.wId = $rootScope.selectedWorklog.id;
			} else {
				vm.aId = $rootScope.selectedAbsence.id;
			}
		}
		
		function updateNote() {
			if (vm.wId !== -1) {
				AdministrationService.updateWorklogNote(vm.wId, vm.note).then(
					function(result) {
						if (result === 1) {
							answer({
								note: vm.note,
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
			} else {
				AdministrationService.updateAbsenceNote(vm.aId, vm.note).then(
					function(result) {
						if (result === 1) {
							answer({
								note: vm.note,
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
		}

		function answer(answer) {
			$rootScope.selectedWorklog = {};
			$rootScope.selectedAbsence = {};
			$mdDialog.hide(answer);
		}

		function cancel() {
			$rootScope.selectedWorklog = {};
			$rootScope.selectedAbsence = {};
			$mdDialog.cancel();
		}

    }
})();
(function() {
	'use strict';

	const templateLoc = ['modules',
						 'addition',
						 'worker-user-create-form',
						 'worker-user-create-form.html'].join('/');

	angular
		.module('Addition')
		.component('workerUserCreateForm', Component());

	function Component() {
		return {
			templateUrl : templateLoc,
			controller  : Controller,
			controllerAs: 'vm',
			bindings    : {
				departments: '='
			}
		}
	}

	Controller.$inject = ['$rootScope', 'AdditionService', 'StatusLogService', 'ResponseWatcherService']

    function Controller($rootScope, AdditionService, StatusLogService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.superiors = [];

		vm.roles = [
			{id: 2, name: 'WORKER_ROLE'}, 
			{id: 3, name: 'SUPERIOR_ROLE'}
		];
		vm.loginName = "";
		vm.selectedRoleForCreation = "";
		vm.firstName = "";
		vm.lastName = "";
		vm.selectedGender = "";
		vm.dateOfBirth = "";
		vm.nationality = "";
		vm.position = "";
		vm.dailyWorkHourTotal = "";
		vm.emailAddres = "";
		vm.selectedDepartmentIdForWorkerCreation = 0;
		vm.selectedSuperiorIdForWorkerCreation = "";
		//Bindable functions
		vm.createNewUserAndWorker = createNewUserAndWorker;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			AdditionService.getSuperiors().then(
				function(result) {
					vm.superiors = result;
				},
				function(error) {
					ResponseWatcherService.checkHttpStatus(error.status);
				}
			);
		}

		function createNewUserAndWorker() {
			var userId = 0;
			AdditionService.saveUser(vm.loginName, '', vm.selectedRoleForCreation, vm.emailAddres).then(
				function(result) {
					StatusLogService.showStatusLog(result.status, 'Create User');

					userId = result.newId;
					var dateOfBirthStr = moment(vm.dateOfBirth).format('YYYY.MM.DD');
					AdditionService.saveWorker(vm.firstName, vm.lastName, vm.selectedGender, dateOfBirthStr, vm.nationality, vm.position, vm.dailyWorkHourTotal, vm.emailAddres, vm.selectedSuperiorIdForWorkerCreation, vm.selectedDepartmentIdForWorkerCreation, userId).then(
						function(result) {
							StatusLogService.showStatusLog(result.status, 'Create Worker');
						},
						function(error) {
							StatusLogService.showStatusLog(-1, 'Create Worker');
							ResponseWatcherService.checkHttpStatus(error.status);
						}
					);
					clearFields();
				},
				function(error) {
					StatusLogService.showStatusLog(-1, 'Create User');
					ResponseWatcherService.checkHttpStatus(error.status);
					clearFields();
				}
			);
		}

		function clearFields() {
			vm.loginName = "";
			vm.selectedRoleForCreation = "";
			vm.firstName = "";
			vm.lastName = "";
			vm.selectedGender = "";
			vm.dateOfBirth = "";
			vm.nationality = "";
			vm.position = "";
			vm.dailyWorkHourTotal = "";
			vm.emailAddres = "";
			vm.selectedDepartmentIdForWorkerCreation = 0;
			vm.selectedSuperiorIdForWorkerCreation = "";
		}

    }
})();
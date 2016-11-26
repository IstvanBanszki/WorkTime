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
			{id: 1, name: 'WORKER-ROLE'}, 
			{id: 2, name: 'SUPERIOR-ROLE'}
		];
		vm.password = "";
		vm.passwordSecond = "";
		vm.passwordError = false;
		vm.loginName = "";
		vm.selectedRoleForCreation = "";
		vm.firstName = "";
		vm.lastName = "";
		vm.selectedGender = "";
		vm.dateOfBirth = "";
		vm.nationality = "";
		vm.position = "";
		vm.dailyWorkHourTotal = "";
		vm.emailAddress = "";
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
			if (vm.password === vm.passwordSecond) {
				AdditionService.saveUser(vm.loginName, vm.password, vm.selectedRoleForCreation).then(
					function(result) {
						StatusLogService.showStatusLog(result.status, 'Create User');

						userId = result.newId;
						var dateOfBirthStr = moment(vm.dateOfBirth).format('YYYY.MM.DD');
						AdditionService.saveWorker(vm.firstName, vm.lastName, vm.selectedGender, dateOfBirthStr, vm.nationality, vm.position, vm.dailyWorkHourTotal, vm.emailAddress, vm.selectedSuperiorIdForWorkerCreation, vm.selectedDepartmentIdForWorkerCreation, userId).then(
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
			} else {
				vm.password = "";
				vm.passwordSecond = "";
				vm.passwordError = true;
			}
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
			vm.emailAddress = "";
			vm.selectedDepartmentIdForWorkerCreation = 0;
			vm.selectedSuperiorIdForWorkerCreation = "";
			vm.password = "";
			vm.passwordSecond = "";
			vm.passwordError = false;
		}

    }
})();
(function() {
	'use strict';

	angular
		.module('Administration')
		.controller('AdministrationController', Controller);

	Controller.$inject = ['$rootScope', 'AdministrationService', 'ResponseWatcherService'];

    function Controller($rootScope, AdministrationService, ResponseWatcherService) {

		var vm = this;
		vm.employees = [];

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			AdministrationService.getEmployees($rootScope.userData.workerId).then(
					function(result) {
						if(result.length > 0) {
							result.forEach(function(employee) {
								vm.employees.push({
									id: employee.id,
									lastName: employee.lastName,
									firstName: employee.firstName,
									name: employee.firstName + ' ' + employee.lastName
								});
							});
							$rootScope.emptyEmployeeList = vm.emptyEmployeeList = false;
						} else {
							$rootScope.emptyEmployeeList = vm.emptyEmployeeList = true;
						}
					},
					function(error) {
						ResponseWatcherService.checkHttpStatus(error.status);
					}
				);
		}
    }
})();
(function() {
	'use strict';

	const templateLoc = ['modules',
						 'worklog',
						 'worklog-form',
						 'worklog-form.html'].join('/');

	angular
		.module('Worklog')
		.component('worklogForm', Component());

	function Component() {
		return {
			templateUrl: templateLoc,
			controller: Controller,
			controllerAs: 'vm'
		}
	}

	Controller.$inject = ['$rootScope', 'WorklogService', 'StatusLogService'];

	function Controller($rootScope, WorklogService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.beginDate = "";
		vm.workHour = 0;
		//Bindable functions
		vm.range = range;
		vm.addWorklog = addWorklog;
		// *********************** //
		// Function implementation //
		// *********************** //

		function range(count) {
			return new Array(count);
		}

		function addWorklog() {
			var newDate = moment(vm.beginDate).format('YYYY.MM.DD');
			WorklogService.addWorklog(newDate, vm.workHour, $rootScope.userData.workerId).then(
				function(result) {
					StatusLogService.showStatusLog(result.status, 'Create New Worklog');
					vm.beginDate = "";
					vm.workHour = 0;
				},
				function(error) {
				}
			)
		}

	}
})();
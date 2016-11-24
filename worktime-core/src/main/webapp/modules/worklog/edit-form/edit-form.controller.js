(function() {
	'use strict';

	angular
		.module('Worklog')
		.controller('WorklogEditController', Controller);

	Controller.$inject = ['$rootScope', '$mdDialog', 'WorklogService', 'ResponseWatcherService'];

	function Controller($rootScope, $mdDialog, WorklogService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
        vm.newBeginDate = "";
		vm.newWorkHour = "";
		vm.id = -1;
		vm.error = false;
		//Bindable functions
		vm.editWorklog = editWorklog;
		vm.range = range;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			var localBegin = $rootScope.selectedWorklog.beginDate;
			vm.newBeginDate = new Date(localBegin.substring(0,4)+'-'+localBegin.substring(5,7)+'-'+localBegin.substring(8,10))
			vm.newWorkHour = $rootScope.selectedWorklog.workHour;
			vm.id = $rootScope.selectedWorklog.id;
		}

		function editWorklog() {
			var newDate = moment(vm.newBeginDate).format('YYYY.MM.DD');
			WorklogService.editWorklog(vm.id, newDate, vm.newWorkHour).then(
				function(result) {
					if(result === 1) {
						answer({
							beginDate: newDate,
							workHour: vm.newWorkHour,
							status: result
						});
					} else {
						vm.error = true;
					}
				},
				function(error) {
					ResponseWatcherService.checkHttpStatus(error.status);
					$scope.cancel();
				}
			)
		}

		function range(count) {
			return new Array(count);
		}

		function answer(answer) {
			$rootScope.selectedWorklog = {};
			$mdDialog.hide(answer);
		}

		function cancel() {
			$rootScope.selectedWorklog = {};
			$mdDialog.cancel();
		}
	}
})();
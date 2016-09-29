(function() {
	'use strict';

	angular
		.module('Worklog')
		.controller('WorklogEditController', WorklogEditController);

	WorklogEditController.$inject = ['$rootScope', '$mdDialog', 'WorklogService']

	function WorklogEditController($rootScope, $mdDialog, WorklogService) {

		var vm = this;
		//Bindable variables
		vm.localBegin = $rootScope.selectedWorklog.beginDate;
        vm.newBeginDate = new Date(vm.localBegin.substring(0,4)+'-'+vm.localBegin.substring(5,7)+'-'+vm.localBegin.substring(8,10))
		vm.newWorkHour = $rootScope.selectedWorklog.workHour;
		vm.id = $rootScope.selectedWorklog.id;
		vm.error = false;
		//Bindable functions
		vm.editWorklog = editWorklog;
		vm.range = range;

		// *********************** //
		// Function implementation //
		// *********************** //
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
					$scope.cancel();
				}
			)
		}

		function answer(answer) {
		  $mdDialog.hide(answer);
		}

		function cancel() {
		  $mdDialog.cancel();
		}

		function range(count) {
			return new Array(count);
		}
	}
})();
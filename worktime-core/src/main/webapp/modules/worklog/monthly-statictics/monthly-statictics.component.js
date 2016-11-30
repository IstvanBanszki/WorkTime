(function() {
	'use strict';

	const templateLoc = ['modules',
						 'worklog',
						 'monthly-statictics',
						 'monthly-statictics.html'].join('/');

	angular
		.module('Worklog')
		.component('monthlyStatictics', Component());

	function Component() {
		return {
			templateUrl: templateLoc,
			controller: Controller,
			controllerAs: 'vm'
		}
	}

	Controller.$inject = ['$rootScope', 'WorklogService', 'StatusLogService', 'ResponseWatcherService'];

	function Controller($rootScope, WorklogService, StatusLogService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.year = 0;
		vm.month = 0;
		vm.excelType = 0
		//Bindable functions
		vm.rangeBetween = rangeBetween;
		vm.getMonthlyStat = getMonthlyStat;
		// *********************** //
		// Function implementation //
		// *********************** //
		function rangeBetween(begin, end) {
			var list = [];
			for (var i = begin; i <= end; i++) {
				list.push(i);
			}
			return list;
		}

		function createExcelFileName(excelType) {
			var employeeName = $rootScope.profileData.firstName+$rootScope.profileData.lastName;
			return employeeName+'-'+moment(new Date()).format('YYYYMMDDHHhhmmss')+'-ExportWorklog.xls'+((excelType === 1) ? '' : 'x');
		}

		function getMonthlyStat() {
			var excelTypeStr = ((vm.excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
			var excelFileName = createExcelFileName(vm.excelType);

			WorklogService.exportMonthlyStatistics($rootScope.userData.workerId, vm.excelType, vm.year, vm.month).then(
				function(result) {
					var blob = new Blob([result], {type: excelTypeStr});
					saveAs(blob, excelFileName);
					StatusLogService.showStatusLog(1, 'Export Monthly Statistics');
				},
				function(error) {
					StatusLogService.showStatusLog(-1, 'Export Monthly Statistics');
					ResponseWatcherService.checkHttpStatus(error.status);
				}
			)
		}

	}
})();
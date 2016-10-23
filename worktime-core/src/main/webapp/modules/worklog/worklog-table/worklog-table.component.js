(function() {
	'use strict';

	const templateLoc = ['modules',
						 'worklog',
						 'worklog-table',
						 'worklog-table.html'].join('/');

	angular
		.module('Worklog')
		.component('worklogTable', Component());

	function Component() {
		return {
			templateUrl: templateLoc,
			controller: Controller,
			controllerAs: 'vm'
		}
	}

	Controller.$inject = ['$rootScope', '$mdDialog', 'WorklogService', 'StatusLogService', 'ResponseWatcherService'];

	function Controller($rootScope, $mdDialog, WorklogService, StatusLogService, ResponseWatcherService) {

		var vm = this;
		//Bindable variables
		vm.worklogs = [];
		vm.sortType = "BeginDate";
		vm.sortReverse = false;
		vm.searchQuery = "";
		vm.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		vm.selectedDateFilter = "All";
		vm.dailyWorkHour = 8;
		//Bindable functions
		vm.showDownCaret = showDownCaret;
		vm.showUpCaret = showUpCaret;
		vm.setSearchTypeOrReverse = setSearchTypeOrReverse;
		vm.getWorklogs = getWorklogs;
		vm.deleteWorklog = deleteWorklog;
		vm.editWorklog = editWorklog;
		vm.exportWorklog = exportWorklog;
		vm.deleteWorklog = deleteWorklog;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			if (typeof vm.worklogs || vm.worklogs.length === 0) {
				vm.getWorklogs();
			}
			if (!(typeof $rootScope.profileData)) {
				vm.dailyWorkHour = $rootScope.profileData.dailyWorkHourTotal;
			}
		}

		function showDownCaret(tableHeader) {
			return (vm.sortType == tableHeader && !vm.sortReverse);
		}

		function showUpCaret(tableHeader) {
			return (vm.sortType == tableHeader && vm.sortReverse);
		}

		function setSearchTypeOrReverse(tableHeader) {
			if(vm.sortType == tableHeader){
				vm.sortReverse = !vm.sortReverse;
			} else {
				vm.sortType = tableHeader;
			}
		}

		function createExcelFileName(excelType) {
			var employeeName = $rootScope.profileData.firstName+$rootScope.profileData.lastName;
			return employeeName+'-'+moment(new Date()).format('YYYYMMDDHHhhmmss')+'-ExportWorklog.xls'+((excelType === 1) ? '' : 'x');
		}

		function getWorklogs() {
			WorklogService.getWorklog($rootScope.userData.workerId, vm.selectedDateFilter).then(
				function(result) {
					vm.worklogs = [];
					vm.worklogs = result;
				},
				function(error) {
					StatusLogService.showStatusLog(-1, 'Filter Worklog');
					ResponseWatcherService.checkHttpStatus(error.status);
				}
			)
		}

		function deleteWorklog(ev, worklog) {
			var confirm = $mdDialog.confirm().title('Delete Worklog')
											 .clickOutsideToClose(true)
											 .htmlContent('<div><p>Are you sure about delete the below worklog?<br>Begin Date: '+worklog.beginDate+', Hour: '+worklog.workHour+'</p></div>')
											 .targetEvent(ev)
											 .ok('Yes')
											 .cancel('No');
			$mdDialog.show(confirm).then(function() { // Yes
				WorklogService.deleteWorklog(worklog.id).then(
					function(result) {
						StatusLogService.showStatusLog(result, 'Delete Worklog');
						for(var i = 0; i < vm.worklogs.length; i++) {
							if(vm.worklogs[i].id === worklog.id) {
							   vm.worklogs.splice(i, 1);
							   break;
							} 
						}
					},
					function(error) {
						StatusLogService.showStatusLog(-1, 'Delete Worklog');
						ResponseWatcherService.checkHttpStatus(error.status);
					}
				)
			}, function() { // No
			});
		}

		function editWorklog(ev, worklog) {
			$rootScope.selectedWorklog = worklog;
			$mdDialog.show({
				templateUrl: 'modules/worklog/edit-form/edit-form.html',
				clickOutsideToClose: true,
				controller: 'WorklogEditController',
				parent: angular.element(document.body),
				targetEvent: ev
			}).then(function(answer) {
				StatusLogService.showStatusLog(answer.status, 'Edit Worklog');
				for(var i = 0; i < vm.worklogs.length; i++) {
					if(vm.worklogs[i].id === worklog.id) {
					   vm.worklogs[i].beginDate = answer.beginDate;
					   vm.worklogs[i].workHour = answer.workHour;
					   break;
					}
				}
			}, function() {
			});
		}

		function exportWorklog(excelType) {

			var excelTypeStr = ((excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
			var excelFileName = createExcelFileName(excelType);

			WorklogService.exportWorklog($rootScope.userData.workerId, vm.selectedDateFilter, excelType).then(
				function(result) {
					var blob = new Blob([result], {type: excelTypeStr});
					saveAs(blob, excelFileName);
					StatusLogService.showStatusLog(1, 'Export Worklog');
				},
				function(error) {
					StatusLogService.showStatusLog(-1, 'Export Worklog');
					ResponseWatcherService.checkHttpStatus(error.status);
				}
			);
		}
	}
})();
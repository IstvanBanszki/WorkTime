(function() {
	'use strict';

	angular
		.module('Worklog')
		.controller('WorklogController', WorklogController);

	WorklogController.$inject = ['$rootScope', '$mdDialog', 'WorklogService'];

	function WorklogController($rootScope, $mdDialog, WorklogService) {

		var vm = this;
		//Bindable variables
		vm.worklogs = [];
		vm.sortType = "BeginDate";
		vm.sortReverse = false;
		vm.searchQuery = "";
		vm.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		vm.selectedDateFilter = "All";
		vm.dailyWorkHour = 8;
		vm.beginDate = "";
		vm.workHour = 0;
		//Bindable functions
		vm.showDownCaret = showDownCaret;
		vm.showUpCaret = showUpCaret;
		vm.setSearchTypeOrReverse = setSearchTypeOrReverse;
		vm.range = range;
		vm.addWorklog = addWorklog;
		vm.initWorklog = initWorklog;
		vm.getWorklogs = getWorklogs;
		vm.deleteWorklog = deleteWorklog;
		vm.editWorklog = editWorklog;
		vm.exportWorklog = exportWorklog;
		vm.deleteWorklog = deleteWorklog;

		// *********************** //
		// Function implementation //
		// *********************** //
		function showDownCaret(tableHeader) {
			return (vm.sortType == tableHeader && !vm.sortReverse);
		};

		function showUpCaret(tableHeader) {
			return (vm.sortType == tableHeader && vm.sortReverse);
		};

		function setSearchTypeOrReverse(tableHeader) {
			if(vm.sortType == tableHeader){
				vm.sortReverse = !vm.sortReverse;
			} else {
				vm.sortType = tableHeader;
			}
		};

		function createExcelFileName(excelType) {
			var employeeName = $rootScope.profileData.firstName+$rootScope.profileData.lastName;
			return employeeName+'-'+moment(new Date()).format('YYYYMMDDHHhhmmss')+'-ExportWorklog.xls'+((excelType === 1) ? '' : 'x');
		};

		function range(count) {
			return new Array(count);
		};

		function showStatus(result) {
			alert = $mdDialog.alert({
				title: 'Worklog Adding',
				textContent: (result === 1 ? 'The saving is succesfull!' : (result === -1 ? 'There is an already saved worklog at the selected date!' : 'There is an error during saving!')),
				clickOutsideToClose: true,
				ok: 'Close'
			});
			$mdDialog.show(alert)
					.finally(function() {
						alert = undefined;
					});
		};

		function addWorklog() {
			//for(var i = 0; i < $scope.worklogs.length; i++) {
				//if(moment($scope.beginDate).isSame($scope.worklogs[i].beginDate, 'year')) {
				//	$scope.showStatus(-1);
				//}
			//}
			var newDate = moment(vm.beginDate).format('YYYY.MM.DD');
			WorklogService.addWorklog(newDate, vm.workHour, $rootScope.userData.workerId).then(
				function(result) {
					vm.worklogs.push({
						id: result.newId,
						beginDate: newDate,
						workHour: vm.workHour
					});
					vm.beginDate = "";
					vm.workHour = 0;
					showStatus(result.status);
				},
				function(error) {
				}
			)
		};

		function initWorklog() {
			if (typeof vm.worklogs || vm.worklogs.length === 0) {
				vm.getWorklogs();
			}
			if (!(typeof $rootScope.profileData)) {
				vm.dailyWorkHour = $rootScope.profileData.dailyWorkHourTotal;
			}
		};

		function getWorklogs() {
			WorklogService.getWorklog($rootScope.userData.workerId, vm.selectedDateFilter).then(
				function(result) {
					vm.worklogs = [];
					vm.worklogs = result;
				},
				function(error) {
				}
			)
		};

		function deleteWorklog(ev, worklog) {
			var confirm = $mdDialog.confirm().title('Worklog Delete')
											 .clickOutsideToClose(true)
											 .htmlContent('<div><p>Are you sure about delete the below worklog?<br>Begin Date: '+worklog.beginDate+', Hour: '+worklog.workHour+'</p></div>')
											 .targetEvent(ev)
											 .ok('Yes')
											 .cancel('No');
			$mdDialog.show(confirm).then(function() { // Yes
				WorklogService.deleteWorklog(worklog.id).then(
					function(result) {
					},
					function(error) {
					}
				)
				for(var i = 0; i < vm.worklogs.length; i++) {
					if(vm.worklogs[i].id === worklog.id) {
					   vm.worklogs.splice(i, 1);
					   break;
					} 
				}
			}, function() { // No
			});
		};

		function editWorklog(ev, worklog) {
			$rootScope.selectedWorklog = worklog;
			$mdDialog.show({
				locals: { worklogData: worklog },
				templateUrl: 'modules/worklog/worklog.edit.html',
				clickOutsideToClose: true,
				controller: 'WorklogEditController',
				parent: angular.element(document.body),
				targetEvent: ev
			}).then(function(answer) {
				for(var i = 0; i < vm.worklogs.length; i++) {
					if(vm.worklogs[i].id === worklog.id) {
					   vm.worklogs[i].beginDate = answer.beginDate;
					   vm.worklogs[i].workHour = answer.workHour;
					   break;
					}
				}
			}, function() {
			});
		};

		function exportWorklog(excelType) {

			var excelTypeStr = ((excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
			var excelFileName = createExcelFileName(excelType);

			WorklogService.exportWorklog($rootScope.userData.workerId, vm.selectedDateFilter, excelType).then(
				function(result) {
					var blob = new Blob([result], {type: excelTypeStr});
					saveAs(blob, excelFileName);
				},
				function(error) {
				}
			);
		};
	}
})();
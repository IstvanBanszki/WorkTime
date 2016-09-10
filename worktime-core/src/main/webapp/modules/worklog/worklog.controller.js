'use strict';

angular.module('Worklog')
.controller('WorklogController', ['$scope', '$rootScope', '$mdDialog', 'WorklogService',
    function ($scope, $rootScope, $mdDialog, WorklogService) {
		$scope.worklogs = [];
		$scope.sortType = "BeginDate";
		$scope.sortReverse = false;
		$scope.searchQuery = "";
		$scope.showDownCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && !$scope.sortReverse);
		};
		$scope.showUpCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && $scope.sortReverse);
		};
		$scope.setSearchTypeOrReverse = function(tableHeader) {
			if($scope.sortType == tableHeader){
				$scope.sortReverse = !$scope.sortReverse;
			} else {
				$scope.sortType = tableHeader;
			}
		};
		$scope.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		$scope.selectedDateFilter = "All";
		$scope.dailyWorkHour = 8;
		
		$scope.beginDate = "";
		$scope.workHour = 0;

		$scope.showStatus = function(result) {
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
		$scope.AddWorklog = function() {
			//for(var i = 0; i < $scope.worklogs.length; i++) {
				//if(moment($scope.beginDate).isSame($scope.worklogs[i].beginDate, 'year')) {
				//	$scope.showStatus(-1);
				//}
			//}
			WorklogService.AddWorklog($scope.beginDate, $scope.workHour, $rootScope.userData.workerId).then(
				function(result) {
					$scope.worklogs.push({
						beginDate: moment($scope.beginDate).format('YYYY.MM.DD'),
						workHour: $scope.workHour
					});
					$scope.beginDate = "";
					$scope.workHour = 0;
					$scope.showStatus(result);
				},
				function(error) {
				}
			)
		};
		$scope.initWorklog = function() {
			if (typeof $scope.worklogs || $scope.worklogs.length === 0) {
				$scope.GetWorklogs();
			}
			if (!(typeof $rootScope.profileData)) {
				$scope.dailyWorkHour = $rootScope.profileData.dailyWorkHourTotal;
			}
		};
		$scope.GetWorklogs = function() {
			WorklogService.GetWorklog($rootScope.userData.workerId, $scope.selectedDateFilter).then(
				function(result) {
					$scope.worklogs = [];
					$scope.worklogs = result;
					$scope.dateFormatter();
				},
				function(error) {
				}
			)
		};
		$scope.DeleteWorklog = function(ev, worklog) {
			var confirm = $mdDialog.confirm().title('Worklog Delete')
											 .clickOutsideToClose(true)
										     .htmlContent('<div><p>Are you sure about delete the below worklog?<br>Begin Date: '+worklog.beginDate+', Hour: '+worklog.workHour+'</p></div>')
										     .targetEvent(ev)
										     .ok('Yes')
										     .cancel('No');
			$mdDialog.show(confirm).then(function() { // Yes
				WorklogService.DeleteWorklog(worklog.id).then(
					function(result) {
					},
					function(error) {
					}
				)
				for(var i = 0; i < $scope.worklogs.length; i++) {
					if($scope.worklogs[i].id === worklog.id) {
					   $scope.worklogs.splice(i, 1);
					   break;
					} 
				}
			}, function() { // No
			});
		};
		$scope.EditWorklog = function(ev, worklog) {
			$rootScope.selectedWorklog = worklog;
			$mdDialog.show({
				locals: { worklogData: worklog },
				templateUrl: 'modules/worklog/worklog.edit.html',
				clickOutsideToClose: true,
				controller: 'WorklogEditController',
				parent: angular.element(document.body),
				targetEvent: ev
			}).then(function(answer) {
				for(var i = 0; i < $scope.worklogs.length; i++) {
					if($scope.worklogs[i].id === worklog.id) {
					   $scope.worklogs[i].beginDate = moment(answer.beginDate).format('YYYY.MM.DD');
					   $scope.worklogs[i].workHour = answer.workHour;
					   break;
					}
				}
			}, function() {
			});
		};
		$scope.ExportWorklog = function(excelType) {
			
			var excelTypeStr = ((excelType === 1) ? 'application/vnd.ms-excel' : 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
			var excelFileName = 'ExportWorklog.xls' + ((excelType === 1) ? '' : 'x');

			WorklogService.ExportWorklog($rootScope.userData.workerId, $scope.selectedDateFilter, excelType).then(
				function(result) {
					var blob = new Blob([result], {type: excelTypeStr});
					saveAs(blob, excelFileName);
				},
				function(error) {
				}
			);
		};
		$scope.timeValue = function(value) {
			return value < 10 ? '0'+value : value+'';
		};
		$scope.range = function(count) {
			return new Array(count);
		};
		$scope.dateFormatter = function() {
			if (!(typeof $scope.worklogs) || $scope.worklogs.length !== 0) {
				$scope.worklogs.forEach(function(worklog) {
					worklog.beginDate = moment(worklog.beginDate).format('YYYY.MM.DD');
				});
			}
		};
    }]);
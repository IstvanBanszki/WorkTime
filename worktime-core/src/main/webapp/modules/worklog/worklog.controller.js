'use strict';

angular.module('Worklog')
.controller('WorklogController', ['$scope', '$rootScope', '$mdDialog', 'WorklogService',
    function ($scope, $rootScope, $mdDialog, WorklogService) {
		$scope.tabs = [{
            title: 'Add',
            url: 'modules/worklog/worklog.add.html'
        }, {
            title: 'Show',
            url: 'modules/worklog/worklog.show.html'
        }];
		$scope.currentTab = $scope.tabs[0].url;
		$scope.isActiveTab = function(tabUrl) {
			return tabUrl == $scope.currentTab;
		};
		$scope.onClickTab = function(tab) {
			$scope.currentTab = tab.url;
		};

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
					 }
					);
		};
		$scope.addWorklog = function() {
			WorklogService.AddWorklog($scope.beginDate, $scope.workHour, $rootScope.userData.workerId).then(
				function(result) {
					$scope.beginDate = "";
					$scope.workHour = 0;
					$scope.worklogs.push({
						beginDate: $scope.beginDate,
						workHour: $scope.workHour
					});
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
		};
		$scope.GetWorklogs = function() {
			WorklogService.GetWorklog($rootScope.userData.workerId).then(
				function(result) {
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
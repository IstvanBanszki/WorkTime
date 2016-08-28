'use strict';

angular.module('Absence')
.controller('AbsenceController', ['$scope', '$rootScope', '$mdDialog', 'AbsenceService',
    function ($scope, $rootScope, $mdDialog, AbsenceService) {
		$scope.tabs = [{
            title: 'Add',
            url: 'modules/absence/absence.add.html'
        }, {
            title: 'Show',
            url: 'modules/absence/absence.show.html'
        }, {
            title: 'Data',
            url: 'modules/absence/absence.data.html'
        }];
		$scope.currentTab = 'modules/absence/absence.add.html';
		$scope.isActiveTab = function(tabUrl) {
			return tabUrl == $scope.currentTab;
		}
		$scope.onClickTab = function(tab) {
			$scope.currentTab = tab.url;
		}

		$scope.absences = [];
		$scope.absenceDatas = [];
		$scope.sortType = "Begin";
		$scope.sortReverse = false;
		$scope.searchQuery = "";
		$scope.showDownCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && !$scope.sortReverse);
		}
		$scope.showUpCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && $scope.sortReverse);
		}
		$scope.setSearchTypeOrReverse = function(tableHeader) {
			if($scope.sortType == tableHeader) {
				$scope.sortReverse = !$scope.sortReverse;
			} else {
				$scope.sortType = tableHeader;
			}
		}

		$scope.absenceType = "1";
		$scope.begin = "";
		$scope.end = "";

		$scope.showStatus = function(result) {
			var textContent = '';
			if (result === -1) {
				textContent = 'The saving is unsuccesfull, with the begin date you are already have absence!';
			} else if (result === -2) {
				textContent = 'The saving is unsuccesfull, with the end date you are already have absence!';
			} else if (result === -3) {
				textContent = 'The saving is unsuccesfull, the begin or end date is in the range of an already exist absence!';
			} else {
				textContent = 'The saving is succesfull!';
			}
			alert = $mdDialog.alert({
				title: 'Absence Adding',
				textContent: textContent,
				clickOutsideToClose: true,
				ok: 'Close'
			});
		    $mdDialog.show(alert)
					 .finally(function() {
						alert = undefined;
					 });
		};	
		$scope.addAbsence = function() {
			AbsenceService.AddAbsence($scope.begin, $scope.end, $rootScope.userData.workerId, $scope.absenceType).then(
				function(result) {
					$scope.absenceType = "1";
					$scope.begin = "";
					$scope.end = "";
					$scope.showStatus(result);
					$scope.GetAbsences();
				},
				function(error) {
				}
			)
		}
		$scope.initAbsence = function() {
			if((typeof $scope.absences || $scope.absences.length === 0) && 
			   (typeof $scope.absenceDatas || $scope.absenceDatas.length === 0)) {
				$scope.GetAbsences();
				$scope.GetAbsenceDatas();
			}
		}
		$scope.GetAbsences = function() {
			AbsenceService.GetAbsence($rootScope.userData.workerId).then(
					function(result) {
						$scope.absences = result;
						$scope.dateFormatter();
					},
					function(error) {
					}
				)
		}
		$scope.GetAbsenceDatas = function() {
			AbsenceService.GetAbsenceData($rootScope.userData.workerId).then(
					function(result) {
						$scope.absenceDatas = result;
					},
					function(error) {
					}
				)
		}
		$scope.dateFormatter = function() {
			if (!(typeof $scope.absences) || $scope.absences.length !== 0) {
				$scope.absences.forEach(function(absence) {
					absence.begin = moment(absence.begin).format('YYYY.MM.DD');
					absence.end = moment(absence.end).format('YYYY.MM.DD');
				});	
			}
		};
    }]);
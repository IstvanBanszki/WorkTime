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
		};
		$scope.onClickTab = function(tab) {
			$scope.currentTab = tab.url;
		};

		$scope.absences = [];
		$scope.absenceDatas = [];
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
			if($scope.sortType == tableHeader) {
				$scope.sortReverse = !$scope.sortReverse;
			} else {
				$scope.sortType = tableHeader;
			}
		};

		$scope.absenceType = "2";
		$scope.beginDate = "";
		$scope.endDate = "";

		$scope.showStatus = function(result) {
			var textContent = '';
			if (result === -1) {
				textContent = 'The saving is unsuccesfull, with the Begin Date you are already have absence!';
			} else if (result === -2) {
				textContent = 'The saving is unsuccesfull, with the End Date you are already have absence!';
			} else if (result === -3) {
				textContent = 'The saving is unsuccesfull, the Begin or End Date is in the range of an already exist absence!';
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
		$scope.AddAbsence = function() {
			AbsenceService.AddAbsence($scope.beginDate, $scope.endDate, $rootScope.userData.workerId, $scope.absenceType).then(
				function(result) {
					$scope.absenceType = "2";
					$scope.beginDate = "";
					$scope.endDate = "";
					$scope.showStatus(result);
				},
				function(error) {
				}
			);
		};
		$scope.initAbsence = function() {
			if((typeof $scope.absences || $scope.absences.length === 0) && 
			   (typeof $scope.absenceDatas || $scope.absenceDatas.length === 0)) {
				$scope.GetAbsences();
				$scope.GetAbsenceDatas();
			}
		};
		$scope.GetAbsences = function() {
			AbsenceService.GetAbsence($rootScope.userData.workerId).then(
				function(result) {
					$scope.absences = result;
					$scope.dateFormatter();
				},
				function(error) {
				}
			);
		};
		$scope.GetAbsenceDatas = function() {
			AbsenceService.GetAbsenceData($rootScope.userData.workerId).then(
				function(result) {
					$scope.absenceDatas = result;
				},
				function(error) {
				}
			);
		};
		$scope.DeleteAbsence = function(ev, absence) {
			var confirm = $mdDialog.confirm().title('Absence Delete')
											 .clickOutsideToClose(true)
										     .htmlContent('<div><p>Are you sure about delete the below Absence?<br>Begin Date: '+absence.beginDate+'<br>End Date: '+absence.endDate+'<br>Absence Type: '+absence.absenceType+'</p></div>')
										     .targetEvent(ev)
										     .ok('Yes')
										     .cancel('No');
			$mdDialog.show(confirm).then(function() { // Yes
				AbsenceService.DeleteAbsence(absence.id).then(
					function(result) {
					},
					function(error) {
					}
				)
				for(var i = 0; i < $scope.absences.length; i++) {
					if($scope.absences[i].id === absence.id) {
						$scope.absences.splice(i, 1);
						break;
					} 
				}
			}, function() { // No
			});			
		};
		$scope.EditAbsence = function(ev, absence) {
			$rootScope.selectedAbsence = absence;
			$mdDialog.show({
				locals: { absenceData: absence },
				templateUrl: 'modules/absence/absence.edit.html',
				clickOutsideToClose: true,
				bindToController: true,
				controller: 'AbsenceEditController',
				parent: angular.element(document.body),
				targetEvent: ev
			}).then(function() {
				for(var i = 0; i < $scope.absences.length; i++) {
					if($scope.absences[i].id === absence.id) {
						//$scope.absences.[i];
						break;
					}
				}
			}, function() {
			});
		};
		$scope.dateFormatter = function() {
			if (!(typeof $scope.absences) || $scope.absences.length !== 0) {
				$scope.absences.forEach(function(absence) {
					absence.beginDate = moment(absence.beginDate).format('YYYY.MM.DD');
					absence.endDate = moment(absence.endDate).format('YYYY.MM.DD');
				});	
			}
		};
    }]);
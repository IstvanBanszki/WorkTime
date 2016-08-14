'use strict';

angular.module('Absence')
.controller('AbsenceController', ['$scope', '$rootScope', 'AbsenceService',
    function ($scope, $rootScope, AbsenceService) {
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
			if($scope.sortType == tableHeader){
				$scope.sortReverse = !$scope.sortReverse;
			} else {
				$scope.sortType = tableHeader;
			}
		}

		$scope.absenceType = "0";
		$scope.begin = "";
		$scope.end = "";

		$scope.addAbsence = function() {
			AbsenceService.AddAbsence($scope.begin, $scope.end, $rootScope.userData.workerId, $scope.absenceType).then(
				function( result ){
					$scope.GetAbsences();
				},
				function( error ){					
				}
			)
		}
		$scope.initAbsence = function() {
			if( (typeof $scope.absences || $scope.absences.length === 0) && 
				(typeof $scope.absenceDatas || $scope.absenceDatas.length === 0) ){
				$scope.GetAbsences();
				$scope.GetAbsenceDatas();
			}
		}
		$scope.GetAbsences = function() {
			AbsenceService.GetAbsence($rootScope.userData.workerId).then(
					function( result ){
						$scope.absences = result;
						$scope.dateFormatter();
					},
					function( error ){						
					}
				)
		}
		$scope.GetAbsenceDatas = function() {
			AbsenceService.GetAbsenceData($rootScope.userData.workerId).then(
					function( result ){
						$scope.absenceDatas = result;
					},
					function( error ){						
					}
				)
		}
		$scope.dateFormatter = function() {
			$scope.absences.forEach(function(absence) {
				absence.begin = moment(absence.begin).format('YYYY.MM.DD');
				absence.end = moment(absence.end).format('YYYY.MM.DD');
			});
		};
    }]);
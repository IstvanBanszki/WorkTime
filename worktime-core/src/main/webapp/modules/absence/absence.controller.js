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
        }];
		$scope.currentTab = 'modules/absence/absence.add.html';
		$scope.isActiveTab = function(tabUrl) {
			return tabUrl == $scope.currentTab;
		}
		$scope.onClickTab = function(tab) {
			$scope.currentTab = tab.url;
		}

		$scope.absences = [];
		$scope.sortType = "Description";
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

		$scope.description = "";
		$scope.absenceType = "0";
		$scope.begin = "";
		$scope.end = "";

		$scope.addAbsence = function() {
			AbsenceService.AddAbsence($scope.description, $scope.begin, $scope.end, $rootScope.userData.workerId, $scope.absenceType).then(
				function( result ){
					
				},
				function( error ){
					
				}
			)
		}
		$scope.initAbsence = function(){
			if( typeof $scope.absences || $scope.absences.length === 0 ){
				AbsenceService.GetAbsence($rootScope.userData.workerId).then(
					function( result ){
						$scope.absences = result;
						$scope.dateFormatter();
					},
					function( error ){
						
					}
				)
			}
		}
		$scope.dateFormatter = function(){
			$scope.absences.forEach(function(absence) {
				absence.begin = moment(absence.begin).format('YYYY.MM.DD HH:mm:ss');
				absence.end = moment(absence.end).format('YYYY.MM.DD HH:mm:ss');
			});
		};
    }]);
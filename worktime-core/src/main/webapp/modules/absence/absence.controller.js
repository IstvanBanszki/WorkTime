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
    }]);
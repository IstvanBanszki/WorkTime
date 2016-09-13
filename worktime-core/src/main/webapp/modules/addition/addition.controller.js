'use strict';

angular.module('Addition')
.controller('AdditionController', ['$scope', '$rootScope', 'AdditionService',
    function($scope, $rootScope, AdditionService) {
		$scope.officesWithDepartments = [];

		$scope.init = function() {
			AdditionService.GetOfficesWithDepartments().then(
				function(result) {
					$scope.officesWithDepartments = result;
				},
				function(error) {
				}
			);
		};


    }]);
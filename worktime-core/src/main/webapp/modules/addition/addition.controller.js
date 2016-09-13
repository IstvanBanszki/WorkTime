'use strict';

angular.module('Addition')
.controller('AdditionController', ['$scope', '$rootScope', 'AdditionService',
    function($scope, $rootScope, AdditionService) {
		$scope.officesWithDepartments = [];
		$scope.departments = [];
		$scope.offices = [];
		$scope.departmentsForOffice = [];

		$scope.selectedDepartmentName = "";
		$scope.selectedDepartment = "";
		$scope.selectedOfficeName = "";
		$scope.selectedOffice = "";

		$scope.showODInformation = false;

		$scope.init = function() {
			AdditionService.GetDepartments().then(
				function(result) {
					$scope.departments = result;
				},
				function(error) {
				}
			);
			AdditionService.GetOffices().then(
				function(result) {
					$scope.offices = result;
				},
				function(error) {
				}
			);
		};

		$scope.setDepartments = function() {
			if (!(typeof $scope.departments) || ($scope.departments.length !== 0)) {
				$scope.departmentsForOffice = [];
				$scope.selectedDepartmentName = "";

				for (var i = 0, len = $scope.offices.length; i < len; i++) {
					if($scope.offices[i].name === $scope.selectedOfficeName) {
						$scope.selectedOffice = $scope.offices[i];
						break;
					}
				}
				if(!(typeof $scope.selectedOffice) || ($scope.selectedOffice !== "")) {
					for (var i = 0, len = $scope.departments.length; i < len; i++) {
						if ($scope.departments[i].officeId === $scope.selectedOffice.id) {
							$scope.departmentsForOffice.push($scope.departments[i]);
						}
					}
				}
			}
		};

		$scope.officeName = "";
		$scope.officeAddress = "";
		$scope.officeDateOfFoundation = "";
		$scope.departmentName = "";
		$scope.departmentDateOfFoundation = "";
		
		$scope.listInformation = function() {
			for (var i = 0, len = $scope.departments.length; i < len; i++) {
				if($scope.departments[i].name === $scope.selectedDepartmentName) {
					$scope.selectedDepartment = $scope.departments[i];
					$scope.showODInformation = true;
					break;
				}
			}
			$scope.officeName = $scope.selectedOfficeName;
			$scope.officeAddress = $scope.selectedOffice.address;
			$scope.officeDateOfFoundation = $scope.selectedOffice.dateOfFoundation;
			$scope.departmentName = $scope.selectedDepartmentName;
			$scope.departmentDateOfFoundation = $scope.selectedDepartment.dateOfFoundation;
		};

    }]);
'use strict';

angular.module('Addition')
.controller('AdditionController', ['$scope', '$rootScope', 'AdditionService',
    function($scope, $rootScope, AdditionService) {
		$scope.officesWithDepartments = [];
		$scope.departments = [];
		$scope.offices = [];
		$scope.departmentsForOffice = [];

		$scope.indexOfSelectedOffice = -1;
		$scope.indexOfSelectedDepartment = -1;

		$scope.selectedDepartmentName = "";
		$scope.selectedDepartment = "";
		$scope.selectedOfficeName = "";
		$scope.selectedOffice = "";

		$scope.showODInformation = false;
		$scope.showEditOfficeForm = false;
		$scope.showEditDepartmentForm = false;

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
		
		$scope.openSelect = function() {		
			$scope.officeWorkerNumber = 0;
			$scope.departmentWorkerNumber = 0;
			$scope.showODInformation = false;
			$scope.showEditOfficeForm = false;
			$scope.showEditDepartmentForm = false;
		};
		$scope.setDepartments = function() {
			if (!(typeof $scope.departments) || ($scope.departments.length !== 0)) {
				$scope.departmentsForOffice = [];
				$scope.selectedDepartmentName = "";

				for (var i = 0, len = $scope.offices.length; i < len; i++) {
					if($scope.offices[i].name === $scope.selectedOfficeName) {
						$scope.selectedOffice = $scope.offices[i];
						$scope.indexOfSelectedOffice = i;
						break;
					}
				}
				if(!(typeof $scope.selectedOffice) || ($scope.selectedOffice !== "")) {
					for (var i = 0, len = $scope.departments.length; i < len; i++) {
						if ($scope.departments[i].officeId === $scope.selectedOffice.id) {
							$scope.officeWorkerNumber += $scope.departments[i].workerNumber;
							$scope.departmentsForOffice.push($scope.departments[i]);
						}
					}
				}
			}
		};

		$scope.officeName = "";
		$scope.officeAddress = "";
		$scope.officeDateOfFoundtation = "";
		$scope.officeWorkerNumber = 0;
		$scope.departmentName = "";
		$scope.departmentDateOfFoundtation = "";
		$scope.departmentWorkerNumber = 0;
		
		$scope.listInformation = function() {
			for (var i = 0, len = $scope.departments.length; i < len; i++) {
				if($scope.departments[i].name === $scope.selectedDepartmentName) {
					$scope.selectedDepartment = $scope.departments[i];
					$scope.showODInformation = true;
					$scope.indexOfSelectedDepartment = i;
					break;
				}
			}
			$scope.officeName = $scope.selectedOfficeName;
			$scope.officeAddress = $scope.selectedOffice.address;
			$scope.officeDateOfFoundtation = $scope.selectedOffice.dateOfFoundtation;
			$scope.departmentName = $scope.selectedDepartmentName;
			$scope.departmentDateOfFoundtation = $scope.selectedDepartment.dateOfFoundtation;
			$scope.departmentWorkerNumber = $scope.selectedDepartment.workerNumber;
		};
		$scope.editOffice  = function() {
			AdditionService.EditOffice($scope.selectedOffice.id, $scope.officeName, $scope.officeAddress, $scope.officeDateOfFoundtation).then(
				function() {
					$scope.offices[$scope.indexOfSelectedOffice].name = $scope.officeName;
					$scope.offices[$scope.indexOfSelectedOffice].address = $scope.officeAddress;
					$scope.offices[$scope.indexOfSelectedOffice].dateOfFoundtation = $scope.officeDateOfFoundtation;
				},
				function(error) {
				}
			);
		};
		$scope.editDepartment = function() {
			AdditionService.EditDepartment($scope.selectedDepartment.id, $scope.departmentName, $scope.departmentDateOfFoundtation, $scope.selectedDepartment.officeId).then(
				function() {
					$scope.departments[$scope.indexOfSelectedDepartment].name = $scope.departmentName;
					$scope.departments[$scope.indexOfSelectedDepartment].dateOfFoundtation = $scope.departmentDateOfFoundtation;
				},
				function(error) {
				}
			);
		};
    }]);
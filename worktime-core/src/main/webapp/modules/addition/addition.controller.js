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
		$scope.showSaveOfficeForm = false;
		$scope.showSaveDepartmentForm = false;

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
		$scope.openEditOffice = function() {
			$scope.showSaveOfficeForm = false;
			$scope.showSaveDepartmentForm = false;
			$scope.showEditOfficeForm = !$scope.showEditOfficeForm;
		};
		$scope.openEditDepartment = function() {
			$scope.showSaveOfficeForm = false;
			$scope.showSaveDepartmentForm = false;
			$scope.showEditDepartmentForm = !$scope.showEditDepartmentForm;
		};
		$scope.openSaveOffice = function() {
			$scope.showEditOfficeForm = false;
			$scope.showEditDepartmentForm = false;
			$scope.showSaveOfficeForm = !$scope.showSaveOfficeForm;
		};
		$scope.openSaveDepartment = function() {
			$scope.showEditOfficeForm = false;
			$scope.showEditDepartmentForm = false;
			$scope.showSaveDepartmentForm = !$scope.showSaveDepartmentForm;
		};
		$scope.openSelect = function() {
			$scope.officeWorkerNumber = 0;
			$scope.departmentWorkerNumber = 0;
			$scope.showODInformation = false;
			$scope.showEditOfficeForm = false;
			$scope.showEditDepartmentForm = false;
			$scope.showSaveOfficeForm = false;
			$scope.showSaveDepartmentForm = false;
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
		$scope.officeDateOfFoundtationForEdit = "";
		$scope.officeWorkerNumber = 0;
		$scope.departmentName = "";
		$scope.departmentDateOfFoundtation = "";
		$scope.departmentDateOfFoundtationForEdit = "";
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
			$scope.officeDateOfFoundtationForEdit = moment($scope.selectedOffice.dateOfFoundtation, 'YYYY.MM.DD', false).toDate();

			$scope.departmentName = $scope.selectedDepartmentName;
			$scope.departmentDateOfFoundtation = $scope.selectedDepartment.dateOfFoundtation;
			$scope.departmentDateOfFoundtationForEdit = moment($scope.selectedDepartment.dateOfFoundtation, 'YYYY.MM.DD', false).toDate();
			$scope.departmentWorkerNumber = $scope.selectedDepartment.workerNumber;
		};
		$scope.editOffice  = function() {
			var newDate = moment($scope.officeDateOfFoundtationForEdit).format('YYYY.MM.DD');
			AdditionService.EditOffice($scope.selectedOffice.id, $scope.officeName, $scope.officeAddress, newDate).then(
				function(result) {
					$scope.offices[$scope.indexOfSelectedOffice].name = $scope.officeName;
					$scope.offices[$scope.indexOfSelectedOffice].address = $scope.officeAddress;
					$scope.offices[$scope.indexOfSelectedOffice].dateOfFoundtation = newDate;
					$scope.officeDateOfFoundtation = newDate;
				},
				function(error) {
				}
			);
		};
		$scope.editDepartment = function() {
			var newDate = moment($scope.departmentDateOfFoundtationForEdit).format('YYYY.MM.DD');
			AdditionService.EditDepartment($scope.selectedDepartment.id, $scope.departmentName, newDate, $scope.selectedDepartment.officeId).then(
				function(result) {
					$scope.departments[$scope.indexOfSelectedDepartment].name = $scope.departmentName;
					$scope.departments[$scope.indexOfSelectedDepartment].dateOfFoundtation = newDate;
					$scope.departmentDateOfFoundtation = newDate;
				},
				function(error) {
				}
			);
		};

		$scope.officeNameForCreation = "";
		$scope.officeAddressForCreation = "";
		$scope.officeDateOfFoundtationForCreation = "";
		$scope.departmentNameForCreation = "";
		$scope.departmentDateOfFoundtationForCreation = "";
		$scope.selectedOfficeIdForCreation = -1;

		$scope.createNewOffice = function() {
			var newDate = moment($scope.officeDateOfFoundtationForCreation).format('YYYY.MM.DD');
			AdditionService.SaveOffice($scope.officeNameForCreation, $scope.officeAddressForCreation, newDate).then(
				function(result) {
				},
				function(error) {
				}
			);
		};
		$scope.createNewDepartment = function() {
			var newDate = moment($scope.departmentDateOfFoundtationForCreation).format('YYYY.MM.DD');
			AdditionService.SaveDepartment($scope.departmentNameForCreation, newDate, $scope.selectedOfficeIdForCreation).then(
				function(result) {
				},
				function(error) {
				}
			);
		};
    }]);
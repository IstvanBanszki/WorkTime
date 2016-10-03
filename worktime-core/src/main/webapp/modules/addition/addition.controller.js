(function() {
	'use strict';

	angular
		.module('Addition')
		.controller('AdditionController', AdditionController);

	AdditionController.$inject = ['$rootScope', 'AdditionService', 'StatusLogService']

    function AdditionController($rootScope, AdditionService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.officesWithDepartments = [];
		vm.departments = [];
		vm.offices = [];
		vm.departmentsForOffice = [];
		vm.indexOfSelectedOffice = -1;
		vm.indexOfSelectedDepartment = -1;
		vm.selectedDepartmentName = "";
		vm.selectedDepartment = "";
		vm.selectedOfficeName = "";
		vm.selectedOffice = "";
		vm.showODInformation = false;
		vm.showEditOfficeForm = false;
		vm.showEditDepartmentForm = false;
		vm.showSaveOfficeForm = false;
		vm.showSaveDepartmentForm = false;

		vm.officeName = "";
		vm.officeAddress = "";
		vm.officeDateOfFoundtation = "";
		vm.officeDateOfFoundtationForEdit = "";
		vm.officeWorkerNumber = 0;

		vm.departmentName = "";
		vm.departmentDateOfFoundtation = "";
		vm.departmentDateOfFoundtationForEdit = "";
		vm.departmentWorkerNumber = 0;

		//Bindable functions
		vm.openEditOffice = openEditOffice;
		vm.openEditDepartment = openEditDepartment;
		vm.openSaveOffice = openSaveOffice;
		vm.openSaveDepartment = openSaveDepartment;
		vm.openSelect = openSelect;
		vm.setDepartments = setDepartments;
		vm.listInformation = listInformation;
		vm.editOffice = editOffice;
		vm.editDepartment = editDepartment;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			AdditionService.getDepartments().then(
				function(result) {
					vm.departments = result;
				},
				function(error) {
				}
			);
			AdditionService.getOffices().then(
				function(result) {
					vm.offices = result;
				},
				function(error) {
				}
			);
		}

		function openEditOffice() {
			vm.showSaveOfficeForm = false;
			vm.showSaveDepartmentForm = false;
			vm.showEditOfficeForm = !vm.showEditOfficeForm;
		}

		function openEditDepartment() {
			vm.showSaveOfficeForm = false;
			vm.showSaveDepartmentForm = false;
			vm.showEditDepartmentForm = !vm.showEditDepartmentForm;
		}

		function openSaveOffice() {
			vm.showEditOfficeForm = false;
			vm.showEditDepartmentForm = false;
			vm.showSaveOfficeForm = !vm.showSaveOfficeForm;
		}

		function openSaveDepartment() {
			vm.showEditOfficeForm = false;
			vm.showEditDepartmentForm = false;
			vm.showSaveDepartmentForm = !vm.showSaveDepartmentForm;
		}

		function openSelect() {
			vm.officeWorkerNumber = 0;
			vm.departmentWorkerNumber = 0;
			vm.showODInformation = false;
			vm.showEditOfficeForm = false;
			vm.showEditDepartmentForm = false;
			vm.showSaveOfficeForm = false;
			vm.showSaveDepartmentForm = false;
		}

		function setDepartments() {
			if (!(typeof vm.departments) || (vm.departments.length !== 0)) {
				vm.departmentsForOffice = [];
				vm.selectedDepartmentName = "";

				for (var i = 0, len = vm.offices.length; i < len; i++) {
					if(vm.offices[i].name === vm.selectedOfficeName) {
						vm.selectedOffice = vm.offices[i];
						vm.indexOfSelectedOffice = i;
						break;
					}
				}
				if(!(typeof vm.selectedOffice) || (vm.selectedOffice !== "")) {
					for (var i = 0, len = vm.departments.length; i < len; i++) {
						if (vm.departments[i].officeId === vm.selectedOffice.id) {
							vm.officeWorkerNumber += vm.departments[i].workerNumber;
							vm.departmentsForOffice.push(vm.departments[i]);
						}
					}
				}
			}
		}

		function listInformation() {
			for (var i = 0, len = vm.departments.length; i < len; i++) {
				if(vm.departments[i].name === vm.selectedDepartmentName) {
					vm.selectedDepartment = vm.departments[i];
					vm.showODInformation = true;
					vm.indexOfSelectedDepartment = i;
					break;
				}
			}
			vm.officeName = vm.selectedOfficeName;
			vm.officeAddress = vm.selectedOffice.address;
			vm.officeDateOfFoundtation = vm.selectedOffice.dateOfFoundtation;
			vm.officeDateOfFoundtationForEdit = moment(vm.selectedOffice.dateOfFoundtation, 'YYYY.MM.DD', false).toDate();

			vm.departmentName = vm.selectedDepartmentName;
			vm.departmentDateOfFoundtation = vm.selectedDepartment.dateOfFoundtation;
			vm.departmentDateOfFoundtationForEdit = moment(vm.selectedDepartment.dateOfFoundtation, 'YYYY.MM.DD', false).toDate();
			vm.departmentWorkerNumber = vm.selectedDepartment.workerNumber;
		}

		function editOffice() {
			var newDate = moment(vm.officeDateOfFoundtationForEdit).format('YYYY.MM.DD');
			AdditionService.editOffice(vm.selectedOffice.id, vm.officeName, vm.officeAddress, newDate).then(
				function(result) {
					StatusLogService.showStatusLog(result, 'Edit Office!');
					if (result === 1) {
						vm.offices[vm.indexOfSelectedOffice].name = vm.officeName;
						vm.offices[vm.indexOfSelectedOffice].address = vm.officeAddress;
						vm.offices[vm.indexOfSelectedOffice].dateOfFoundtation = newDate;
						vm.officeDateOfFoundtation = newDate;
					}
				},
				function(error) {
				}
			);
		}

		function editDepartment() {
			var newDate = moment(vm.departmentDateOfFoundtationForEdit).format('YYYY.MM.DD');
			AdditionService.editDepartment(vm.selectedDepartment.id, vm.departmentName, newDate, vm.selectedDepartment.officeId).then(
				function(result) {
					StatusLogService.showStatusLog(result, 'Edit Department!');
					if (result === 1) {
						vm.departments[vm.indexOfSelectedDepartment].name = vm.departmentName;
						vm.departments[vm.indexOfSelectedDepartment].dateOfFoundtation = newDate;
						vm.departmentDateOfFoundtation = newDate;
					}
				},
				function(error) {
				}
			);
		}

    }
})();
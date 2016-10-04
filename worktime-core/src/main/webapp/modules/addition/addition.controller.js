(function() {
	'use strict';

	angular
		.module('Addition')
		.controller('AdditionController', AdditionController);

	AdditionController.$inject = ['$rootScope', '$mdDialog', 'AdditionService', 'StatusLogService']

    function AdditionController($rootScope, $mdDialog, AdditionService, StatusLogService) {

		var vm = this;
		//Bindable variables
		vm.departmentsForOffice = [];
		vm.departments = [];
		vm.offices = [];
		vm.departmentsForOffice = [];
		vm.indexOfSelectedOffice = -1;
		vm.indexOfSelectedDepartment = -1;
		vm.selectedDepartmentName = "";
		vm.selectedDepartment = "";
		vm.selectedOfficeName = "";
		vm.selectedOffice = {
			id: -1,
			name: "",
			address: "",
			dateOfFoundtation: ""
		};
		vm.showODInformation = false;
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
		vm.openSaveOffice = openSaveOffice;
		vm.openSaveDepartment = openSaveDepartment;
		vm.openSelect = openSelect;
		vm.setDepartments = setDepartments;
		vm.listInformation = listInformation;
		vm.editDepartment = editDepartment;
		vm.editOffice = editOffice;

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

		function openSaveOffice() {
			vm.showSaveOfficeForm = !vm.showSaveOfficeForm;
		}

		function openSaveDepartment() {
			vm.showSaveDepartmentForm = !vm.showSaveDepartmentForm;
		}

		function openSelect() {
			vm.officeWorkerNumber = 0;
			vm.departmentWorkerNumber = 0;
			vm.showODInformation = false;
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
						$rootScope.addition = {
							indexOfSelectedOffice: i
						}
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

		function editOffice(event) {
			$rootScope.selectedOffice = vm.selectedOffice;
			$mdDialog.show({
				templateUrl: 'modules/addition/office-edit-form/office-edit-form.html',
				clickOutsideToClose: true,
				bindToController: true,
				controller: 'AdditionOfficeEditFormController',
				parent: angular.element(document.body),
				targetEvent: event
			}).then(function(answer) {
				vm.offices[vm.indexOfSelectedOffice].name = answer.name;
				vm.offices[vm.indexOfSelectedOffice].address = answer.address;
				vm.offices[vm.indexOfSelectedOffice].dateOfFoundtation = answer.dateOfFoundtation;
				vm.officeDateOfFoundtationForEdit = answer.dateOfFoundtationStr;
				StatusLogService.showStatusLog(answer.status, 'Edit Office');
			}, function() {
			});
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
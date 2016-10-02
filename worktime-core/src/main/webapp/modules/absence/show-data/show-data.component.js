(function() {
	'use strict';

	const templateLoc = ['modules',
						 'absence',
						 'show-data',
						 'show-data.html'].join('/');

	angular
		.module('Absence')
		.component('absenceShowData', Component());

	function Component() {
		return {
			templateUrl: templateLoc,
			controller: Controller,
			controllerAs: 'vm'
		}
	}

	Controller.$inject = ['$rootScope', 'AbsenceService'];

	function Controller($rootScope, AbsenceService) {

		var vm = this;
		//Bindable variables
		vm.absenceDatas = [];
		//Bindable functions
		vm.getAbsenceDatas = getAbsenceDatas;

		activate();
		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			if((typeof vm.absenceDatas || vm.absenceDatas.length === 0)) {
				getAbsenceDatas();
			}
		}

		function getAbsenceDatas() {
			AbsenceService.getAbsenceData($rootScope.userData.workerId).then(
				function(result) {
					vm.absenceDatas = result;
				},
				function(error) {
				}
			);
		}
	}
})();
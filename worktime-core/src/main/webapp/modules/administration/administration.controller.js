'use strict';

angular.module('Administration')
.controller('AdministrationController', ['$scope', '$rootScope', 'AdministrationService',
    function ($scope, $rootScope, AdministrationService) {
		$scope.dateFilters = ["All", "This Week", "Last Week", "This Month", "This Year"];
		$scope.selectedDateFilterString = "All";
		$scope.selectedDateFilter = "All";

		$scope.employees = [];
		$scope.employeesRaw = [];
		$scope.selectedEmployee = "";
		$scope.employeeWorklogs = [];
		$scope.emptyEmployeeList = false;
		$scope.emptyWorklogList = false;
		
		$scope.init = function () {
			AdministrationService.GetEmployees($rootScope.userData.workerId).then(
					function( result ){
						$scope.employeesRaw = result;
						if($scope.employeesRaw.length > 0) {
							$scope.employeesRaw.forEach(function(employee) {
								$scope.employees.push(employee.firstName + ' ' + employee.lastName); 
							});
							$scope.emptyEmployeeList = false;
						} else {
							$scope.emptyEmployeeList = true;
						}
					},
					function( error ){
					}
				);
		};
		$scope.filter = function () {
			var splitted = $scope.selectedEmployee.split(" ")
			AdministrationService.GetWorklogsByEmployee(splitted[0], splitted[1]).then(
					function( result ){
						$scope.employeeWorklogs = result;
						if($scope.employeeWorklogs.length > 0) {
							$scope.emptyWorklogList = false;
						} else {
							$scope.emptyWorklogList = true;
						}
					},
					function( error ){
					}
				);
		};
		$scope.dateFilterFirst = function() {
			if( selectedDateFilterString == "This Week" ){
				moment.localeData('en-us').firstDayOfWeek();
			}
		};

		$scope.sortType = "BeginDate";
		$scope.sortReverse = false;
		$scope.searchQuery = "";
		$scope.showDownCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && !$scope.sortReverse);
		};
		$scope.showUpCaret = function(tableHeader) {
			return ($scope.sortType == tableHeader && $scope.sortReverse);
		};
		$scope.setSearchTypeOrReverse = function(tableHeader) {
			if($scope.sortType == tableHeader){
				$scope.sortReverse = !$scope.sortReverse;
			} else {
				$scope.sortType = tableHeader;
			}
		};
    }]);
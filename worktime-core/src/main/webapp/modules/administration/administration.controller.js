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
		
		$scope.init = function () {
			AdministrationService.GetEmployees($rootScope.userData.workerId).then(
					function( result ){
						$scope.employeesRaw = result;
						$scope.employeesRaw.forEach(function(employee) {
							$scope.employees.push(employee.firstName + ' ' + employee.lastName); 
						});
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
					},
					function( error ){
						
					}
				);
		};
		$scope.dateFilterFirst = function() {
			if( selectedDateFilterString == "This Week" ){
				moment.localeData('en-us').firstDayOfWeek();
			}
		}
    }]);
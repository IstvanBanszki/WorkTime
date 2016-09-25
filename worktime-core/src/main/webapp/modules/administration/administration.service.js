(function() {
	'use strict';

	angular
		.module("Administration")
		.factory('AdministrationService', AdministrationService);

	AdministrationService.$inject =	['$http', '$rootScope', '$q'] 
	
	function AdministrationService($http, $rootScope, $q) {

		return {
			getWorklogsByEmployee  : getWorklogsByEmployee,
			getAbsencesByEmployee  : getAbsencesByEmployee,
			acceptEmployeeAbsence  : acceptEmployeeAbsence,
			getEmployees 		   : getEmployees,
			editEmployeeWorkerData : editEmployeeWorkerData,
			getEmployeeWorkerData  : getEmployeeWorkerData,
			exportEmployeeWorklogs : exportEmployeeWorklogs,
			exportEmployeeAbsences : exportEmployeeAbsences
		};

		function getWorklogsByEmployee(employeeId, dateFilter, showDailyWorkhours) {
			var deferred = $q.defer();
			return $http({
				method : "POST",
				url : "/api/administration/v1/worklog/"+employeeId,
				headers : {
					'Content-Type': 'application/json'
				},
				data: {
					'dateFilter': dateFilter,
					'showDailyWorkhours': showDailyWorkhours
				}
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;

				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function getAbsencesByEmployee(employeeId, dateFilter, listNotApproved) {
			var deferred = $q.defer();
			return $http({
				method : "POST",
				url : "/api/administration/v1/absence/"+employeeId,
				headers : {
					'Content-Type': 'application/json'
				},
				data: {
					'dateFilter': dateFilter,
					'notApprove': listNotApproved
				}
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;

				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function getEmployees(superiorWorkerId) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/administration/v1/employee/"+superiorWorkerId,
				headers : {
					'Content-Type': 'application/json'
				}
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;

				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function acceptEmployeeAbsence(absenceId) {
			var deferred = $q.defer();
			return $http({
				method : "POST",
				url : "/api/administration/v1/approve/"+absenceId,
				headers : {
					'Content-Type': 'application/json'
				}
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;

				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function editEmployeeWorkerData(firstName, lastName, position, emailAddress, dailyWorkHourTotal, employeeId) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : '/api/administration/v1/workerData/'+employeeId,
				headers : {
					'Content-Type': 'application/json'
				},
				data: {
					'firstName': firstName,
					'lastName': lastName,
					'position': position,
					'emailAddress': emailAddress,
					'dailyWorkHourTotal': dailyWorkHourTotal
				}
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;

				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function getEmployeeWorkerData(employeeId) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/administration/v1/workerData/"+employeeId,
				headers : {
					'Content-Type': 'application/json'
				}
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;

				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function exportEmployeeWorklogs(employeeId, type, dateFilter, showDailyWorkhours) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/administration/v1/worklog/"+employeeId+'/'+dateFilter+'/'+showDailyWorkhours+'/'+type+'/export',
				headers : {
					'Content-Type': 'application/json'
				},
				responseType: 'arraybuffer'
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;

				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function exportEmployeeAbsences(employeeId, type, dateFilter, showNotApprove) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/administration/v1/absence/"+employeeId+'/'+dateFilter+'/'+showNotApprove+'/'+type+'/export',
				headers : {
					'Content-Type': 'application/json'
				},
				responseType: 'arraybuffer'
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;

				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
	}
})();
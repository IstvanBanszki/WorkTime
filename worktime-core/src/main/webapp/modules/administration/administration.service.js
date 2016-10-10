(function() {
	'use strict';

	angular
		.module("Administration")
		.factory('AdministrationService', Service);

	Service.$inject = ['$http', '$rootScope', '$q'] 
	
	function Service($http, $rootScope, $q) {

		return {
			getWorklogsByEmployee  : getWorklogsByEmployee,
			getAbsencesByEmployee  : getAbsencesByEmployee,
			acceptEmployeeAbsence  : acceptEmployeeAbsence,
			getEmployees 		   : getEmployees,
			editEmployeeWorkerData : editEmployeeWorkerData,
			getEmployeeWorkerData  : getEmployeeWorkerData,
			exportEmployeeWorklogs : exportEmployeeWorklogs,
			exportEmployeeAbsences : exportEmployeeAbsences,
			updateWorklogNote	   : updateWorklogNote,
			updateAbsenceNote	   : updateAbsenceNote
		};

		function getWorklogsByEmployee(employeeId, dateFilter, showDailyWorkhours) {
			var deferred = $q.defer();
			return $http({
				method : "POST",
				url : "/api/administration/v1/employees/"+employeeId+"/worklog",
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
				url : "/api/administration/v1/employees/"+employeeId+"/absence",
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
				url : "/api/administration/v1/superiors/"+superiorWorkerId,
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
				url : "/api/administration/v1/absences/"+absenceId+"/approve",
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
				url : '/api/administration/v1/employees/'+employeeId+"/workerData",
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
				url : "/api/administration/v1/employees/"+employeeId+"/workerData",
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

		function updateWorklogNote(worklogId, note) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : "/api/administration/v1/worklogs/"+worklogId,
				headers : {
					'Content-Type': 'application/json'
				},
				data: {
					'note': note
				}
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;

				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		
		function updateAbsenceNote(absenceId, note) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : "/api/administration/v1/absences/"+absenceId,
				headers : {
					'Content-Type': 'application/json'
				},
				data: {
					'note': note
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
				url : "/api/administration/v1/employees/"+employeeId+'/dates/'+dateFilter+'/'+showDailyWorkhours+'/types/'+type+'/worklog/export',
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
				url : "/api/administration/v1/employees/"+employeeId+'/dates/'+dateFilter+'/'+showNotApprove+'/types/'+type+'/absence/export',
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
(function() {
	'use strict';

	angular
		.module("Absence")
		.factory('AbsenceService', Service);

	Service.$inject = ['$http', '$rootScope', '$q'];

	function Service($http, $rootScope, $q) {

		return {
			addAbsence	   : addAbsence,
			getAbsence	   : getAbsence,
			getAbsenceData : getAbsenceData,
			deleteAbsence  : deleteAbsence,
			editAbsence    : editAbsence,
			exportAbsence  : exportAbsence
		};

		function addAbsence(beginDate, endDate, workerId, absenceType) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : "/api/absence/v1/workers/"+workerId+"/absence",
				headers : {
					'Content-Type': 'application/json'
				},
				data: {
					'beginDate': beginDate, 
					'endDate': endDate,
					'absenceType': absenceType
				}
			}).then(function successCallback(response) {
				
					deferred.resolve(response.data);
					return deferred.promise;
					
				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function getAbsence(workerId, dateFilter) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/absence/v1/workers/"+workerId+"/dates/"+dateFilter+"/absence",
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
		function getAbsenceData(workerId) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/absence/v1/workers/"+workerId+"/absenceData",
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
		function deleteAbsence(absenceId) {
			var deferred = $q.defer();
			return $http({
				method : "DELETE",
				url : "/api/absence/v1/absences/"+absenceId,
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
		function editAbsence(absenceId, beginDate, endDate, absenceType) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : "/api/absence/v1/absences/"+absenceId+"/edit",
				headers : {
					'Content-Type': 'application/json'
				},
				data: {
					'beginDate': beginDate, 
					'endDate': endDate,
					'absenceType': absenceType
				}
			}).then(function successCallback(response) {
				
					deferred.resolve(response.data);
					return deferred.promise;
					
				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function exportAbsence(workerId, dateFilter, excelType) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/absence/v1/workers/"+workerId+'/dates/'+dateFilter+'/types/'+excelType+'/export',
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
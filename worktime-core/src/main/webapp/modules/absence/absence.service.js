'use strict';

angular.module("Absence")
.factory('AbsenceService', ['$http', '$rootScope', '$q', function AbsenceServiceFactory($http, $rootScope, $q){
	var service = {};
	service.AddAbsence = function(beginDate, endDate, workerId, absenceType) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : "/api/absence/v1/workerId/"+workerId,
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
	service.GetAbsence = function(workerId, dateFilter) {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/absence/v1/workerId/"+workerId+'/dateFilter/'+dateFilter,
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
	service.GetAbsenceData = function(workerId) {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/absencedata/v1/workerId/"+workerId,
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
	service.DeleteAbsence = function(id) {
		var deferred = $q.defer();
		return $http({
			method : "DELETE",
			url : "/api/absence/v1/id/"+id,
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
	service.EditAbsence = function(id, beginDate, endDate, absenceType) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : "/api/absence/v1/id/"+id,
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
	service.ExportAbsence = function(workerId, dateFilter, excelType) {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/absence/v1/workerId/"+workerId+'/dateFilter/'+dateFilter+'/type/'+excelType+'/export',
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
	return service;
}])
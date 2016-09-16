'use strict';

angular.module("Worklog")
.factory('WorklogService', ['$http', '$rootScope', '$q', function WorklogServiceFactory($http, $rootScope, $q){
	var service = {};
	service.AddWorklog = function(beginDate, workHour, workerId) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : "/api/worklog/v1/workerId/"+workerId,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'beginDate': beginDate,
				'workHour': workHour
			}
		}).then(function successCallback(response) {
			
				deferred.resolve(response.data);
				return deferred.promise;
				
			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.GetWorklog = function(workerId, dateFilter) {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/worklog/v1/workerId/"+workerId+'/dateFilter/'+dateFilter,
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
	service.DeleteWorklog = function(id) {
		var deferred = $q.defer();
		return $http({
			method : "DELETE",
			url : "/api/worklog/v1/id/"+id,
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
	service.EditWorklog = function(id, beginDate, workHour) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : "/api/worklog/v1/id/"+id,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'beginDate': beginDate,
				'workHour': workHour
			}
		}).then(function successCallback(response) {
			
				deferred.resolve(response.data);
				return deferred.promise;
				
			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.ExportWorklog = function(workerId, dateFilter, excelType) {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/worklog/v1/workerId/"+workerId+'/dateFilter/'+dateFilter+'/type/'+excelType+'/export',
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
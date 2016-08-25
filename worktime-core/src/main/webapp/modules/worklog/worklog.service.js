'use strict';

angular.module("Worklog")
.factory('WorklogService', ['$http', '$rootScope', '$q', function WorklogServiceFactory($http, $rootScope, $q){
	var service = {};
	service.AddWorklog = function(begin, workHour, workerId) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : "/api/worklog/v1/workerId/"+workerId,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'begin': begin,
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
	service.GetWorklog = function(workerId) {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/worklog/v1/workerId/"+workerId,
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
	return service;
}])
'use strict';

angular.module("Worklog")
.factory('WorklogService', ['$http', '$rootScope', '$q', function WorklogServiceFactory($http, $rootScope, $q){
	var service = {};
	service.AddWorklog = function(description, begin, end, workerId) {
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/rest/worklog/v1/saveworklog",
			headers : {
				'Content-Type': 'application/json'
			},
			data: { 
				'description': description,
				'begin': begin, 
				'end': end, 
				'workerId': workerId
			}
		}).then(function successCallback(response) {
			
				deferred.resolve(response.data.status);
				return deferred.promise;
				
			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.GetWorklog = function(workerId) {
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/rest/worklog/v1/getworklog/"+workerId,
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
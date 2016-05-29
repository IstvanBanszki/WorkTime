'use strict';

angular.module("Worklog")
.factory('WorklogService', ['$http', '$rootScope', '$q', function WorklogServiceFactory($http, $rootScope, $q){
	var service = {};	
	service.AddWorklog = function(description, begin, end, workerId) {
		var deferred = $q.defer();
		var worklogData = {};
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
			
				worklogData.result = response.data.result;
				deferred.resolve(worklogData);
				return deferred.promise;
				
			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	return service;
}])
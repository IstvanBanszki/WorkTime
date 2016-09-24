(function() {
	'use strict';

	angular
		.module("Worklog")
		.factory('WorklogService', WorklogService);

	WorklogService.$inject = ['$http', '$rootScope', '$q'];

	function WorklogService($http, $rootScope, $q) {

		var service = {
			addWorklog	  : addWorklog,
			getWorklog 	  : getWorklog,
			deleteWorklog : deleteWorklog,
			editWorklog   : editWorklog,
			exportWorklog : exportWorklog
		};
		return service;

		// *********************** //
		// Function implementation //
		// *********************** //
		function addWorklog(beginDate, workHour, workerId) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : "/api/worklog/v1/worklog/"+workerId,
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
		function getWorklog(workerId, dateFilter) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/worklog/v1/worklog/"+workerId+'/'+dateFilter,
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
		function deleteWorklog(worklogId) {
			var deferred = $q.defer();
			return $http({
				method : "DELETE",
				url : "/api/worklog/v1/worklog/"+worklogId,
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
		function editWorklog(worklogId, beginDate, workHour) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : "/api/worklog/v1/worklog/"+worklogId+"/edit",
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
		function exportWorklog(workerId, dateFilter, excelType) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/worklog/v1/worklog/"+workerId+'/'+dateFilter+'/'+excelType+'/export',
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
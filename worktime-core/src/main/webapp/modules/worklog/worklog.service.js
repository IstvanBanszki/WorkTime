(function() {
	'use strict';

	angular
		.module("Worklog")
		.factory('WorklogService', Service);

	Service.$inject = ['$http', '$rootScope', '$q'];

	function Service($http, $rootScope, $q) {

		return {
			addWorklog	  : addWorklog,
			getWorklog 	  : getWorklog,
			deleteWorklog : deleteWorklog,
			editWorklog   : editWorklog,
			exportWorklog : exportWorklog
		};

		// *********************** //
		// Function implementation //
		// *********************** //
		function addWorklog(beginDate, workHour, workerId) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : "/api/worklog/v1/workers/"+workerId,
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
				url : "/api/worklog/v1/workers/"+workerId+'/dates/'+dateFilter,
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
				url : "/api/worklog/v1/workers/"+worklogId+"/edit",
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
				url : "/api/worklog/v1/workers/"+workerId+'/dates/'+dateFilter+'/types/'+excelType+'/export',
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
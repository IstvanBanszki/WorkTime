'use strict';

angular.module("Administration")
.factory('AdministrationService', ['$http', '$rootScope', '$q', function AdministrationServiceFactory($http, $rootScope, $q) {
	var service = {};	
	service.GetWorklogsByEmployee = function(firstName, lastName) {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/administration/v1/firstName/"+firstName+"/lastName/"+firstName,
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
	service.GetEmployees = function( workerId ){
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/administration/v1/workerId/"+workerId,
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
	service.RemoveProfileData = function(){
		$rootScope.profileData= {};
	}
	return service;
}])
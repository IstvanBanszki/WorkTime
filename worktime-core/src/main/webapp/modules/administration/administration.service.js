'use strict';

angular.module("Administration")
.factory('AdministrationService', ['$http', '$rootScope', '$q', function AdministrationServiceFactory($http, $rootScope, $q) {
	var service = {};	
	service.GetWorklogsByEmployee = function(firstName, lastName) {
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/rest/worklogadministration/v1/employees/",
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'firstName': firstName, 
				'lastName': lastName
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
			method : "POST",
			url : "/rest/worklogadministration/v1/workerId/"+workerId+"/employees",
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
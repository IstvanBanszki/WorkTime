'use strict';

angular.module("Addition")
.factory('AdditionService', ['$http', '$rootScope', '$q', function AdditionServiceFactory($http, $rootScope, $q) {
	var service = {};
	service.GetOfficesWithDepartments = function() {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : '/api/addition/v1/officesWithDepartments',
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
	service.GetOffices = function() {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : '/api/addition/v1/offices',
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
	service.GetDepartments = function() {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : '/api/addition/v1/departments',
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
	service.EditOffice = function(officeId, name, address, dateOfFoundation) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/addition/v1/officeId/' + officeId,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'name': name,
				'address': address,
				'dateOfFoundation': dateOfFoundation
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;

			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.EditDepartment = function(departmentId, name, dateOfFoundation, officeId) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/addition/v1/departmentId/' + departmentId,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'name': name,
				'dateOfFoundation': dateOfFoundation,
				'officeId': officeId
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
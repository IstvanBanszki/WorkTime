'use strict';

angular.module("Addition")
.factory('AdditionService', ['$http', '$rootScope', '$q', function AdditionServiceFactory($http, $rootScope, $q) {
	var service = {};
	service.GetOffices = function() {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : '/api/addition/v1/office',
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
			url : '/api/addition/v1/department',
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
	service.EditOffice = function(officeId, name, address, dateOfFoundtation) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/addition/v1/office/' + officeId,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'name': name,
				'address': address,
				'dateOfFoundtation': dateOfFoundtation
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;

			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.EditDepartment = function(departmentId, name, dateOfFoundtation, officeId) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/addition/v1/department/' + departmentId,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'name': name,
				'dateOfFoundtation': dateOfFoundtation,
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
	service.SaveOffice = function(name, address, dateOfFoundtation) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/addition/v1/office' ,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'name': name,
				'address': address,
				'dateOfFoundtation': dateOfFoundtation
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;

			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.SaveDepartment = function(name, dateOfFoundtation, officeId) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/addition/v1/department',
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'name': name,
				'dateOfFoundtation': dateOfFoundtation,
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
	service.SaveUser = function(name, password, role) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/addition/v1/user' ,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'name': name,
				'password': password,
				'role': role
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;

			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.SaveWorker = function(firstName, lastName, gender, dateOfBirth, nationality, position, dailyWorkHourTotal, emailAddres) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/addition/v1/worker',
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'firstName': firstName,
				'lastName': lastName,
				'gender': gender,
				'dateOfBirth': dateOfBirth,
				'nationality': nationality,
				'position': position,
				'dailyWorkHourTotal': dailyWorkHourTotal,
				'emailAddres': emailAddres
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;

			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.GetSuperiors = function() {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : '/api/addition/v1/superior',
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
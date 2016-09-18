'use strict';

angular.module("Administration")
.factory('AdministrationService', ['$http', '$rootScope', '$q', function AdministrationServiceFactory($http, $rootScope, $q) {
	var service = {};
	service.GetWorklogsByEmployee = function(employeeId, dateFilter, showDailyWorkhours) {
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/api/administration/v1/worklog/"+employeeId,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'dateFilter': dateFilter,
				'showDailyWorkhours': showDailyWorkhours
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;

			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.GetAbsencesByEmployee = function(employeeId, dateFilter, listNotApproved) {
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/api/administration/v1/absence/"+employeeId,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'dateFilter': dateFilter,
				'notApprove': listNotApproved
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;

			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.GetEmployees = function(superiorWorkerId){
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/administration/v1/employee/"+superiorWorkerId,
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
	service.AcceptEmployeeAbsence = function(absenceId) {
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/api/administration/v1/approve/"+absenceId,
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
	service.EditEmployeeWorkerData = function(firstName, lastName, position, emailAddress, dailyWorkHourTotal, employeeId) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/administration/v1/workerData/'+employeeId,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'firstName': firstName,
				'lastName': lastName,
				'position': position,
				'emailAddress': emailAddress,
				'dailyWorkHourTotal': dailyWorkHourTotal
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;

			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}		
	service.GetEmployeeWorkerData = function(employeeId){
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/administration/v1/workerData/"+employeeId,
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
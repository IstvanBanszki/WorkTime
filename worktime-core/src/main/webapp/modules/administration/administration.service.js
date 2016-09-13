'use strict';

angular.module("Administration")
.factory('AdministrationService', ['$http', '$rootScope', '$q', function AdministrationServiceFactory($http, $rootScope, $q) {
	var service = {};
	service.GetWorklogsByEmployee = function(firstName, lastName, dateFilter, showDailyWorkhours) {
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/api/administration/v1/firstName/"+firstName+"/lastName/"+lastName+'/worklog',
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
	service.GetAbsencesByEmployee = function(firstName, lastName, dateFilter, listNotApproved) {
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/api/administration/v1/firstName/"+firstName+"/lastName/"+lastName+'/absence',
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
	service.GetEmployees = function(workerId){
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
	service.AcceptEmployeeAbsence = function(absenceId) {
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/api/administration/v1/approve/absenceId/"+absenceId,
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
	service.EditEmployeeWorkerData = function(firstName, lastName, position, emailAddress, dailyWorkHourTotal, workerId) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : '/api/administration/v1/workerData/workerId/'+workerId,
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
	service.GetEmployeeWorkerData = function(workerId){
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/administration/v1/workerData/workerId/"+workerId,
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
(function() {
	'use strict';

	angular
		.module("Addition")
		.factory('AdditionService', Service);

	Service.$inject = ['$http', '$rootScope', '$q'];

	function Service($http, $rootScope, $q) {

		return {
			getOffices 	   : getOffices,
			getDepartments : getDepartments,
			editOffice 	   : editOffice,
			editDepartment : editDepartment,
			saveOffice 	   : saveOffice,
			saveDepartment : saveDepartment,
			saveUser 	   : saveUser,
			saveWorker 	   : saveWorker,
			getSuperiors   : getSuperiors
		};

		function getOffices() {
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
		function getDepartments() {
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
		function editOffice(officeId, name, address, dateOfFoundtation) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : '/api/addition/v1/offices/' + officeId,
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
		function editDepartment(departmentId, name, dateOfFoundtation, officeId) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : '/api/addition/v1/departments/' + departmentId,
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
		function saveOffice(name, address, dateOfFoundtation) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : '/api/addition/v1/offices' ,
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
		function saveDepartment(name, dateOfFoundtation, officeId) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : '/api/addition/v1/departments',
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
		function saveUser(name, password, role) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : '/api/addition/v1/users' ,
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
		function saveWorker(firstName, lastName, gender, dateOfBirth, nationality, position, dailyWorkHourTotal, emailAddres) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : '/api/addition/v1/workers',
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
		function getSuperiors() {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : '/api/addition/v1/superiors',
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
	}
})();
'use strict';

angular.module("Profile")
.factory('ProfileService', ['$http', '$rootScope', '$q', function ProfileServiceFactory($http, $rootScope, $q) {
	var service = {};
	service.Profile = function(workerId) {
		var deferred = $q.defer();
		return $http({
			method : "GET",
			url : "/api/profile/v1/workerId/"+workerId,
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
	service.ChangePassword = function(loginName, oldPassword, newPassword) {
		var deferred = $q.defer();
		return $http({
			method : "PUT",
			url : "/api/login/v1/loginName/"+loginName,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'oldPassword': oldPassword, 
				'newPassword': newPassword
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;
				
			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.SetProfileData = function(parameter) {
		$rootScope.profileData = {
			firstName: parameter.firstName,
			lastName : parameter.lastName,
			gender : parameter.gender,
			dateOfBirth : parameter.dateOfBirth,
			nationality : parameter.nationality,
			position : parameter.position,
			dailyWorkHourTotal : parameter.dailyWorkHourTotal,
			departmentName : parameter.departmentName,
			officeName : parameter.officeName
		};
	}
	service.RemoveProfileData = function(){
		$rootScope.profileData= {};
	}
	return service;
}])
'use strict';

angular.module("Profile")
.factory('ProfileService', ['$http', '$rootScope', '$q', function ProfileServiceFactory($http, $rootScope, $q){
	var service = {};	
	service.Profile = function( workerId ){
		var deferred = $q.defer();
		var profileData = {};
		return $http({
			method : "POST",
			url : "/rest/profile/v1/getprofile/"+workerId,
			headers : {
				'Content-Type': 'application/json'
			}
		}).then(function successCallback(response) {
			
				profileData.dateOfRegistration = response.data.dateOfRegistration;

				profileData.firstName = response.data.firstName;
				profileData.lastName = response.data.lastName;
				profileData.gender = response.data.gender;
				profileData.dateOfBirth = response.data.dateOfBirth;
				profileData.nationality = response.data.nationality;

				profileData.position = response.data.position;
				profileData.emailAddress = response.data.emailAddress;
				profileData.dailyWorkHourTotal = response.data.dailyWorkHourTotal;
				profileData.departmentName = response.data.departmentName;
				profileData.officeName = response.data.officeName;

				deferred.resolve(profileData);
				return deferred.promise;
				
			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.SetProfileData = function( parameter ){
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
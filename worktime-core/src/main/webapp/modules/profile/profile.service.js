(function() {
	'use strict';

	angular
		.module("Profile")
		.factory('ProfileService', ProfileService);

	ProfileService.$inject = ['$http', '$rootScope', '$q']; 

	function ProfileService($http, $rootScope, $q) {

		var service = {
			Profile			  : Profile,
			ChangePassword 	  : ChangePassword,
			SetProfileData 	  : SetProfileData,
			RemoveProfileData : RemoveProfileData
		};
		return service;

		// *********************** //
		// Function implementation //
		// *********************** //
		function Profile(workerId) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : "/api/profile/v1/"+workerId,
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
		function ChangePassword(loginName, oldPassword, newPassword) {
			var deferred = $q.defer();
			return $http({
				method : "PUT",
				url : "/api/login/v1/"+loginName,
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
		function SetProfileData(parameter) {
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
		function RemoveProfileData() {
			$rootScope.profileData= {};
		}
	}
})();
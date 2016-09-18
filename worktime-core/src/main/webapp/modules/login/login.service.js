'use strict';

angular.module("Login")
.factory('LoginService', ['$http', '$cookies', '$rootScope', '$q', function LoginServiceFactory($http, $cookies, $rootScope, $q){
	var service = {};	
	service.Login = function( loginName, password ){
		var deferred = $q.defer();
		return $http({
			method : "POST",
			url : "/api/login/v1/"+loginName,
			headers : {
				'Content-Type': 'application/json'
			},
			data: {
				'password': password
			}
		}).then(function successCallback(response) {

				deferred.resolve(response.data);
				return deferred.promise;
				
			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.SetUserData = function( parameter, loginName, password ){
		var userDataCoded = btoa(loginName+':'+password+':'+parameter.workerId+':'+parameter.roleName);
		$rootScope.userData = {
			loginName: loginName,
			password : password,
			workerId : parameter.workerId,
			roleName : parameter.roleName
		};
		$http.defaults.headers.common['Authorization'] = $rootScope.userData;
		$cookies.putObject('data', $rootScope.userData);
	}
	service.RemoveUserData = function(){
		$rootScope.userData= {};
        $http.defaults.headers.common.Authorization = {};
		$cookies.remove('data');
	}
	return service;
}])
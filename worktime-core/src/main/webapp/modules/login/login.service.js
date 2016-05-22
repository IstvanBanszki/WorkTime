'use strict';

angular.module("Login")
.factory('LoginService', ['$http', '$cookies', '$rootScope', '$q', function LoginServiceFactory($http, $cookies, $rootScope, $q){
	var service = {};	
	service.Login = function( loginName, password ){
		var deferred = $q.defer();
		var userData = {};
		return $http({
			method : "POST",
			url : "/rest/login/v1/getlogin",
			headers : {
				'Content-Type': 'application/json'
			},
			data: { 
				'loginName': loginName, 
				'password': password
			}
		}).then(function successCallback(response) {
			
				userData.workerId  = response.data.workerId;
				userData.roleName  = response.data.roleName;
				userData.loginName = loginName;
				userData.password  = password;

				deferred.resolve(userData);
				return deferred.promise;
				
			}, function errorCallback(response) {

				deferred.reject(response);
				return deferred.promise;
			});
	}
	service.SetUserData = function( parameter ){
		var userDataCoded = btoa(parameter.loginName+':'+parameter.password+':'+parameter.workerId+':'+parameter.roleName);
		$rootScope.userData = {
			loginName: parameter.loginName,
			password : parameter.password,
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
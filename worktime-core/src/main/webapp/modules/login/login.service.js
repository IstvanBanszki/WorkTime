'use strict';

angular.module("Login")
.factory('LoginService', ['$http', '$cookies', '$rootScope', function LoginServiceFactory($http, $cookies, $rootScope){
	var service = {};	
	service.Login = function( loginName, password ){
		var userData = {}
		$http({
			method : "POST",
			url : "/rest/login/v1/getlogin",
			headers : {
				'Content-Type': 'application/json'
			},
			data: { 
				'loginName': loginName, 
				'password': password
			}
		}).then(function (response) {
			if( response.data ){
				userData.workerId = response.data.workerId;
				userData.roleName = response.data.roleName;
				userData.loginName = loginName;
				userData.password  = password;
			} else {
				userData.workerId = {};
				userData.roleName = {};				
			}
			userData.statuscode = response.status;
			userData.statustext = response.statustext; 
		});
		return userData;
	}
	service.SetUserData = function( parameter ){
		var userDataCoded = btoa(parameter.loginName+':'+parameter.password);
		$rootScope.userData = {
			loginName: parameter.loginName,
			workerId : parameter.workerId,
			roleName : parameter.roleName,
			secret	 : userDataCoded
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
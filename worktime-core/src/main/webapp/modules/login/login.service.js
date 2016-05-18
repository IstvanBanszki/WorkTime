angular.module("Login")
.factory('LoginService', ['$http', '$cookieStore', '$rootScope', function LoginServiceFactory($http, $cookieStore, $rootScope){
	var service = {};	
	service.Login = function( loginName, password ){
		var response = {}
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
				response.workerId = response.data.workerId;
				response.roleName = response.data.roleName;
				response.loginName = response.data.loginName;
			} else {
				response.workerId = {};
				response.roleName = {};
				response.loginName = {};				
			}
			response.statuscode = response.status;
			response.statustext = response.statustext; 
		});
		return response;
	}
	service.SetUserData = function( loginName, password, workerId, roleName ){
		$rootScope.userData = {
			loginName: loginName,
			password: password,
			workerId: workerId,
			roleName: roleName
		};
		var userDataCoded = btoa(loginName+':'+password);
		$http.defaults.headers.common['Authorization'] = userDataCoded;
		$cookieStore.put('globals', userDataCoded);
	}
	service.ClearUserData = function(){
		$rootScope.userData= {};
        $http.defaults.headers.common.Authorization = {};
		$cookieStore.remove('globals');
	}
	return service;
}])
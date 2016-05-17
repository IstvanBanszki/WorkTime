angular.module("Login")
.factory('LoginService', ['$http', function LoginServiceFactory($http){
	var service = {};	
	service.Login = function( loginName, password ){
		//var myHeaders = (username || password) ? {authorization : "Basic "
		//	+ btoa(username + ":" + password),
		//	'Content-Type': 'application/json'
		//} : {};
		var response = {}
		$http({
			method : "POST",
			url : "/rest/login/v1/getlogin",
			headers : {
				'Authorization' : "Basic "+ btoa(loginName + ":" + password),
				'Content-Type': 'application/json'
			},
			data: { 
				'loginName': loginName, 
				'password': password
			}
		}).then(function (response) {
			response.content = response.data;
			response.statuscode = response.status;
			response.statustext = response.statustext; 
		});
		return response;
	}
	return service;
}])
angular.module("Login")
.factory('LoginService', ['$http', function LoginServiceFactory($http){
	var service = {};	
	service.Login = function (username, password){
		//var myHeaders = (username || password) ? {authorization : "Basic "
		//	+ btoa(username + ":" + password),
		//	'Content-Type': 'application/json'
		//} : {};
		var response = {}
		$http({
			method : "POST",
			url : "localhost:8080/rest/login/v1/getlogin",
			headers : {authorization : "Basic "+ btoa(username + ":" + password),
				'Content-Type': 'application/json'
			},
			data: { 
				'username': username, 
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
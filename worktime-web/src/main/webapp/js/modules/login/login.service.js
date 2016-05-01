angular.module("Login")
.factory('LoginService', ['$http', function LoginServiceFactory($http){
	var service = {};	
	service.Login = function (username, password){
		var response = {}
		$http({
			method : "POST",
			url : "localhost:8080/loginService"
		}).then(function (response) {
			response.content = response.data;
			response.statuscode = response.status;
			response.statustext = response.statustext; 
		});
		return response;
	}
	return service;
}])
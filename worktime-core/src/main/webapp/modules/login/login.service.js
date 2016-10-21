(function() {
	'use strict';

	angular
		.module("Login")
		.factory('LoginService', Service);

	Service.$inject = ['$http', '$cookies', '$rootScope', '$q']; 
	
	function Service($http, $cookies, $rootScope, $q) {

		return {
			getLogin	   : getLogin,
			getToken	   : getToken,
			setUserData    : setUserData,
			removeUserData : removeUserData
		};

		// *********************** //
		// Function implementation //
		// *********************** //
		function getLogin(loginName, password) {
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
		function getToken(loginName, role) {
			var deferred = $q.defer();
			return $http({
				method : "GET",
				url : '/api/login/v1/tokens/loginNames/'+loginName+'/roles/'+role,
				headers : {
					'Content-Type': 'application/json;'
				}
			}).then(function successCallback(response) {

					deferred.resolve(response.data);
					return deferred.promise;
					
				}, function errorCallback(response) {

					deferred.reject(response);
					return deferred.promise;
				});
		}
		function setUserData(parameter, token, loginName, password) {
			var userDataCoded = 'Basic ' + token;
			$rootScope.userData = {
				loginName: loginName,
				password : password,
				workerId : parameter.workerId,
				roleName : parameter.roleName,
				secret   : userDataCoded
			};
			$http.defaults.headers.common.Authorization = userDataCoded;
			$cookies.putObject('data', $rootScope.userData);
		}
		function removeUserData() {
			$rootScope.userData= {};
			$http.defaults.headers.common.Authorization = {};
			$cookies.remove('data');
		}
	}
})();
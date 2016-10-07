(function() {
	'use strict';

	angular
		.module("Login")
		.factory('LoginService', LoginService);

	LoginService.$inject = ['$http', '$cookies', '$rootScope', '$q']; 
	
	function LoginService($http, $cookies, $rootScope, $q) {

		return {
			login		   : login,
			setUserData    : setUserData,
			removeUserData : removeUserData
		};

		// *********************** //
		// Function implementation //
		// *********************** //
		function login(loginName, password) {
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
		function setUserData(parameter, loginName, password) {
			var userDataCoded = btoa(loginName+':'+password+':'+parameter.workerId+':'+parameter.roleName);
			$rootScope.userData = {
				loginName: loginName,
				password : password,
				workerId : parameter.workerId,
				roleName : parameter.roleName
			};
			$http.defaults.headers.common['Authorization'] = userDataCoded;
			$cookies.putObject('data', $rootScope.userData);
		}
		function removeUserData() {
			$rootScope.userData= {};
			$http.defaults.headers.common.Authorization = {};
			$cookies.remove('data');
		}
	}
})();
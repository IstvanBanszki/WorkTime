(function() {
	'use strict';

	angular
		.module('Utility')
		.factory('ResponseWatcherService', ResponseWatcherService);

	ResponseWatcherService.$inject = ['$location', '$http', 'StatusLogService']
		
	function ResponseWatcherService($location, $http, StatusLogService) {

		return {
			checkHttpStatus: checkHttpStatus
		}

		function checkHttpStatus(httpStatusCode) {

			if(httpStatusCode > 399) {
				if (httpStatusCode == 401) {
					StatusLogService.showStatusLog(-1, 'Token Expired');
				} else if(httpStatusCode == 400) {
					StatusLogService.showStatusLog(-1, 'Token Invalid');
				}
				$http.defaults.headers.common.Authorization = 'Basic ';
				$location.path('/login');
			}
		}

	}
})();
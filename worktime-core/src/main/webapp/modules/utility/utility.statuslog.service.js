(function() {
	'use strict';

	angular
		.module('Utility')
		.factory('StatusLogService', StatusLogService);

	function StatusLogService() {

		var optionsOverride = 'toast-top-left';

		return {
			showStatusLog: showStatusLog
		}

		function showStatusLog(rowCount, title) {

			if (rowCount === 1) {
				toastr.success('Succesfull!', title, optionsOverride);
			} else {
				toastr.error('Error Occured!', title, optionsOverride);
			}
		}

	}
})();
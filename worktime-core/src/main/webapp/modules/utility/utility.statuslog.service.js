(function() {
	'use strict';

	angular
		.module('Utility')
		.factory('StatusLogService', StatusLogService);

	function StatusLogService() {

		var optionsOverride = {
			tapToDismiss: true,
			positionClass: 'toast-bottom-right',
			fadeIn: 300,
			fadeOut: 1000
		};

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
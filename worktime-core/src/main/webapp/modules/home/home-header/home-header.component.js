(function() {
	'use strict';

	const templateLoc = ['modules',
						 'home',
						 'home-header',
						 'home-header.html'].join('/');

	angular
		.module('Home')
		.component('homeHeader', Component());

	function Component() {
		return {
			templateUrl : templateLoc
		}
	}

})();
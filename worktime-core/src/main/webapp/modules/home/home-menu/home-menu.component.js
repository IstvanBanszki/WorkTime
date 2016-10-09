(function() {
	'use strict';

	const templateLoc = ['modules',
						 'home',
						 'home-menu',
						 'home-menu.html'].join('/');

	angular
		.module('Home')
		.component('homeMenu', Component());

	function Component() {
		return {
			templateUrl : templateLoc,
			controller  : Controller,
			controllerAs: 'vm'
		}
	}

	Controller.$inject = ['$rootScope'];

    function Controller($rootScope) {

		var vm = this;
		//Bindable variables
		vm.links = [{
			title: 'Home', url: '#home'
		}, {
			title: 'Profile', url: '#profile'
		}, {
			title: 'Worklog', url: '#worklog'
		}, {
			title: 'Absence', url: '#absence'
		}];
		vm.currentLink = '#home';
		//Bindable functions
		vm.isActiveLink = isActiveLink;
		vm.onClickLink = onClickLink;

		activate();

		// *********************** //
		// Function implementation //
		// *********************** //
		function activate() {
			if ($rootScope.userData.roleName === 'COMPANY-ADMIN-ROLE') {
				vm.links.push({ title: 'Administration', url: '#administration' });
				vm.links.push({ title: 'Addition', url: '#addition' });
			}
		}

		function isActiveLink(url) {
			return url == vm.currentLink;
		}

		function onClickLink(link) {
			vm.currentLink = link.url;
		}

    }

})();
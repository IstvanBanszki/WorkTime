(function() {
	'use strict';

	angular
		.module('Home')
		.controller('HomeController', HomeController);

	HomeController.$inject = ['$rootScope'];

    function HomeController($rootScope) {

		var vm = this;
		//Bindable variables
		vm.links = [{
			title: 'Home', url: '#home'
		}, {
			title: 'Personal', url: '#profile'
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
			if( $rootScope.userData.roleName === 'COMPANY-ADMIN-ROLE' ){
				vm.links.push({ title: 'Administration', url: '#administration' });
				vm.links.push({ title: 'Addition', url: '#addition' });
			}
		}
		function isActiveLink(url) {
			return url == vm.currentLink;
		};
		function onClickLink(link) {
			vm.currentLink = link.url;
		};
    }
})();
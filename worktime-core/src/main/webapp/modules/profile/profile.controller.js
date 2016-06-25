'use strict';

angular.module('Profile')
.controller('ProfileController', ['$scope', '$rootScope', 'ProfileService',
    function ($scope, $rootScope, ProfileService) {
		$scope.tabs = [{
            title: 'Account',
            url: 'modules/profile/profile.account.html'
        }, {
            title: 'Personal',
            url: 'modules/profile/profile.personal.html'
        }, {
            title: 'Work',
            url: 'modules/profile/profile.work.html'
		}];
		$scope.currentTab = 'modules/profile/profile.account.html';

		$scope.isActiveTab = function(tabUrl) {
			return tabUrl == $scope.currentTab;
		}
		$scope.onClickTab = function (tab) {
			$scope.currentTab = tab.url;
		}
		$scope.init = function () {
			ProfileService.Profile( $rootScope.userData.workerId ).then(
				function( result ){
					ProfileService.SetProfileData( result );
					// Account Tab - initial value
					$scope.loginName = $rootScope.userData.loginName;					
					$scope.dateOfRegistration = moment(result.dateOfRegistration).format('YYYY.MM.DD HH:mm:ss');
					$scope.role = $rootScope.userData.roleName;
					// Personal Tab - initial value
					$scope.firstName = result.firstName;
					$scope.lastName = result.lastName;
					$scope.gender = result.gender;
					$scope.dateOfBirth = moment(result.dateOfBirth).format('YYYY.MM.DD HH:mm:ss');
					$scope.nationality = result.nationality ;
					// Work Tab - initial value
					$scope.position = result.position;
					$scope.emailAddress = result.emailAddress;
					$scope.dailyWorkHourTotal = result.dailyWorkHourTotal;
					$scope.departmentName = result.departmentName;
					$scope.officeName = result.officeName;
				},
				function( error ){
					$scope.error = true;
					ProfileService.RemoveProfileData();
					$scope.errorMessage = error.status;
				}
			)
		};
    }]);
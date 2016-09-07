'use strict';

angular.module('Profile')
.controller('ProfileController', ['$scope', '$rootScope', '$mdDialog', 'ProfileService',
    function ($scope, $rootScope, $mdDialog, ProfileService) {

		$scope.oldPassword = "";
		$scope.newPassword = "";
		$scope.newPasswordSecond = "";

		$scope.changePassword = function() {
			if($scope.newPassword == $scope.newPasswordSecond) {
				ProfileService.ChangePassword($scope.userData.loginName, $scope.oldPassword, $scope.newPassword).then(
					function(result) {
						$scope.showStatus(result);
					},
					function(error) {
					}
				)
				$scope.clearPasswords();
			} else {
				$scope.showStatus(-2);
				$scope.clearPasswords();
			}
		};
		$scope.showStatus = function(result) {
			var textContent = '';
			if (result === -2) {
				textContent = 'The newly entered passwords are not the same!';
			} else if (result === 2) {
				textContent = 'Wrong old password typed!';
			} else if (result === 1) {
				textContent = 'Change is succesfull!';
			} else {
				textContent = 'Unexpected Error! Result - '+result;
			}
			alert = $mdDialog.alert({
				title: 'Password Change',
				textContent: textContent,
				clickOutsideToClose: true,
				ok: 'Close'
			});
		    $mdDialog.show(alert)
					 .finally(function() {
						alert = undefined;
					});
		};	
		$scope.clearPasswords = function() {
			$scope.oldPassword = "";
			$scope.newPassword = "";
			$scope.newPasswordSecond = "";
		};
		$scope.init = function() {
			ProfileService.Profile($rootScope.userData.workerId).then(
				function(result) {
					ProfileService.SetProfileData(result);
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
				function(error) {
					$scope.error = true;
					ProfileService.RemoveProfileData();
					$scope.errorMessage = error.status;
				}
			)
		};
    }]);
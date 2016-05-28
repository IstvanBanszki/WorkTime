'use strict';

angular.module('Profile')
.filter('birthDateFilter', function() {
		return function(msg){
			if( typeof msg !== "undefined" ){
				return msg.year+'.'+(msg.monthValue > 10 ? msg.monthValue : '0'+msg.monthValue)
				+'.'+(msg.dayOfMonth > 10 ? msg.dayOfMonth : '0'+msg.dayOfMonth);	
			} else {
				return "";
			}
		}
    })
.filter('registrationDateFilter', function() {
		return function(msg){
			if( typeof msg !== "undefined" ){
				return msg.year+'.'+(msg.monthValue > 10 ? msg.monthValue : '0'+msg.monthValue)+'.'+
				(msg.dayOfMonth > 10 ? msg.dayOfMonth : '0'+msg.dayOfMonth)+' '+msg.hour+':'+msg.minute+':'+msg.second;
			} else {
				return "";
			}
		}
    });
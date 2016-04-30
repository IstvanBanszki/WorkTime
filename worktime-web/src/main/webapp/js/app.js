(function () {

    'use strict';
    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

})();
'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'myApp.view1',
    'myApp.view2',
    'angularjs-datetime-picker',
    'myApp.version'
]).config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');

    //$routeProvider.otherwise({redirectTo: '/view1'});
}]);

angular.module('myApp').run(function($rootScope) {
    $rootScope.gmtDate = new Date('2015-01-01 00:00:00 -00:00');
});

'use strict';

var myApp = angular.module('myApp.view1', ['ngRoute']);

myApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view1', {
        templateUrl: 'view1/view1.html',
        controller: 'View1Ctrl'
    });
}]);

myApp.controller("View1Ctrl", function ($scope, $http) {

    $scope.submitForm = function () {

        if ($scope.form.$valid) {

            $http({
                method: 'POST',
                url: 'http://localhost:8080/rest/api/loan/customer',
                data: angular.toJson($scope.customer),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(success, error);
        }

        function success(response) {
            console.log('Customer successfully registered');
            clearFormData()
        }

        function error(response) {
            console.log(response.statusText);
        }

        //Clear the form
        function clearFormData() {
            alert("Customer Submitted successfully!");
            $scope.customer.firstName = '';
            $scope.customer.lastName = '';
            $scope.customer.emailAddress = '';
            $scope.customer.birthday = '';
            $scope.customer.mobilePhone = '';
            $scope.customer.address = '';
        };
    };
});

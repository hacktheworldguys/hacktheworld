'use strict';
var myApp = angular.module('myApp.view2', ['ngRoute']);

myApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view2', {
        templateUrl: 'view2/view2.html',
        controller: 'View2Ctrl'
    });
}]);

myApp.controller("View2Ctrl", function ($scope, $http) {

    $scope.loginBox = true;
    $scope.signupBox = false;

    $scope.singUpForm = function () {

        $http({
            method: 'POST',
            url: 'http://localhost:8080/rest/api/loan/admin',
            data: angular.toJson($scope.admin),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(success, error);

        function success(response) {
            console.log('Admin registered successfully!');
            clearFormData()
        };

        function error(response) {
            console.log(response.statusText);
        };

        //Clear the form
        function clearFormData() {
            alert("Admin registered successfully!");
            $scope.admin.firstName = '';
            $scope.admin.lastName = '';
            $scope.admin.emailAddress = '';
            $scope.admin.password = '';

        };
    };

    $scope.signInForm = function () {

        $http({
            method: 'POST',
            url: 'http://localhost:8080/rest/api/loan/login',
            data: angular.toJson($scope.admin),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(success, error);

        function success(response) {

            if (response.data.message == 'OK') {
                console.log('successfully logged in! Welcome');
            } else {
                alert('Invalid Email or password. Try again!')
                clearFormData()
            }


        };

        function error(response) {
            console.log(response.statusText);
        };

        //Clear the form
        function clearFormData() {
            $scope.admin.emailAddress = '';
            $scope.admin.password = '';

        };
    };
});
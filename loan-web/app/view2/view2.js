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

    $scope.applyLoanForm = function () {
        $scope.loan.customerId = $scope.login.customerId;

        var json = 'http://ipv4.myexternalip.com/json';
        $http.get(json).then(function(result) {
            $scope.loan.ipAddress= result.data.ip;

            $http({
                method: 'POST',
                url: 'http://localhost:8080/rest/api/loan/apply',
                data: angular.toJson($scope.loan),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(success, error);

            $http({
                method: 'POST',
                url: 'http://localhost:8080/rest/api/loan/applies',
                data: angular.toJson($scope.loan),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(applyListResponse, applyListError);

            function applyListResponse(response) {
                $scope.loans = response.data;
            };

            function applyListError(response) {
                console.log(response.statusText);
            };

            function success(response) {
                console.log('Loan Apply is successfully registered!');
                clearFormData()
            };

            function error(response) {
                console.log(response.statusText);
            };

            //Clear the form
            function clearFormData() {
                alert("Loan Apply is successfully registered!");
                $scope.loan.amount= '';
                $scope.loan.term = '';
                $scope.loan.customerId='';
            };

            $http({
                method: 'POST',
                url: 'http://localhost:8080/rest/api/loan/applies',
                data: angular.toJson($scope.loan),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(applyListResponse, applyListError);

            function applyListResponse(response) {
                $scope.loans = response.data;
            };

            function applyListError(response) {
                console.log(response.statusText);
            };
        }, function(e) {
            alert("When getting ip address, something was wrong. please control your internet connection");
        });
    };

    $scope.signInForm = function () {

        $http({
            method: 'POST',
            url: 'http://localhost:8080/rest/api/loan/login',
            data: angular.toJson($scope.login),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(success, error);

        function success(response) {

            if (response.data.message == 'OK') {

                $scope.loginBox = false;
                $scope.signupBox = true;

               // $scope.loan.customer = response.data.customerId;
                $scope.login.email = response.data.email;
                $scope.login.customerId = response.data.customerId;

                console.log('successfully logged in! Welcome,'+response.data.email);
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
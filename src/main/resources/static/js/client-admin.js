var clientAdmin = angular.module('clientAdmin', ['ngRoute']);

clientAdmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/client/:clientId', {
                    'templateUrl': '/html/client-admin.html',
                    'controller': 'clientAdminCtrl'
                }).otherwise({
                    redirectTo: '/clients'
                });
    }]);

clientAdmin.controller('clientAdminCtrl', function ($scope, $routeParams, $http, $rootScope, $window) {


    $scope.client = {};
    $rootScope.policy = {};
    $rootScope.policies = [];
    $scope.isDisabled = false;
    $scope.init = function () {

        $scope.getPolicies();
        $scope.is_readonly = true;
        $scope.clientId = $routeParams.clientId;
    };
    $scope.getPolicies = function () {
        $http({
            url: '/api/policies',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('All policies retrived sucessfully');
                $rootScope.policies = data;
                console.log($rootScope.policies);
                console.log('Policies size :' + $rootScope.policies.length);
                $scope.getClient($scope.clientId);
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $rootScope.getClient = function () {

        $http({
            url: '/api/clients/' + $scope.clientId,
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('Client retrived');
                $scope.client = data;
                console.log('Client : ' + $scope.client.clientName);
                console.log('Client : ' + $scope.client.clientId);
                $scope.getClientPolicies($scope.client.clientId);
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.edit = function (isReadonly, isDisabled) {

        $scope.is_readonly = isReadonly;
        $scope.isDisabled = isDisabled;
    };
    $scope.updateClient = function () {

        console.log("client " + $scope.client.clientId);
        console.log('update client');
        $http({
            url: '/api/client/' + $scope.client.clientId,
            method: 'put',
            headers: {
                'Content-Type': 'application/json'
            },
            data: $scope.client
        }).success(function (data, status) {
            console.log('get success code:' + status);
            if (status == 200) {
                $rootScope.message = "Client Updated Successfully";
                console.log('Client Updated');
                $window.location.reload();
            } else {
                console.log('status:' + status);
            }
        })
                .error(function (error) {
                    $rootScope.message = "Oops we recieved your request but there was a problem processing it";
                    console.log(error);
                });
    };
    $scope.getClientPolicies = function (clientId) {
        $scope.clientPolicies = [];
        console.log("Policies: " + $rootScope.policies);
        angular.forEach($rootScope.policies, function (policy) {
            if (angular.equals(policy.client.clientId, clientId)) {
                console.log("Policy obtained " + policy.client.clientName);
                $scope.clientPolicies.push(policy);
            } else {
                console.log("Not matched....");
            }
        });
    };
});



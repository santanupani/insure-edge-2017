var underwritter = angular.module('underwritter', ['ngRoute']);

underwritter.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/policy-requests/:reference', {
                    'templateUrl': '/html/underwritter.html',
                    'controller': 'policyCtrl'
                }).otherwise({
                    redirectTo: '/policy-requests'
        });
    }]);



underwritter.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams) {
    
    
    
         $scope.init = function () {
               $scope.policyRequest = $scope.getPoicyRequest($routeParams.reference);
         };
    
    
       $scope.getPoicyRequest = function (reference) {

        $http({
            url: '/api/policy-requests/' + reference,
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('policy Request retrived sucessfully');
                $scope.policyRequest = data;
                console.log(data);
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;

            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };



});
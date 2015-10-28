var claimAdmin = angular.module('claimAdmin', ['ngRoute']);


claimAdmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                    .when('/claim-requests', {
                        'templateUrl': '/html/claim-requests-list.html',
                        'controller': 'listClaimRequestCtrl'
                    }).when('/claim-requests/:claimNumber', {
                    'templateUrl': '/html/claim-requests.html',
                    'controller': 'claimRequestCtrl'
                }).otherwise({
            redirectTo: '/claim-requests'
        });
    }]);

claimAdmin.controller('listClaimRequestCtrl', function ($scope, $http, $rootScope) {

    $scope.init = function () {
        $scope.getAllClaimRequest();
    };
    
    $scope.getAllClaimRequest = function () {
        $scope.request = [];
        $http({
            url: '/api/claim-requests',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log(' all claim Request retrived sucessfully');
                $scope.claimRequest = data;
               
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


claimAdmin.controller('claimRequestCtrl', function ($scope, $rootScope, $http, $routeParams) {
    
    $scope.init = function () {
        
        $scope.claimNumber = $routeParams.claimNumber;
        $scope.getClaimRequest($routeParams.claimNumber);
        
    };
    
    $scope.getClaimRequest = function (claimNumber) {
        $http({
            url: '/api/claim-requests/' + claimNumber,
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('claim Request retrived sucessfully');
                $rootScope.claimRequest = data;
                console.log('Returned claim request: ' + data);
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


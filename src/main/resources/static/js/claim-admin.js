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


claimAdmin.controller('claimRequestCtrl', function ($scope, $rootScope, $http, $routeParams, $sce, $window) {

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
                console.log($rootScope.claimRequest.investigationReport);
                $scope.viewReportPDF($rootScope.claimRequest.claimNumber);
              //  window.open($rootScope.claimRequest.investigationReport);

                var str = $rootScope.claimRequest.investigationReport;
                var bytes = [];

                for (var i = 0; i < str.length; ++i)
                {
                    bytes.push(str.charCodeAt(i));
                    bytes.push(0);
                }
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

    $scope.viewReportPDF = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber,
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving Inverstigation report PDF');
                var file = new Blob([data], {type: 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                $scope.investigationReport = $sce.trustAsResourceUrl(fileURL);
                window.open($scope.investigationReport);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    }
});


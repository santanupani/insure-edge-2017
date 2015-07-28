var underwritter = angular.module('underwritter', ['ngRoute']);

underwritter.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/quotations/:reference', {
                    'templateUrl': '/html/underwritter.html',
                    'controller': 'policyCtrl'
                }).otherwise({
                    redirectTo: '/policies'
                });
    }]);



underwritter.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams) {

    $scope.quotations;

    $scope.init = function () {

        if ($rootScope.quotation == undefined) {
            
            $scope.quotations = $scope.getQuotation($routeParams.reference);
            
        } else {
            $scope.quotations = $rootScope.quotation;
        }
    };

    $scope.getQuotation = function (reference) {

        $http({
            url: '/api/quotations/' + reference,
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('quotations retrived sucessfully');
                $rootScope.quotation = data;
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


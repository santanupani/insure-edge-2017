var broker = angular.module('broker', ['ngRoute']);

broker.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                    .when('/quotation-requests/:reference', {
                	'templateUrl': '/html/viewQuotationRequest.html',
                	'controller': 'viewQuotationRequestCtrl'
                }).otherwise({
                    redirectTo: '/products'
                });
    }]);

broker.controller('viewQuotationRequestCtrl', function ($scope, $routeParams, $http) {

    $scope.viewQuotationdetail;
    
    $scope.ref = $routeParams.reference;
    
    $http({
        url: '/api/quotation-requests/' + $scope.ref,
        method: 'get',
    }).
            success(function (data, status) {
                console.log('get success code::' + status);
                if (status == 200) {
                    $scope.viewQuotationdetail = data;
                    console.log('Quotationdetail Detail::' + $scope.viewQuotationdetail);
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });
});




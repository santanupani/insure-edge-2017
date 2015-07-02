var broker = angular.module('broker', ['ngRoute']);

broker.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                    .when('/quotation-requests/:reference', {
                	'templateUrl': '/html/quotation-requests.html',
                	'controller' : 'quotationRequestsCtrl'
                }).otherwise({
                    redirectTo: '/products'
                });
    }]);

broker.controller('quotationRequestsCtrl', function ($scope, $routeParams, $http) {

    $scope.quotationRequest;
    
    $scope.init = function(){
    	 $scope.getQuotationRequest($routeParams.reference);
    };
    
    $scope.getQuotationRequest = function(reference){
    	$http({
            url: '/api/quotation-requests/' + reference,
            method: 'get',
        }).
                success(function (data, status) {
                    console.log('get success code::' + status);
                    if (status == 200) {
                        $scope.quotationRequest = data;
                        console.log('Quotationdetail Detail::' + $scope.quotationRequest);
                    } else {
                        console.log('status:' + status);
                    }
                })
                .error(function (error) {
                    console.log(error);
                });	
    };
    
    
});




var broker = angular.module('broker', ['ngRoute']);

broker.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/quotation-requests/:reference', {
                    'templateUrl': '/html/quotation-requests.html',
                    'controller': 'quotationRequestsCtrl'
                }).otherwise({
            redirectTo: '/products'
        });
    }]);

broker.controller('quotationRequestsCtrl', function ($scope, $routeParams, $http) {

    $scope.quotationRequest;

    //call the service and get all categories(make status false in database)
    $scope.categorieslist = [{"name": 'Category I', "status": true}, {"name": 'Category II', "status": false}, {"name": 'Category III', "status": false}];
    $scope.categories = [];
    $scope.categoryNumber = 0;
    $scope.models=[];
     $scope.init = function () {
        $scope.getQuotationRequest($routeParams.reference);
    };

    $scope.getQuotationRequest = function (reference) {
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
    
    
    
    $scope.accept = function () {
        $scope.categories.push($scope.categorieslist[0]);
    };

    $scope.add = function () {
        $scope.categoryNumber++;
        $scope.categorieslist[$scope.categoryNumber].status = true;
        $scope.categories.push($scope.categorieslist[$scope.categoryNumber]);
    };
    
    $scope.save=function(){
        console.log($scope.categories);
    };


});




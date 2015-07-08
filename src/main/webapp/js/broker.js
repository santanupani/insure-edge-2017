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

broker.controller('quotationRequestsCtrl', function ($scope, $routeParams, $http, $location) {

    $scope.quotationRequest;
    $scope.toggle = true;
    $scope.messageBody = {};


    $scope.init = function () {
        $scope.getQuotationRequest($routeParams.reference);

    };

   
    $scope.categorieslist = [{"name": 'Category I', "status": true}, {"name": 'Category II', "status": false}, {"name": 'Category III', "status": false}];
    $scope.categories = [];
    $scope.categoryNumber = 0;
    
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

    $scope.rejectQuotationRequest = function (reference, reason) {
        
         $scope.messageBody.reason;
         
        $http({
            url: '/api/quotation-requests/' + reference,
            method: 'put',
            headers: {
                    'Content-Type': 'application/json',
                },
            data: $scope.messageBody,
        }).
            success(function (data, status) {
                    console.log('get success code:' + status);
                    if (status == 200) {
                        $scope.reason = reason;
                        console.log(data);
                        console.log('Rejection Reason:' + reason);
                        $scope.getQuotationRequest($routeParams.reference);
                        $location.path("/quotation-requests/"+ reference);
                    } else {
                        console.log('status:' + status);
                    }
                })
                .error(function (error) {
                    console.log(error);
                });
    }
    

    $scope.accept = function () {
        $scope.categories.push($scope.categorieslist[0]);
    };

    $scope.add = function () {
        $scope.categoryNumber++;
        $scope.categorieslist[$scope.categoryNumber].status = true;
        $scope.categories.push($scope.categorieslist[$scope.categoryNumber]);
    };
    
    $scope.save=function(form){
        if (form.$invalid) {
            console.log("Form Validation Failure");
        } else {
        	console.log("Form Validation Success");
                
               
                
                 
                     
            console.log($scope.models.category);
            $http({
                url: '/api/createPolicy',
                method: 'post',
                headers: {
                    'Content-Type': 'application/json',
                },
                data: $scope.models.category
            }).success(function (data, status) {
                if (status == 200) {
                    console.log('All the premiums are saved in premium table');
                    console.log("Data:" + data);
                } else {
                    console.log('status:' + status);
                }
            }).error(function (error) {
                console.log(error);
            });
        }
    };


});

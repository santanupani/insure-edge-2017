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

    $scope.message ;
    $scope.reference ;
    $scope.quotationRequest;
    $scope.quotation ;
    $scope.mode ;
    $scope.reject;
 

    
    $scope.init = function () {
        $scope.reference = $routeParams.reference;     
        $scope.mode = undefined;
        $scope.message = undefined;
        $scope.reject = {};
        $scope.quotation = {
            "options" :[{"name": "Category I"}]
        };
        $scope.getQuotationRequest($scope.reference);
    };

    $scope.getQuotationRequest = function (reference) {
        $http({
            url: '/api/quotation-requests/' + reference,
            method: 'get'
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

    $scope.rejectQuotationRequest = function () {
    	$scope.reject.status = "REJECTED";
        $http({
            url: '/api/quotation-requests/' + $scope.reference,
            method: 'put',
            headers: {
                    'Content-Type': 'application/json'
                },
            data: $scope.reject
        }). success(function (data, status) {
                    console.log('get success code:' + status);
                    if (status == 200) {
                        console.log('Quotation Rejected. Reason:' + $scope.reject.reason);
                        //$location.path("/quotation-requests/"+ reference);
                        $scope.init();
                        $scope.message = "Quotation Request Rejected Successfully";
                    } else {
                        console.log('status:' + status);
                    }
                })
                .error(function (error) {
                    console.log(error);
                });
    };
    
    $scope.changeMode = function(mode){
    	$scope.mode = mode;
    };
    

    $scope.add = function () {
       // $scope.categoryNumber++;
       // $scope.categorieslist[$scope.categoryNumber].status = true;
       // $scope.categories.push($scope.categorieslist[$scope.categoryNumber]);
    };
    
    $scope.remove = function(){
    	if($scope.categories.length>1){
    		 $scope.categoryNumber--;
    		$scope.categories.pop();
    	}    	
    }
    
    $scope.save=function(form){
        if (form.$invalid) {
            console.log("Form Validation Failure");
        } else {
            $scope.quotation.reference = $scope.reference;
            console.log($scope.quotationRequest);
            console.log($scope.quotation);
                           
            $http({
                url: '/api/quotations',
                method: 'post',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: $scope.quotation
            }).success(function (data, status) {
                 console.log('get success CODE:' + status);
                if (status == 200) {
                    console.log('All the data are saved in quotationOptions and quotation table');
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

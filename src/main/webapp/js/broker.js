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
    $scope.mode ;
    $scope.reject = {};
    $scope.categorieslist = [{"name": 'Category I', "status": true}, {"name": 'Category II', "status": false}, {"name": 'Category III', "status": false}];
    $scope.categories = [];
    $scope.categoryNumber = 0;
    $scope.quotation = {};

    
    $scope.init = function () {
        $scope.getQuotationRequest($routeParams.reference);
        $scope.categories.push($scope.categorieslist[0]);

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

    $scope.rejectQuotationRequest = function () {
    	$scope.reject.status = "REJECTED";
        $http({
            url: '/api/quotation-requests/' + $routeParams.reference,
            method: 'put',
            headers: {
                    'Content-Type': 'application/json',
                },
            data: $scope.reject,
        }). success(function (data, status) {
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
    };
    
    $scope.changeMode = function(mode){
    	$scope.mode = mode;
    };
    

    $scope.add = function () {
        $scope.categoryNumber++;
        $scope.categorieslist[$scope.categoryNumber].status = true;
        $scope.categories.push($scope.categorieslist[$scope.categoryNumber]);
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
        	console.log("Form Validation Success");
                               
             $scope.quotation.categories=[];   
            for(var i=0; i< $scope.categories.length; i++) {
            	$scope.quotation.categories[i]= {};
            	$scope.quotation.categories[i].location =  $scope.categories[i].location;
                $scope.quotation.categories[i].limit =  $scope.categories[i].limit;
                $scope.quotation.categories[i].commodity =  $scope.categories[i].commodity;
                $scope.quotation.categories[i].cover =  $scope.categories[i].cover;
                $scope.quotation.categories[i].period =  $scope.categories[i].period;
                $scope.quotation.categories[i].excess =  $scope.categories[i].excess;
                $scope.quotation.categories[i].premium =  $scope.categories[i].premium;
                console.log("All data of the category are populated");
            	
            }  
                           
            $http({
                url: '/api/quotations',
                method: 'post',
                headers: {
                    'Content-Type': 'application/json',
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

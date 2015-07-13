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

broker.controller('quotationRequestsCtrl', function ($scope, $routeParams, $http, $location, $rootScope) {
    
    $scope.reference ;
    $scope.quotationRequest;
    $scope.quotation ;
    $scope.mode ;
    $scope.reject;
    $scope.com ;
                               
    
    $scope.init = function () {
        $scope.reference = $routeParams.reference;     
        $scope.reject = {};
        $scope.quotation = {
            "options" :[{"name": "Category 1"}]
        };
        $scope.getQuotationRequest($scope.reference);
        $scope.com = ['Cash', 'Bullion', 'Diamond' ,'Art'];
    };
    
    $scope.getQuotationRequest = function () {
        $http({
            url: '/api/quotation-requests/' + $scope.reference,
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
            url: '/api/quotation-requests/' + $scope.reference + "/reject",
            method: 'put',
            headers: {
                    'Content-Type': 'application/json'
                },
            data: $scope.reject
        }). success(function (data, status) {
                    console.log('get success code:' + status);
                    if (status == 200) {
                        console.log('Quotation Rejected. Reason:' + $scope.reject.reason);
                        $scope.init();
                        $rootScope.message = "Quotation Request Rejected Successfully";
                        $scope.mode = undefined;
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
        var option = {};
        var optionName = "Category " + ($scope.quotation.options.length + 1);
        option.name = optionName;
        $scope.quotation.options.push(option);
      
    };
    
    $scope.remove = function(){
    	if($scope.quotation.options.length>1)
    		$scope.quotation.options.pop();
    		
    };
    
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
                if (status === 200) {
                    console.log('All the data are saved in quotationOptions and quotation table');
                    $scope.init();
                    $rootScope.message = "Quotation Request Accepted Successfully";
                    $scope.mode = undefined;
                } else {
                    console.log('status:' + status);
                }
            }).error(function (error) {
                console.log(error);
            }); 
        }
    };


});

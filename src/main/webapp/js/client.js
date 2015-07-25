var polygon = angular.module('polygon', ['ngRoute']);

polygon.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/products', {
                    'templateUrl': '/html/products.html',
                    'controller': 'productsCtrl'
                })
                .when('/products/:id/questionnaires', {
                    'templateUrl': '/html/questionnaires.html',
                    'controller': 'questionnairesCtrl'
                }).when('/viewquote',{
                    'templateUrl': '/html/view-quotation.html',
                    'controller': 'viewQuotationCtrl'
                })
                        .otherwise({
                    redirectTo: '/products'
                });
    }]);

polygon.controller('productsCtrl', function ($scope, $rootScope, $http) {

    $scope.init = function () {
        $scope.getProducts();
    };
    
    $scope.getProducts = function () {
        console.log('get products');
        $http({
            url: '/api/products',
            method: 'get'
        }).success(function (data, status) {
            if (status === 200) {
                console.log('retrived successfully');
                $rootScope.products = data;
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;
                ;
            }
        }).error(function (error) {
            $rootScope.message = "Oops, we received your request, but there was an error processing it";
        });
    };
    
    $scope.closeNotification = function(){
    	$rootScope.message = undefined;
    };
});

polygon.controller('questionnairesCtrl', function ($scope, $rootScope, $http, $routeParams, $location) {

	$scope.product ;
	
    $scope.questionnaires = [];
    
    $scope.quotationRequest = {};

    $scope.init = function () {
               
        if ($rootScope.products == undefined) {
            $scope.getProducts();
        } else {
            $scope.product = $rootScope.products[$routeParams['id'] - 1];
            $scope.quotationRequest.productId = $scope.product.id;
            $scope.getQuestionnaires($routeParams['id']);
        }
    };

    $scope.getProducts = function () {
        console.log('get products');
        $http({
            url: '/api/products',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('retrived successfully');
                $rootScope.products = data;
                $scope.init();
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;
                ;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
            ;
        });
    };

    $scope.getQuestionnaires = function (productId) {
        $http({
            url: '/api/products/' + productId + '/questionnaires',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('retrived successfully');
                $scope.questionnaires = data;
                for (var i = 0; i < $scope.questionnaires.length; i++) {
                    if ($scope.questionnaires[i].answerType == 'checkbox') {
                        $scope.questionnaires[i].answer = 'false';
                    }
                }
                $scope.getBrokers();
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;
                
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
            ;
        });
    };

    $scope.getBrokers = function () {
        $http({
            url: '/api/brokers',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('brokers retrived successfully');
                $scope.brokers = data;
                console.log($scope.brokers);
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;
                
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };

    $scope.submitQuotationRequest = function (form) {
    	
        if (form.$invalid) {
            console.log("Form Validation Failure");
        } else {
        	console.log("Form Validation Success");
            $scope.quotationRequest.questionnaires = [];
            for(var i=0; i< $scope.questionnaires.length; i++) {
            	$scope.quotationRequest.questionnaires[i]= {};
            	$scope.quotationRequest.questionnaires[i].question =  $scope.questionnaires[i].question;
            	$scope.quotationRequest.questionnaires[i].answer = $scope.questionnaires[i].answer;
            }            
            console.log($scope.quotationRequest);
            $http({
                url: '/api/quotation-requests',
                method: 'post',
                headers: {
                    'Content-Type': 'application/json',
                },
                data: $scope.quotationRequest
            }).success(function (data, status) {
                if (status == 200) {
                    console.log('All the questions and answers saved succesfullly');
                    $rootScope.message = "Reference Number : " + data;
                    $location.path("/products");
                } else {
                    console.log('status:' + status);
                }
            }).error(function (error) {
                console.log(error);
                $rootScope.message = error;
            });
        }

    };
});

polygon.controller('viewQuotationCtrl', function ($scope, $rootScope, $http) {
    
    $scope.quotationdetails;
    $scope.quotationId;
    
       $scope.init = function () {
        $scope.quotationId = $routeParams.quotationId;     
        $scope.getQuotation($scope.quotationId);
        
    };
    
        $scope.getQuotation = function () {
        $http({
            url: '/api/view-quotation/' + $scope.quotationId,
            method: 'get'
        }).
                success(function (data, status) {
                    console.log('get success code::' + status);
                    if (status == 200) {
                        $scope.quotationdetails = data;
                        console.log('Quotationde Details:' + $scope.quotationdetails);
                    } else {
                        console.log('status:' + status);
                    }
                })
                .error(function (error) {
                    console.log(error);
                });
    };
    
    
    
    
    $scope.quotationRequest = {
        reference : "28888",
        status: "ACCEPTED",
    createDate: "23/07/2015",
    companyName: "Reverside",
    applicantName: "Thabo",
    applicantEmail: "thabothulare68@gmail.com"};

    $scope.product= {
      id: 1,
      name: "Cash and Valuables in Transit",
      description: "All risk cover for your cash and valuables whilst in transit.",
      image: "/img/products/Cash and Valuables in Transit.jpg"
    };
    
     $scope.questionnaires = [
    {
        question: "What do you wish to insure ?",
        answer: "Cash and Coins "
      },
      {
        question: "What is the maximum amount you wish to insure ?",
        answer: "12345"
      },
      {
        question: "Is the above amount the total full value of the goods being moved ?",
        answer: "true"
      },
      {
        question: "Do you want first loss cover for additional premium ?",
        answer: "true"
      },
      {
        question: "Do you use the service of a professional valuables carriers ?",
        answer: "true"
      },
      {
        question: "What is the name of the professional valuables carriers ?",
        answer: "Protea Coin Service"
      },
     
      {
        question: "How many times per week are the valuables carried ?",
        answer: "3 times/week"
      },
      {
        question: "Please select from where and to where are the valuables carried :",
        answer: "Rustenburg to Kimberley"
      },
      {
        question: "What is the approximate distance ?",
        answer: "200km to 300km"
      },
      {
        question: "Are you currently insured ?",
        answer: "true"
      },
      {
        question: "Please provide details of your previous insurance company :",
        answer: "Reverside"
      },
      {
        question: "Please provide details of losses of valuable goods of insured/uninsured the past 5 years ?",
        answer: "false"
      },
    
      {
        question: "Do you have more history details of insured/uninsured goods :",
        answer: "false"
      },
  
    
      {
        question: "Do you require SASRIA cover ?",
        answer: "true"
      }];
    
    $scope.options = [
    {
      location: "23 Locations",
      commodity: "Cash",
      limit: "89999",
      cover: "Cash in Transit",
      duration: "11 weeks",
      excess: "7999,99",
      premium: "1076.89"
    },
    {
      location: "16 Locations",
      commodity: "Art",
      limit: "1200000",
      cover: "Cash In Transit",
      duration: "6 years",
      excess: "1299,87",
      premium: "172.12"
    },
       {
      location: "18 Locations",
      commodity: "Diamond",
      limit: "1200000",
      cover: "Cash In Transit",
      duration: "2 years",
      excess: "1299,87",
      premium: "172.12"
    }];



});

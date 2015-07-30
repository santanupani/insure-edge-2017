var polygon = angular.module('polygon', ['ngRoute']);

polygon.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/products', {
                    'templateUrl': '/html/products.html',
                    'controller': 'productsCtrl'
                }).when('/products/:id/questionnaires', {
            'templateUrl': '/html/questionnaires.html',
            'controller': 'questionnairesCtrl'
        }).when('/quotations/:reference', {
            'templateUrl': '/html/quotations.html',
            'controller': 'quotationsCtrl'
        }).when('/quotations/:reference/policies', {
            'templateUrl': '/html/policies.html',
            'controller': 'policyCtrl'
        }).otherwise({
            redirectTo: '/products'
        });
    }]);

polygon.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
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

    $scope.closeNotification = function () {
        $rootScope.message = undefined;
    };
});

polygon.controller('questionnairesCtrl', function ($scope, $rootScope, $http, $routeParams, $location) {

    $scope.product;

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
            for (var i = 0; i < $scope.questionnaires.length; i++) {
                $scope.quotationRequest.questionnaires[i] = {};
                $scope.quotationRequest.questionnaires[i].question = $scope.questionnaires[i].question;
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

polygon.controller('quotationsCtrl', function ($scope, $rootScope, $http, $routeParams) {


    $scope.init = function () {
        $scope.getQuotation($routeParams.reference);
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

polygon.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams) {


    $scope.quotations;
    $scope.quotationOption;
    $scope.policyRequest = {};

    $scope.init = function () {
            $scope.reference = $routeParams.reference;
            $
          $scope.debitOrderDate = ['1st', '7th'];
           $scope.accounttype = ['Current', 'Savings', 'Transmition'];

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
                $scope.init();
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;

            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };

    $scope.submitforPolicy = function (form) {

        if (form.$invalid) {
            console.log("Form Validation Failure");
        } else {

            console.log("Form Validation Sucess");
            $rootScope.message = "Form Validation was succesfull";
        
        $scope.policyRequest.reference = $scope.reference;
        console.log("ref " + $scope.reference);
         console.log($scope.policyRequest);
        $http({
            url: '/api/policy-requests',
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
            },
            data: $scope.policyRequest
        }).success(function (data, status) {
            if (status == 200) {
                console.log('All the details are saved succesfullly');
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
    
    $scope.closeNotification = function () {
        $rootScope.message = undefined;
    };

});

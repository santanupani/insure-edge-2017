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
        }).when('/quotations/:reference/application', {
            'templateUrl': '/html/policies.html',
            'controller': 'policyCtrl'
        }).otherwise({
            redirectTo: '/products'
        });
    }]);


polygon.directive('format', function ($filter) {
    'use strict';

    return {
        require: '?ngModel',
        link: function (scope, elem, attrs, ctrl) {
            if (!ctrl) {
                return;
            }

            ctrl.$formatters.unshift(function () {
                return $filter('number')(ctrl.$modelValue);
            });

            ctrl.$parsers.unshift(function (viewValue) {
                var plainNumber = viewValue.replace(/[\,\.]/g, ''),
                        b = $filter('number')(plainNumber);

                elem.val(b);

                return plainNumber;
            });
        }
    };
});

polygon.directive('phoneInput', function($filter, $browser) {
    return {
        require: 'ngModel',
        link: function($scope, $element, $attrs, ngModelCtrl) {
            var listener = function() {
                var value = $element.val().replace(/[^0-9]/g, '');
                $element.val($filter('tel')(value, false));
            };

            // This runs when we update the text field
            ngModelCtrl.$parsers.push(function(viewValue) {
                return viewValue.replace(/[^0-9]/g, '').slice(0,10);
            });

            // This runs when the model gets updated on the scope directly and keeps our view in sync
            ngModelCtrl.$render = function() {
                $element.val($filter('tel')(ngModelCtrl.$viewValue, false));
            };

            $element.bind('change', listener);
            $element.bind('keydown', function(event) {
                var key = event.keyCode;
                // If the keys include the CTRL, SHIFT, ALT, or META keys, or the arrow keys, do nothing.
                // This lets us support copy and paste too
                if (key == 91 || (15 < key && key < 19) || (37 <= key && key <= 40)){
                    return;
                }
                $browser.defer(listener); // Have to do this or changes don't get picked up properly
            });

            $element.bind('paste cut', function() {
                $browser.defer(listener);
            });
        }

    };
});

polygon.filter('tel', function () {
    return function (tel) {
        if (!tel) { return ''; }

        var value = tel.toString().trim().replace(/^\+/, '');

        if (value.match(/[^0-9]/)) {
            return tel;
        }

        var country, city, number;

        switch (value.length) {
            case 1:
            case 2:
            case 3:
                city = value;
                break;

            default:
                city = value.slice(0, 3);
                number = value.slice(3);
        }

        if(number){
            if(number.length>3){
                number = number.slice(0, 3) + '-' + number.slice(3,7);
            }
            else{
                number = number;
            }

            return ("(" + city + ") " + number).trim();
        }
        else{
            return "(" + city;
        }

    };
});

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



polygon.controller('questionnairesCtrl', function ($scope, $rootScope, $http, $routeParams, $location, $filter) {

    $scope.product;
    $scope.numberOfDecimals = 2;
    $scope.histories = [];
    $scope.questionnaires = [];
    $scope.isHistory;
    $scope.location;
    $scope.quotationRequest = {};
    $scope.productId;

    $scope.models = {};

    $scope.init = function () {
        $scope.isHistory = false;
          if ($rootScope.products == undefined) {
            $scope.getProducts();
        } else {
            $scope.product = $rootScope.products[$routeParams['id'] - 1];
            $scope.quotationRequest.productId = $scope.product.id;
            $scope.getQuestionnaires($routeParams['id']);
            $scope.location = {"options": []};
            $scope.add();
        }
    };

          $scope.dateOptions = {
            changeYear: true,
            changeMonth: true
//            yearRange: '2014:-2020'
        };
    $scope.addRemoveHistory = function () {

        if ($scope.isHistory) {
            $scope.histories = [];
            console.log($scope.histories);
        } else {
            $scope.histories[0] = {};
            console.log($scope.histories);
        }
    };

    $scope.addMoreHistory = function () {
        $scope.histories[$scope.histories.length] = {};
    };

    $scope.removeMoreHistory = function () {
        $scope.histories.pop();
        if ($scope.histories.length == 0 || !$scope.isHistory) {
            $scope.histories = [];
            $scope.isHistory = undefined;
        }
    };

    $scope.add = function () {
        var model = {};
        model = {
            cash: false,
            bullion: false,
            preciousStone: false,
            other: false,
            diamonds: false,
            otherExtra: false
        };

        var option = {};
        option.isGoodsMoved = false;
        option.isGoodsMovedStatic = false;
        option.isServiceCarrier = false;
        option.isStoreVault = false;
        option.optionName = "Location-Option-" + ($scope.location.options.length + 1);
        $scope.location.options.push(option);
    };

    $scope.remove = function () {
        if ($scope.location.options.length > 1)
            $scope.location.options.pop();

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
                $scope.productId = productId;
                console.log($scope.productId);
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
                console.log('Broker: ' + $scope.brokers[0].name);
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
            $scope.quotationRequest.locationOptions = [];
            var commodity = '';
            var commodities = $scope.models.cash + $scope.models.bullion + $scope.models.diamonds + $scope.models.preciousStone + $scope.models.otherExtra;
            angular.forEach(commodities.split("undefined"), function (token) {
                if (token != '') {
                    commodity = token + '/' + commodity;
                }
            });
            commodity = commodity.substring(0, commodity.length - 1);
            for (var i = 0; i < $scope.location.options.length; i++) {
                $scope.location.options[i].commodity = commodity;
                console.log($scope.location.options[i].commodity);
                console.log('Product ID: ' + $scope.productId);
                if ($scope.productId == 2 || $scope.productId == 3) {
                    $scope.location.options[i].duration = "Static";
                } else {
                    $scope.location.options[i].duration = $scope.location.options[i].noOfTimes + " x Weekly";
                }
                $scope.quotationRequest.locationOptions[i] = {};
                $scope.quotationRequest.locationOptions[i] = $scope.location.options[i];
                console.log($scope.quotationRequest.locationOptions[i].isGoodsMoved);
            }
            $scope.quotationRequest.questionnaires = [];
            for (var i = 0; i < $scope.questionnaires.length; i++) {
                $scope.quotationRequest.questionnaires[i] = {};
                $scope.quotationRequest.questionnaires[i].question = $scope.questionnaires[i].question;
                $scope.quotationRequest.questionnaires[i].answer = $scope.questionnaires[i].answer;
            }

            console.log($scope.histories);
            $scope.quotationRequest.histories = $scope.histories;
            console.log($scope.quotationRequest);
            console.log($scope.quotationRequest.histories);
            $scope.quotationRequest.brokerId = 1;
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

    $scope.formatNumber = function (nmbr) {
        return $filter('currency')(nmbr, 'ZAR ', 2);

    };

    $scope.changeMode = function (mode) {
        $scope.mode = mode;
    };


});

polygon.controller('quotationsCtrl', function ($scope, $rootScope, $http, $routeParams) {



    $scope.init = function () {
        $rootScope.quotations = $scope.getQuotation($routeParams.reference);
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

polygon.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams, $location) {

	$scope.quotationOptionId;
	$scope.policyRequest = {};
	$scope.quotationSelected = [];

	$scope.init = function () {
		if($rootScope.quotations ==undefined){
			console.log('Quotation is undefied.'+$routeParams.reference);
			$scope.getQuotation($routeParams.reference);
		}else{
			$scope.quotation = $rootScope.quotations;
		}
		$scope.debitOrderDate = ['5th', '7th'];
		$scope.accounttype = ['Current', 'Savings', 'Transmition'];

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


	$scope.submitforPolicy = function (form) {

		if (form.$invalid) {
			console.log("Form Validation Failure");
		} else {

			console.log("Form Validation Sucess");
			if(angular.equals($scope.policyRequest.isRepresentative,'No')){
				$('#isRepresentative').css({'color':'red'});
				return;
			}
			
			$rootScope.message = "Form Validation was succesfull";

			$scope.policyRequest.reference = $rootScope.quotation.quotationRequest.reference;
			$scope.policyRequest.quotationId = $scope.quotationId;
			console.log($scope.policyRequest);
			console.log($scope.file);
			$http({
				url: '/api/policy-requests',
				method: 'post',
				headers: {
					'Content-Type': undefined,
				},
				transformRequest: function (data) {
					var formData = new FormData();

					formData.append('policyRequest', new Blob([angular.toJson(data.policyRequest)], {
						type: "application/json"
					}));
					formData.append("file", data.file);
					return formData;
				},
				data: {policyRequest: $scope.policyRequest, file: $scope.file}

			}).success(function (data, status) {
				if (status == 200) {
					console.log('All the details are saved succesfullly');
					$rootScope.message = "Your Policy Request has been Succesfully submitted";
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

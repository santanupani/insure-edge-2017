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
	}).when('/quotations/:reference/:quotationOptionId', {
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

	$scope.models = [];

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

	$scope.addRemoveHistory = function(){

		if($scope.isHistory){
			$scope.histories = [];
			console.log($scope.histories);
		}else{
			$scope.histories[0] = {};
			console.log($scope.histories);
		}
	};

	$scope.addMoreHistory = function(){
		$scope.histories[$scope.histories.length] = {};
	};

	$scope.removeMoreHistory = function(){
		$scope.histories.pop();
		if($scope.histories.length == 0 || !$scope.isHistory){
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
				other:false,
				diamonds: false,
				otherExtra:false
		};
		$scope.models.push(model);

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

			for (var i = 0; i < $scope.location.options.length; i++) {
				var commodity ='';
				var tokens = $scope.models[i].cash + $scope.models[i].bullion + $scope.models[i].diamonds + $scope.models[i].preciousStone +$scope.models[i].otherExtra;
				angular.forEach(tokens.split("false"),function(token){
					if(token != ''){
						commodity = token+'/'+commodity;
					}
				});
				$scope.location.options[i].commodity = commodity;
				$scope.location.options[i].duration = $scope.models[i].noOfTimes + $scope.models[i].period;
				$scope.quotationRequest.locationOptions[i] = {};
				$scope.quotationRequest.locationOptions[i] = $scope.location.options[i];
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
		$scope.getSelectedQuotation($routeParams.reference, $routeParams.quotationOptionId);
		$scope.debitOrderDate = ['1st', '5th', '7th'];
		$scope.accounttype = ['Current', 'Savings', 'Transmition'];



	};


	$scope.getSelectedQuotation = function (reference, quotationOptionId) {

		$http({
			url: '/api/quotations/' + reference + '/' + quotationOptionId,
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('quotations retrived sucessfully');
				$scope.quotationSelected = data;
				console.log('Selected Reference: ' + $scope.quotationSelected.quotation.quotationRequest.reference + '\nSelected Option ID: ' + $scope.quotationSelected.selectedQuotation.quotationOptionId);
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

			$scope.policyRequest.reference = $scope.quotationSelected.quotation.quotationRequest.reference;
			$scope.policyRequest.quotationOptionId = $scope.quotationSelected.selectedQuotation.quotationOptionId;
			console.log("Quotation OptionID :" + $scope.quotationSelected.selectedQuotation.quotationOptionId);
			console.log("ref " + $scope.quotationSelected.quotation.quotationRequest.reference);
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

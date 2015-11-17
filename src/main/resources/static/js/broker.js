var broker = angular.module('broker', ['ngRoute']);

broker.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/quotation-requests/:reference', {
		'templateUrl': '/html/quotation-requests.html',
		'controller': 'quotationRequestsCtrl'
	}).when('/quotations', {
		'templateUrl': '/html/broker-scheduler.html',
		'controller': 'brokerSchedulerCtrl'
	}).otherwise({
		redirectTo: '/products'
	});
}]);

broker.directive('format', function ($filter) {
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

broker.controller('quotationRequestsCtrl', function ($scope, $routeParams, $http, $location, $rootScope, $sce) {

	$scope.reference ;
	$scope.numberOfDecimals = 2;
	$scope.quotationRequest;
	$scope.quotation ;
	$scope.mode ;
	$scope.reject;
	$scope.com ;
	$scope.questionnairres = [];
	$scope.updateQuotation;


	$scope.init = function () {
		$scope.reference = $routeParams.reference;
		$scope.isQuotationCreated = true;
		$scope.reject = {};
		$scope.quotation = {
				"options" :[]
		};
		$scope.getQuotationRequest($scope.reference);

	};
	
		
	$scope.updateQuotation = function () {

		$scope.updateQuotation = {};
		
		$scope.updateQuotation.reference = $scope.quotationRequest.reference;
		console.log('Updated quotation reference: '+$scope.updateQuotation.reference);
		$scope.updateQuotation.options = [];;
		for(var i=0;i<$scope.quotationRequest.locationOptions.length;i++){
			$scope.updateQuotation.options[i] = {};
			$scope.updateQuotation.options[i].pavement = $scope.quotationRequest.locationOptions[i].pavement;
			$scope.updateQuotation.options[i].commodity = $scope.quotationRequest.locationOptions[i].commodity;
			$scope.updateQuotation.options[i].staticLimit = $scope.quotationRequest.locationOptions[i].staticLimit;
			$scope.updateQuotation.options[i].duration = $scope.quotationRequest.locationOptions[i].duration;
			$scope.updateQuotation.options[i].cover = $scope.quotationRequest.locationOptions[i].cover;
			$scope.updateQuotation.options[i].excess = $scope.quotationRequest.locationOptions[i].excess;
			$scope.updateQuotation.options[i].limit = $scope.quotationRequest.locationOptions[i].limit;
			$scope.updateQuotation.options[i].location = $scope.quotationRequest.locationOptions[i].fromLocation;
			$scope.updateQuotation.options[i].premium = $scope.quotationRequest.locationOptions[i].premium;
		}
		
		$http({
			url: '/api/quotation-update/'+$scope.updateQuotation.reference,
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.updateQuotation
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				$rootScope.message = "Quotation updated Successfully";
				$scope.viewQuotationPDF($scope.updateQuotation.reference);
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			$rootScope.message = "Oops we recieved your request but there was a problem processing it";
			console.log(error);
		});
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
				$scope.questionnairres = $scope.quotationRequest.questionnaire;

				for(var i=0;i<$scope.quotationRequest.questionnaire.length;i++){

					if(angular.equals($scope.quotationRequest.questionnaire[i].answer,'true')){
						$scope.quotationRequest.questionnaire[i].answer = 'Yes';
						console.log($scope.quotationRequest.questionnaire[i].answer);
					}else if(angular.equals($scope.quotationRequest.questionnaire[i].answer,'false')){
						$scope.quotationRequest.questionnaire[i].answer = 'No';
						console.log($scope.quotationRequest.questionnaire[i].answer);
					}
				}

				angular.forEach($scope.quotationRequest.locationOptions,function(locationOption){
					locationOption.cover = 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery - as per standard policy';
					locationOption.excess = '0% minimum R 0,000,000.00 (Own armoured vehicles/static/onsite)';
					locationOption.premium = 'R 00,000.00 (per month excluding 14% vat)';
					locationOption.pavement = 'R 0,000.00 per cross pavement carry.';
				});
				console.log('Quotation Request Detail::' + $scope.quotationRequest);
				console.log('Questionairres Detail::' + $scope.questionnairres);
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			console.log(error);
		});
	};

	$scope.rejectQuotationRequest = function (rejectform) {
		if (rejectform.$invalid) {
			console.log("Form Validation Failure");
		} else {
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
				$rootScope.message = "";
				console.log(error);
			});
		}
	};

	$scope.changeMode = function(mode){
		$scope.mode = mode;
	}; 

	$scope.viewQuotationPDF = function(reference){
		console.log('Ref: ' + reference);
		$http({
			url: '/api/quotation-request-pdf/' + reference,
			responseType:'arraybuffer',
			headers: { 'Accept': 'application/pdf' },
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('Retrieving Quotation PDF');
				var file = new Blob([data], {type: 'application/pdf'});
				var fileURL = URL.createObjectURL(file);
				$scope.quotationPDF = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});

	};


	$scope.save=function(form){

		if (form.$invalid) {
			console.log("Form Validation Failure");
		} else {
			$scope.quotation.reference = $scope.reference;

			console.log("Ref Test : "+$scope.reference);
			for(var i=0;i<$scope.quotationRequest.locationOptions.length;i++){
				$scope.quotation.options[i] = {};
				$scope.quotation.options[i].cover = $scope.quotationRequest.locationOptions[i].cover;
				$scope.quotation.options[i].commodity = $scope.quotationRequest.locationOptions[i].commodity;
				$scope.quotation.options[i].location = $scope.quotationRequest.locationOptions[i].fromLocation;
				$scope.quotation.options[i].duration = $scope.quotationRequest.locationOptions[i].duration;
				$scope.quotation.options[i].excess = $scope.quotationRequest.locationOptions[i].excess;
				$scope.quotation.options[i].premium = $scope.quotationRequest.locationOptions[i].premium;
				$scope.quotation.options[i].limit = $scope.quotationRequest.locationOptions[i].limit;
				$scope.quotation.options[i].staticLimit = $scope.quotationRequest.locationOptions[i].staticLimit;
				$scope.quotation.options[i].pavement = $scope.quotationRequest.locationOptions[i].pavement;
			}; 

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
//					$scope.init();
					$rootScope.message = "Quotation Request Accepted Successfully";
//					$scope.mode = undefined;
					$scope.isQuotationCreated = false;
					$scope.viewQuotationPDF($scope.quotation.reference );

				} else {
					console.log('status:' + status);
				}
			}).error(function (error) {
				$rootScope.message = "Oops, we received your request, but there was an error processing it";
			}); 
		}
	};

	$scope.submitQuotation = function(){

		$http({
			url: '/api/quotation-submit/'+$scope.reference,
			method: 'post',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.reference
		}).success(function (data, status) {
			console.log('Submit quotation status :' + status);
			if (status === 200) {
				console.log('Email notification send succesfully to the client');
				$scope.init();
				$scope.quotationView = true;
				$scope.mode = undefined;
				$rootScope.message = "Quotation Accepted Successfully";
			} else {
				console.log('status:' + status);
			}
		}).error(function (error) {
			$rootScope.message = "Oops, we received your request, but there was an error processing it";
		}); 
	};

	$scope.formatNumber = function(nmbr){
		return $filter('currency')(nmbr, 'R ', 2);

	};


});


broker.controller('brokerSchedulerCtrl', function ($scope, $rootScope, $http,$filter) {

	$scope.mode ;
	$scope.noOfDays=[];
	$scope.quotations = [];
	$scope.currDate; 

	$scope.init = function () {
		$scope.currDate = $filter("date")(Date.now(),'dd/MM/yyyy');
		$scope.getAllQuotations();
	};


	$scope.getAllQuotations = function () {
		console.log('get all quotations');
		$http({
			url: '/api/quotations',
			method: 'get'
		}).success(function (data, status) {
			if (status === 200) {
				console.log('retrived successfully');
				$scope.quotations = data;
				for (var i = 0; i < $scope.quotations.length; i++) {
					$scope.noOfDays[i] = $scope.dayDiff($scope.quotations[i].quotationRequest.createDate,$scope.currDate);
				}
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

	$scope.getQuotation = function (reference) {
		angular.forEach($scope.quotations,function(quotation){
			if(quotation.quotationRequest.reference == referennce){
				return quotation;
			}
		});
	};

	$scope.changeMode = function(mode){
		$scope.mode = mode;
	};

	$scope.closeNotification = function () {
		$rootScope.message = undefined;
	};

	$scope.dayDiff = function(firstDate,secondDate){
		var date2 = new Date($scope.formatString(secondDate));
		var date1 = new Date($scope.formatString(firstDate));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());   
		$scope.dayDifference = Math.ceil(timeDiff / (1000 * 3600 * 24));
		return $scope.dayDifference;
	};

	$scope.formatString = function(format) {
		var day   = parseInt(format.substring(0,2));
		var month  = parseInt(format.substring(3,5));
		var year   = parseInt(format.substring(6,10));
		var date = new Date(year, month-1, day);
		return date;
	};
});


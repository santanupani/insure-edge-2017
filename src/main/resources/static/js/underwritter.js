var underwritter = angular.module('underwritter', ['ngRoute']);

underwritter.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/policy-requests/:reference', {
		'templateUrl': '/html/underwritter.html',
		'controller': 'policyRequestCtrl'
	}).when('/client/:policyReference', {
		'templateUrl': '/html/client-details.html',
		'controller': 'clientDetailsCtrl'
	}).when('/policy/:policyReference', {
		'templateUrl': '/html/policy.html',
		'controller': 'policyCtrl'
	}).when('/policies', {
		'templateUrl': '/html/policy.html',
		'controller': 'policyCtrl'
	}).otherwise({
		redirectTo: '/policy-requests'
	});
}]);

underwritter.filter('offset', function () {
	return function (input, start) {
		start = parseInt(start, 10);
		return input.slice(start);
	};
});

$(document).ready(function () {
	$("#regId").mouseout().css("text-transform", "uppercase");
});

underwritter.controller('policyRequestCtrl',function($scope, $rootScope, $http, $routeParams, $location){
	$scope.mode;
	$scope.reference;
	$scope.reject;
	$scope.accept;
	$rootScope.policyRequest;
	
	$scope.init = function(){
		$scope.reference = $routeParams.reference;
		$scope.reject = {};
		$scope.accept = {};
		$rootScope.policyRequest = {};
		$scope.getPolicyRequest($routeParams.reference);
	};
	
	$scope.getPolicyRequest = function (reference) {
		$http({
			url: '/api/policy-requests/' + reference,
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('policy Request retrived sucessfully');
				$rootScope.policyRequest = data;
				console.log('Returned policy request: '+$rootScope.policyRequest);
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;

			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};

	$scope.rejectPolicyRequest = function (rejectform) {
		if (rejectform.$invalid) {
			console.log("Form Validation Failure");
		} else {
			$scope.reject.status = "REJECTED";
			$http({
				url: '/api/policy-requests/' + $scope.reference + "/reject",
				method: 'put',
				headers: {
					'Content-Type': 'application/json'
				},
				data: $scope.reject
			}).success(function (data, status) {
				console.log('get success code:' + status);
				if (status === 200) {
					console.log('Policy request Rejected. Reason:' + $scope.reject.reason);
					$scope.init();
					$rootScope.message = "Policy Request Rejected Successfully";
					$scope.mode = undefined;
				} else {
					console.log('status:' + status);
				}
			})
			.error(function (error) {
				$rootScope.message = "Oops, we received your request, but there was an error processing it";
				console.log(error);
			});
		}
	};

	$scope.acceptPolicyRequest = function (acceptform) {
		if (acceptform.$invalid) {
			console.log("Form Validation Failure");
		} else {
			console.log("Form Validation Success");
			$scope.init();
			$scope.mode = undefined;
		}
	};

	$scope.changeMode = function (mode) {
		$scope.mode = mode;
	};
});


underwritter.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams, $location) {
	
	$rootScope.policy = {};
	$scope.itemsPerPage = 5;
	$scope.currentPage = 0;
	$rootScope.policies = [];
	
	$scope.init = function () {
		
		$scope.getPolicies();
		$scope.initNewPolicy();
	};

	/*Initialize new policy*/
	$scope.initNewPolicy = function (policy,policyRequest) {
		$rootScope.policy.client = {};
		$rootScope.policy.client.contact = {};
		$rootScope.policy.client.bankAccount = {};
		$rootScope.policy.policySchedule = {};
		
		if (policy == undefined) {
			console.log('Policy request :'+policy);
			$rootScope.policy.policyReference = 'New Policy';
			$rootScope.policy.brokerFee = 20;
			$rootScope.policy.client = {};
			$rootScope.policy.client.clientName = 'Reverside';

		} else {
			$scope.policy = $rootScope.policy;
			console.log('Existing Policy :'+$rootScope.policy.client.clientName);
		}
	};

	/* A UI selected policy to be displayed*/
	$rootScope.getPolicy = function (reference) {
		angular.forEach($scope.policies, function (policy) {
			if (angular.equals(reference, policy.policyReference)) {
				$scope.policy = policy;
				$location.path('/policy/' + $routeParams.policyReference);
			}
		});

	};

	/*Get all list of policies*/
	$scope.getPolicies = function () {
		$http({
			url: '/api/policies',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('All policies retrived sucessfully');
				$rootScope.policies = data;
				console.log($rootScope.policies);
				$rootScope.getPolicy($routeParams.policyReference);

			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;

			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};

	$scope.range = function () {
		var rangeSize = 3;
		var ret = [];
		var start;

		start = $scope.currentPage;
		if (start > $scope.pageCount() - rangeSize) {
			start = $scope.pageCount() - rangeSize + 1;
		}

		for (var i = start; i < start + rangeSize; i++) {
			ret.push(i);
		}
		return ret;
	};

	$scope.prevPage = function () {
		if ($scope.currentPage > 0) {
			$scope.currentPage--;
		}
	};

	$scope.prevPageDisabled = function () {
		return $scope.currentPage === 0 ? "disabled" : "";
	};

	$scope.pageCount = function () {
		return Math.ceil($scope.policies.length / $scope.itemsPerPage) - 1;
	};

	$scope.nextPage = function () {
		if ($scope.currentPage < $scope.pageCount()) {
			$scope.currentPage++;
		}
	};

	$scope.nextPageDisabled = function () {
		return $scope.currentPage === $scope.pageCount() ? "disabled" : "";
	};

	$scope.setPage = function (n) {
		$scope.currentPage = n;
	};

	$scope.commissionInfo = {
			'brokerCommission': '20%',
			'adminFee': '0.00',
			'initialFee': '0.00',
			'policyFee': '0.00',
			'umaFee': '0.00'
	};

	$scope.policyOptions = {
			'frequencyOptions': [
			                     'Declaration',
			                     'Other',
			                     ],
			                     'sasriaFrequencyOptions': [
			                                                'N/A',
			                                                'Other',
			                                                ],
			                                                'deviceOptions': [
			                                                                  'Nedbank Cameo',
			                                                                  'Standard Bnk',
			                                                                  'N/A',
			                                                                  ],
			                                                                  'reInstatements': [
			                                                                                     '',
			                                                                                     ],
	};

	$scope.wording = {
			'scheduleAttaching': '1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS\n 2) POLYGON GENERAL',
			'typeOfCover': 'Armed robbery, hijacking, and accidental damage...',
			'geographicalDuration': 'refer to special conditions',
			'specialCondition': 'Geographical and duration: Cash - once cash has recorded...'

	};
        
        
        
        $scope.schedulleAttaching = "1) SPECIALISED VALUABLES INSURANCE POLICY WORDING - GENERAL TERMS AND CONDITIONS\n\
                              2) POLYGON GENERAL COMPUTER NUCLEAR EXCEPTIONS \n\
                              3) POLYGON CASH AND VALUABLES IN TRANSIT WORDING \n\
                              4) VAULT AND STATIC RISK COVER WORDING";
    
        
        $scope.typeOfCover = "Theft, armed robbery, hijacking and accidental damages to the subject matter.  Excluding fraud, dishonesty or criminal  involvement of the\n\
                              Insured or their employees. \n\
                               Always subject to the terms and conditions of the policy.";
    
        $scope.specialPolicyConditions = "";
});

underwritter.controller('clientDetailsCtrl', function ($scope, $rootScope, $location, $routeParams) {
	$scope.client = {};
	$scope.policy = {};
	$scope.init = function () {
		$scope.getClient();


	};

	$scope.getClient = function () {
		angular.forEach($scope.policies, function (policy) {
			if (angular.equals($routeParams.policyReference, policy.policyReference)) {
				$scope.policy = policy;
				$scope.client = policy.client;
				console.log('Client :' + $scope.client);
				$location.path('/client/' + $routeParams.policyReference);
			}
		});

	};
});





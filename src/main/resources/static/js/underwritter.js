var underwritter = angular.module('underwritter', ['ngRoute']);

underwritter.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/policy-requests/:reference', {
		'templateUrl': '/html/underwritter.html',
		'controller': 'policyCtrl'
	}).when('/client', {
		'templateUrl': '/html/client-details.html',
		'controller': 'clientDetailsCtrl'
	}).when('/policy/:policyReference', {
		'templateUrl': '/html/policy.html',
		'controller': 'policyCtrl'
	}).otherwise({
		redirectTo: '/policy-requests'
	});
}]);

underwritter.filter('offset', function() {
	return function(input, start) {
		start = parseInt(start, 10);
		return input.slice(start);
	};
});

$(document).ready(function () {
	$("#regId").mouseout().css("text-transform", "uppercase");
});


underwritter.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams,$location) {

	$scope.mode;
	$scope.reference;
	$scope.reject;
	$scope.accept;
	$rootScope.policy={};
	$scope.itemsPerPage = 5;
	$scope.currentPage = 0;
	$scope.policies = [];

	$scope.init = function () {
		$scope.reference = $routeParams.reference;
		$scope.reject = {};
		$scope.accept = {};
		$scope.policyRequest = $scope.getPolicyRequest($routeParams.reference);
//		$scope.getPolicy($routeParams.policyReference);
		$scope.getPolicies();

	};


	$scope.getPolicyRequest = function (reference) {
		$http({
			url: '/api/policy-requests/' + reference,
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('policy Request retrived sucessfully');
				$scope.policyRequest = data;
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
	
	/* A UI selected policy to be displayed*/
	$scope.getPolicy = function(reference){
		angular.forEach($scope.policies,function(policy){
			if(angular.equals(reference,policy.policyReference)){
				$scope.policy = policy; // Assign a newly selected policy
				$location.path('/policy/'+$routeParams.policyReference);
			}
		});
			
	}

	$scope.commissionInfo = {
			'brokerCommission':'20%',
			'adminFee':'0.00',
			'initialFee':'0.00',
			'policyFee':'0.00',
			'umaFee':'0.00'
	}

	$scope.policyOptions = {
			'frequencyOptions':[
			                    'Declaration',
			                    'Other',
			                    ],
			                    'sasriaFrequencyOptions':[
			                                              'N/A',
			                                              'Other',
			                                              ],
			                                              'deviceOptions':[
			                                                               'Nedbank Cameo',
			                                                               'Standard Bnk',
			                                                               'N/A',
			                                                               ],
			                                                               'reInstatements':[
			                                                                                 '',

			                                                                                 ],
	};

	/*Get all list of policies*/
	$scope.getPolicies = function () {
		$http({
			url: '/api/policies',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('All policies retrived sucessfully');
				$scope.policies = data;
				console.log($scope.policies);
				$scope.getPolicy($routeParams.policyReference);
				$location.path('/policy/'+$routeParams.policyReference);
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;

			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};

	$scope.range = function() {
		var rangeSize = 2;
		var ret = [];
		var start;

		start = $scope.currentPage;
		if ( start > $scope.pageCount()-rangeSize ) {
			start = $scope.pageCount()-rangeSize+1;
		}

		for (var i=start; i<start+rangeSize; i++) {
			ret.push(i);
		}
		return ret;
	};

	$scope.prevPage = function() {
		if ($scope.currentPage > 0) {
			$scope.currentPage--;
		}
	};

	$scope.prevPageDisabled = function() {
		return $scope.currentPage === 0 ? "disabled" : "";
	};

	$scope.pageCount = function() {
		return Math.ceil($scope.policies.length/$scope.itemsPerPage)-1;
	};

	$scope.nextPage = function() {
		if ($scope.currentPage < $scope.pageCount()) {
			$scope.currentPage++;
		}
	};

	$scope.nextPageDisabled = function() {
		return $scope.currentPage === $scope.pageCount() ? "disabled" : "";
	};

	$scope.setPage = function(n) {
		$scope.currentPage = n;
	};
});

underwritter.controller('clientDetailsCtrl', function ($scope, $rootScope, $routeParams) {

	$scope.init = function () {
		$scope.getClient();

	};

	$scope.getClient = function () {
		if($rootScope.policy == undefined){
			console.log('Policy object doesn\'t exists. Calling one ');
			$rootScope.getPolicy($routeParams.policyReference);
		}else{
			$scope.client = $rootScope.policy.client;
		}
	};
});





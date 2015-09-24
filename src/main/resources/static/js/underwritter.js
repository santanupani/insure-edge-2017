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

underwritter.controller('policyRequestCtrl',function($scope, $rootScope, $http, $routeParams){
	$scope.mode;
	$scope.reference;
	$scope.reject;
	$scope.accept;
//	$rootScope.policyRequest;

	$scope.init = function(){
		$scope.reference = $routeParams.reference;
		$scope.reject = {};
		$scope.accept = {};
//		$rootScope.policyRequest = {};
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
				console.log('Returned policy request: '+data);
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

	$scope.policy = {};
	$scope.policy.client = {};
	$scope.policy.client.contact = {};
	$scope.policy.client.bankAccount = {};
	$scope.policy.policySchedule = {};
	$scope.policy.policySchedule.indemnityOption = [{}];

	$scope.init = function () {
		console.log('Client Policy request: '+$rootScope.policyRequest.quotation.quotationRequest.reference);
		$scope.getPolicies();
		console.log('Policies size :'+$rootScope.policies.length);
		$scope.initNewPolicy($rootScope.policy,$rootScope.policyRequest);

	};

	/*Initialize new policy*/
	$scope.initNewPolicy = function (policy,policyRequest) {


		if (policy != undefined) {
			console.log('Policy request :'+policyRequest);
			$scope.policy.policyReference = 'newre-feren-ce-0000-000';
			$scope.policy.brokerFee = 20;

			/*Initializing the Policy contact details*/
			$scope.policy.underwritingYear = 2015;
			$scope.policy.renewalDate = '7/12/2015';
			$scope.policy.retroActiveDate = '7/12/2015';
			$scope.policy.notes = $scope.wording.notes;

			/*Initializing the Client's contact details*/
			$scope.policy.client.clientName = $rootScope.policyRequest.quotation.quotationRequest.companyName;
			$scope.policy.client.contact.contactPerson = $rootScope.policyRequest.quotation.quotationRequest.applicantName;
			$scope.policy.client.contact.email = $rootScope.policyRequest.quotation.quotationRequest.applicantEmail;
			$scope.policy.client.contact.street = $rootScope.policyRequest.streetAddress;
			$scope.policy.client.contact.workTelNumber = $rootScope.policyRequest.telephoneNumber;
			$scope.policy.client.contact.faxNumber = $rootScope.policyRequest.faxNumber;
			$scope.policy.client.contact.code = $rootScope.policyRequest.quotation.quotationRequest.applicantName;
			$scope.policy.client.contact.street = $rootScope.policyRequest.quotation.quotationRequest.applicantName;

			/*Initializing the Client's bankAccoutn details*/
			$scope.policy.client.bankAccount.branch = $rootScope.policyRequest.branchCode;
			$scope.policy.client.bankAccount.accountName = $rootScope.policyRequest.accountName;
			$scope.policy.client.bankAccount.bankName = $rootScope.policyRequest.bankName;
			$scope.policy.client.bankAccount.accountNumber = $rootScope.policyRequest.accountNumber;

			/*Initializing the Client's PolicySchedule details*/
			$scope.policy.policySchedule.scheduleAttaching = $scope.wording.scheduleAttaching;
			$scope.policy.policySchedule.premium = $rootScope.policyRequest.quotationOption.premium;
			$scope.policy.policySchedule.excessStructure = $rootScope.policyRequest.quotationOption.excess;
                        
                  

			angular.forEach($rootScope.policyRequest.quotation.quotationRequest.questionnaire,function(questionnairre){
				switch(questionnairre.question){
				case 'What do you wish to insure ?':
					console.log('Question:'+questionnairre.question+', answer is: '+questionnairre.answer);
					$scope.policy.policySchedule.subjectMatter = questionnairre.answer;
				case 'What is the maximum amount you wish to insure ?':
					console.log('Question:'+questionnairre.question+', answer is: '+questionnairre.answer);
					$scope.policy.policySchedule.maximumSumInsured = questionnairre.answer;
					$scope.policy.policySchedule.sumInsured = $scope.policy.policySchedule.maximumSumInsured;
				case 'Please specify policy insecption date for annual cover :':
					console.log('Question:'+questionnairre.question+', answer is: '+questionnairre.answer);
					$scope.policy.inceptionDate = questionnairre.answer;
				}
			});
			$scope.policy.policySchedule.typeOfCover = $scope.wording.typeOfCover;
			$scope.policy.policySchedule.geographicalDuration = $scope.wording.geographicalDuration;
			$scope.policy.policySchedule.specialCondition = $scope.wording.specialCondition;

			$rootScope.policies.push($scope.policy);
			console.log('Policies size :'+$rootScope.policies.length);

		} else {
			$scope.policy = $rootScope.policy;
			console.log('Existing Policy :'+$rootScope.policy.client.clientName);
		}
	};

	$scope.submitForPolicy = function (form) {
		$http({
			url: '/api/policy',
			method: 'post',
			headers: {
				'Content-Type': 'application/json',
			},
			data: $scope.policy
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
	};

	/* A UI selected policy to be displayed*/
	$rootScope.getPolicy = function (reference) {
		angular.forEach($scope.policies, function (policy) {
			if (angular.equals(reference, policy.policyReference)) {
				$scope.policy = policy;
				$location.path('/policy/' + reference);
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
				console.log('Policies size :'+$rootScope.policies.length);
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
			                                                                                     'Nedbank Cameo',
			                                                                                     'Standard Bnk',
			                                                                                     'N/A',
			                                                                                     ],
			                                                                                     'stats': [
			                                                                                                  'Active',
			                                                                                                  'Terminated',
			                                                                                                  'Cancelled',
			                                                                                                  ],
			 			                                                                                     'subAgentNames': [
						                                                                                                  'Thabo',
						                                                                                                  'Binod',
						                                                                                                  'Manmay',
						                                                                                                  'Ardhendu'
						                                                                                                  ]
	};

	$scope.wording = {
			'scheduleAttaching': '1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS\n2) POLYGON GENERAL',
			'typeOfCover': 'Armed robbery, hijacking, and accidental damage...',
			'geographicalDuration': 'refer to special conditions',
			'specialCondition': 'Geographical and duration: Cash - once cash has recorded...',
			'notes':'[Add notes for this policy]'

	};
        
        
        
        $scope.schedulleAttaching = "1) SPECIALISED VALUABLES INSURANCE POLICY WORDING - GENERAL TERMS AND CONDITIONS" +
                                     "\n2) POLYGON GENERAL COMPUTER NUCLEAR EXCEPTIONS" +
                                     "\n3) POLYGON CASH AND VALUABLES IN TRANSIT WORDING" +
                                     "\n4) VAULT AND STATIC RISK COVER WORDING";
    
        
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
				$scope.policy.client = policy.client;
				console.log('Client :' + $scope.policy.client);
				$location.path('/client/' + $routeParams.policyReference);
			}
		});

	};
});





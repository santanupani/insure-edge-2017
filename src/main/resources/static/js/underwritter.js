var underwritter = angular.module('underwritter', ['ngRoute', 'ngCookies']);
underwritter.config(['$routeProvider', function ($routeProvider) {

	$routeProvider
	.when('/policy-requests/:reference', {
		'templateUrl': '/html/underwritter.html',
		'controller': 'policyRequestCtrl'
	}).when('/clients', {
		'templateUrl': '/html/client-list.html',
		'controller': 'listClientCtrl'
	}).when('/clients/:clientId', {
		'templateUrl': '/html/clients.html',
		'controller': 'viewClientCtrl'
	}).when('/policy/:reference', {
		'templateUrl': '/html/policy.html',
		'controller': 'policyCtrl'
	}).when('/policies', {
		'templateUrl': '/html/policy.html',
		'controller': 'policyCtrl'
	}).when('/policy-request-list', {
		'templateUrl': '/html/policy-request-list.html',
		'controller': 'getAllPolicyRequestCtrl'
	}).when('/policy-list', {
		'templateUrl': '/html/policy-list.html',
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

underwritter.controller('underwritterCtrl', function ($scope, $rootScope, $http, $cookies, $window) {

	$scope.init = function(){
		if($cookies.token == undefined){
			console.log($window);
			$window.location.href= "/login?state="+encodeURIComponent($window.location.href);
		} else {
			$scope.validate($cookies.token);
		}
	};

	$scope.validate = function(token){
		$http({
			method: 'POST',
			url: '/validate',
			headers: {
				"Content-Type": "text/html"
			},
			data: token
		}).success(function(data, status){
			$rootScope.session = data;
			$http.defaults.headers.common['Authorization'] = 'Basic ' + $cookies.token;
		}).error(function(error, status){
			$window.location.href= "/logout";
		});
	};

});

underwritter.controller('policyRequestCtrl', function ($scope, $rootScope, $http, $routeParams, $cookieStore) {
	$scope.mode;
	$rootScope.reference;
	$scope.reject;
	$scope.accept;

	$scope.init = function () {
		$scope.reference = $routeParams.reference;
		$scope.reject = {};
		$scope.accept = {};
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
				console.log($rootScope.policyRequest.quotation.quotationRequest.locationOptions[0].premium);
				$cookieStore.put('reference', $rootScope.policyRequest.quotation.quotationRequest.reference);
				console.log('Returned policy request: ' + data);
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

underwritter.controller('getAllPolicyRequestCtrl', function ($scope, $http, $rootScope) {

	$scope.init = function () {
		$scope.getAllPolicyRequest();
	};

	$scope.getAllPolicyRequest = function () {
		$scope.request = [];
		$http({
			url: '/api/policy-requests',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log(' all policy Request retrived sucessfully');
				$scope.policyRequest = data;
				$scope.request = data;
				$scope.policyRequestSize = $scope.request.length;
				console.log('Returned policy request: ' + data);
				console.log('Returned policy request: ' + $scope.request.length);
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

underwritter.controller('listClientCtrl', function ($scope, $rootScope, $http) {

	$scope.clients = {};

	$scope.init = function () {
		$scope.getAllClients();
	};


	$scope.getAllClients = function () {
		$http({
			url: '/api/clients',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log(' all clients retrived sucessfully');
				$scope.clients = data;
				console.log('Returned Cleint: ' + data);
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

underwritter.controller('viewClientCtrl', function ($scope, $rootScope, $http, $routeParams, $window) {

	$scope.client = {};
	$rootScope.policy = {};
	$rootScope.policies = [];
	$scope.isDisabled = false;

	$scope.init = function () {

		$scope.getPolicies();
		$scope.is_readonly = true;
		$scope.clientId = $routeParams.clientId;

	};


	$scope.getPolicies = function () {
		$http({
			url: '/api/policies',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('All policies retrived sucessfully');
				$rootScope.policies = data;
				console.log($rootScope.policies);
				console.log('Policies size :' + $rootScope.policies.length);
				$scope.getClient($scope.clientId);
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;

			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};

	$rootScope.getClient = function () {

		$http({
			url: '/api/clients/' + $scope.clientId,
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('Client retrived');
				$scope.client = data;
				console.log('Client : ' + $scope.client.clientName);
				console.log('Client : ' + $scope.client.clientId);
				$scope.getClientPolicies($scope.client.clientId);
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;

			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});

	};


	$scope.edit = function (isReadonly, isDisabled) {

		$scope.is_readonly = isReadonly;
		$scope.isDisabled = isDisabled;
	};

	$scope.updateClient = function () {

		console.log("client " + $scope.client.clientId);
		console.log('update client');
		$http({
			url: '/api/client/' + $scope.client.clientId,
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.client
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				$rootScope.message = "Client Updated Successfully";
				console.log('Client Updated');
				$window.location.reload();
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			$rootScope.message = "Oops we recieved your request but there was a problem processing it";
			console.log(error);
		});
	};

	$scope.getClientPolicies = function (clientId) {
		$scope.clientPolicies = [];
		console.log("Policies: " + $rootScope.policies);
		angular.forEach($rootScope.policies, function (policy) {
			if (angular.equals(policy.client.clientId, clientId)) {
				console.log("Policy obtained " + policy.client.clientName);
				$scope.clientPolicies.push(policy);
			} else {
				console.log("Not matched....");
			}
		});
	};

});

underwritter.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams, $location, $sce, $filter, $window, $cookieStore){

	$rootScope.policy = {};
	$scope.itemsPerPage = 5;
	$scope.currentPage = 0;
	$rootScope.policies = [];
	$scope.policy = {};
	$scope.policy.client = {};
	$scope.policy.client.contact = {};
	$scope.policy.client.bankAccount = {};
	$scope.policy.indemnityOption = [{}];
	$scope.btnValue;
	$scope.init = function () {

		$scope.getClient();
		$scope.btnValue = (angular.equals($scope.policy.policyReference, '[YYYY-MM00]')) ? 'Update' : 'Create Policy';

		if($rootScope.policyRequest == undefined){
			console.log('Working on existing policy');
			$scope.getPolicies();
			$scope.getSubAgents();
		}else{
			console.log('New policy creation');
			$scope.isUpdate = false;
			$scope.initNewPolicy($rootScope.policy);
//			$scope.getPolicyRequest($cookieStore.get('reference'));
			$scope.getPolicies();
			$scope.getSubAgents();
		}
	};

	$scope.updatePolicy = function(reference){
		console.log('Update Policy');
		console.log('Policy Fee '+$scope.policy.policyFee);
		$http({
			url: '/api/policy-update/' + reference,
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.policy
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				$rootScope.message = "Policy Updated Successfully";
				console.log('Policy Updated');
				$window.location.reload();
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			$rootScope.message = "Oops we recieved your request but there was a problem processing it";
			console.log(error);
		});
	};

	$scope.submitForPolicySchedule = function(reference){
		console.log('Ref: ' + reference);
		$http({
			url: '/api/policy-schedules/' + reference,
			responseType:'arraybuffer',
			headers: { 'Accept': 'application/pdf' },
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('Retrieving policy schedule PDF');
				var file = new Blob([data], {type: 'application/pdf'});
				var fileURL = URL.createObjectURL(file);
				$scope.policyScheduleContent = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};


	$scope.getPolicyRequest = function (reference) {
		console.log('Ref: ' + reference);
		$http({
			url: '/api/policy-request/' + reference,
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('Retrieving policyRequest for policy creation');
				$rootScope.policyRequest = data;
				console.log('Returned policy request: ' + $rootScope.policyRequest.quotation.quotationRequest.reference);

			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	/*Initialize new policy*/
	$scope.initNewPolicy = function (policy) {

		$scope.noOfTimesPerWeek;
		if (policy != undefined) {
			console.log('initializing new policy state');
			$scope.policy.reference = '[YYYY-MM00]';
			$scope.policy.brokerFee = 20;
			$scope.policy.status = 'Active';
			$scope.policy.underwriterFee = 12.5;
			$scope.policy.policyFee = '0.0';
			$scope.policy.initialFee = '0.0';
			$scope.policy.umaFee = '0.0';
			$scope.policy.collectByDebitOrder = true;
			$scope.policy.brokerCommission = 12.5;
			$scope.policy.underwritingYear = new Date().getFullYear();;
			$scope.policy.notes = $scope.wording.notes;

			$scope.policy.productName = $rootScope.policyRequest.quotation.quotationRequest.product.name;
			$scope.policy.underwriterId = 1;
			$scope.policy.client.clientName = $rootScope.policyRequest.quotation.quotationRequest.companyName;
			$scope.policy.client.regNumber = $rootScope.policyRequest.vatRegNumber;
			$scope.policy.client.incomeTaxNumber = $rootScope.policyRequest.accountName;
			$scope.policy.client.designation = $rootScope.policyRequest.designation;
			$scope.policy.client.vatNumber = $rootScope.policyRequest.vatRegNumber;
			$scope.policy.client.contact.contactPerson = $rootScope.policyRequest.quotation.quotationRequest.applicantName;
			$scope.policy.client.contact.email = $rootScope.policyRequest.quotation.quotationRequest.applicantEmail;
			$scope.policy.client.contact.street = $rootScope.policyRequest.streetAddress;
			$scope.policy.client.contact.workTelNumber = $rootScope.policyRequest.telephoneNumber;
			$scope.policy.client.contact.faxNumber = $rootScope.policyRequest.faxNumber;
			$scope.policy.client.contact.code = $rootScope.policyRequest.postalCode;
			$scope.policy.client.contact.suburb = $rootScope.policyRequest.suburb;
			$scope.policy.client.bankAccount.branch = $rootScope.policyRequest.branchCode;
			$scope.policy.client.bankAccount.accountName = $rootScope.policyRequest.accountName;
			$scope.policy.client.bankAccount.accountType = $rootScope.policyRequest.accType;
			$scope.policy.client.bankAccount.bankName = $rootScope.policyRequest.bankName;
			$scope.policy.client.bankAccount.accountNumber = $rootScope.policyRequest.accountNumber;
			$scope.policy.scheduleAttaching = $scope.wording.scheduleAttaching;
			$scope.policy.specialCondition = $scope.wording.specialCondition;

			angular.forEach($scope.policyRequest.quotation.quotationRequest.questionnaire, function (questionnairre) {
				switch (questionnairre.question) {
				case 'Please select the duration for your cover  ?':
					console.log('Question for duration:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
					$scope.policy.frequency = questionnairre.answer;
					break;	
				case 'Do you require SASRIA cover ?':
					console.log('Question for duration:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
					$scope.policy.excludeSasria = questionnairre.answer;
					break;	
				case 'What is the maximum amount you wish to insure ?':
					console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
					var maxSumToken = questionnairre.answer.split(',');
					var tokenMax = '';
					for (i = 0; i < maxSumToken.length; i++) {
						tokenMax += maxSumToken[i];
					}
					$scope.policy.maximumSumInsured = tokenMax;
					$scope.policy.sumInsured = $scope.policy.maximumSumInsured;
					break;

				case 'Please specify policy inception date for annual cover :':
					console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
					if(angular.equals(questionnairre.answer,null)){
						break;
					}else{
						var subString = new Date($filter('limitTo')(questionnairre.answer, 10, 0));
						$scope.policy.policyInceptionDate = questionnairre.answer;
						console.log('Policy inception date: '+$scope.policy.policyInceptionDate);
						break;
					}
				case 'Please specify policy inception date for monthly cover :':
					console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
					if(angular.equals(questionnairre.answer,null)){
						break;
					}else{
						var subString = new Date($filter('limitTo')(questionnairre.answer, 10, 0));
						$scope.policy.policyInceptionDate = questionnairre.answer
						console.log('Policy inception date: '+$scope.policy.policyInceptionDate);
						break;
					}
				case 'Please specify from date for once-off:':
					console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
					if(angular.equals(questionnairre.answer,null)){
						break;
					}else{
						var subString = new Date($filter('limitTo')(questionnairre.answer, 10, 0));
						$scope.policy.policyInceptionDate = questionnairre.answer
						console.log('Policy inception date: '+$scope.policy.policyInceptionDate);
						break;
					}
					case 'How many times per week are the valuables carried ?':
					console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
					$scope.noOfTimesPerWeek = questionnairre.answer;
					console.log("week " + $scope.noOfTimesPerWeek);
					break;
					}
				});
			$scope.policy.typeOfCover = $rootScope.policyRequest.quotation.option[0].cover;
			$scope.policy.subjectMatter = $rootScope.policyRequest.quotation.option[0].commodity;
			$scope.policy.excessStructure = $rootScope.policyRequest.quotation.option[0].excess;
			$scope.policy.geographicalDuration = $scope.wording.geographicalDuration;
			var conveyans = '';
			angular.forEach($scope.policyRequest.quotation.quotationRequest.locationOptions,function(locationOption){
				if($scope.policyRequest.quotation.quotationRequest.product.id == 1 || $scope.policyRequest.quotation.quotationRequest.product.id == 4){
					conveyans = conveyans.concat('Per Road per Vehicle of '+locationOption.professionalCarriers+'\n');
				}else{
					conveyans = conveyans.concat('Vault or other specific storage');
				}
			});
			$scope.policy.conveyances = conveyans;

			$scope.options = $rootScope.policyRequest.quotation.option;
			$scope.policy.indemnityOption = [];
			for(var i=0; i < $scope.options.length; i++){
				if($scope.options[i].staticLimit == 0 ){
					if($rootScope.policyRequest.quotation.quotationRequest.product.id == 1){

						$scope.policy.indemnityOption[i] = {};
						$scope.policy.indemnityOption[i].indemnityItemOption = 'Policy Limit';
						$scope.policy.indemnityOption[i].indemnityValue = $scope.options[i].duration + ' whilst in transit and per cross pavement carry limit';
						$scope.policy.indemnityOption[i].sumInsured = $scope.options[i].limit;
						$scope.policy.indemnityOption[i].pavement = $scope.options[i].pavement;
						$scope.policy.indemnityOption[i].premium = $scope.options[i].premium;

					}else if($rootScope.policyRequest.quotation.quotationRequest.product.id == 2
							|| $rootScope.policyRequest.quotation.quotationRequest.product.id == 3){
						$scope.policy.indemnityOption[i] = {};
						$scope.policy.indemnityOption[i].indemnityItemOption = 'Vault Limit';
						$scope.policy.indemnityOption[i].indemnityValue = $scope.options[i].duration+' Service';
						$scope.policy.indemnityOption[i].sumInsured = $scope.options[i].limit;
						$scope.policy.indemnityOption[i].premium = $scope.options[i].premium;
					}
				}else{
					if($rootScope.policyRequest.quotation.quotationRequest.product.id == 4){
						$scope.policy.indemnityOption[i] = {};
						$scope.policy.indemnityOption[i].indemnityItemOption = 'Policy/Vault Limit';
						$scope.policy.indemnityOption[i].indemnityValue = $scope.options[i].duration + ' service whilst in transit and per cross-pavement carry limit';
						$scope.policy.indemnityOption[i].pavement = $scope.options[i].pavement;
						$scope.policy.indemnityOption[i].staticLimit = $scope.options[i].staticLimit;
						$scope.policy.indemnityOption[i].sumInsured = $scope.options[i].limit;
						$scope.policy.indemnityOption[i].premium = $scope.options[i].premium;
					}
				}
			};
			} else {
				$scope.policy = $rootScope.policy;
				console.log('Existing Policy :' + $scope.policy.client.clientName);
			}
		};

		$scope.update = function (isUpdated) {
			$scope.isUpdate = isUpdated;
		};

		$scope.cancel = function(isUpdated){
			$window.location.reload();
		};

		$scope.submitForPolicy = function (isValid) {
			if (!isValid) {
				console.log("Form Validation Failure");
			} else {
				$http({
					url: '/api/policy',
					method: 'post',
					headers: {
						'Content-Type': 'application/json',
					},
					data: $scope.policy
				}).success(function (data, status) {
					if (status == 200) {
						console.log('New policy saved successfully');
						$rootScope.message = "Reference Number : " + data;
						$location.path('/policy/'+data);
					} else {
						console.log('status:' + status);
					}
				}).error(function (error) {
					console.log(error);
					$rootScope.message = error;
				});
			}

		};


		$rootScope.getPolicy = function (reference) {
			angular.forEach($rootScope.policies, function (policy) {
				if (angular.equals(reference, policy.reference)) {
					$scope.policy = policy;
					$scope.update(true);
					$scope.btnValue = 'Save Changes';
					$location.path('/policy/' + reference);
				}
			});
		};


		$scope.getPolicies = function () {
			$http({
				url: '/api/policies',
				method: 'get'
			}).success(function (data, status) {
				if (status == 200) {
					console.log('All policies retrived sucessfully');
					$rootScope.policies = data;
					console.log($rootScope.policies);
					console.log('Policies size :' + $rootScope.policies.length);
					$rootScope.getPolicy($routeParams.reference);
				} else {
					console.log('status:' + status);
					$rootScope.error = "error status code : " + status;
				}
			}).error(function (error) {
				console.log(error);
				$rootScope.error = error;
			});
		};
		$scope.getSubAgents = function () {
			$http({
				url: '/api/sub-agents',
				method: 'get'
			}).success(function (data, status) {
				if (status == 200) {
					console.log('All subAgents retrived sucessfully');
					$scope.subAgents = data;
					console.log($scope.subAgents);
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
			var rangeSize = 0;
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
				                     'Annually',
				                     'Monthly',
				                     'Once Off'
				                     ],
				                     'sasriaFrequencyOptions': [
				                                                'Annually',
				                                                'Monthly',
				                                                'N/A'
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
				                                                                                               ]
		};
		$scope.wording = {
				'scheduleAttaching': '1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS' +
				'\n2) POLYGON GENERAL COMPUTER NUCLEAR EXCEPTIONS\n3) POLYGON CASH AND VALUABLES IN TRANSIT WORDING' +
				'\n4) VAULT AND STATIC RISK COVER WORDING',
				'typeOfCover': 'Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured. ' +
				'whilst in the custody and care of Protea Coin Group and/or whilst within the Nedbank Camera Managed Unit at the premises declared' +
				'to Insurers, Excluding fraud, dishonesty or criminal involvement of the Insured or their employees.',
				'geographicalDuration': 'refer to special conditions',
				'specialCondition': '1) Geographical and duration: Cash - once cash has recorded as deposited into the CIMA Managed Unit including authorised collection of the security.' +
				'\n2) Premium: The minimum monthly premium of R0.00 is made up of: R0.00 miniun deposit',
				'conveyances': 'Per Road vehicle of Protea Coin Group',
				'notes': ''

		};
		$scope.getClient = function () {
			angular.forEach($rootScope.policies, function (policy) {
				if (angular.equals($routeParams.reference, policy.reference)) {
					$scope.policy = policy;
					$scope.policy.client = policy.client;
					console.log('Client UI policies size: ' + $rootScope.policies.length);
					console.log('Client :' + $scope.policy.client);
					$location.path('/client/' + $routeParams.reference);
				}
			});
		};
	});

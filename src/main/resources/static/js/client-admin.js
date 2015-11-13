var clientAdmin = angular.module('clientAdmin', ['ngRoute']);

clientAdmin.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/clients/:clientId', {
		'templateUrl': '/html/client-admin.html',
		'controller': 'clientAdminCtrl'
	}).when('/policy-request/:reference', {
		'templateUrl': '/html/generic-policy-requests.html',
		'controller': 'genericPolicyRequestsCtrl'
	}).when('/claims/:reference', {
		'templateUrl': '/html/claim.html',
		'controller': 'claimCtrl'
	}).otherwise({
		redirectTo: '/clients'
	});
}]);


clientAdmin.directive('fileModel', ['$parse', function ($parse) {

	return {
		restrict: 'A',
		link: function (scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function () {
				scope.$apply(function () {
					modelSetter(scope, element[0].files[0]);
					console.log("Taking a file  " + scope.claimQuestionnaire.attachment);
					console.log(scope.claimQuestionnaire.attachment);
				});
			});
		}
	};
}]);

clientAdmin.controller('genericPolicyRequestsCtrl', function ($scope, $routeParams, $http, $rootScope, $window,$location) {

	$scope.policyRequestType = {};
	$scope.init = function () {
		$scope.getRequestType();
		$scope.policyRequestType.policyNo = $routeParams.reference;

	};

	$scope.submitGenericRequest = function (form) {

		if (form.$invalid) {
			console.log("Form Validation Failure");
		} else {
			console.log("Form Validation Success");
			$scope.policyRequestType.requestQuestionnaire = [];
			
			console.log("Request type ID: "+$scope.policyRequestType.requestTypeId);
			for (var i = 0; i < $scope.requestQuestionnaires.length; i++) {
				$scope.policyRequestType.requestQuestionnaire[i] = {};
				
				$scope.policyRequestType.requestQuestionnaire[i].question = $scope.requestQuestionnaires[i].question;
				$scope.policyRequestType.requestQuestionnaire[i].answer = $scope.requestQuestionnaires[i].answer;
			}
			
			
			console.log($scope.policyRequestType);
			$http({
				url: '/api/generic-policy-requests',
				method: 'post',
				headers: {
					'Content-Type': 'application/json',
				},
				data: $scope.policyRequestType
			}).success(function (data, status) {
				if (status == 200) {
					console.log('All the questions and answers saved succesfullly');
					$rootScope.message = "Reference Number : " + data;
					$location.path("/policy-request/"+$scope.policyRequestType.policyNo);
				} else {
					console.log('status:' + status);
				}
			}).error(function (error) {
				console.log(error);
				$rootScope.message = error;
			});
		}
	};

	$scope.getRequestQuestionnaire = function (requestTypeId) {

		$http({
			url: '/api/request-type/' + requestTypeId + '/request-questionnaire',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('retrived successfully');
				$scope.requestQuestionnaires = data;
				for (var i = 0; i < $scope.requestQuestionnaires.length; i++) {
					if ($scope.requestQuestionnaires[i].requestAnswerType == 'checkbox') {
						$scope.requestQuestionnaires[i].answer = 'false';
					}
				}
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


	$scope.getRequestType = function () {
		console.log('get Request Types');
		$http({
			url: '/api/request-types',
			method: 'get'
		}).success(function (data, status) {
			if (status === 200) {
				console.log('retrived successfully');
				$scope.requestTypes = data;
				console.log('Request Type: ' + $scope.requestTypes[0].requestType);
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;
				;
			}
		}).error(function (error) {
			$rootScope.message = "Oops, we received your request, but there was an error processing it";
		});
	};


	$rootScope.getPolicy = function (reference) {
		angular.forEach($rootScope.policies, function (policy) {
			if (angular.equals(reference, policy.reference)) {
				$scope.policy = policy;

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

	$scope.cancelPolicy = function(reference){

	};

});

clientAdmin.controller('clientAdminCtrl', function ($scope, $routeParams, $http, $rootScope, $window) {


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

clientAdmin.controller('claimCtrl', function ($scope, $rootScope, $http, $routeParams, $location) {

    $scope.init = function () {
        $scope.getClaimType();
        $scope.reference = $routeParams.reference;
    };

    $scope.getClaimQuestionnaire = function (clientTypeId) {


        $http({
            url: '/api/claim-types/' + clientTypeId + '/claim-questionnaires',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('retrived successfully');
                $scope.claimQuestionnaires = data;
                for (var i = 0; i < $scope.claimQuestionnaires.length; i++) {
                    if ($scope.claimQuestionnaires[i].claimAnswerType == 'checkbox') {

                        $scope.claimQuestionnaires[i].answer = 'false';
                    }
                }
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


    $scope.getClaimType = function () {
        console.log('get Claim types');
        $http({
            url: '/api/claim-types',
            method: 'get'
        }).success(function (data, status) {
            if (status === 200) {
                console.log('retrived successfully');
                $scope.claimTypes = data;
                console.log('Claim Type: ' + $scope.claimTypes[0].claimType);
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;
                ;
            }
        }).error(function (error) {
            $rootScope.message = "Oops, we received your request, but there was an error processing it";
        });
    };

    $scope.getClaimQuestionnaire = function (clientTypeId) {


        $http({
            url: '/api/claim-types/' + clientTypeId + '/claim-questionnaires',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('retrived successfully');
                $rootScope.claimQuestionnaires = data;
                for (var i = 0; i < $scope.claimQuestionnaires.length; i++) {
                    if ($scope.claimQuestionnaires[i].claimAnswerType == 'checkbox') {

                        $scope.claimQuestionnaires[i].answer = 'false';
                    }
                }
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


    $scope.submitClaimRequest = function (form) {

        if (form.$invalid) {
            console.log("Form Validation Failure");
        } else {
            console.log("Form Validation Success");
            $scope.claimRequest.claimQuestionnaires = [];
            $scope.files = [];

            for (var i = 0; i < $scope.claimQuestionnaires.length; i++) {
                $scope.claimRequest.claimQuestionnaires[i] = {};
                if ($scope.claimQuestionnaires[i].claimAnswerType == 'blob'){
                    $scope.claimRequest.claimQuestionnaires[i].question = $scope.claimQuestionnaires[i].question;
                    $scope.claimRequest.claimQuestionnaires[i].attachment = null;
                    console.log($scope.claimQuestionnaires[i].attachment);
                    $scope.files[i] = {};
                    if ($scope.claimQuestionnaires[i].attachment == undefined) {
                        console.log("it is null");
                    } else {
                        $scope.files[i] = $scope.claimQuestionnaires[i].attachment;
                        console.log("got it");
                    }
                }
                else {

                    $scope.claimRequest.claimQuestionnaires[i].question = $scope.claimQuestionnaires[i].question;
                    $scope.claimRequest.claimQuestionnaires[i].answer = $scope.claimQuestionnaires[i].answer;

                }

            }
            console.log($scope.claimRequest);
            console.log($scope.files);
            $scope.claimRequest.reference = $routeParams.reference;
            console.log($routeParams.reference);

            $http({
                url: '/api/claim-requests',
                method: 'post',
                headers: {
                    'Content-Type': undefined,
                },
                transformRequest: function (data) {
                    var formData = new FormData();

                    formData.append('claimRequest', new Blob([angular.toJson(data.claimRequest)], {
                        type: "application/json"
                    }));

                    console.log("transform request");

                    for (var i = 23; i < data.files.length; i++) {
                        formData.append('file' + i, data.files[i]);
                        console.log(data.files[i]);
                        console.log("appending...");
                    }
                    console.log("File size: " + data.files.length);

                    return formData;
                    console.log(data);
                },
                data: {claimRequest: $scope.claimRequest, files: $scope.files}

            }).success(function (data, status) {
                if (status == 200) {
                    console.log('All the questions and answers saved succesfullly');
                    $rootScope.message = "Claim Number : " + data;
                    $location.path("/clients/" + 2);
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
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
        }).when('/policy/:policyReference', {
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

underwritter.controller('policyRequestCtrl', function ($scope, $rootScope, $http, $routeParams, $cookieStore) {
    $scope.mode;
    $scope.reference;
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
        $http({
            url: '/api/policy-requests',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log(' all policy Request retrived sucessfully');
                $scope.policyRequest = data;
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
});


underwritter.controller('listClientCtrl', function ($scope, $rootScope, $http) {

    $scope.clients = {};
    $scope.init = function () {
        $scope.getAllClients();
    };
    
        if ($scope.policies == undefined) {
            console.log('Policies don\'t exists.');
            $scope.getPolicies();
        }
        $scope.getClient();

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

underwritter.controller('viewClientCtrl', function ($scope, $rootScope, $http, $routeParams, $location) {

    $scope.client = {};

    $scope.isDisabled = false;

    $scope.init = function () {

        $scope.is_readonly = true;
        $scope.clientId = $routeParams.clientId;

        $scope.getClient($scope.clientId);
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
            } else {
                console.log('status:' + status);
            }
        })
                .error(function (error) {
                    $rootScope.message = "Oops we recieved your request but there was a problem processing it";
                    console.log(error);
                });
    };

    $scope.closeNotification = function () {
        $rootScope.message = undefined;
    };
});


underwritter.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams, $location, $cookieStore) {

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
        $scope.isUpdate = false;
        $scope.getClient();
        $scope.btnValue = (angular.equals($scope.policy.policyReference, 'New-Policy-Creation')) ? 'Update' : 'Create Policy';
        $scope.getPolicies();
        $scope.getPolicyRequest($cookieStore.get('reference'))
        $scope.getSubAgents();
        $scope.initNewPolicy($rootScope.policy);
        $scope.policies.push($rootScope.policy);

    };

    $scope.getPolicyRequest = function (reference) {
        console.log('Ref: ' + reference);

        $http({
            url: '/api/policy-requests/' + reference,
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

        if (policy != undefined) {
            $scope.getPolicyRequest($cookieStore.get('reference'));
            console.log('initializing new policy state');
            $scope.policy.brokerFee = 20;
            $scope.policy.underwritingYear = 2015;
            $scope.policy.notes = $scope.wording.notes;
            $scope.policy.underwriterId = 1;
            $scope.policy.client.clientName = $rootScope.policyRequest.quotation.quotationRequest.companyName;
            $scope.policy.client.regNumber = $rootScope.policyRequest.vatRegNumber;
            $scope.policy.client.incomeTaxNumber = $rootScope.policyRequest.accountName;
            $scope.policy.client.vatNumber = $rootScope.policyRequest.vatRegNumber;
            $scope.policy.client.contact.contactPerson = $rootScope.policyRequest.quotation.quotationRequest.applicantName;
            $scope.policy.client.contact.email = $rootScope.policyRequest.quotation.quotationRequest.applicantEmail;
            $scope.policy.client.contact.street = $rootScope.policyRequest.streetAddress;
            $scope.policy.client.contact.workTelNumber = $rootScope.policyRequest.telephoneNumber;
            $scope.policy.client.contact.faxNumber = $rootScope.policyRequest.faxNumber;
            $scope.policy.client.contact.code = $rootScope.policyRequest.quotation.quotationRequest.applicantName;
            $scope.policy.client.contact.street = $rootScope.policyRequest.quotation.quotationRequest.applicantName;
            $scope.policy.client.contact.suburb = $rootScope.policyRequest.suburb;
            $scope.policy.client.bankAccount.branch = $rootScope.policyRequest.branchCode;
            $scope.policy.client.bankAccount.accountName = $rootScope.policyRequest.accountName;
            $scope.policy.client.bankAccount.bankName = $rootScope.policyRequest.bankName;
            $scope.policy.client.bankAccount.accountNumber = $rootScope.policyRequest.accountNumber;

            $scope.policy.scheduleAttaching = $scope.wording.scheduleAttaching;
            $scope.policy.premium = $rootScope.policyRequest.quotationOption.premium;
            $scope.policy.excessStructure = $rootScope.policyRequest.quotationOption.excess;



            angular.forEach($scope.policyRequest.quotation.quotationRequest.questionnaire, function (questionnairre) {
                switch (questionnairre.question) {
                    case 'What do you wish to insure ?':
                        console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
                        $scope.policy.subjectMatter = questionnairre.answer;
                    case 'What is the maximum amount you wish to insure ?':
                        console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
                        var maxSumToken = questionnairre.answer.split(',');
                        var tokenMax = '';
                        for (i = 0; i < maxSumToken.length; i++) {
                            tokenMax += maxSumToken[i];
                        }
                        $scope.policy.maximumSumInsured = tokenMax;
                        $scope.policy.sumInsured = $scope.policy.maximumSumInsured;

                    case 'Please specify policy insecption date for annual cover :':
                        console.log('Question:' + questionnairre.question + ', answer is: ' + questionnairre.answer);
                        $scope.policy.inceptionDate = questionnairre.answer;
                    case '':
                }
            });
            $scope.policy.typeOfCover = $scope.wording.typeOfCover;
            $scope.policy.geographicalDuration = $scope.wording.geographicalDuration;

        } else {
            $scope.policy = $rootScope.policy;
            console.log('Existing Policy :' + $scope.policy.client.clientName);
        }
    };

    $scope.update = function (isUpdated) {
        $scope.isUpdate = isUpdated;
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
                    $scope.getPolicies()
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
            if (angular.equals(reference, policy.policyReference)) {
                $scope.policy = policy;
                $scope.update(true);
                $scope.btnValue = 'Save Changes'
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
        'scheduleAttaching': '1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS' +
                '\n2) POLYGON GENERAL COMPUTER NUCLEAR EXCEPTIONS\n3) POLYGON CASH AND VALUABLES IN TRANSIT WORDING' +
                '\n4) VAULT AND STATIC RISK COVER WORDING',
        'typeOfCover': 'Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured. ' +
                'whilst in the custody and care of Protea Coin Group and/or whilst within the Nedbank Camera Managed Unit at the premises declared' +
                'to Insurers, Excluding fraud, dishonesty or criminal involvement of the Insured or their employees.',
        'geographicalDuration': 'refer to special conditions',
        'specialCondition': 'Geographical and duration: Cash - once cash has recorded',
        'notes': '[Add notes for this policy]'

    };

    $scope.getClient = function () {
        angular.forEach($rootScope.policies, function (policy) {
            if (angular.equals($routeParams.policyReference, policy.policyReference)) {
                $scope.policy = policy;
                $scope.policy.client = policy.client;
                console.log('Client UI policies size: ' + $rootScope.policies.length);
                console.log('Client :' + $scope.policy.client);
                $location.path('/client/' + $routeParams.policyReference);
            }
        });

    };

});




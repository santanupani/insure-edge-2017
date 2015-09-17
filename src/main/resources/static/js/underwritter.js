var underwritter = angular.module('underwritter', ['ngRoute']);

underwritter.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/policy-requests/:reference', {
                    'templateUrl': '/html/underwritter.html',
                    'controller': 'policyCtrl'
                }).when('/create-policy/:reference', {
                    'templateUrl': '/html/create-policy.html',
                    'controller': 'createPolicyCtrl'
                }).when('/client-policy', {
                    'templateUrl': '/html/client-policy.html',
                    'controller': 'clientPolicyCtrl'
                }).otherwise({
            redirectTo: '/policy-requests'
        });
    }]);

$(document).ready(function(){
   $("#regId").mouseout().css("text-transform","uppercase"); 
});


underwritter.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams) {

    $scope.mode;
    $scope.reference;
    $scope.reject;

    $scope.init = function () {
        $scope.reference = $routeParams.reference;
        $scope.reject = {};
        $rootScope.policyRequest = $scope.getPoicyRequest($routeParams.reference);
    };


    $scope.getPoicyRequest = function (reference) {


        $http({
            url: '/api/policy-requests/' + reference,
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('policy Request retrived sucessfully');
                $rootScope.policyRequest = data;
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
                if (status == 200) {
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

    $scope.changeMode = function (mode) {
        $scope.mode = mode;
    };
    
    


});

underwritter.controller('createPolicyCtrl', function ($scope, $rootScope, $http, $routeParams) {

    $scope.init = function () {
                
         if ($rootScope.policyRequest == undefined) {
            $scope.getPoicyRequest();
        } else {
            $scope.policyRequests = $rootScope.policyRequest[$routeParams.reference];
        }
    };
    
    
    $scope.tabs = [
    { title:'Dynamic Title 1', content:'Dynamic content 1' },
    { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: false }
  ];


    $scope.getPoicyRequest = function (reference) {


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

   
});

underwritter.controller('clientPolicyCtrl', function ($scope, $rootScope, $http, $routeParams) {
	$scope.clientPolicy = $scope.clientPolicy = {
			'id':'1',
			'inceptionDate':'29/09/2015',
			'renewalDate':'12/09/2015',
			'underwritingYear':'2012',
			'status':'Active',
			'notes':'This policy is pending processing, awaiting support documents from the client.',
			'client':{
				'id':'1',
				'regNumber':'CKD2134De',
				'incomeTaxNumber':'2019938',
				'bankAccounts':[
				     {
				    	 'id':'1',
				    	 'accountNumber':'',
				    	 'accountName':'',
				    	 'branch':'',
				    	 'bankName':''
				     }
				],
				'contactDetails':{
					'id':'1',
					'email':'info@reverside.co.za',
					'contact_person':'Thabo Sethi',
					'street':'Piet Retief, 234B, Malibongwe',
					'city':'Johannesburg'
				},
			},
			'subAgent':{
				'id':'1',
				'firstName':'Ardhendu',
				'lastName':'Patri',
				'email':'ardhendhu.patri@reverside.co.za',
				'broker':{
					'id':'1',
					'name':'Blue Quanta',
					'email':'Manmay.Mohanty@reverside.co.za'
				}
			},
			'underwriter':{
				'id':'1',
				'firstName':'Thabo',
				'lastName':'Thulare',
				'email':'thabothulare68@gmail.com'
			},
			
	};

	$scope.init = function(){
		
	};
	
	$scope.getClientPolicy = function (policyNo) {
        $http({
            url: '/api/client-policy/' + policyNo,
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('Client policy retrived sucessfully');
                $scope.clientPolicy = data;
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


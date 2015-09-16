var underwritter = angular.module('underwritter', ['ngRoute']);

underwritter.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/policy-requests/:reference', {
                    'templateUrl': '/html/underwritter.html',
                    'controller': 'policyCtrl'
                }).when('/client-details', {
            'templateUrl': '/html/client-details.html',
            'controller': 'clientDetailsCtrl'
        }).otherwise({
            redirectTo: '/policy-requests'
        });
    }]);

$(document).ready(function () {
    $("#regId").mouseout().css("text-transform", "uppercase");
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

underwritter.controller('clientDetailsCtrl', function ($scope) {

    $scope.policyRequest = {
        "quotation": {
            "quotationRequest": {
                "companyName": "Reverside",
                "applicantName": "Thabo",
                "applicantEmail": "thabothulare68@gmail.com",
            },
        },
        "companyRegNumber": "12345ertyui",
        "vatRegNumber": "234dfghj2345",
        "telephoneNumber": "(012)345-6789",
        "faxNumber": "(012)345-6789",
        "streetAddress": "Small",
        "suburb": "Midrand",
        "postalCode": "099",
        "accountHolder": "Thabo",
        "accountName": "Thulare",
        "bankName": "FNB",
        "accountNumber": "67890567",
        "branchCode": "7888"
    }


});
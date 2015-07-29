var underwritter = angular.module('underwritter', ['ngRoute']);

underwritter.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/policy-requests', {
                    'templateUrl': '/html/underwritter.html',
                    'controller': 'policyCtrl'
                }).otherwise({
            redirectTo: '/policy-requests'
        });
    }]);



underwritter.controller('policyCtrl', function ($scope, $rootScope, $http, $routeParams) {

    $scope.policyRequest = {

        "quotation": {
            "quotationRequest": {
                "questionnaire": [
                    {
                        question: "What do you wish to insure ?",
                        answer: "Cash and Coins "
                    },
                    {
                        question: "What is the maximum amount you wish to insure ?",
                        answer: "12345"
                    },
                    {
                        question: "Is the above amount the total full value of the goods being moved ?",
                        answer: "true"
                    },
                    {
                        question: "Do you want first loss cover for additional premium ?",
                        answer: "true"
                    },
                    {
                        question: "Do you use the service of a professional valuables carriers ?",
                        answer: "true"
                    },
                    {
                        question: "What is the name of the professional valuables carriers ?",
                        answer: "Protea Coin Service"
                    },
                    {
                        question: "How many times per week are the valuables carried ?",
                        answer: "3 times/week"
                    },
                    {
                        question: "Please select from where and to where are the valuables carried :",
                        answer: "Rustenburg to Kimberley"
                    },
                    {
                        question: "What is the approximate distance ?",
                        answer: "200km to 300km"
                    },
                    {
                        question: "Are you currently insured ?",
                        answer: "true"
                    },
                    {
                        question: "Please provide details of your previous insurance company :",
                        answer: "Reverside"
                    },
                    {
                        question: "Please provide details of losses of valuable goods of insured/uninsured the past 5 years ?",
                        answer: "false"
                    },
                    {
                        question: "Do you have more history details of insured/uninsured goods :",
                        answer: "false"
                    },
                    {
                        question: "Do you require SASRIA cover ?",
                        answer: "true"
                    }
                ],
                reference: "2445-2334-5678",
                status: "ACCEPTED",
                createDate: "23/07/2015",
                companyName: "Reverside Software Solutions",
                applicantName: "Binod Sethi",
                applicantEmail: "binod.sethi@gmail.com",
                "product": {
                    id: 1,
                    name: "Cash and Valuables in Transit",
                    description: "All risk cover for your cash and valuables whilst in transit.",
                    image: "/img/products/Cash and Valuables in Transit.jpg"
                },
                "broker": {
                    "id": 1,
                    "name": "Thabo Thulare",
                    "email": "thabo.thulare@gmail.com",
                    "code": "0045"
                }
            },
            "quotationId": 1,
            "option":
                    {
                        id: 1,
                        location: "23 Locations",
                        commodity: "Cash",
                        limit: "89999",
                        cover: "Cash in Transit",
                        duration: "11 weeks",
                        excess: "7999,99",
                        premium: "1076.89"
                    }
        },
        "companyRegNumber": "",
        "vatRegNumber": "",
        telephoneNumber: "0744412288",
        "streetAddress": "",
        "postalCode": "",
        "designation": "",
        "buisnessDesc": "",
        "representive": "",
        "accountHolder": "",
        "accountName": "",
        accountNumber: "12345678",
        branchCode: "52237",
        debitOrder: "7th",
        "bankStatement": [
            "byte"
        ],
        bankName: "Standard Bank",
        faxNumber: "011234567",
        "suburb": "",
        accountType: "Savings",
        "quotationOptionId": 1,
        "quotationId": 1

    };


});
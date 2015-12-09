var claimAdmin = angular.module('claimAdmin', ['ngRoute', 'ngCookies']);


claimAdmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/claim-requests', {
                    'templateUrl': '/html/claim-requests-list.html',
                    'controller': 'listClaimRequestCtrl'
                }).when('/claim-requests/:claimNumber', {
            'templateUrl': '/html/claim-requests.html',
            'controller': 'claimRequestCtrl'
        }).otherwise({
            redirectTo: '/claim-requests'
        });
    }]);

claimAdmin.directive('modal', function () {
    return {
        template: '<div class="modal fade">' +
                '<div class="modal-dialog">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
                '<h4 class="modal-title">{{ title }}</h4>' +
                '</div>' +
                '<div class="modal-body" ng-transclude></div>' +
                '</div>' +
                '</div>' +
                '</div>',
        restrict: 'E',
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.title = attrs.title;

            scope.$watch(attrs.visible, function (value) {
                if (value == true)
                    $(element).modal('show');
                else
                    $(element).modal('hide');
            });

            $(element).on('shown.bs.modal', function () {
                scope.$apply(function () {
                    scope.$parent[attrs.visible] = true;
                });
            });

            $(element).on('hidden.bs.modal', function () {
                scope.$apply(function () {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});

claimAdmin.controller('claimCtrl', function ($scope, $rootScope, $http, $cookies, $window) {

    $scope.init = function () {
        if ($cookies.token == undefined) {

            $window.location.href = "/login?state=" + encodeURIComponent($window.location.href);
        } else {
            $scope.validate($cookies.token);

        }
    };

    $scope.validate = function (token) {
        $http({
            method: 'POST',
            url: '/validate',
            headers: {
                "Content-Type": "text/html"
            },
            data: token
        }).success(function (data, status) {
            $rootScope.session = data;

            if ($rootScope.session.role != "ClAIMADMIN") {
                $window.location.href = "/logout";
            }

            $http.defaults.headers.common['Authorization'] = 'Basic ' + $cookies.token;
        }).error(function (error, status) {
            $window.location.href = "/logout";
        });
    };

});


claimAdmin.controller('listClaimRequestCtrl', function ($scope, $http, $rootScope) {

    $scope.init = function () {
        $scope.getAllClaimRequest();
    };

    $scope.getAllClaimRequest = function () {
        $scope.request = [];
        $http({
            url: '/api/claim-requests',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log(' all claim Request retrived sucessfully');
                $scope.claimRequest = data;

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


claimAdmin.controller('claimRequestCtrl', function ($scope, $rootScope, $http, $routeParams, $sce, $window) {

    $scope.documentName;
    $scope.init = function () {

        $scope.claimNumber = $routeParams.claimNumber;
        $scope.getClaimRequest($routeParams.claimNumber);
        $scope.showModal = false;

    };

    $scope.toggleModal = function (attachment, documentName) {
        console.log(attachment);
        console.log(documentName);
        $scope.showModal = !$scope.showModal;
        $scope.attachment = attachment;
        $scope.documentName = documentName;
    };


    $scope.getClaimRequest = function (claimNumber) {
        $http({
            url: '/api/claim-requests/' + claimNumber,
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('claim Request retrived sucessfully');
                $rootScope.claimRequest = data;

                if ($rootScope.claimRequest.investigationReportC != null) {
                    $scope.getInvestigationReport($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.affidavitC != null) {
                    $scope.getAffidavit($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.comfirmationAmountC != null) {
                    $scope.getComfirmationAmount($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.proofOfPickupC != null) {
                    $scope.getProofOfPickup($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.transTrackDocumentC != null) {
                    $scope.getTransTrackDocument($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.reportC != null) {
                    $scope.getQuote($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.proofOfPickupC != null) {
                    $scope.getProofOfPickup($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.amountBankedC != null) {
                    $scope.getAmountBanked($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.quoteC != null) {
                    $scope.getQuote($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.photo1C != null) {
                    $scope.getPhoto1($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.photo2C != null) {
                    $scope.getPhoto2($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.photo3C != null) {
                    $scope.getPhoto3($rootScope.claimRequest.claimNumber);
                }
                if ($rootScope.claimRequest.photo4C != null) {
                    $scope.getPhoto4($rootScope.claimRequest.claimNumber);
                }

                console.log('Returned claim request: ' + data);
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };

    $scope.downloadInvestigationReport = function () {
        saveAs($scope.investigationReport, $scope.claimRequest.claimNumber + " - investigationReport");
    };
    $scope.downloadComfirmationAmount = function () {
        saveAs($scope.comfirmationAmount, $scope.claimRequest.claimNumber + " - comfirmationAmount");
    };
    $scope.downloadProofOfPickup = function () {
        saveAs($scope.proofOfPickup, $scope.claimRequest.claimNumber + " - proofOfPickup");
    };
    $scope.downloadAffidavit = function () {
        saveAs($scope.affidavit, $scope.claimRequest.claimNumber + " - affidavit");
    };
    $scope.downloadAmountBanked = function () {
        saveAs($scope.amountBanked, $scope.claimRequest.claimNumber + " - amountBanked");
    };
    $scope.downloadTransTrackDocument = function () {
        saveAs($scope.transTrackDocument, $scope.claimRequest.claimNumber + " - transTrackDocument");
    };
    $scope.downloadQuote = function () {
        saveAs($scope.quote, $scope.claimRequest.claimNumber + " - quote");
    };
    $scope.downloadReport = function () {
        saveAs($scope.report, $scope.claimRequest.claimNumber + " - report");
    };
    $scope.downloadPhoto1 = function () {
        saveAs($scope.photo1, $scope.claimRequest.claimNumber + " - photo1");
    };
    $scope.downloadPhoto2 = function () {
        saveAs($scope.photo2, $scope.claimRequest.claimNumber + " - photo2");
    };
    $scope.downloadPhoto3 = function () {
        saveAs($scope.photo3, $scope.claimRequest.claimNumber + " - photo3");
    };
    $scope.downloadPhoto4 = function () {
        saveAs($scope.photo4, $scope.claimRequest.claimNumber + " - photo3");
    };

    $scope.getInvestigationReport = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/investigationReport',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving Inverstigation report');
                angular.forEach(data, function (file) {
                    console.log(file.toString());
                });
                var investigationReport = new Blob([data], {type: $rootScope.claimRequest.investigationReportC.toString()});
                var fileURL = URL.createObjectURL(investigationReport);
                $scope.investigationReport = investigationReport
                $scope.investigationReportName = "Investigation Report";
                $scope.investigationReportURL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getComfirmationAmount = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/comfirmationAmount',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving comfirmationAmount');
                var comfirmationAmount = new Blob([data], {type: $rootScope.claimRequest.comfirmationAmountC.toString()});
                var fileURL = URL.createObjectURL(comfirmationAmount);
                $scope.comfirmationAmount = comfirmationAmount;
                $scope.comfirmationAmountName = "Comfirmation Amount";
                $scope.comfirmationAmountURL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getProofOfPickup = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/proofOfPickup',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving proofOfPickup');
                var proofOfPickup = new Blob([data], {type: $rootScope.claimRequest.proofOfPickupC.toString()});
                var fileURL = URL.createObjectURL(proofOfPickup);
                $scope.proofOfPickup = proofOfPickup;
                $scope.proofOfPickupName = "Proof Of Pick Up";
                $scope.proofOfPickupURL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getAffidavit = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/affidavit',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving affidavit');
                var affidavit = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(affidavit);
                $scope.affidavit = affidavit;
                $scope.affidavitName = "Affidavit";
                $scope.affidavitURL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getAmountBanked = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/amountBanked',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving amountBanked');
                var amountBanked = new Blob([data], {type: $rootScope.claimRequest.amountBankedC.toString()});
                var fileURL = URL.createObjectURL(amountBanked);
                $scope.amountBanked = amountBanked;
                $scope.amountBankedName = "Amount Banked";
                $scope.amountBankedURL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getTransTrackDocument = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/transTrackDocument',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving transTrackDocument');
                var transTrackDocument = new Blob([data], {type: $rootScope.claimRequest.transTrackDocumentC.toString()});
                var fileURL = URL.createObjectURL(transTrackDocument);
                $scope.transTrackDocument = transTrackDocument;
                $scope.transTrackDocumentName = "Trans Track Document";
                $scope.transTrackDocumentURL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getQuote = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/quote',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving quote');
                var quote = new Blob([data], {type: $rootScope.claimRequest.quoteC.toString()});
                var fileURL = URL.createObjectURL(quote);
                $scope.quote = quote;
                $scope.quoteName = "Quote";
                $scope.quoteURL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getReport = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/report',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving report');
                var report = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(report);
                $scope.report = report;
                $scope.reportName = "Report";
                $scope.reportURL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getPhoto1 = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/photo1',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving photo1');
                var photo1 = new Blob([data], {type: $rootScope.claimRequest.photo1C.toString()});
                var fileURL = URL.createObjectURL(photo1);
                $scope.photo1 = photo1;
                $scope.photo1Name = "Photo 1";
                $scope.photo1URL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getPhoto2 = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/photo2',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving photo2');
                var photo2 = new Blob([data], {type: $rootScope.claimRequest.photo2C.toString()});
                var fileURL = URL.createObjectURL(photo2);
                $scope.photo2 = photo2;
                $scope.photo2Name = "Photo 2";
                $scope.photo2URL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getPhoto3 = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/photo3',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving photo 3');
                var photo3 = new Blob([data], {type: $rootScope.claimRequest.photo3C.toString()});
                var fileURL = URL.createObjectURL(photo3);
                $scope.photo3 = photo3;
                $scope.photo3Name = "Photo 3";
                $scope.photo3URL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
    $scope.getPhoto4 = function (claimNumber) {
        console.log('ClaimNumber: ' + claimNumber);
        $http({
            url: '/api/claim/' + claimNumber + '/photo4',
            responseType: 'arraybuffer',
            headers: {'Accept': '*/*'},
            method: 'get'
        }).success(function (data, status) {
            console.log(data);
            if (status == 200) {
                console.log('Retrieving photo 4');
                var photo4 = new Blob([data], {type: $rootScope.claimRequest.photo4C.toString()});
                var fileURL = URL.createObjectURL(photo4);
                $scope.photo4 = photo4;
                $scope.photo4Name = "Photo 4";
                $scope.photo4URL = $sce.trustAsResourceUrl(fileURL);
            } else {
                console.log('status:' + status);
                $scope.error = "error status code : " + status;
            }
        }).error(function (error) {
            console.log(error);
            $rootScope.error = error;
        });
    };
});


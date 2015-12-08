var claimAdmin = angular.module('claimAdmin', ['ngRoute']);


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
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
        scope.title = attrs.title;

        scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
      }
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

    $scope.init = function () {

        $scope.claimNumber = $routeParams.claimNumber;
        $scope.getClaimRequest($routeParams.claimNumber);
        $scope.showModal = false;

    };
    
     $scope.toggleModal = function(attachment){
        $scope.showModal = !$scope.showModal;
        $scope.attachment = attachment;
    };
    

    $scope.getClaimRequest = function (claimNumber) {
        $http({
            url: '/api/claim-requests/' + claimNumber,
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('claim Request retrived sucessfully');
                $rootScope.claimRequest = data;
//                $scope.getInvestigationReport($rootScope.claimRequest.claimNumber);
//                $scope.getAffidavit($rootScope.claimRequest.claimNumber);
//                $scope.getComfirmationAmount($rootScope.claimRequest.claimNumber);
//                $scope.getProofOfPickup($rootScope.claimRequest.claimNumber);
//                if($rootScope.claimRequest.investigationReportC != null){
//                    $scope.getInvestigationReport($rootScope.claimRequest.claimNumber);
//                }
//                if($rootScope.claimRequest.affidavitC != null){
//                    $scope.getAffidavit($rootScope.claimRequest.claimNumber);
//                }
//                if($rootScope.claimRequest.comfirmationAmountC != null){
//                    $scope.getComfirmationAmount($rootScope.claimRequest.claimNumber);
//                }
//                if($rootScope.claimRequest.proofOfPickupC != null){
//                    $scope.getProofOfPickup($rootScope.claimRequest.claimNumber);
//                }
//                
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
                angular.forEach(data,function(file){
                    console.log(file.toString());
                });
                var file = new Blob([data], {type: $rootScope.claimRequest.investigationReportC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(fileURL);
                saveAs(file, claimNumber+" - investigationReport");
                $scope.investigationReport = $sce.trustAsResourceUrl(fileURL);
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
                var file = new Blob([data], {type: $rootScope.claimRequest.comfirmationAmountC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                saveAs(file, claimNumber+" - comfirmationAmount");
                $scope.comfirmationAmount = $sce.trustAsResourceUrl(fileURL);
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
                var file = new Blob([data], {type: $rootScope.claimRequest.proofOfPickupC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                saveAs(file, claimNumber+" - proofOfPickup");
                $scope.proofOfPickup = $sce.trustAsResourceUrl(fileURL);
         
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
                var file = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                saveAs(file, claimNumber+" - affidavit");
                $scope.affidavit = $sce.trustAsResourceUrl(fileURL);
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
                var file = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                saveAs(file, claimNumber+" - amountBanked");
                $scope.affidavit = $sce.trustAsResourceUrl(fileURL);
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
                var file = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                saveAs(file, claimNumber+" - transTrackDocument");
                $scope.affidavit = $sce.trustAsResourceUrl(fileURL);
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
                var file = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                saveAs(file, claimNumber+" - quote");
                $scope.affidavit = $sce.trustAsResourceUrl(fileURL);
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
                var file = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                saveAs(file, claimNumber+" - report");
                $scope.affidavit = $sce.trustAsResourceUrl(fileURL);
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
                console.log('Retrieving photo2');
                var file = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                saveAs(file, claimNumber+" - photo");
                $scope.affidavit = $sce.trustAsResourceUrl(fileURL);
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
                var file = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                saveAs(file, claimNumber+" - photo");
                $scope.affidavit = $sce.trustAsResourceUrl(fileURL);
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
                console.log('Retrieving affidavit');
                var file = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                $scope.affidavit = $sce.trustAsResourceUrl(fileURL);
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
                console.log('Retrieving affidavit');
                var file = new Blob([data], {type: $rootScope.claimRequest.affidavitC.toString()});
                var fileURL = URL.createObjectURL(file);
                console.log(file);
                $scope.affidavit = $sce.trustAsResourceUrl(fileURL);
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


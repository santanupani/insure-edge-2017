var polygon = angular.module('polygon', []);

polygon.controller('clientCtrl', function ($scope, $http) {
    
    $scope.products = [];
    
    $scope.error;
    
    $scope.init = function(){
        $scope.getProducts();
    };

    $scope.getProducts = function () {
        //log here
        $http({
            url: '/api/products',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('retrived successfully');
                $scope.products = data;
            } else {
                console.log('status:' + status);
            }
        }).error(function (error) {
            // log here
            $scope.error = "No Data Base Connection";
        });
    };



});






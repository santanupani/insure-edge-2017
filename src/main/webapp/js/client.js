var myApp = angular.module('myApp', []);

myApp.controller('clientCtrl', function ($scope, $http) {
    $scope.products = [];
    $scope.error;

    $http({
        url: 'http://localhost:8080/api/products',
        method: 'get'
    }).
            success(function (data, status) {
                if (status == 200) {
                    console.log('retrived successfully');
                    $scope.products = data;
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                
                $scope.error = "No Data Base Connection";
                
            });

});






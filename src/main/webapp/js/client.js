var polygon = angular.module('polygon', ['ngRoute']);

polygon.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/list-products', {
            'templateUrl': '/html/list-products.html',
            'controller': 'listProductsCtrl'
        })
        .otherwise({
        	redirectTo: '/list-products'
        });
}]);

polygon.controller('clientCtrl', function ($scope) {
    
    $scope.error;
    
    $scope.init = function(){
        
    };
});



polygon.controller('listProductsCtrl', function ($scope, $rootScope, $http) {
	
	$scope.products = [];
     
    $scope.init = function(){
        $scope.getProducts();
    };

    $scope.getProducts = function () {
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
        	$rootScope.error = "No Data Base Connection";
        });
    };
});






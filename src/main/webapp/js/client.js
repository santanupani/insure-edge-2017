var polygon = angular.module('polygon', ['ngRoute']);

polygon.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/products', {
            'templateUrl': '/html/products.html',
            'controller': 'productsCtrl'
        }).when('/products/:id/questionnaires', {
            'templateUrl': '/html/questionnaires.html',
            'controller': 'questionnairesCtrl'
        })
        .otherwise({
        	redirectTo: '/products'
        });
}]);



polygon.controller('productsCtrl', function ($scope, $rootScope, $http) {
     
    $scope.init = function(){
        $scope.getProducts();
    };

    $scope.getProducts = function () {
    	// TODO : Add log 
        $http({
            url: '/api/products',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('retrived successfully');
                $rootScope.products = data;
            } else {
                console.log('status:' + status);
                // TODO : set error value
            }
        }).error(function (error) {
            // TODO : log here
        	$rootScope.error = "No Data Base Connection";
        });
    };
});


polygon.controller('questionnairesCtrl', function ($scope, $rootScope, $http, $routeParams) {
	
	console.log($routeParams['id']);
	console.log($rootScope.products);
	

});

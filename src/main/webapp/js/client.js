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
        console.log('get products');
        $http({
            url: '/api/products',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('retrived successfully');
                $rootScope.products = data;
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;;
            }
        }).error(function (error) {
            console.log(error);
        	$rootScope.error = error;;
                
        });
    };
});


polygon.controller('questionnairesCtrl', function ($scope, $rootScope, $http, $routeParams) {
	
	$scope.questionnaires = [];
	
	$scope.init = function(){
		console.log($rootScope.products);
		if($rootScope.products==undefined) {
			$scope.getProducts();
		} else {
			$scope.product = $rootScope.products[$routeParams['id']-1];
			$scope.getQuestionnaires($routeParams['id']);
		}
	};
	
	$scope.getProducts = function () {
        console.log('get products');
        $http({
            url: '/api/products',
            method: 'get'
        }).success(function (data, status) {
            if (status == 200) {
                console.log('retrived successfully');
                $rootScope.products = data;
                $scope.init();
            } else {
                console.log('status:' + status);
                $rootScope.error = "error status code : " + status;;
            }
        }).error(function (error) {
            console.log(error);
        	$rootScope.error = error;;
                
        });
    };
    
    $scope.getQuestionnaires = function(productId){
    	$scope.questionnaires = 
    		 [{
   			  "id": "0",
   			  "question" : "What is your name ?",
   			  "answerType" : "text"
   			},
   			{
   			  "id": "1",
   			  "question" : "What is your gender ?",
   			  "answerType" : "select",
   			  "answerValues" : ["Female", "Male"]
   			},
   			{
   			  "id": "2",
   			  "question" : "What is your age ?",
   			  "answerType" : "number",
   			  "dependsOn" : 1,
   			  "onAnswer"  : "Male"
   			},
   			{
   			  "id": "3",
   			  "question" : "What is your salary ?",
   			  "answerType" : "number",
   			  "dependsOn" : 1,
   			  "onAnswer" : "Female"
   			},
   			{
   			    "id" : "4",
   			    "question" : "Do you have any criminal record ?",
   			    "answerType" : "checkbox"
   			},
   			{
   			    "id" : "5",
   			    "question" : "Please provide details of your crime",
   			    "answerType" : "textarea",
   			    "dependsOn" : 4,
   			    "onAnswer" : "true"
   			}];
    };
	

});

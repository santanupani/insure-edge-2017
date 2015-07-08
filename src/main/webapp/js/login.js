var login = angular.module('login', ['ngRoute']);

login.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when('/login', {
                    'templateUrl': '/html/loginForm.html',
                    'controller': 'loginCtrl'
                })
                .when('/user', {
                    'templateUrl': '/html/users.html',
                    'controller': 'userCtrl'
                })
                .otherwise({
                    redirectTo: '/login'
                });
    }]);

login.controller('loginCtrl', function ($scope, $http, $location) {

    $scope.modelData = {};

    $scope.submitLogin = function (loginform) {

        $scope.modelData.user_Name = $scope.userName;
        $scope.modelData.password = $scope.password;
        $scope.modelData.role = $scope.role;

        if (loginform.$invalid) {
            console.log("Form Validation is Failled");
        } else {
            console.log("Form Validation is Successfull");

            $http({
                url: '/api/createuser',
                method: 'post',
                headers: {
                    'Content-Type': 'application/json',
                },
                data: $scope.modelData
            }).success(function (data, status) {
                if (status == 200) {
                    console.log('user creation invoked successfully');
                    console.log('Data:' + data);
                    $location.path("/users");
                } else {
                    console.log('status:' + status);
                }
            })
                    .error(function (error) {
                        console.log(error);
                    });
        }
    };
});







var manager = angular.module('manager', ['ngRoute', 'ngCookies']);


manager.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/policy/approvals', {
		'templateUrl': '/html/policy-approvals.html',
		'controller': 'policyApprovalCtrl'
	}).when('/policy/:reference/approval', {
		'templateUrl': '/html/policy-approval.html',
		'controller': 'policyApprovalCtrl'
	}).otherwise({
		redirectTo: '/policy/approvals'
	});
}]);


manager.controller('managerLoginCtrl', function ($scope, $rootScope, $http, $cookies, $window) {

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

			if ($rootScope.session.role != "MANAGER") {
				$window.location.href = "/logout";
			}

			$http.defaults.headers.common['Authorization'] = 'Basic ' + $cookies.token;
		}).error(function (error, status) {
			$window.location.href = "/logout";
		});
	};

});

manager.controller('policyApprovalCtrl', function ($scope, $rootScope, $http, $routeParams, $location, $sce, $filter, $window, $cookieStore) {
	$rootScope.policies;
	
	$scope.init = function () {
		$scope.getPolicies();
	};

	$scope.updatePolicy = function (reference) {
		console.log('Update Policy');
		console.log('Policy Fee ' + $scope.policy.policyFee);
		$http({
			url: '/api/policy-update/' + reference,
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.policy
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				$rootScope.message = "Policy Updated Successfully";
				$scope.policy = data;
				console.log('Policy Updated');
				$window.location.reload();
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			$rootScope.message = "Oops we recieved your request but there was a problem processing it";
			console.log(error);
		});
	};
	

	$scope.approvePolicy = function (reference) {
		console.log('Update Policy');
		console.log('Policy Fee ' + $scope.policy.policyFee);
		$http({
			url: '/api/policy/' + reference+'/approval',
			method: 'put',
			headers: {
				'Content-Type': 'application/json'
			},
			data: $scope.policy
		}).success(function (data, status) {
			console.log('get success code:' + status);
			if (status == 200) {
				$rootScope.message = "Policy Approved Successfully";
				$scope.policy = data;
				$location.path('/policy/approvals');
			} else {
				console.log('status:' + status);
			}
		})
		.error(function (error) {
			$rootScope.message = "Oops we recieved your request but there was a problem processing it";
			console.log(error);
		});
	};
	
	$scope.submitForPolicySchedule = function (reference) {
		console.log('Ref: ' + reference);
		$http({
			url: '/api/policy-schedules/' + reference,
			responseType: 'arraybuffer',
			headers: {'Accept': 'application/pdf'},
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('Retrieving policy schedule PDF');
				var file = new Blob([data], {type: 'application/pdf'});
				var fileURL = URL.createObjectURL(file);
				$scope.isPolicyCreated = true;
				$scope.policyScheduleContent = $sce.trustAsResourceUrl(fileURL);
			} else {
				console.log('status:' + status);
				$scope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
			
	$scope.update = function (isUpdated) {
		$scope.isUpdate = isUpdated;
	};

	$scope.cancel = function (isUpdated) {
		$window.location.reload();
	};


	$scope.getPolicy = function (reference) {
		angular.forEach($rootScope.policies, function (policy) {
			if (angular.equals(reference, policy.reference)) {
				$scope.policy = policy;
				$scope.update(true);

				$scope.submitForPolicySchedule(reference);
				$location.path('/policy/' + reference+'/approval');
			}
		});
	};


	$scope.getPolicies = function () {
		$http({
			url: '/api/policies',
			method: 'get'
		}).success(function (data, status) {
			if (status == 200) {
				console.log('All policies retrived sucessfully');
				$rootScope.pendingApprovalPolicies = data;
				console.log($rootScope.pendingApprovalPolicies);
				$rootScope.policies = [];
				angular.forEach($rootScope.pendingApprovalPolicies,function(policy){
					
					if(angular.equals(policy.status,'Pending Approval')){
						console.log(policy.status);
						$rootScope.policies.push(policy);
					}
				});
				console.log($rootScope.policies);
				$scope.getPolicy($routeParams.reference);
			} else {
				console.log('status:' + status);
				$rootScope.error = "error status code : " + status;
			}
		}).error(function (error) {
			console.log(error);
			$rootScope.error = error;
		});
	};
	
	$scope.getClient = function () {
		angular.forEach($rootScope.policies, function (policy) {
			if (angular.equals($routeParams.reference, policy.reference)) {
				$scope.policy = policy;
				$scope.policy.client = policy.client;
				console.log('Client UI policies size: ' + $rootScope.policies.length);
				console.log('Client :' + $scope.policy.client);
				$location.path('/client/' + $routeParams.reference);
			}
		});
	};
});
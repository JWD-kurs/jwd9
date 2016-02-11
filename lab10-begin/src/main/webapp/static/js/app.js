var wafepaApp = angular.module('wafepaApp', ['ngRoute']);

wafepaApp.controller('ActivityController', function($scope, $http, $location, $routeParams) {
	
	$scope.getActivities = function() {
		// GET api/activities
		var requestParams = { name : $scope.search, page : $scope.page};
		$http.get('api/activities', { params : requestParams })
				.success(function(data, status, headers) {
					$scope.activities = data;
					$scope.totalPages = headers('total-pages');
				})
				.error(function() {
					$scope.errorMessage = 'Oops, something went wrong.';
				});
		
	};
	
	$scope.deleteActivity = function(id) {
		$http.delete('api/activities/' + id)
				.success(function () {
					$scope.getActivities();
				})
				.error(function() {
					
				});
	};
	
	$scope.initActivity = function() {
		$scope.activity = {};
		
		if ($routeParams && $routeParams.id) { // edit stranica
			$http.get('api/activities/' + $routeParams.id)
					.success(function(data) {
						$scope.activity = data;
					})
					.error(function() {
						
					});
		}
	};
	
	$scope.saveActivity = function() {
		if ($scope.activity.id) { // edit stranica
			$http.put('api/activities/' + $scope.activity.id, $scope.activity)
					.success(function() {
						$location.path('/activities');
					})
					.error(function() {
						
					});
		} else { // add stranica
			$http.post('api/activities', $scope.activity)
					.success(function() {
						$location.path('/activities');
					})
					.error(function() {
						
					});
		}
		
	};
});

wafepaApp.controller('LogController', function($scope, $http, $location, $routeParams) {
	
	$scope.getLogs = function() {
		if ($routeParams.activityId) {
			$http.get('api/activities/' + $routeParams.activityId)
					.success(function(data) {
						$scope.activity = data;
					})
					.error(function() {
						
					});
		}
		
		var requestParams = { activityId : $routeParams.activityId };
		// GET api/logs
		$http.get('api/logs', { params : requestParams })
				.success(function(data) {
					$scope.logs = data;
				})
				.error(function() {
					$scope.errorMessage = 'Oops, something went wrong.';
				});	
	};
	
	$scope.initLog = function() {
		$scope.log = {};
		
		$http.get('api/activities') // dobavi sve akt. sa servera za dropdown meni
				.success(function(data) {
					$scope.activities = data;
				})
				.error(function() {
					
				});
	};
	
	$scope.saveLog = function() {
		$http.post('api/logs', $scope.log)
				.success(function() {
					$location.path('/logs');
				})
				.error(function() {
					
				});
	};
	
});

wafepaApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'static/html/home.html'
        })
        .when('/activities', {
            templateUrl : 'static/html/activities.html',
            controller : 'ActivityController'
        })
        .when('/activities/add', {
            templateUrl : 'static/html/addEditActivity.html',
            controller : 'ActivityController'
        })
        .when('/activities/edit/:id', {
            templateUrl : 'static/html/addEditActivity.html',
            controller : 'ActivityController'
        })
        .when('/logs', {
            templateUrl : 'static/html/logs.html',
            controller : 'LogController'
        })
        .when('/logs/add', {
            templateUrl : 'static/html/addEditLog.html',
            controller : 'LogController'
        })
        .when('/activities/:activityId/logs', {
            templateUrl : 'static/html/logs.html',
            controller : 'LogController'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);
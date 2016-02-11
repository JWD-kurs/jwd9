var wafepaApp = angular.module('wafepaApp.controllers', []);

wafepaApp.controller('ActivityController', function($scope, $location, $routeParams, activityRestService) {
	
	$scope.getActivities = function() {
		// GET api/activities
		var requestParams = { name : $scope.search, page : $scope.page};
		activityRestService.getActivities(requestParams)
				.success(function(data, status, headers) {
					$scope.activities = data;
					$scope.totalPages = headers('total-pages');
				})
				.error(function() {
					$scope.errorMessage = 'Oops, something went wrong.';
				});
		
	};
	
	$scope.deleteActivity = function(id) {
		activityRestService.deleteActivity(id)
				.success(function () {
					$scope.getActivities();
				})
				.error(function() {
					
				});
	};
	
	$scope.initActivity = function() {
		$scope.activity = {};
		
		if ($routeParams && $routeParams.id) { // edit stranica
			activityRestService.getActivity($routeParams.id)
					.success(function(data) {
						$scope.activity = data;
					})
					.error(function() {
						
					});
		}
	};
	
	$scope.saveActivity = function() {
		activityRestService.saveActivity($scope.activity)
			.success(function() {
				$location.path('/activities');
			})
			.error(function() {
				
			});
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
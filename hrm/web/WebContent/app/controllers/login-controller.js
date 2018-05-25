app.controller('loginController', function($scope, $http){
	$scope.formSubmit = function(){
		console.log($scope.username);
			$http.post('login.html')
				.then(function(data) {
					console.log(data);
				});
	}
});

<!DOCTYPE html>
<html lang="en" data-ng-app="angularApp">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/materialize.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>House Revenue Management</title>
</head>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/materialize.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angular/angular-1.6.9.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angular/angular-animate-1.6.9.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angular/angular-route-1.6.9.js"></script>

<body data-ng-controller="indexController">
	<%-- <div class="navbar-fixed">
		<nav class="transparent z-depth-0">
			<div class="nav-wrapper">
				<div class="row">
					<div class="col s12">
						<ul class="right hide-on-med-and-down">
						<%if(request.getRemoteUser() == null) {%>
							<li><a href="${pageContext.request.contextPath}/pub/login.html" class="purple-text">Login</a></li>
						<% } %>
						</ul>
					</div>
				</div>
			</div>
		</nav>
	</div> --%>

	<div class="row">
		<div class="banner">
			<div class="center-align">
				<h1 class="purple-text">House Revenue Management</h1>
				<p>Use this <a href="https://www.mkyong.com/ant/ant-how-to-create-a-jar-file-with-external-libraries/" class="red-text">URL</a> to update ANT file..</p>
				<a data-ng-href="{{url}}" class="purple-text">Login</a>
			</div>
		</div>
	</div>

</body>
<script>

var app = angular.module('angularApp',[]);
app.controller('indexController', function($scope){
	 $scope.url = "${pageContext.request.contextPath}/pub/login.html";
});
</script>

</html>

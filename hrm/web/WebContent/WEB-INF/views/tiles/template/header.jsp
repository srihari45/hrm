<style type="text/css">
.side-nav .divider {
	margin: 0;
	height: 2px;
	border-bottom: 1px solid #e0e0e0;
	background-color: transparent;
}

.sub-link {
	padding: 5px 70px !important;
}

.side-nav .sub-link:hover {
    background-color: rgba(0, 0, 0, 0.05);
}

.side-nav li > a.live {
	background-color: #d8d5d5;
}

.mr-3 {
	margin-right: 3em !important;
}

.side-nav .menu {
	padding: 0 15px;
}

.side-nav .collapsible-header, .side-nav.fixed .collapsible-header {
	padding: 5px 16px;
}

.menu-btn {
	padding: 8px;
}

.profile {
	top: 4em !important;
}

.side-nav li > a {
    height: auto;
 }
.home-link {
	color: #fff !important;
}
</style>

<%if(request.getRemoteUser() != null){ %>
	<nav class="blue" role="navigation">
		<div class="nav-wrapper">
			<a href="#" data-activates="menu-bar" class="button-collapse show-on-large"><i class="material-icons">menu</i></a>
			<ul class="right mr-3">
				<li><a class="dropdown-button" href="#!" data-activates="dropdown-profile"><%=request.getRemoteUser()%> <i class="material-icons right">more_vert</i></a></li>
			</ul>
	
			<ul class="side-nav side-header" id="menu-bar">
				<li class="blue">
					<ul class="collapsible menu-btn">
						<li>
							<a class="button-collapse-close menu"> <i class="material-icons white-text">menu</i></a> 
						    <a class="home-link" href="javascript:void(0);">Home</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	
	<ul id="dropdown-profile" class="dropdown-content profile">
		<li><a class="waves-effect waves-blue a-header" href="#">My Account</a></li>
		<li><a class="waves-effect waves-blue a-header" href="${pageContext.request.contextPath}/auth/logoutAction.html">Logout</a></li>
	</ul>
<%} %>
<script type="text/javascript">
$(document).ready(function() {
    $('.button-collapse').sideNav({
      menuWidth: 320, // Default is 300
      edge: 'left', // Choose the horizontal origin
      closeOnClick: false, // Closes side-nav on <a> clicks, useful for Angular/Meteor
      draggable: true // Choose whether you can drag to open on touch screens
    });
    $('.button-collapse-close').click(function(e) {
      $('.button-collapse').sideNav('hide');
    });
    $('.collapsible').collapsible();
  });
</script>
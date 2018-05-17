
<ul id="slide-out" class="side-nav fixed">
	<li class="white">
		<ul class="collapsible collapsible-accordion">
			<li><a class="collapsible-header waves-effect waves-blue">Test<i
					class="material-icons right" style="margin-right: 0;">arrow_drop_down</i></a>
				<div class="collapsible-body">
					<ul>
						<li><a class="waves-effect waves-blue" href="#">Create/Edit</a></li>
						<li><div class="divider"></div></li>
					</ul>
				</div></li>
		</ul>
	</li>
	<li class="white">
		<ul class="collapsible collapsible-accordion">
			<li><a class="collapsible-header waves-effect waves-blue">Registration<i
					class="material-icons right" style="margin-right: 0;">arrow_drop_down</i></a>
				<div class="collapsible-body">
					<ul>
						<li><a class="waves-effect waves-blue" href="#">Register</a></li>
						<li><a class="waves-effect waves-blue" href="#">Generate
								Question Paper</a></li>
						<li><div class="divider"></div></li>
					</ul>
				</div></li>
		</ul>
	</li>
	<li class="white">
		<ul class="collapsible collapsible-accordion">
			<li><a class="collapsible-header waves-effect waves-blue">Credentials<i
					class="material-icons right" style="margin-right: 0;">arrow_drop_down</i></a>
				<div class="collapsible-body">
					<ul>
						<li><a class="waves-effect waves-blue" href="#">Add User</a></li>
						<li><a class="waves-effect waves-blue" href="#">Reset</a></li>
						<li><a class="waves-effect waves-blue" href="#">Manage
								Subscription</a></li>
						<li><a class="waves-effect waves-blue" href="#">Config
								Editor</a></li>
						<li><div class="divider"></div></li>
					</ul>
				</div></li>
		</ul>
	</li>
	<li><a class="waves-effect waves-blue" href="${pageContext.request.contextPath}/auth/logoutAction.html">Logout</a></li>
</ul>
<a href="#" data-activates="slide-out" class="button-collapse"><i
	class="mdi-navigation-menu"></i></a>
<script type="text/javascript">
$(document).ready(function() {
  $('.button-collapse').sideNav({
        menuWidth: 300,
        closeOnClick: true
      }
    );
    $('.collapsible').collapsible();
});
</script>
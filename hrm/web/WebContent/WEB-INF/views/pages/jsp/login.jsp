<style>
.invalid-color{
	color:red;
}
</style>
<body>
  <div class="row">
    <div class="banner">
      <div class="center-align">
        <div class="z-depth-1 card-color row" style="padding: 32px 48px 0px 48px; border: 1px solid #b9d8d5;">
          <form class="page-form narrow-form public-form signin-form" id="signIn" method="POST" autocomplete="off">
            <div class='row'>
              <div class='col s12'>
                      <h4 class="indigo-text">Login to continue to HRM</h4>
              </div>
     		  <%if(request.getParameter("error") != null){%>
			     <div class="invalid-color">
       			     <b>Invalid Login!</b>
       			 </div>
     				<%}%>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
                <input class='active validate' type='email' name='username' autofocus="autofocus"/>
                <label for='email'>Enter your email</label>
              </div>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
                <input class='active validate' type='password' name='password' />
                <label for='password'>Enter your password</label>
              </div>
               <div class='col s12'>
	              <label style='float: left;'>
						<a class='light-blue-text' href='#!'><b>Forgot Password?</b></a>
				  </label>
			  </div>
            </div>

            <br />
              <div class='row'>
                <button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect indigo'>Login</button>
              </div>
          </form>
        </div>
      </div>
      </div>
    </div>
</body>
</html>

<script type="text/javascript">

 $('#signIn').submit(function (event) {
     $(this).attr('action', '${pageContext.request.contextPath}/pub/login.html');
  });
  document.onkeypress = enterKey;
  
  function enterKey(evt) {
 		var evt = (evt) ? evt : ((event) ? event : null);
 		if (evt.keyCode == 13 ) {
 			 $('#signIn').click();
 		}
  }
</script>
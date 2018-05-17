
<body>
  <div class="row">
    <div class="banner">
      <div class="center-align">
        <div class="z-depth-1 grey lighten-4 row" style="padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
          <form method="post">
            <div class='row'>
              <div class='col s12'>
                      <h5 class="indigo-text">Login into your account</h5>
              </div>
     		  <%if(request.getParameter("user") != null){%>
			     <div class="validation" aria-live="polite" aria-hidden="false">
       			     <b>Invalid username!</b>
       			 </div>
     				<%} else if(request.getParameter("bad") != null){%>
			     <div class="validation" aria-live="polite" aria-hidden="false">
       			     <b>Invalid Credentials!</b>
       			 </div>
     				<%}else if(request.getParameter("inactive") != null){%>
			     <div class="validation" aria-live="polite" aria-hidden="false">
       			     <b>User is inactive!</b>
       			 </div>
     				<%} else if(request.getParameter("error") != null){%>
     					 <div class="validation" aria-live="polite" aria-hidden="false">
       			     <b>Invalid Login!</b>
       			 </div>
     				<%} else if(request.getParameter("custom") != null){%>
 					 <div class="validation" aria-live="polite" aria-hidden="false">
   			    	 <b>Error while Login. Try again.</b>
   				 </div>
			 <%} %>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
                <input class='active validate' type='email' name='username' id='username' />
                <label for='email'>Enter your email</label>
              </div>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
                <input class='active validate' type='password' name='password' id='password' />
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

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
  <div class="row">
    <div class="banner">
      <div class="center-align">
        <div class="z-depth-1 card-color row" style="padding: 32px 48px 0px 48px; border: 1px solid #b9d8d5;">
          <form:form modelAttribute="signUpForm" name="signUpForm" class="page-form" id="signUpForm" method="POST" autocomplete="off">
          
          	<div id="errorDiv" style="display:none;">
			   <form:errors path="firstName" />
	           <form:errors path="lastName" />
	           <form:errors path="email" />
	           <form:errors path="phone" />
	           <form:errors path="refEmail" />
	           <form:errors path="errorString" />
		     </div>
		     
            <div class='row'>
              <div class='col s12'>
                      <h4 class="indigo-text">Please provide your details</h4>
              </div>
            </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='firstName' class='active validate' autofocus="autofocus"/>
                <form:label path='firstName'>First Name</form:label>
              </div>
            </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='lastName' class='active validate' />
                <form:label path='lastName'>Last Name</form:label>
              </div>
            </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='email' class='active validate' />
                <form:label path='email'>Email</form:label>
              </div>
            </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='phone' class='active validate' />
                <form:label path='phone'>Mobile Number</form:label>
              </div>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='refEmail' class='active validate'/>
                <form:label path='refEmail'>Reference Email(Optional)</form:label>
              </div>
            </div>

            <br />
              <div class='row'>
                <a class="btn btn-large" id="submitButton" onclick="submitForm()">SignUp</a>
              </div>
          </form:form>
        </div>
      </div>
      </div>
    </div>
</body>
</html>

<script type="text/javascript">

$(document).ready(function() {
    var toastContent = "";
	$("#errorDiv [id$='.errors']").each(function() {
		toastContent += $(this).html();
		toastContent += "<br/>";
	});
	callErrorToast(toastContent);
});

$("#email").on("blur", function(){
	   if($(this).val() != "" && !validateEmail(document.getElementById("email"))){
		   showError(this, "Please enter valid email id.");
	   }
});

$("#refEmail").on("blur", function(){
	   if($(this).val() != "" && !validateEmail(document.getElementById("refEmail"))){
		   showError(this, "Please enter valid email id.");
	   }
});

$("#phone").on("blur", function(){
	var telephone = $(this).val();
	if(telephone != "" && isNaN(telephone)){
		showErrorCssWithToast("phone", "Please enter valid mobile number.");
	} else if(telephone != "" && telephone.length != 10){
		showErrorCssWithToast("phone", "mobile number should be 10 digit.");
	}
});

 function submitForm(){
	 
	 var errorFlag = false;
	   $("#signUpForm input.validate").each(function(){
		   if($(this).val()=="" || $(this).val() == null){
			   errorFlag = true;
			   displayErrorCss(this.id);
		   }
	 });
	 
	 if(errorFlag){
		 callErrorToast("Highlighted fields are mandatory.");
		 return false;
	 }
	 
	 if(!validateEmailByString($("#email").val())){
		   showErrorCssWithToast("email", "Please enter valid email id.");
		   return false;
	 }
	 
	 if(!validateEmailByString($("#refEmail").val())){
		   showErrorCssWithToast("refEmail", "Please enter valid email id.");
		   return false;
	 }
	 
	 document.signUpForm.action="${pageContext.request.contextPath}/pub/signUp.html";
	 document.signUpForm.submit();
 }

 document.onkeypress = enterKey;

  function enterKey(evt) {
 		var evt = (evt) ? evt : ((event) ? event : null);
 		if (evt.keyCode == 13 ) {
 			 $('#signUpForm').click();
 		}
  }
</script>
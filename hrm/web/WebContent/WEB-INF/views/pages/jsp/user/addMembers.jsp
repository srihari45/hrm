<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <div class="row">
    <div class="banner">
      <div class="center-align">
        <div class="z-depth-1 card-color row" style="padding: 32px 48px 0px 48px; border: 1px solid #b9d8d5;">
          <form:form modelAttribute="addMemberForm" name="addMemberForm" id="addMemberForm" class="page-form" method="POST" autocomplete="off">
		    
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
                      <h4 class="indigo-text">Please enter member details</h4>
              </div>
            </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='firstName' id='firstName' type="text" class='active validate' autofocus="autofocus"/>
                <form:label path='firstName'>First Name</form:label>
              </div>
            </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='lastName' id='lastName' type="text" class='active validate' />
                <form:label path='lastName'>Last Name</form:label>
              </div>
            </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='email' id='email' type="text" class='active validate' />
                <form:label path='email'>Email</form:label>
              </div>
            </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='phone' id='phone' type="text" class='active validate' maxlength="10" />
                <form:label path='phone'>Mobile Number</form:label>
              </div>
            </div>

            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='refEmail' id='refEmail' type="text" class='active validate'/>
                <form:label path='refEmail'>Reference Email(Optional)</form:label>
              </div>
            </div>

            <div class='col s3'></div>
            <div class='row'>
                <a class='col s6 btn btn-large waves-effect indigo' id="submitButton" onclick="submitForm()">ADD</a>
            </div>
          </form:form>
        </div>
      </div>
      </div>
    </div>

<script>

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
	   $("#addMemberForm input.validate").each(function(){
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
	 
	 if($("#refEmail").val() != "" && !validateEmailByString($("#refEmail").val())){
		   showErrorCssWithToast("refEmail", "Please enter valid email id.");
		   return false;
	 }
	 
	 document.addMemberForm.action="${pageContext.request.contextPath}/user/submitAddMember.html";
	 document.addMemberForm.submit();
}

document.onkeypress = enterKey;

 function enterKey(evt) {
		var evt = (evt) ? evt : ((event) ? event : null);
		if (evt.keyCode == 13 ) {
			 $('#addMemberForm').click();
		}
 }
</script>
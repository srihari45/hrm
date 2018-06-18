<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
  <div class="row">
    <div class="banner">
      <div class="center-align">
        <div class="z-depth-1 card-color row" style="padding: 32px 48px 0px 48px; border: 1px solid #b9d8d5;">
          <form:form modelAttribute="workScheduleForm" name="workScheduleForm" id="workScheduleForm" class="page-form" method="POST" autocomplete="off">
		    
		    <div id="errorDiv" style="display:none;">
			   <form:errors path="scheduleCandidate" />
	           <form:errors path="scheduleWork" />
	           <form:errors path="scheduleDate" />
	           <form:errors path="errorString" />
		     </div>
		     
            <div class='row'>
              <div class='col s12'>
                      <h4 class="indigo-text">Please enter member details</h4>
              </div>
            </div>
            
            <div class="row">
				<div class="input-field col s12">
					<label for="scheduleCandidate"></label>
					<form:select path="scheduleCandidate" id="scheduleCandidate" class="active validate">
						<form:option value="" disabled="disabled" selected="selected">Select Candidate</form:option>
						<form:option value="1">Member1</form:option>
						<form:option value="2">Member2</form:option>
						<form:option value="3">Member3</form:option>
					</form:select>
				</div>
		   </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path='scheduleWork' id='scheduleWork' class='active validate' />
                <form:label path='scheduleWork'>Work Description</form:label>
              </div>
            </div>
            
            <div class='row'>
              <div class='input-field col s12'>
                <form:input path="scheduleDate" id="scheduleDate" class='active validate datepicker'/>
                <form:label path='scheduleDate'>Date of Expense</form:label>
              </div>
            </div>

            <div class='col s3'></div>
            <div class='row'>
                <a class='col s6 btn btn-large waves-effect indigo' id="submitButton" onclick="submitForm()">SCHEDULE</a>
            </div>
          </form:form>
        </div>
      </div>
      </div>
    </div>
</body>

<script>

$(document).ready(function() {
	$('select').material_select();
    var toastContent = "";
	$("#errorDiv [id$='.errors']").each(function() {
		toastContent += $(this).html();
		toastContent += "<br/>";
	});
	callErrorToast(toastContent);
	 $('.datepicker').pickadate({
		    selectMonths: true, // Creates a dropdown to control month
		    selectYears: 10, // Creates a dropdown of 15 years to control year,
		    today: 'Today',
		    clear: 'Clear',
		    close: 'Ok',
		    format: 'mm/dd/yyyy',
		    closeOnSelect: false // Close upon selecting a date,
  });
});

function submitForm(){
	 
	 var errorFlag = false;
	   $("#workScheduleForm input.validate").each(function(){
		   if($(this).val()=="" || $(this).val() == null){
			   errorFlag = true;
			   displayErrorCss(this.id);
		   }
	 });
	 
	 if(errorFlag){
		 callErrorToast("Highlighted fields are mandatory.");
		 return false;
	 }
	 
	 document.workScheduleForm.action="${pageContext.request.contextPath}/user/submitWorkSchedule.html";
	 document.workScheduleForm.submit();
}

document.onkeypress = enterKey;

 function enterKey(evt) {
		var evt = (evt) ? evt : ((event) ? event : null);
		if (evt.keyCode == 13 ) {
			 $('#workScheduleForm').click();
		}
 }
</script>
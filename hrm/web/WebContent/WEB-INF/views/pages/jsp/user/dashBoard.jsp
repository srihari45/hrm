	<div class="row">
		<div class="banner">
			<div class="container">
				<div class='col s2'></div>
				<div class='col s8'>
					<div class="z-depth-1 card-color row" style="padding: 32px 48px 0px 48px; border: 1px solid #b9d8d5;">
						
						<div class='row'>
							<div class='col s12'>
								<h2 class="indigo-text center-align">Current Statistics</h2>
							</div>
						</div>
						
						<div class='row'>
							<div class='col s12'>
								<h5 class="indigo-text" id="getTitle"></h5>
							</div>
							<div class='col s12' id="curExpesnseDetails"></div>
						</div>
						
						<form class="page-form narrow-form public-form" method="POST" autocomplete="off">
							
							<div class='row'>
				              <div class='col s12'>
				                      <h5 class="indigo-text">Enter your expenses</h5>
				              </div>
				            </div>

				            <div class='row'>
				              <div class='input-field col s12'>
				                <input class='active validate datepicker' type="text" id="dateOfExpense"/>
				                <label for='dateOfExpense'>Date of Expense</label>
				              </div>
				            </div>
				
				            <div class='row'>
				              <div class='input-field col s12'>
				                <input class='active validate' type="text" id='description' />
				                <label for='description'>Description</label>
				              </div>
				            </div>
				            
				            <div class="row">
								<div class="input-field col s12">
									<label for="includeMems"></label>
									<select id="includeMems" class="active validate" multiple>
										<option value="" disabled="disabled" selected="selected">Incule Members</option>
										<option value="1">Member1</option>
										<option value="2">Member2</option>
										<option value="3">Member3</option>
									</select>
								</div>
						   </div>

				            <div class='col s3'></div>
				            <div class='row'>
				                <a class='col s6 btn btn-large waves-effect indigo disabled' id="submitButton">Submit</a>
				            </div>
				          </form>
				          
					</div>
				</div>
			</div>
		</div>
	</div>

<script>
$(document).ready(function() {
	
	$("#getTitle").html(currentMonth() + " Expenses");
	$("#curExpesnseDetails").html("<p>No Expenses Found</p>");
	
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
	 $('.timepicker').pickatime({
	    default: 'now', // Set default time: 'now', '1:30AM', '16:30'
	    fromnow: 0,       // set default time to * milliseconds from now (using with default = 'now')
	    twelvehour: false, // Use AM/PM or 24-hour format
	    donetext: 'OK', // text for done-button
	    cleartext: 'Clear', // text for clear-button
	    canceltext: 'Cancel', // Text for cancel-button
	    autoclose: false, // automatic close timepicker
	    ampmclickable: true, // make AM PM clickable
	    aftershow: function(){} //Function for after opening timepicker
	 });
});
</script>

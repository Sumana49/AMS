/**

Avnet Asset Tracking Application JavaScript
Login.jsp

*/
		
var emp_details=[["test","test"]];

/* Alert Hiding */
function hideAlert(){
		$( "#loginErrorField" ).slideUp(500);
}

$(document).ready(function() {
	$("#loginErrorField").html("");

});

function error(){
	$("#loginErrorField").html("");
}

/* Login Validation */
function clientValidation(username,password){

	 if(username==""){
	 	if(password=" "){

 		$( "#loginErrorField" ).hide();
 		$("#loginErrorField").html("<div class='alert-close' onclick='hideAlert();'>x</div>"+"Username & Password is necessary");
		$( "#loginErrorField" ).slideDown( 400 );	

	 	}else{

	 	$( "#loginErrorField" ).hide();
		$("#loginErrorField").html("<div class='alert-close' onclick='hideAlert();'>x</div>"+"Username is necessary");
		$( "#loginErrorField" ).slideDown( 400 );

		}
		return false;
	}
	 if (password=="") {

		$( "#loginErrorField" ).hide();
		$("#loginErrorField").html("<div class='alert-close' onclick='hideAlert();'>x</div>"+"Password is necessary");
		$( "#loginErrorField" ).slideDown( 400 );
		return false;

	}
	return true;
}

function  mockJSONValidation(username,password) {

	for(var i=0;i<emp_details.length;i++)
			{
				if(username==emp_details[i][0])
				{
					if(password==emp_details[i][0])
					{
						return true;	
					}
					else
					{
						error();
						$( "#loginErrorField" ).hide();
						$("#loginErrorField").html("<div class='alert-close' onclick='hideAlert();'>x</div>"+"Username and Password mismatch");
						$( "#loginErrorField" ).slideDown( 400 );
						$("#loginForm #password").val("");
						return false;
					}
				}

			}
			error();
			$("#loginForm #username").val();
			$("#loginForm #password").val("");
			$( "#loginErrorField" ).hide();
			$("#loginErrorField").html("<div class='alert-close' onclick='hideAlert();'>x</div>"+"Username doesn't exist, Try again");
			$( "#loginErrorField" ).slideDown( 400 );
			return false;
}

function login()
{
	error();
	var username=$("#loginForm #username").val();
	var password=$("#loginForm #password").val();
	if(clientValidation(username,password))
	{
		if(mockJSONValidation(username,password)){
		$( "#loginErrorField" ).hide();
			
			return true;

		}
		return false;
	}
	return false;
			
}
$(document).ajaxStart(function(){
	popupInfoFixed("Please wait...Loading..","alert-info");
 }).ajaxStop(function(){
	hideAlertFixed();
 });

//DUMMY js DO NOT USE IT 
	
function GetURLParameter(sParam)
	{
	  	var sPageURL = window.location.search.substring(1);
	  	var sURLVariables = sPageURL.split('&');
	    for (var i = 0; i < sURLVariables.length; i++)
	    {
	    	var sParameterName = sURLVariables[i].split('=');
	        if (sParameterName[0] == sParam)
	        {
	            return sParameterName[1];
	        }
	    }
	    return null;
	}
var formScope;

$(function(){
	var errormessages = $('#errormessages');
	var getAssetTypes="getAssetTypes.do";
	var getApproverNames="getApproverNames.do";
	var LoginSubmitID=$("#LoginSubmitID");
	var UsernameID=$("#UsernameID");
	var PasswordID=$("#PasswordID");
	var employeeID=$("#employeeID");
	var errmsg_eid=$("#errmsg_eid");
	var getEmployeeDetails="getEmployeeDetails.do";
	var label_error=$('label.error');
	var namelabel=$("#namelabel");
	var assetType=$("#assetType");
	var assetApprover=$("#assetApprover");
	var assetStartDate=$("#assetStartDate");
	var assetNumberOfDays=$("#assetNumberOfDays");
	var assetSeverity=$("#assetSeverity");
	var assetNote=$("#assetNote");
	var submitbtn=$("#submitbtn");
	var errmsgno_ofdays=$("#errmsgno_ofdays");
	var resetbtn=$("#resetbtn");
	var errmsgnote=$("#errmsgnote");
	var reqstatus=$("#reqstatus");
	var requestSubmissionConformation = $("#submission-dialog");
	var closex = " <div class='alert-close'>x</div> ";
	var	register_form=$("#register-form");

	// CLEARING THE ERROR MESSAGE ON CHANGE FROM EACH ELEMENT INDIVIDUALLY
	assetType.on('change',function(){
		assetType.valid();
	});
	assetSeverity.on('change',function(){
		assetSeverity.valid();
	});
	assetApprover.on('change',function(){
		assetApprover.valid();
	});
	
	assetStartDate.on('change',function(){
		assetStartDate.valid();
	});
	
	assetNumberOfDays.on('change',function(){
		assetNumberOfDays.valid();
	});
		
	assetNote.on('change',function(){
		assetNote.valid();
	});
	
	var error=null;
	 error=GetURLParameter("credentials_mismatch");
	if(error=="true"){
		errormessages.html("Please enter valid credentials" + closex);
		errormessages.addClass('alert');
		errormessages.addClass('alert-error');
		errormessages.hide();
		errormessages.slideDown( 400 );
		$('.alert-close').on('click',function(){
		errormessages.slideUp(400);
		});
	}
	
	$.getJSON( getAssetTypes, function( data ) {
		  for(var i=0;i<data.type_names.length;i++){
			  $('<option>').val(data.type_id[i]).text(data.type_names[i]).appendTo('#assetType');
		  }
		assetType.select2();
		});
	
	$.getJSON( getApproverNames, function( data ) {
	  for(var i=0;i<data.approverName.length;i++){
		  $('<option>').val(data.approverId[i]).text(data.approverName[i]).appendTo('#assetApprover');
	  }
	  assetApprover.select2();
	});
	
	if(reqstatus.text().trim()!="default" && reqstatus.text().trim()!="" 
		&& reqstatus.text().trim()!="fields_missing" && reqstatus.text()!="ack_missing" ){
		var req = reqstatus.html();
		reqstatus.html("Asset Request Submitted. REQUEST ID : "+ req +" "+ closex);
		reqstatus.addClass('alert');
		reqstatus.addClass('alert-success');
		$('.alert-close').on('click',function(){
			reqstatus.slideUp(400);
			reqstatus.html("");
			//window.location.replace("/AMSWeb/home.do");
		});
	}
	else if(reqstatus.text()=="fields_missing")
	{
	reqstatus.html("Some required fields missing. Try Again." + closex);
	reqstatus.addClass('alert');
	reqstatus.addClass('alert-error');
	$('.alert-close').on('click',function(){
		reqstatus.slideUp(400);
		reqstatus.html("");
		//window.location.replace("/AMSWeb/home.do");
	});
	}
	else if(reqstatus.text()=="ack_missing")
	{
	reqstatus.html("Something went wrong. Contact service team." + closex);
	reqstatus.addClass('alert');
	reqstatus.addClass('alert-error');
	$('.alert-close').on('click',function(){
		reqstatus.slideUp(400);
		reqstatus.html("");
		//window.location.replace("/AMSWeb/home.do");
	});
	}
	else if(reqstatus.text()=="default")
	{
	reqstatus.html("Asset Request Failed. Try Again." + closex);
	reqstatus.addClass('alert');
	reqstatus.addClass('alert-error');
	$('.alert-close').on('click',function(){
		reqstatus.slideUp(400);
		reqstatus.html("");
		//window.location.replace("/AMSWeb/home.do");
	});
	}
	
	LoginSubmitID.click(function(){
		var username=UsernameID.val();
		var password=PasswordID.val();
		 
		if(username=="" || password==""){
			if(username=="" && password==""){
				UsernameID.addClass("needed");
				PasswordID.addClass("needed"); 
			}else if(username!=""){
				UsernameID.removeClass("needed");
				PasswordID.addClass("needed");
				
			}else if(password!=""){
				PasswordID.removeClass("needed");
				UsernameID.addClass("needed");
				 
			}
		return false;
		}
	});
	
 	employeeID.keypress(function(key) {
 		errormessages.slideUp(400);
			if (key.which != 13 && key.which != 8 && key.which != 0 && (key.which < 48 || key.which > 57)) {
		        errmsg_eid.html("Digits Only").show().fadeOut("slow");
		        key.preventDefault();	
			}  
		});	
	employeeID.keyup(function(key) {
				if(employeeID.val().length>=4){
					var empId = employeeID.val();
					$.ajax({ 
				    url : getEmployeeDetails,  
				    timeout:10000,
					data :  "empId="+ empId,  
				    success : function(response) {  
				      if(response.name != null ){
				    	namelabel.text(response.name).removeClass('invalid_eid');
						assetType.prop('disabled',false);
						assetStartDate.prop('disabled',false);
						assetNumberOfDays.prop('disabled',false);
						assetSeverity.prop('disabled',false);
						assetApprover.prop('disabled',false);
						assetNote.prop('disabled',false);
						submitbtn.addClass('btn-primary').removeClass('btn-disable').prop('disabled',false);
						resetbtn.addClass('btn-primary').removeClass('btn-disable').prop('disabled',false);
					 	}else{  namelabel.text("Invalid Employee ID").addClass('invalid_eid');
					 		disableForm();
						 }
				 },  
				 error : function(e) {  	 
					 	namelabel.text("Unable To Fetch :: Contact Admin").addClass('invalid_eid');
					 	disableForm();
					 }  
				});  
				}
				else
				  {
					 reqstatus.hide();
					employeeID.keypress(function(key) {
							if (employeeID.val().length<4 && key.which==13)
								errmsg_eid.html("Please check your employee id").show().fadeOut("slow");
					});
					disableForm();
					namelabel.text("");
				  	//resetbtn.css({backgroundColor: '#428bca'}).prop('disabled',false);
					resetbtn.removeClass('btn-disable').addClass('btn-primary').prop('disabled',false);
				  	//if (employeeID.val()==""){resetbtn.css({backgroundColor: '#B4B8C7'}).prop('disabled',true);}
				  	if (employeeID.val()==""){resetbtn.removeClass('btn-primary').addClass('btn-disable').prop('disabled',true);}
				   }
				 });			 

		function disableForm()
			{
				$('label.error').hide();
				errmsgno_ofdays.html("").show();
				assetType.select2("val","").prop('disabled',true);
			  	assetStartDate.val("").attr("placeholder","Select a start date").prop('disabled',true);
			    assetNumberOfDays.val("").attr("placeholder","Number of days").prop('disabled',true);
			  	assetSeverity.select2("val","").prop('disabled',true);
			  	assetApprover.select2("val","").prop('disabled',true);
			  	assetNote.val("").attr("placeholder","Specification & Purpose").prop('disabled',true);
			  	//submitbtn.css({backgroundColor: '#B4B8C7'}).prop('disabled',true);
			  	submitbtn.removeClass('btn-primary').addClass('btn-disable').prop('disabled',true);
			}

		assetType.select2();  
		assetSeverity.select2(); 
		assetApprover.select2();
		assetStartDate.datepicker({dateFormat:'dd/mm/yy',minDate: 0}).attr('readonly','readonly');
		register_form.validate({
                    rules: {
                    	assetType:{ required: true },
                    	assetStartDate: { required: true },
                    	assetNumberOfDays: { digits: true,
                    						 maxlength: 3
                    						},
                    	assetSeverity:{ required: true },
                    	assetApprover:{ required: true },
                    	assetNote:{
                            required: true,
                            minlength: 5,
                            maxlength:250
                        },
                        assetStartDate : { 
                        	required: true
                        }
                    },
                   messages: {
                        assetType: { required:"Please select an asset type"},
                        assetStartDate:{ required:"Please select date"},
                        assetNumberOfDays: { digits:"Please enter a numeric value.",
                        	maxlength: "Please enter only three digit number."},  
                        assetSeverity: { required: "Please select a severity level" },
                        assetApprover: { required: "Please select an Approver" },
                        assetNote: {
                            required: "Please provide a note",
                            minlength: "Your note must be at least 5 characters long"
                        },
                        assetStartDate : { 
                        	required: "Please enter a Start Date"
                        }
                    },
                    submitHandler: function(form) {
                    	requestSubmissionConformation.dialog( "open" );
                    	formScope=form;
                    }
                });

			  requestSubmissionConformation.dialog({
		      autoOpen: false,
		      height: 200,
		      width: 350,
		      modal: true,
		      buttons: {
			        "Apply": function(){
			        	formScope.submit();
			        	requestSubmissionConformation.dialog( "close" );
			        },
			        "Cancel": function(){
			        	requestSubmissionConformation.dialog( "close" );
			        }
		      },
	      close: function() {
	    	  requestSubmissionConformation.dialog( "close" );
	      }
		});
        
			  assetNumberOfDays.keydown(function(key) {
			  //assetNumberOfDays.keypress(function(key) {
			    	//if ((key.which != 13 || key.keyCode != 13 || key.charCode != 13) && (key.which != 8 || key.keyCode != 8 || key.charCode != 8) && (key.which != 0 || key.keyCode != 0 || key.charCode != 0) && (key.which != 9 || key.keyCode != 9 || key.charCode != 9) && (key.which < 48 || key.which > 57)) {
			    	if (key.which != 13 && key.which != 8  && key.which != 0 && 
			    			key.which != 9  && (key.which < 48 || key.which > 57)) {
			    		errmsgno_ofdays.html("Please use digits Only").show();
				        key.preventDefault();	
					} 
			    	else{
			    			errmsgno_ofdays.html("").show();	}
			 	 	
			    	if(assetNumberOfDays.val().length>2 && (key.which!=8 && key.which!=9 && key.which!=13)){
			 	 		errmsgno_ofdays.html("Please use 3 digits only").show();
			 	 		return false;
			 	 	}
					
			 	 	if(assetNumberOfDays.val()==0 && assetNumberOfDays.val().length==1 ){
	                	if(key.which == 48 || key.keyCode == 48 || key.charCode == 48){
	                		errmsgno_ofdays.html("Please use just a 0 or more no. of days").show();
		                 return false;
		                }
		            }	
				});	
			  
	  assetNote.keypress(function(key) {
      if(assetNote.val().length>249){
    	  errmsgnote.html("Use less charecters").show().fadeOut("slow");
	          return false;
	      }
       });
		

			resetbtn.on('click',function(){
				errormessages.slideUp(400);
			employeeID.val("").attr("placeholder","Employee ID");
			namelabel.html("");
			disableForm();
	  		//resetbtn.css({backgroundColor: '#B4B8C7'}).prop('disabled',true);
			resetbtn.removeClass('btn-primary').addClass('btn-disable').prop('disabled',true);
			});
});
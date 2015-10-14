/**
 * 
 */

$(function(){
	
	 var empIdErr1="Please enter your employee id";
	 var empIdErr2="Please check your employee id";
	 var empIdErr3="Please enter only digits";
	 $('#secureLoginForm').validate({
        rules: {
   
        	EmpID:{ 
        		    required: true,
        		    digits: true,
        		    maxlength: 5,
        		    minlength: 4}
        },
        onkeyup: false,
       messages: {
    	 EmpID:{
            	required : empIdErr1,
            	maxlength :empIdErr2,
            	minlength :empIdErr2,
            	digits:empIdErr3
            }         
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

	
});

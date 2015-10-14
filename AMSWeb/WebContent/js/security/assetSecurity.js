$(function(){
	
	var closex = " <div class='alert-close'>x</div> ";
	var errmsgs=$('#errormessages');
	var empId=$('#idContainer');
	if($('.namecontainer').text().trim()==""){
		
		console.log("invoked");
		errmsgs.html("Invalid Employee ID "+ closex);
		errmsgs.addClass('alert');
		errmsgs.addClass('alert-success');
		$('.alert-close').on('click',function(){
			errmsgs.slideUp(400);
		});
	} 
    if(empId.text().trim()!="")
    {
    	var tdQuery=$("tbody tr td");
    	$('#assetDetails').DataTable( {
        	"async":false,
            "bFilter":false,
            "bInfo":true,
            responsive:true,
      		'iDisplayLength': 10,
      		"order": [[ 1, "desc" ]],
      		"bLengthChange": false,
      		 "bAutoWidth": false,
      		"bServerSide": true,
      		"processing":true,
      		"bSort":false,
      		"oLanguage":{
      			"sInfoFiltered":""
      		},
    		"ajax":{
    			"url" : "getAssetSecurity.do",
    			"data" : "empId="+empId, 
    			"timeout": 5000,
    			"failure": function(){
    				tdQuery.html("Sorry! Try Reloading the Page! ");
    				tdQuery.html('color','red');
    			},
    			"error": function(){
    				tdQuery.html("Sorry! Something went wrong! ");
    				tdQuery.css('color','red');
    		    },
    		},
    		"createdRow": function ( row, data, index ) 
            {
    			    var flagQuery=$('td', row).eq(2);	
            		if(data.inHouseFlag=="Yes"){
            			flag="<i class='icon-checkmark-circle flagyes'></i>";
        				
            		}else if(data.inHouseFlag=="No"){
            			flag="<i class='icon-cancel-circle flagno'></i>";
            		}
            		
            		flagQuery.html(flag);
            		flagQuery.addClass('aligncenter');        		
           	},
            "sAjaxDataProp": "assetRequestHistory",
            "sPaginationType": "input",
            "error": function () {
            	tdQuery.html("Sorry! Try Reloading the Page! ");
            },
           	"columns": [
    		{ "mDataProp": "assetType"},
    		{ "mDataProp": "productName"},
            { "mDataProp": "inHouseFlag"}
            ]
    	       } );
    	
    }
    
      
 
    });
    
function convertDate(data){
	var date = new Date(data.dueDate);

	var curr_date = date.getDate();
    var curr_month = date.getMonth() + 1;
    var curr_year = date.getFullYear();
    
	return curr_year+"-"+("0" + curr_month).slice(-2)+"-"+("0" + curr_date).slice(-2);
	
}






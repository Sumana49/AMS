/**

Avnet Asset Tracking Application JavaScript
request.jsp

*/

/* Global Variables */

var currentRequest = "null",itemLoaded=0;
var i=0;
var availableArray = [];
var IdOriginal=[];
var Category="";
var searchStringType;
var adminActionId;
var noComments="No comments available";
var suggestion="Please enter comments";
function viewAsset(e) 
{ 
	currentRequest = $(e).data('id');
}
function convertDate(data){
	if(data=="-"){

	}
	else{
			var date = new Date(data);

		var curr_date = date.getDate();
	    var curr_month = date.getMonth() + 1;
	    var curr_year = date.getFullYear();
	    
		return ("0" + curr_date).slice(-2)+"-"+("0" + curr_month).slice(-2)+"-"+curr_year;
		
	}
    return "-";
    }
//Available assets ajax call

function keyPressDone(searchStringType)
{
	availableArray = [];
	var changeText=$("#assignAssetTextBox").val();
	$.ajax({
			type: "GET",
			url: "availableAssets.do?searchString="+changeText+"&searchIdType="+searchStringType+"&categoryId="+Category+"",
			async: false,
			timeout:10000,
		}).done(function(data) {
			var i=0;
			if(data.assetId=="no"){
				if(changeText.length>1){
				popupInfoFixed("No matches found for your search", 'alert-error');
				}
			}
			else
				{
				$( "#ErrorFieldContentFixed" ).slideUp(500);
				 for(i=0;i<data.assetType.length;i++){
					availableArray[i]=data.assetType[i]+"-"+data.assetId[i];
					IdOriginal[i]=data.assetIdOriginal[i];
				 }
				}	
		//Asset autocomplete				
			$( "#assignAssetTextBox" ).autocomplete({
			      source: availableArray,
			      minLength:2,
			      autofill:true
			    });
			autocompleteflag = 1;
		}).fail(function(data) {
			popupInfoFixed("No matches found for your search", 'alert-error');
	    });
}

//Resolved function functionality

function assignAssetFinal(formName) {
	hideAlert();
	  var lengthFunc;
	  var idCalled = formName;       
      var idCalledREQ = idCalled.substring(12);
	  lengthFunc=$("#noteDisplay"+idCalledREQ).html();
      if(!lengthFunc)
	  	{
	  		lengthFunc=1;
	  	}
      if(lengthFunc.length>500)
		{
    		popupInfo("Request: " + idCalledREQ + " Please enter a shorter comment", 'alert-error');
    		 var  u="requests.do?from="+from+"&to="+to;
    		 setTimeout(function(){
	        	   $("#Requests").DataTable().ajax.url(u).load();
	           }, 500);
		}
      
      else{
    	  
    
      var textAreaOnSubmit = $("#noteDisplay" + idCalledREQ).text();
      var submitClick= $("#adminStates" + idCalledREQ).val();
      if((submitClick=="resolved")&&( $("#selectedAssetDisplay"+idCalledREQ).text()=="")){
    	  $("#selectedAssetDisplay"+idCalledREQ).html("Please select an asset");
    	  $("#selectedAssetDisplay"+idCalledREQ).addClass("errorColor");
      }
      else{
      if(((submitClick=="onHold")||(submitClick=="rejected"))&&((textAreaOnSubmit==noComments)||(textAreaOnSubmit=="")||(textAreaOnSubmit==suggestion)))
    	  {
    	  $("#noteDisplay" + idCalledREQ).html(suggestion);
    	  $("#noteDisplay" + idCalledREQ).addClass("errorColor");
    	  }
      else{  
    	  	$.ajax({
		        url: $(formName).attr("action"),
		        type: 'post',
		        timeout:10000,
		        data: $(formName).serialize()
		    }).done(function(res) {       	        
		         popupInfo("Request: " + idCalledREQ + " is processed", 'alert-success');
		         itemLoaded = 0;
		            $("#AssetButton" + idCalledREQ).css('display', 'none');
		            $("#commentClick" + idCalledREQ).css('display', 'none');
		           var  u="requests.do?from="+from+"&to="+to;
		           setTimeout(function(){
		        	   $("#Requests").DataTable().ajax.url(u).load();
		           }, 500);
		    }).fail(function(data) {
			    popupInfo("Request Processing failed", 'alert-error');
		    });
       }
      }
     }
    return false;
}
$(function() {
	var assignAssetTextBox=$("#assignAssetTextBox");
	$("#assignAsset").on('change',function(){
		hideAlert();
		var searchtypevalue = $("#assignAsset").val();
		assignAssetTextBox.val("");
	if(searchtypevalue!=0){
		assignAssetTextBox.prop('disabled', false);
				if(searchtypevalue=='1'){
					assignAssetTextBox.keyup(function() {
						if(assignAssetTextBox.val().length<3){
							keyPressDone(searchtypevalue);
						}
						
					});
					assignAssetTextBox.attr('placeholder','Enter the Asset ShortText');
				}
				if(searchtypevalue=='2'){
					assignAssetTextBox.keyup(function() {
						if(assignAssetTextBox.val().length<3){
							keyPressDone(searchtypevalue);
						}
					});
					assignAssetTextBox.attr('placeholder','Enter the Asset Identity');
				}
				}
	});
});

$(document).ready(function()
{
/* DatePicker From / To */

	$( "#from" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 1,
		      changeYear: true,
              dateFormat: "yy-mm-dd",
              maxDate:"+0D",
		      onClose: function( selectedDate ) {
		    	$( "#to" ).datepicker( "option", "minDate", selectedDate );
			 	  
		      }
    });

	$( "#to" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 1,
		      changeYear: true,
              dateFormat: "yy-mm-dd",
              maxDate:"+0D",
		      onClose: function( selectedDate ) {
		    	  if(selectedDate!="")
		        	$( "#from" ).datepicker( "option", "maxDate", selectedDate );
		    	  else
		    		  $( "#from" ).datepicker( "option", "maxDate", "+0D" );
		      }
	 });

/* Request Data Load */


$("#assignAsset").select2({
	width:200
}); 

/* Request Datatable Load */
 table = $("#Requests")
				.on('xhr.dt', function ( e, settings, json ) {
					if (typeof json.errorMessage != 'undefined'){
						settings.oLanguage.sZeroRecords=json.errorMessage;
						
					}
			        
				})
				.DataTable({
					"processing": true,
				    "serverSide": true,
				    "searching": false,
			  		"responsive":true,
			  		"pageLength": 10,
			  		"order": [[ 5, "desc" ]],
			  		"lengthChange": false,
			  		"sPaginationType": "input",
			  		"ajax": {
			  			"url":"requests.do?from=&to="
			  		},
			          

			  		"createdRow": function (row, data, index) {
			  			if(data.DateOfApproval!='-'){
			  				$('td', row).eq(5).html(convertDate(data.DateOfApproval.slice(0,10)));
			  			}
        			},
					"columns": [
					
					{
						"data": null,
						"orderable":false,
						"class":"details-control",
						"defaultContent":'<i class="icon-plus cursorPointer"></i>',
						"width":"5%"
				    },
				  
			        {	
			         	"data": "EmployeeID",
			        	"width":"7%"
			         },
			          { 
			        	"data": "Name" 
			        },
			        { 
			        	"data": "Type" ,
			        	"width":"20%"
			        },
			       
			        { 
			        	"data": "Status",
			        	"width":"20%"
			         },
			         { 
			        	"data": "DateOfApproval",
			        	"width":"20%"
			         }
			        ]
			
			});

 function searchRequests()
 {
	 hideAlert();
		var from=$("#from").val();
	    var to=$("#to").val();
	    if(from!="" && to!="")
	    {
	    	$("#to").removeClass("needed");
	    	$("#from").removeClass("needed");
	        table.order( [ 5, 'desc' ] );
	        var u="requests.do?from="+from+"&to="+to;
	        table.ajax.url(u).load();
	     }
	    else {
	    	if(from==""){
	    		$("#from").addClass("needed");
	    	}
	    	else{
	    		$("#from").removeClass("needed");
	    	}
	    	if(to==""){
	    		$("#to").addClass("needed");
	    	}
	    	else{
	    		$("#to").removeClass("needed");
	    	}
	    	
	    }
 }
 /* Date Filtering */
 $("#filter").on('click',searchRequests);

 function enterKeyRequestSearch(e)
 {
 		var key=e.which;
 		if(key==13)
 		{
 			searchRequests();
 		}
 }


 $("#to").on('keypress',enterKeyRequestSearch);

 $(document).on('change',adminActionId,function(){
		var adminStateValue = $(adminActionId).val();
		if(adminStateValue=="onHold" || adminStateValue=="rejected")
			{
				$("#Comments").addClass("required");
			}
		else if(adminStateValue=="resolved")
			{
				$("#Comments").removeClass("required");
			}
});

function format(data)
{
	var RequestID=data.RequestID;
	var status = "";
	var Severity=data.Severity;
	Category=data.CategoryId;
	if(data && data.Status){
		status =data.Status.toUpperCase();
		$("#adminStates"+RequestID).select2({
		}); 
	}
	if(Severity=="H")
		{
		Severity="High";
		}
	else if(Severity=="L")
		{
		Severity="Low";
		}
	else if(Severity=="M")
		{
		Severity="Medium";
		}
		
	adminActionId ="#adminStates"+data.RequestID;
	
	if(status=='BU_APPROVED'|| status=='ADMIN_HOLD')
	{
		var parameter = "#resolveform"+RequestID;
			return "<form id='resolveform"+RequestID+"' method='post' onsubmit='return assignAssetFinal(\""+parameter+"\")' action='resolvedAction.do' modelAttribute='resolveObject'>" +
					"<div class='datatable-popdown'> " +
					"<div class='datatable-popdown-header'>Details</div>" +
					"<div class='datatable-popdown-content clearfix'>"
		+"<div class='datatable-contentleft clearfix'>"+
			"<div ><span class='spanMoveRight'>Request Id</span><span class='valueColor'>"+RequestID+"</span></div>" +
			"<div><span class='spanMoveRight'>Note</span><span class='valueColor'>"+data.Purpose+"</span></div>" 
			
		+"</div>"
		+"<div class='datatable-contentright' clearfix>"+

			"<div><span class='spanMoveRight'>Due Date</span><span class='valueColor'>"+data.DueDate.slice(0,10)+"</span></div>" +
			"<div><span class='spanMoveRight'>Severity</span><span class='valueColor'>"+Severity+"</span></div>" 	
			
		+"<input type='hidden' name='ownerId' value='"+data.EmployeeID+"'/>"
		+"<input type='hidden' name='requestId' value='"+RequestID+"'/>"
		+"<input type='hidden' name='statusId' value='"+status+"'/>"
					
		+"</div>" 
		+"</div>" +
		"<div class='clearfix'>" +
			"<span id='Comments' class='datatable-popdown-header'>Comments</span>" +
			"<a class='posright marginAlign commentDisplay ' href='#modal-two' data-id='"+RequestID+"'  onClick='viewAsset(this)'>" +
					"<span><i class='icon-plus'></i></span><span id='commentClick"+RequestID+"'>Add</span>" +
			"</a>" +
		"</div>"+
		"<div id='noteDisplay"+RequestID+"' class='commentFooter commentColor'>"+data.Comments+"</div>"+
		"<div class='sectionDivision'>" +
		"<div class='commentFooter'><div class='clearfix'>" +
				"<span class='spanMoveRight'>Selected Asset</span>" +
				"<span class='valueColor' id='selectedAssetDisplay"+RequestID+"'></span>" +
				"<span id='selectedAssetOnModal"+RequestID+"' class='selectedAssetOnModal'>" +
				"<a href='#modal-one' class='colorLink' name='selectPopUp' data-id='"+RequestID+"' onClick='viewAsset(this)'>" +
						"<span  class='posright'><span><i class='icon-folder-open'></i>Select</span></span></a></span>"+
			"</div>" +
			"<div>" +
				"<span class='spanMoveRight'>Status</span>" +
				"<span><select id='adminStates"+RequestID+"' class='selectStylenew' name='adminStatus'>" +
					"<option value='resolved'>Resolved</option>" +
					"<option value='onHold'>Hold</option>" +
					"<option value='rejected'>Rejected</option></select>" +
				"</span>"+
			"</div></div>"+
		"</div>"+
		"<div class='poscenter buttonAlign' id='AssetButton"+RequestID+"'><input type='submit' class='btn btn-primary'  value='submit'/>" +
		"</div>"+
				"</div>" +
				"</form>";
	}

	else
	{
		return	"<div class='datatable-popdown'> <div class='datatable-popdown-header'>Details</div><div class='datatable-popdown-content'>"
		+"<div class='datatable-contentleft clearfix'>"
			+"<div class='lefttext clearfix'><div><b>Request Id</b></div><div><b>Purpose</b></div><div><b>Note</b></div></div>"
			+"<div class='righttext clearfix'><div>"+RequestID+"</div><div>"+data.Purpose+"</div><div>"+data.Severity+"</div></div>"
		+"</div>"
		+"<div class='datatable-contentright' clearfix>"
			+"<div class='lefttext clearfix'><div><b>Due Date</b></div><div><b>Due Of Issue</b></div></div>"
			+"<div class='righttext clearfix'> <div>"+data.DueDate.slice(0,10)+"</div><div> "+data.DateOfIssue.slice(0,10)+"</div></div>"
		+"</div>"
		
		+"</div></div>";
	}

}

$('#Requests').on( 'click', 'tbody tr td.details-control', function () 
	{	
		var this_attr = $(this);
 		var tr = this_attr.closest("tr");
		
		var row = table.row(tr);
		
		if (row.child.isShown()) {

			row.child.hide();
			tr.removeClass("shownChild");
			tr.children('td').children('i').removeClass('icon-minus');
			tr.children('td').children('i').addClass('icon-plus');
			hideAlert();
		
		}
		else
		{
			var dataObject= row.data();
			row.child(format(dataObject)).show();		
			tr.addClass("shownChild");
			tr.children('td').children('i').removeClass('icon-plus');
			tr.children('td').children('i').addClass('icon-minus');
		
		}		
	
});



$('#makeAssignAsset').click(function(e) 
    { 	
	hideAlert();
		var idFound; var flag=0;
    	var theSelectionTextBoxAssetId = $("#assignAssetTextBox").val();
    	for ( var i = 0;i <availableArray.length ; i++ ) {
    	    if(theSelectionTextBoxAssetId==availableArray[i])
    	    	{
    	    		idFound=i;
    	    		flag=1;
    	    		var idPassing=IdOriginal[idFound];
    	       		$("#resolveform"+currentRequest).append("<input type='hidden' name='assetIdOriginal' value='"+idPassing+"'/>");
    	       		}
    	  	}
    		if(flag==0)
    			{   			
    	    		popupInfoFixed("Please Select a valid Asset", 'alert-error');
    	      		e.preventDefault();  	    		   	    	
    			}
    	
    	var tempId=theSelectionTextBoxAssetId.substr(theSelectionTextBoxAssetId.indexOf("-") + 1);  
    	var assetDisplay=$("#selectedAssetDisplay"+currentRequest);
    	assetDisplay.text("");
    	assetDisplay.removeClass("errorColor");
    	if(theSelectionTextBoxAssetId!="")
		{
    	if(flag!=0){
    		assetDisplay.html(theSelectionTextBoxAssetId);
    		assetDisplay.append("<input type='hidden' name='assetId' value='"+tempId+"'/>");
    	} 	
    	$("#assignAsset").select2('val',0);
    	$("#assignAssetTextBox").val("").attr('placeholder','Search').prop('disabled', true);
      	itemLoaded = 1;
    	
    		}
    	else{
    		popupInfoFixed("Please Select an Asset", 'alert-error');
    		e.preventDefault();
    	}
    	
   });

$('#cancelAssignAsset').click(function(e) 
	    { 
			hideAlert();
			$("#assignAsset").select2('val',0);
			$("#assignAssetTextBox").val("").attr('placeholder','Search').prop('disabled', true);
			$("#ErrorFieldContentFixed" ).hide();
	    });
$("#cancelComments").click(function(e) 
	    { 
			hideAlert();
			$("#commentText").val("");
			$("#ErrorFieldContentFixed" ).hide();
			window.location.href="#";
			
	    });
$("#commentText").keyup(function() {
		$( "#ErrorFieldContent" ).slideUp(500);
		var commentsDisplay = "";
		var noteDisplay=$("#noteDisplay"+currentRequest);
		commentsDisplay=noteDisplay.text();
		if((commentsDisplay==noComments)||(commentsDisplay==suggestion))
		{
			noteDisplay.text("");
			noteDisplay.removeClass("errorColor");
		}
		var commentLength=$("#noteDisplay"+currentRequest).html().length;
	    valLength =  $("#commentText").val().length;
	    var finalLength=commentLength+valLength;
	    if(finalLength > 493){ 
	    	popupInfoFixed("Comment Length Exceeded", 'alert-error');
	    	$('#addComments').prop('disabled',true);
	    	$('#addComments').addClass("disabled");
	    }
	    else{
	    	$('#addComments').prop('disabled',false);
	    	$('#addComments').removeClass("disabled");
	    	hideAlert();
	    }
	    	
});
$('#addComments').click(function(e) 
	    { 	
			hideAlert();
			var noteDisplay=$("#noteDisplay"+currentRequest);
			var theTextArea = $("#commentText").val();
			noteDisplay.append("<p>"+theTextArea+"</p>");
	      	$("#commentText").val("");      	
	       	commentsDisplay=noteDisplay.html();
	      	$("#resolveform"+currentRequest+" #comments").remove();
	      	$("#resolveform"+currentRequest).append("<input id='comments' type='hidden' name='comments' value='"+commentsDisplay+"'/>");
	      	window.location.href="#home";
 });

$('#reset').on('click',function (){
	hideAlert();
	$('#from').val("").removeClass("needed");
    $('#to').val("").removeClass("needed");
    $( "#to" ).datepicker( "option", "minDate", "");
    $( "#from" ).datepicker( "option", "maxDate", "+0D" );
    table.order( [ 5, 'desc' ] );
    table.ajax.url("requests.do?from=&to=").load();
    
    });
});



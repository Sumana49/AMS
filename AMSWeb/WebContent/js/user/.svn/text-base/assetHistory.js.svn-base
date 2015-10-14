// User Asset History - JS file
$(document).ready(function() {

	// default variables
	var MAX_COUNT = 3;
	var assetTypeList="";
	var assetStatusList="";
	var autocompleteflag=0;
	var assetHistoryTable="";
	var approverData='';
	
	//service URLs
	var getApproverNamesServiceUrl = "getApproverNames.do";
	var sendMailRemainderServiceUrl = "assetMail.do";
	var getAssetTypesServiceUrl = "getAssetTypes.do";
	var changeApproverServiceUrl = "changeApprover.do";
	var getAssetStatusServiceUrl = "getAssetStatus.do";
	var assetHistoryServiceUrl = "getAssetHistory.do";
	
	//DOM Querying
	var changingDialogElement = $( "#dialog-form-changing" );
	var sendingDialogElement = $( "#dialog-form-sending" );
	var loadingDialogElement =   $( "#dialog-form-loading" );
	var sendMailConfirmDialogElement = $( "#dialog-form" );
	var errorMessageBlockElement = $("#errordiv");
	var selectTypeIdElement = $("#searchtypeid");
	var searchBoxIdElement = $("#searchboxid");
	var assetHistoryTableElement = $('#assetHistoryTable');
	var sendMailDialogFormContentElement = $("#dialog-form-content");
	var resetButtonElement = $('#resetbuttonid');
	var searchedLabelElement = $('.searchedLabel');
	var searchedValueElement = $('.searchedValue');
	var dataTableErrorElement = $("tbody tr:first-child td");
	var bodyElement = $('html, body');
	
	//Predefined Messages	
	var defaultErrorMessgae = "Something Went Wrong. Try Again.";
	var reminderSentMessage = "Reminder sent successfully.";
	var approverChangedNotifyText="Approver changed Successfully.";
	var searchDefaultPlaceholder = 'Search';
	var searchByAssetTypePlaceholder = 'Enter the type of asset';
	var searchByStatusPlaceholder = 'Enter the type of status';
	var searchByDatePlaceholder = 'YYYY-MM-DD';
	var dataTableErrorText = "Sorry ! Try Reloading the Page ! ";
	var remindBUHeadText = "Remind BU Head";
	var remindSupportTeamText = "Remind Support Team";
	var defaultComment = "No Comments Available.";
	var selectApproverWarning = "Please select a different approver !";
	
	//CSS classes
	var alertWithError = 'alert alert-error';
	var alertWithSuccess= 'alert alert-success';
	var dataTableErrorDecoration = 'datatableError';
	var plusIconCSSClass = 'icon-plus';
	var minusIconCSSClass = 'icon-minus';
	var valueNeededCSSClass  = 'needed';
	
	//predefined html tags
	var remindBUHeadLink = "<a href='#'>Remind BU Head</a>";
	var remindSupportTeamLink = "<a href='#'>Remind Support Team</a>";
	var plusIcon = '<i class="icon-plus plusIconColor"></i>';
	var closex = " <div class='alert-close'>x</div> ";
	
	//DOM Ids & classes attribute
	var changePersonSelectElement='#changePersonSelect';
	var requestIdElement = ".requestid";
	var requestNoteElement = ".requestnote";
	var requestSeverityElement = ".requestseverity";
	var requestDueDateElement = ".requestduedate";
	var requestStartDateElement = ".requeststartdate";
	var requestReturnDateElement = ".requestreturneddate";
	var requestCommentElement = ".requestcomment";
	var plusIconElement = 'tbody tr td:first-child';
	
	//to change the div into popup
	var changingDialog = changingDialogElement.dialog({
	      autoOpen: false,
	      height: 150,
	      width: 350,
	      modal: true
	});
	
	var sendingDialog = sendingDialogElement.dialog({
	      autoOpen: false,
	      height: 150,
	      width: 350,
	      modal: true
	});
	
	
	var loadingDialog =loadingDialogElement.dialog({
	      autoOpen: false,
	      height: 150,
	      width: 350,
	      modal: true
	});
	
	//to get the list of approver names
	$.getJSON( getApproverNamesServiceUrl, function( data ) {
		approverData=data;
  	  for(var i=0;i<data.approverName.length;i++){
  		  
  		  $('<option>').val(data.approverId[i]).text(data.approverName[i]).appendTo(changePersonSelectElement);
  	  }	  
  	  
	});
	
	searchedLabelElement.hide();
	
	//action for remind mail popup
	dialog = sendMailConfirmDialogElement.dialog({
      autoOpen: false,
      height: 200,
      width: 350,
      modal: true,
      buttons: {
        "Send": function(){

	    	var data = $(this).data('myData');
        	sendingDialog.dialog("open");
        	bodyElement.animate({scrollTop: sendingDialogElement.offset().top});
        	$.ajax({
        	    url: sendMailRemainderServiceUrl,
        	    data:{
        	    	requestId : data.requestId,
        	    	approverId : data.currentApproverId,
        	    	remindString : data.action
        	    },
        	    async: "false",
        	    timeout:10000,
        	    error: function(){
        	    	sendingDialog.dialog("close");
        	        errorMessageBlockElement.html(defaultErrorMessgae + closex);
        	        errorMessageBlockElement.addClass(alertWithError);
        	        errorMessageBlockElement.attr('style','display:block');
        	        bodyElement.animate({scrollTop: errorMessageBlockElement.offset().top});
        			$('.alert-close').on('click',function(){
        				errorMessageBlockElement.slideUp(400);
        			});
        			assetHistoryTable.clear().draw();
        	    },
        	    failure:function(){
        	    	sendingDialog.dialog("close");
        	        errorMessageBlockElement.html(defaultErrorMessgae + closex);
        	        errorMessageBlockElement.addClass(alertWithError);
        	        errorMessageBlockElement.attr('style','display:block');
        	        bodyElement.animate({scrollTop: errorMessageBlockElement.offset().top});
        			$('.alert-close').on('click',function(){
        				errorMessageBlockElement.slideUp(400);
        			});
        			assetHistoryTable.clear().draw();
        	    },
        	    success: function(data){
        	    	sendingDialog.dialog("close");
        	    	if(data.flag=="true"){
        	    	errorMessageBlockElement.html(reminderSentMessage + closex);
        	    	errorMessageBlockElement.addClass(alertWithSuccess);
        	    	errorMessageBlockElement.attr('style','display:block');
        	    	bodyElement.animate({scrollTop: errorMessageBlockElement.offset().top});
         			$('.alert-close').on('click',function(){
         				errorMessageBlockElement.slideUp(400);
         			});
        	    	}else{
        	    		errorMessageBlockElement.html(defaultErrorMessgae + closex);
            	        errorMessageBlockElement.addClass(alertWithError);
            	        errorMessageBlockElement.attr('style','display:block');
            	        bodyElement.animate({scrollTop: errorMessageBlockElement.offset().top});
            			$('.alert-close').on('click',function(){
            				errorMessageBlockElement.slideUp(400);
            			});
        	    	}
        	    	assetHistoryTable.clear().draw();
        	    }
        	});       	
            dialog.dialog( "close" );
        	
        },
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
          dialog.dialog( "close" );
      }
    });
	

	//search by - select box actions
	selectTypeIdElement.on('change',function(){
		errorMessageBlockElement.slideUp(400);
		var searchtypevalue = selectTypeIdElement.val();
		searchBoxIdElement.val("");
		if(searchtypevalue!=""){
				searchBoxIdElement.prop('disabled', false);
				if(searchtypevalue=='1'){
					searchBoxIdElement.datepicker("hide");
					searchBoxIdElement.datepicker("destroy");
					$.getJSON( getAssetTypesServiceUrl, function( data ) {
						assetTypeList = data.type_names;
						searchBoxIdElement.autocomplete({
						      source: assetTypeList
						     
						    });
						autocompleteflag = 1;
					 });
					searchBoxIdElement.attr('placeholder',searchByAssetTypePlaceholder);
					
				}else{
					
				}
				if(searchtypevalue=='2'){
					searchBoxIdElement.datepicker("hide");
					searchBoxIdElement.datepicker("destroy");
					$.getJSON( getAssetStatusServiceUrl, function( data ) {
						assetStatusList = data.assetStatus;
							searchBoxIdElement.autocomplete({
									source: assetStatusList
							});
					  });
					searchBoxIdElement.attr('placeholder',searchByStatusPlaceholder);
					autocompleteflag = 1;
				}else{
					if(autocompleteflag==1){
						searchBoxIdElement.autocomplete();
						autocompleteflag=0;
						}
				}
				if(searchtypevalue =='3'){
					searchBoxIdElement.attr('placeholder',searchByDatePlaceholder);
					searchBoxIdElement.datepicker({
						dateFormat: "yy-mm-dd",
              			maxDate:"+0D"
					});

				}
				else{
				}
		}
		else{
			searchBoxIdElement.attr('placeholder',searchDefaultPlaceholder);
			searchBoxIdElement.prop('disabled', true);
		}
	});
   
	//to change the style select box
	selectTypeIdElement.select2();
			
	//converting 'assetHistoryTable' table into DataTable
	
	loadingDialog.dialog("open");

	assetHistoryTable = assetHistoryTableElement.on('xhr.dt', function ( e, settings, json ) {
        if(json.recordsFiltered==0){
        	
        }else{
        	
        }
    } ).DataTable( {
    	
        "bFilter":false,
        "bInfo":true,
        "responsive":true,
        "autoWidth": true,
  		'iDisplayLength': 10,
  		"bLengthChange": false,
  		"bServerSide": true,
  		"processing":true,
  		"order":[[2,"desc"]],
  		"oLanguage":{
  			"sInfoFiltered":""
  		},
		"ajax":{
			"url" : assetHistoryServiceUrl,
			"async":false,
			"data":{
        		"flowType":"user",
        		"searchType" : "",
        		"searchValue" : ""
        	},
			"timeout": 10000,
			"failure": function(){
				dataTableErrorElement.html(dataTableErrorText);
				dataTableErrorElement.addClass(dataTableErrorDecoration);
				loadingDialog.dialog("close");
			},
			"error": function(){
				dataTableErrorElement.html(dataTableErrorText);
				dataTableErrorElement.addClass(dataTableErrorDecoration);
				loadingDialog.dialog("close");
		    },
		},
        "sAjaxDataProp": "assetRequestHistory",
    	"sPaginationType": "input",
        "error": function () {
        	dataTableErrorElement.html(dataTableErrorText);
        	dataTableErrorElement.addClass(dataTableErrorDecoration);
        	loadingDialog.dialog("close");
        },
        "fnDrawCallback": function( oSettings ) {
        	 loadingDialog.dialog("close");
          },
        "createdRow": function ( row, data, index ) 
        {
        		
        		if(data.severity!="error"){
	        		$('td', row).eq(2).html(convertDate(data.dayOfRequest));
	        	
	        		if(data.status=="Requested"){
	        			if(data.buCounter<MAX_COUNT){
	        				action = remindBUHeadLink;
	        			}else{
	        				action = remindBUHeadText;
	        			}
	        		}else if(data.status=="BU_Approved"){
	        			if(data.stCounter<MAX_COUNT){
	        				action = remindSupportTeamLink;
	        			}else{
	        				action = remindSupportTeamText;
	        			}
	        		}else{
	        			action="None";
	        		}
	        		       		
	        		$('td', row).eq(4).html(action);
        		}
       	},
       	"columnDefs": [ { "targets": 4, "orderable": false },
       				  ],
       	"columns": [
		{
			"orderable":false,
			"class":"details-control",
			"defaultContent":plusIcon
	    },
		{ "mDataProp": "assetType"},
        { "mDataProp": "dayOfRequest"},
        { "mDataProp": "status"},
        { "mDataProp": "action" }
        ]

    } );
    
    // function to create expansion tab
    function format(data)
{
    		
    	var template = $("#template"); //var template = document.getElementById("template");
    	template.find(requestIdElement).html(data.requestId); //template.querySelector(requestIdElement).innerHTML = data.requestId; 
        template.find(requestNoteElement).html(data.note); //template.querySelector(requestNoteElement).innerHTML = data.note;
    	template.find(requestSeverityElement).html(severity(data.severity)); //template.querySelector(requestSeverityElement).innerHTML = severity(data.severity);
    	if(data.dueDate!="Nil"){
    		template.find(requestDueDateElement).html(convertDate(data.dueDate)); //template.querySelector(requestDueDateElement).innerHTML = convertDate(data.dueDate);
    	}else{
    		template.find(requestDueDateElement).html(""); //template.querySelector(requestDueDateElement).innerHTML="";
    	}
    	if(data.dateOfIssue!="Nil"){
    		template.find(requestStartDateElement).html(convertDate(data.dateOfIssue)); //template.querySelector(requestStartDateElement).innerHTML = convertDate(data.dateOfIssue);
    	}else{
    		template.find(requestStartDateElement).html(""); //template.find(requestStartDateElement).html(""); //template.querySelector(requestStartDateElement).innerHTML="";
    	}
    	if(data.dateOfReturn!="Nil"){
    		template.find(requestReturnDateElement).html(convertDate(data.dateOfReturn)); //template.querySelector(requestReturnDateElement).innerHTML = convertDate(data.dateOfReturn);    		
    	}else{
    		template.find(requestReturnDateElement).html(""); //template.querySelector(requestReturnDateElement).innerHTML="";
    	}
    	if(data.comments!="Nil"){
    		template.find(requestCommentElement).html(data.comments); //template.querySelector(requestCommentElement).innerHTML = data.comments;    		
    	}else{
    		template.find(requestCommentElement).html(defaultComment); //template.querySelector(requestCommentElement).innerHTML=defaultComment;
    	}

    	var tempselect="";
    	if(data.status=="Requested"){
    		tempselect = "<div class='aHorizontalLine'><hr/></div><div class='foot'><div class='head'><h3>Change Approver</h3></div><div class='displayInlineBlock changeApproverSelect'><select id='req"+data.requestId+"'></select></div><div class='changeapproverbtn'> <button id='btn"+data.requestId+"' class='btn btn-primary changeapproverbtn'>Change</button></div></div>";         
    	}
    	return	"<div id='row"+data.requestId+"' class='innertablecontainer'>"+template.html()+""+tempselect+"</div>";   //return	"<div id='row"+data.requestId+"' class='innertablecontainer'>"+template.innerHTML+""+tempselect+"</div>";
}
    
    //function to format date into indian date format
    function convertDate(data){
    	var date = new Date(data);

		var curr_date = date.getDate();
	    var curr_month = date.getMonth() + 1;
	    var curr_year = date.getFullYear();
	    
		return ("0" + curr_date).slice(-2)+"-"+("0" + curr_month).slice(-2)+"-"+curr_year;
		
    }
    
    //severity mapping function
    function severity(value){
    	var result="";
    	if(value=="H"){
    		result="High";
    	}else if(value=="M"){
    		result="Medium";
    	}else if(value=="L"){
    		result="Low";
    	}else{
    		result="No Severity Available.";
    	}
    	return result;	
    }


    // on click function for plus icon in table
    assetHistoryTableElement.on( 'click', plusIconElement, function (){	
    	errorMessageBlockElement.slideUp(400);

		var this_attr = $(this);
 		var trRow = this_attr.closest("tr");
		
		var row = assetHistoryTable.row(trRow);
		
		if (row.child.isShown()) {

			this_attr.children('i').removeClass(minusIconCSSClass);
			this_attr.children('i').addClass(plusIconCSSClass);
			
			var this_data = row.data();
			var childRow = '#row'+this_data.requestId;
			$(childRow).slideUp(400);
			row.child.hide();
		
		}else
		{

					this_attr.children('i').removeClass(plusIconCSSClass);
					this_attr.children('i').addClass(minusIconCSSClass);
					var this_data = row.data();
					if(this_data.severity=="error"){
						assetHistoryTableElement.off( 'click', plusIconElement);
					}else{
						row.child(format(this_data)).show();
						var childRow = '#row'+this_data.requestId;
						$(childRow).hide();$(childRow).slideDown(400);
						var tempselectid="#req"+this_data.requestId;
						var tempbtnid="#btn"+this_data.requestId;
						var currentApprover="";
					  	for(var i=0;i<approverData.approverName.length;i++){
					  			if(approverData.approverId[i]==this_data.currentApproverId){
					  					currentApprover=approverData.approverId[i];
					  					$('<option>').val(currentApprover).text(approverData.approverName[i]).attr("selected","selected").appendTo(tempselectid);
					  				}else{
					  					$('<option>').val(approverData.approverId[i]).text(approverData.approverName[i]).attr("selected",null).appendTo(tempselectid);
					  				}
					  	  }
						$(tempselectid).select2();
						
						$(tempbtnid).on("click",function(){
							var changeapproverselectvalue = $(tempselectid).val();
							if(changeapproverselectvalue!=currentApprover){
								changingDialog.dialog("open");
								bodyElement.animate({scrollTop: changingDialogElement.offset().top});
								$.ajax({
				    				  url: changeApproverServiceUrl,
				    				  async:"false",
				    				  data:{
				    					  requestId:this_data.requestId,
				    					  approverId:changeapproverselectvalue
								
				    				  },
				    				  timeout:5000,
				    				  success : function(data) {
				    					  changingDialog.dialog("close");
					    					if(data.flag=="true"){
					    						errorMessageBlockElement.html(approverChangedNotifyText + closex);
					    	        	    	errorMessageBlockElement.addClass(alertWithSuccess);
					    	        	    	errorMessageBlockElement.attr('style','display:block');
					    	        	    	bodyElement.animate({scrollTop: errorMessageBlockElement.offset().top});
					    	         			$('.alert-close').on('click',function(){
					    	         				errorMessageBlockElement.slideUp(400);
					    	         			});
					    					}else{
					    						errorMessageBlockElement.html(defaultErrorMessgae + closex);
					    	        	        errorMessageBlockElement.addClass(alertWithError);
					    	        	        errorMessageBlockElement.attr('style','display:block');
					    	        	        bodyElement.animate({scrollTop: errorMessageBlockElement.offset().top});
					    	        			$('.alert-close').on('click',function(){
					    	        				errorMessageBlockElement.slideUp(400);
					    	        			});
					    					}
					    					assetHistoryTable.clear().draw();
					    				},
					    				failure : function(){
					    					changingDialog.dialog("close");
					    					errorMessageBlockElement.html(defaultErrorMessgae + closex);
					            	        errorMessageBlockElement.addClass(alertWithError);
					            	        errorMessageBlockElement.attr('style','display:block');
					            	        bodyElement.animate({scrollTop: errorMessageBlockElement.offset().top});
					            			$('.alert-close').on('click',function(){
					            				errorMessageBlockElement.slideUp(400);
					            			});
					    				},
					    				error : function(){
					    					changingDialog.dialog("close");
					    					errorMessageBlockElement.html(defaultErrorMessgae + closex);
					            	        errorMessageBlockElement.addClass(alertWithError);
					            	        errorMessageBlockElement.attr('style','display:block');
					            	        bodyElement.animate({scrollTop: errorMessageBlockElement.offset().top});
					            			$('.alert-close').on('click',function(){
					            				errorMessageBlockElement.slideUp(400);
					            			});
					    				}
					    				
				    				});
								
								
							}else{
								alert(selectApproverWarning);
							}
			    			
						});
						
					}

		}
	});
    
    //search functionality
    function searchRequest(){
    	errorMessageBlockElement.slideUp(400);

		var searchtype = $("#searchtypeid").val();
    	var searchkey = $("#searchboxid").val().trim();
    	var flag = "failure";
    	
    	if(searchtype!="" && searchkey!=""){
    		if(searchtype=="1"){
    			for(var i=0;i<assetTypeList.length;i++){
    				if(searchkey==assetTypeList[i]){
    					flag="success";
    				}else{
    					$("#searchboxid").val("").addClass(valueNeededCSSClass);
    				}
    			}
    		}
    	
    		
    		if(searchtype=="2"){
    			for(var i=0;i<assetStatusList.length;i++){
    				if(searchkey==assetStatusList[i]){
    					flag="success";
    				}else{
    					$("#searchboxid").val("").addClass(valueNeededCSSClass);
    				}
    			}
    		}
    		
    		if(searchtype=="3"){
    			var dateregex =  /^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/;
    			if(dateregex.test(searchkey)){
    				flag="success";
    			}else{
    				$("#searchboxid").val("").addClass(valueNeededCSSClass);
    			}
    		}
    	}
            if(flag=="success")
            {
            	flag="failure";
            	loadingDialog.dialog("open");
            	bodyElement.animate({scrollTop: loadingDialogElement.offset().top});
                var u="getAssetHistory.do?searchType="+searchtype+"&searchValue="+searchkey;
                assetHistoryTable.order( [ [2,'desc'] ] );
                if(searchtype=="3"){
                	searchedValueElement.text(convertDate(searchkey));
                }else{
                	searchedValueElement.text(searchkey);
                }
                searchedLabelElement.slideDown(400);
                assetHistoryTable.ajax.url(u).load();
                var errorFlagCheck = searchBoxIdElement.hasClass(valueNeededCSSClass);
                if(errorFlagCheck){
                	searchBoxIdElement.removeClass(valueNeededCSSClass);
                }                
                
            }
	}
   
    
    $("#searchbuttonid").on('click',searchRequest);
    
    
    function enterKeySearchRequest(e){
    	// querying which key is pressed
    	var key=e.which;
    	// key code for enter is 13
    	// if enter key is pressed do the operation
    	if(key==13)
    	{
    		searchRequest();
    	}
    }
     // when enterkey is pressed when the focus 
    // is on the search box (searchboxid)
    searchBoxIdElement.on('keypress',enterKeySearchRequest);

    assetHistoryTableElement.on( 'click', 'a', function (){	
		var this_attr = $(this);
 		var tr = this_attr.closest("tr");
		
		var row = assetHistoryTable.row(tr);
		this_row_data = row.data();
		this_row_data.action = $(this).html();
		if(this_row_data.action===(remindBUHeadText))
			sendMailDialogFormContentElement.html("Do you want to Notify the BU Head?");
		else if(this_row_data.action===(remindSupportTeamText))
			sendMailDialogFormContentElement.html("Do you want to Notify the Support Team?");
		dialog.data('myData',this_row_data).dialog( "open" );
		bodyElement.animate({scrollTop: sendMailConfirmDialogElement.offset().top});  
    });
    
    
    //reset button functionality
	resetButtonElement.on('click',function (){
		errorMessageBlockElement.slideUp(400);

		errorMessageBlockElement.slideUp(400);

	    selectTypeIdElement.select2("val","");
		searchBoxIdElement.val("").attr('placeholder','Search');
		searchBoxIdElement.prop('disabled', true);
		var errorFlagCheck = searchBoxIdElement.hasClass(valueNeededCSSClass);
        if(errorFlagCheck){
        	searchBoxIdElement.removeClass(valueNeededCSSClass);
        }
	    assetHistoryTable.clear();
	    assetHistoryTable.order( [ [2,'desc'] ] );
	    searchedLabelElement.slideUp(400);
	    assetHistoryTable.ajax.url(assetHistoryServiceUrl).load();
	    });
});
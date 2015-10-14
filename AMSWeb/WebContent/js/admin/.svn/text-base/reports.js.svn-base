/**
 * 
 */
/* Report table */
var usersList=[];
var usersNameList=[]; 
var usersIdList=[]; 
var assetsList = [];
var wrap = new Array();
$(document).ready(function()
{	
	$("#exportAssetsHistory").hide();
	var autocompleteflag=0;    
var assetHistorySearch = $("#SearchTerm");
var userHistory = $("#userHistory");
var assetHistory = $("#assetHistoryUser");
var assetSelect = $("#assetSelect");
var historyTableAsset = $("#historyTableAsset");
var historyTableUser = $("#historyTableUser");

	assetSelect.on('change',function()
		    {
				var searchtypevalue = assetSelect.val();
				if(searchtypevalue!="")
				{
					assetHistorySearch.prop('disabled', false);
					if(searchtypevalue=='USER_ID')
					{
						assetHistorySearch.unbind("keyup");
						
						if(autocompleteflag==1){
						assetHistorySearch.autocomplete('destroy');
						autocompleteflag=0;
						}
						$.getJSON( "assetsHistorySearch.do", function( res ) 
						{
							for(var i=0;i<res.data.length;i++){	
								usersNameList.push(res.data[i].EmployeeName);
								usersIdList.push(res.data[i].EmployeeId);
								usersList.push(res.data[i].EmployeeName);
								usersList.push(res.data[i].EmployeeId);
							}				

							assetHistorySearch.autocomplete(
								{
								source: usersList

							    });
						});
						autocompleteflag=1;    
						assetHistorySearch.attr('placeholder','Enter Emp NAME/ID');
						
					}
					if(searchtypevalue=='ASSET_ID')
						{
						assetHistorySearch.attr('placeholder','Enter Asset ID');	
						if(autocompleteflag==1){
							assetHistorySearch.autocomplete('destroy');
							autocompleteflag=0;
							}
						assetHistorySearch.attr('placeholder','Enter Asset ID');
						assetHistorySearch.keyup(function() {
							if(assetHistorySearch.val().length==3){
								
								var searchText=assetHistorySearch.val();
								
								if(/^[a-zA-Z0-9- ]*$/.test(searchText) == true) {
									$.ajax({
										type: "GET",
										url: "getAssetIdentities.do?searchString="+searchText,
										async: false,
									}).done(function(res) {
										for(var i=0;i<res.data.length;i++){	
											assetsList.push(res.data[i].assetIdentity);
											}
										assetHistorySearch.autocomplete({
										      source: assetsList
										    });
								    });
									autocompleteflag=1;
								}
							}
						});	
						
			
						}
				}
				else
					{
						assetHistorySearch.attr('placeholder','Search');
						assetHistorySearch.prop('disabled', true);
					}
		     
		    });

		var searchString="";
			var userTable="false";
			var assetTable="false";	
			function getAssetHistory() 
				{
				
				$("#historyData").addClass("current");
				var searchType=$("#assetSelect option:selected").val();
				searchString=$("#SearchTerm").val();
				searchString=assetHistorySearch.val();
				var flag = "failure";
		 		if(searchString=="")
	 			{
		 		$("#historyData").removeClass("current");
	 			assetHistorySearch.val("").addClass("needed");
	 			}
				if(searchType=='USER_ID')
				{
					for(var i=0;i<usersList.length;i++){
						if(searchString == usersList[i]){
							flag="success";
						}
						else
						{
							$("#historyData").removeClass("current");
							assetHistorySearch.val("").addClass("needed");
						}
					}
				userHistory.removeClass('hideTable');
				assetHistory.addClass('hideTable');	
					if(flag == "success")
			 		{
						for(var i=0;i<usersNameList.length;i++){
			            	if(searchString==usersNameList[i])
			            		{
			            		searchString=usersIdList[i];
			            		}
			            	}
						if(userTable=="false")
							
							{
							$("#historyData").addClass("current");	
						assetHistorySearch.val("").removeClass("needed");
						$("#exportAssetsHistory").show();
							table2 = historyTableUser.dataTable({  
								"async":false,
						        "bFilter":false,
						        "bInfo":true,
						        responsive:true,
								"processing": true,
							    "serverSide": true,
							    "searching": false,
						  		"responsive":true,
						  		"pageLength":10,
						  		"order": [[ 2, "desc" ]],
						  		"lengthChange": false,
						  		"sPaginationType": "input",
						  		"oLanguage":{
						  			"sInfoFiltered":""
						  		},
								"ajax":
							    {
							    	"url":"getAssetsHistoryReport.do?searchType="+searchType+"&searchString="+searchString
							    },"timeout": 10000,
								"failure": function(){
									$("tbody tr td").html("Sorry ! Try Reloading the Page ! ");
									$("tbody tr td").css('color','red');
								},
								"error": function(){
									$("tbody tr td").html("Sorry ! Try Reloading the Page ! ");
									$("tbody tr td").css('color','red');
							    },
							     "createdRow": function (row, data, index) {			          
							            $('td', row).eq(2).html(convertDate(data.IssuedDate));
							            $('td', row).eq(3).html(convertDate(data.ReturnDate));
							        },
							    "columns": [{
							        "data": "AssetIdentity"

							    }, 
							    {
							        "data": "AssetType"

							    },
							    {
							        "data": "IssuedDate"

							    },
							    {
							        "data": "ReturnDate"

							    }]


							});
							userTable="true";
							}
						else
							{
							$("#historyData").addClass("current");
							assetHistorySearch.val("").removeClass("needed");
							historyTableUser.DataTable().ajax.url('getAssetsHistoryReport.do?searchType='+searchType+'&searchString='+searchString).load();
							}
						flag="failure";
			 		}
					else
						{
						userHistory.addClass('hideTable');
						}
				

	}
				if(searchType=='ASSET_ID')
					{
		 			if(assetsList.length==0)
	 				{
		 				$("#historyData").removeClass("current");	
	 				assetHistorySearch.val("").addClass("needed");
	 				}
					for(var j=0;j<assetsList.length;j++)
	 	 			{
	 					if(searchString == assetsList[j])
	 					{
	 						flag="success";
	 			
	 					}
	 					else{
	 						$("#historyData").removeClass("current");
	 						assetHistorySearch.val("").addClass("needed");
	 					}
	 				}
					userHistory.addClass('hideTable');
					assetHistory.removeClass('hideTable');
						if(flag == "success")
				 		{
							if(assetTable=="false")
								{
								$("#historyData").addClass("current");
								assetHistorySearch.val("").removeClass("needed");
								$("#exportAssetsHistory").show();
								table3 = historyTableAsset.dataTable({  
									"async":false,
							        "bFilter":false,
							        "bInfo":true,
							        responsive:true,
									"processing": true,
								    "serverSide": true,
								    "searching": false,
							  		"responsive":true,
							  		"pageLength":10,
							  		"order": [[ 2, "desc" ]],
							  		"lengthChange": false,
							  		"oLanguage":{
							  			"sInfoFiltered":""
							  		},
							  		"sPaginationType": "input",
									"ajax":
								    {
								    	"url":"getAssetsHistoryReport.do?searchType="+searchType+"&searchString="+searchString
								    },
								    "timeout": 10000,
									"failure": function(){
										$("tbody tr td").html("Sorry ! Try Reloading the Page ! ");
										$("tbody tr td").css('color','red');
									},
									"error": function(){
										$("tbody tr td").html("Sorry ! Try Reloading the Page ! ");
										$("tbody tr td").css('color','red');
								    },
								     "createdRow": function (row, data, index) {
								          
								            $('td', row).eq(2).html(convertDate(data.IssuedDate));
								            $('td', row).eq(3).html(convertDate(data.ReturnDate));
								        },

								    "columns": [{
								        "data": "UserID"
								    }, 
								    {
								        "data": "Designation"
								    },
								    {
								        "data": "IssuedDate"
								    },
								    {
								        "data": "ReturnDate"
								    }]


								});
								assetTable = true;
								}
							else
								{
								$("#historyData").addClass("current");
								assetHistorySearch.val("").removeClass("needed");
								historyTableAsset.DataTable().ajax.url('getAssetsHistoryReport.do?searchType='+searchType+'&searchString='+searchString).load();
								}
								flag="failure";
				 		}
						else
							{
							assetHistory.addClass('hideTable');
							}
	}

	}
		
		$('#getAssetHistoryBtn').on( 'click',getAssetHistory);
		
		function enterKeyAssetHistorySearch(e)
		{
			var key=e.which;
			if(key==13)
			{
				getAssetHistory();
			}
		}
		
		assetHistorySearch.on( 'keypress',enterKeyAssetHistorySearch);
		
		$('#exportAssetsHistory').click(function()
				{
			
			var selectedSearchType = assetSelect.val();
			for(var i=0;i<usersNameList.length;i++){
            	if(searchString==usersNameList[i])
            		{
            		searchString=usersIdList[i];
            		}
            	}
			location.href = "downloadAssetHistoryReport.do?selectedSearchType=" + selectedSearchType + "&selectedSearchString="+searchString;
				});
		function convertDate(data){
			if(data=="")
				{
				return data;
				}
			if(data == null)
    		{
    		return data;
    		}
	    	var date = new Date(data);

			var curr_date = date.getDate();
		    var curr_month = date.getMonth() + 1;
		    var curr_year = date.getFullYear();
		    
			return ("0" + curr_date).slice(-2)+"-"+("0" + curr_month).slice(-2)+"-"+curr_year;
			
	    }

		$("#previewId").click(function(e){			
			hideAlert();
			var selectedReportType="";
			selectedReportType = $("#reportType").select2("val");
		    if(selectedReportType==0 ){
		    	popupInfo("Select Report Type", 'alert-error');
		    	$("#reportpurchase").hide();
		    	return;			    	
		    }
		    else{
		    	$.ajax({
			        url: "getReportDetails.do",
			       type:"GET",
			       data: {reportType : $( "#reportType option:selected" ).text()}
			    
			    
			    }).done(function (res) {
			    	if(res.error==null)
					{
			    		//showing the table again
				    	$("#reportpurchase").show();
			    		//s download button
			    		$("#generateReportDownload").show();
			    						if(res.data[0]==null)
			    						{	
			    							var tableContents = "<table id='gentab' class='innerTable cell-border hover row-border'></table>";			    							
			    								$("#generateReportDiv").html(tableContents);
			    								$("#gentab").dataTable({
			    					    			"processing": true,
			    					    			"searching": false,
			    					    	  		"responsive":true,
			    					    	  		"columns":res.columnName});
			    								
			    						}
			    						else
			    						{	
			    							var tableContents = "<table id='gentab' class='innerTable cell-border hover row-border'></table>";			    							
		    								 $("#generateReportDiv").html(tableContents);
		    								
		    								 if(selectedReportType=="1")
		    									 createTable("gentab",res,1,'desc');
		    								 else
		    									 createTable("gentab",res,0,'asc');
			    			}
							
					}
			    	else
			    	{
			    		$("#reportpurchase").hide();
			    		popupInfo(res.error, 'alert-error');
			    	}
			        
			    }).fail(function (res) {
			    	$("#reportpurchase").hide();
			    	popupInfo(res.error, 'alert-error');
			       
			    });
				
			    function createTable(tableID,res,orderByColumn,ascORdesc)
			    {
			    	var innerTableID="#"+tableID;
			    	var dash_table=$(innerTableID).dataTable({
			    			"processing": true,
			    			"oSearch": {"sSearch": "Initial search"},
			    		    "searching": false,
			    	  		"responsive":true,
			    	  		"lengthChange": false,
			    	  		"data":res.data,
			    	  		"columns":res.columnName,
			    	  		"scrollX":true,
					        "sScrollX": "100%",
			    	  		"sPaginationType": "input",
			    	  		"order":[[orderByColumn,ascORdesc]]
			    	}).addClass('display');
			    	wrap =[];
			    }
		    }
		});
	

	
	
	
		$("#generateReportDownload").hide();
	$.getJSON( "getReportTypeJson.do", function(data) {
		
		var list = data.data;
		
		for(var i=0;i<list.reportId.length;i++){
	
		$('<option>').val(list.reportId[i]).text(list.reportName[i]).appendTo('#reportType');
		
		}
		
	});
	
	
var table = $("#purchaseReport").dataTable({  
		bFilter:false,
		responsive:true,
		'iDisplayLength': 5,
		"order": [[ 4, "asc" ]],
		"scrollX":true,
        "sScrollX": "96%",
		"bLengthChange": false,
		
});
		
		$("#reportType").on('change', function () {
			//hide table
			$("#reportpurchase").hide();
			//hide download button
			$("#generateReportDownload").hide();
			});
		
					
			////Custom Report////
			var typeJsonData;
			
			// hiding Download button
			$("#customReportDownload").hide();

			// Populating Asset Type Dropdown
			$("#customSelectAttributesList").select2("enable", false);
			
			$.getJSON("getTypeWithAttributesJson.do", function (data) {
			
			    var assetTypeList = data.assetType;
			    typeJsonData = data.assetType;
			    
			    //adding 'All Assets option in AssetType dropdown
			    //'All Assets' type value is 0
			    // Added in JSP
			   // $("#customSelectAssetType").append($('<option>', {value:"0", text: "-- All Assets --"}));
			    for (var i = 0; i < assetTypeList.length; i++) {
			    	//populating all asstibutes other than 'All Assets'
			    	if(assetTypeList[i].assetTypeId!="0"){			    	
			        $("#customSelectAssetType").append($('<option>', {value:assetTypeList[i].assetTypeId, text: assetTypeList[i].assetTypeName}));
			        $("#financeAssetType").append($('<option>', {value:assetTypeList[i].assetTypeId, text: assetTypeList[i].assetTypeName}));
			    	}
			    }
			});
			$("#checkboxSelectAll").click(function(){
			    if($("#checkboxSelectAll").is(':checked') ){
			        $("#customSelectAttributesList > option").prop("selected","selected");
			        $("#customSelectAttributesList").trigger("change");
			    }else{
			        $("#customSelectAttributesList > option").removeAttr("selected");
			         $("#customSelectAttributesList").trigger("change");
			     }
			});
			//Populating Attributes dropdown based AssetType Selected
			$("#customSelectAssetType").on('change', function () {
				//hide download button
				$("#customReportDownload").hide();
				//hide table
				$("#reportCustom").hide();
		    	$("#customReportDiv").hide();
		    	 
				$("#customSelectAttributesList").select2('val', '');
			    var selectedAssetType = $("#customSelectAssetType").select2("val");

			    $('#checkboxSelectAll').prop('checked', false);
			    $("#customSelectAttributesList").select2("enable", true);
			    $("#checkboxSelectAll").removeAttr("disabled");
			    //Looping through assetTypeJson
			    $.each(typeJsonData, function (index, assetTypeLoopObject) {			    	
			        //Checking if selected asset type matches the loop object
			        if (selectedAssetType == assetTypeLoopObject.assetTypeId) {
			            //getting attributes for selected asset types and populating the dropdown
			        	$('#customSelectAttributesList').empty();
			        	for (var i = 0; i < assetTypeLoopObject.attributeNameArray.length; i++) {
			            	$('<option>').val(assetTypeLoopObject.attributeIdArray[i]).text(assetTypeLoopObject.attributeLabelNameArray[i]).appendTo('#customSelectAttributesList');
			              
			            }
			        }
			    });
			});
			
			//Saving Query
			$("#saveQuery").click(function(){
				//removing previously set validation error
				$("#saveQueryErrorDisplay").html("");
				var selectedAssetType = $("#customSelectAssetType").select2("val");
				var selectedAttributesArray = $("#customSelectAttributesList").select2('val');
				
				var queryName = $('#newQuery').val();
				if(queryName==""){
					$("#saveQueryErrorDisplay").html("Report name cannot be empty!");
					return false;
				}
				else{
					//empty name input box on successful validation
					$("#newQuery").val("");
					$("#saveQueryErrorDisplay").html("");
				  $.ajax({
			          url: "saveCustomReportQuery.do",
			          type: 'POST',
			          data: {"assetTypeId":selectedAssetType,"attributesArray[]":selectedAttributesArray,"queryName":queryName}
			      }).done(function (res) {
			    	  if(res=="error"){
			    		  popupInfo("Problem in Saving Report !", 'alert-error');
				      }else{
				    	  popupInfo(res, 'alert-info');
				    	 //repopulating report dropdown
				    	  $.ajax( "getReportTypeJson.do" )
				    	  .done(function(data) {
				    		  var list = data.data;
				    		  $('#reportType').empty();
				    			for(var i=0;i<list.reportId.length;i++){				    		
				    			$('<option>').val(list.reportId[i]).text(list.reportName[i]).appendTo('#reportType');
				    			}
				    	  });
				    	  
				    	 
				      }
			    	  
			      }).fail(function (res) {
			          popupInfo("An Unexpected Error Occured!", 'alert-error');
			      });
			  }
			  });
			
			////Custom Report End////
			
			function enterKeyFinanceReportView(e)
			{
				var key=e.which;
				if(key==13)
				{
					financeReportPreview();
				}
			}
			
			// finance report generation by pressing enter
			$('#percentage').on('keypress',enterKeyFinanceReportView);
			
			//finance report download button hide
			$('#financeReportDownload').hide();
			
			//End of document ready
			});
			////Custom Report Function Calls////

			function customReportPreview() {
				hideAlert();
			    var selectedAssetType = $("#customSelectAssetType").select2("val");
			    var selectedAttributesArray = $("#customSelectAttributesList").select2('val');
			   
			    //removing selected choices in attribute list box
			    //validation
			    if(selectedAssetType==0 ){
			    	popupInfo("Select Asset Type", 'alert-error');
			    	$("#reportCustom").hide();
			    	 $("#customReportDiv").hide();
			    	return;			    	
			    }else if(selectedAttributesArray.length==0){
			    	popupInfo("Select Attributes", 'alert-error');
			    	$("#reportCustom").hide();
			    	 $("#customReportDiv").hide();
			    	return;
			    }
			    $.ajax({
			        async: false,
			        url: "customReport.do",
			        type: "GET",
			        data: {
			            "assetTypeId": selectedAssetType,
			            "attributesArray[]": selectedAttributesArray
			        }
			    }).done(function (res) {
			    	//checking if report data is null from response
			    	//error is set only when response is null
			    	if(res.error==null){			    		
					    $("#reportCustom").show();
					    //download button
			    		$("#customReportDownload").show();
					    $("#customReportDiv").show();
					    $('#checkboxSelectAll').prop('checked', false);
			        var tableContents = "<table id='customReportTable' class='innerTable cell-border hover row-border'></table>";
			        $("#customReportDiv").html(tableContents);
			        //convert table into datatable
			       $('#customReportTable').dataTable({
				        "responsive": true,
				        "bFilter": true,
				        "sDom": '<"top">rt<"bottom"ilp><"clear">',
				        "lengthChange": false,
				        "scrollX":true,
				        "sScrollX": "100%",
				        "columns": res.columnName,
				        "data": res.data,
				        "sPaginationType": "input"
				    });	
			    	}else{
			    		//if error is not null
			    		$("#reportCustom").hide();
			    		$("#customReportDiv").hide();
			    		popupInfo(res.error, 'alert-error');
			    	}
			        
			       // createCustomTable("customReportTable",colNames, res.data);
			    }).fail(function (res) {
			        popupInfo(res.error, 'alert-error');
			    });
			}
			
		////Custom Report Function calls End////
		

			// Finance Report
			function validateFinanceInput() {
				var flag = 0;
				$("#financeAssetType").next().find('p').html('');
				$("#depreciationType").next().find('p').html('');
				$("#percentage").next().find('p').html('');
				if ($("#financeAssetType").val() == '') {
					$("#financeAssetType").next().css("display", "inline-block");
					$("#financeAssetType").next().find('p').html(
							'This field cannot be empty');
					flag = 1;
				}
				if ($("#depreciationType").val() == '') {
					$("#depreciationType").next().css("display", "inline-block");
					$("#depreciationType").next().find('p').html(
							'This field cannot be empty');
					flag = 1;
				}
				if ($("#percentage").val() == '') {
					$("#percentage").next().css("display", "inline-block");
					$("#percentage").next().find('p').html('This field cannot be empty');
					flag = 1;
				}
				;
				return flag;
			}
			function financeReportPreview() {
				hideAlert();
				var flag = 0;
				flag = validateFinanceInput();
				if (flag == 1)
					return false;
				var financeAssetTypeId = $("#financeAssetType").select2("val");
				var depreciationType = $("#depreciationType").val();
				var percentage = $("#percentage").val();
			
				$('financeReportDetails').html("");
				$
						.ajax({
							async : false,
							url : "getFinanceReportDetails.do",
							type : "GET",
							data : {
								"financeAssetTypeId" : financeAssetTypeId,
								"depreciationType" : depreciationType,
								"percentage" : percentage
							}
						})
						.done(
								function(res) {
									$('#financeReportDownload').show();
									x = res;
									$('#financeReportDetails')
											.html(
													"<table id='financeReportTable' class='innerTable cell-border hover row-border' cellspacing='0' width='100%'></table>");
									// convert table into datatable
									$('#financeReportTable').dataTable({
										"responsive" : true,
										"bFilter" : true,
										"sDom" : '<"top">rt<"bottom"ilp><"clear">',
										"lengthChange" : false,
										"sPaginationType" : "input",
										"data" : res.data,
										"columns" : res.columnName
									}).addClass('display');
			
								}).fail(function(data) {
							popupInfo("Failed", 'alert-error');
						});
			
			}
			// ///Export Report////

			function exportReport() {
				hideAlert();
			    var selectedReportType = $("#select2-chosen-1").text();
			    if(selectedReportType != "-- Report Type --")
			    	location.href = "downloadReport.do?reportType=" + selectedReportType;
			    else
			    	popupInfo("Select Report Type", 'alert-error');
			}
			function exportFinanceReport() {
			
				var flag = 0;
				flag = validateFinanceInput();
				if (flag == 1)
					return false;
				var financeAssetTypeId = $("#financeAssetType").select2("val");
				var depreciationType = $("#depreciationType").val();
				var percentage = $("#percentage").val();
				location.href = "exportFinanceReport.do?financeAssetTypeId="
						+ financeAssetTypeId + "&depreciationType=" + depreciationType
						+ "&percentage=" + percentage;
			
			}			
			function exportCustomReport() {
				 var selectedAssetType = $("#customSelectAssetType").select2("val");
				 var selectedAttributesArray = $("#customSelectAttributesList").select2('val');
				    if(selectedAttributesArray.length==0){
				    	selectedAttributesArray.push('0');
				    	 $("#customSelectAttributesList > option").prop("selected","selected");
				        $("#customSelectAttributesList").trigger("change");
				    	selectedAttributesArray = $("#customSelectAttributesList").select2('val');
				    }
			     location.href = "exportCustomReport.do?assetTypeId=" + selectedAssetType+"&attributesArray[]="+selectedAttributesArray;
			}
			
			////Export Report End////
			
			////Util Functions////
			function handleErrorJson(jsonData, errorString) {
				if(jsonData.error != null){
					 popupInfo(errorString, 'alert-error');
				}
			}
			////Util Functions End////

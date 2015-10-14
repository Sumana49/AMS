/**

Avnet Asset Tracking Application JavaScript
asset.jsp

*/
$(document).ready(function(){
var assetTypeList="";
var assetTypes = [];
var assetIds = [];
var availableArray = [];
var typeArray=new Array();
   
$(function() {
	$("#procuredDate").attr('readonly', true);
    $( "#procuredDate" ).datepicker({
        dateFormat: "dd-mm-yy",
	        maxDate: 0,
	   	    changeMonth: true,
		   	changeYear: true,
		   	onClose: function() {
	   	        	$("#procuredDate").valid();
	   	        }
    });
});

function initLoad(){
    
    $.ajax({
            dataType: 'json',
            url: "getTypeJson.do",
            type: 'GET',
            success: function (data) {
                var asset = data.asset;
                for(var i=0;i<asset.length;i++){
                    typeArray.push(asset[i].name);
                 
                }
                   
                
                }
         });
}
initLoad();

$(function () {
		$('#assetType').val("");
$.getJSON( "getTypeJson.do", function(data) {
var list = data.asset;
for(var i=0;i<list.length;i++){
$('<option>').val(list[i].assetTypeId).text(list[i].name).appendTo('#assetType');
}
 
$("#assetType").select2();

       $("#assetType").on('change',function(){
	    	   var list="";
    	   
               var selectedAssetType="";
	    	   var dateFormat = [];
	    	   var validateFields = [];
	    	   var validateLabels=[];
			selectedAssetType = $("#assetType").select2("val");
		    if(selectedAssetType==0 )
		    {
		    	$("#addAssetDiv").hide();
		    	return;			    	
		    }
		    else
		    	{
		    	$("#addAssetDiv").show();
			    	 
			    	  $.ajax({
	                        async: false,
	                        url: "getTypeJson.do",
	                        dataType: 'json',
	                        type: 'GET',
	                        
	                        success: function (data) {
	                        	list=data.asset;
	                        },
	                        error: function(res){
	                        	 popupInfo(res.status, 'alert-error');
	                        }
	                    });
			    	
		    	var text = $("#assetType").val();
			         for (var i = 0; i < list.length; i++) 
			         {
			             if (text == list[i].assetTypeId) 
			             {
			 
			                 var contents = "";
			 
			                 for (var j = 0; j < list[i].fields.length; j++)
			                 {
			                     contents += '<div>';
			                     contents += '<label>' + list[i].labels[j] + '</label>';
				                     if(list[i].types[j]=="date"){
				                    	 dateFormat.push(list[i].fields[j]);
				                     }
				                 validateFields.push(list[i].fields[j]);
				                 validateLabels.push(list[i].labels[j]);
			                     contents += '<span class="tooltips" href="#">' + 
			                     '<input type="text" name="' + 'attributeTypeList['+j+'].value' + '"id="' + list[i].fields[j]+ '"/>'+
			                     '<input type="hidden" name="' + 'attributeTypeList['+j+'].fieldName' + '"value="' + list[i].fields[j]+ '">'+
			                     	'<input type="hidden" name="' + 'attributeTypeList['+j+'].id' + '"value="' + list[i].ids[j]+ '">';
			                     contents += '</div>';
			                 }
 
			                 $("#addAssetDiv").html(contents);
			             }
			         }
		    	}
			    $.each(dateFormat, function(fieldIndex,fieldObject){
			    
	        		var dateobj = '#'+fieldObject;
	        		$(dateobj).attr('readonly', true);
	        		if(fieldObject=="warrantyExpiryDate")
	        		{
	        			var dateObject = $(dateobj);
	       		       	
	       		   	 	
	       		   	 	dateObject.datepicker({
	       		   	        dateFormat: "dd-mm-yy",
	       		   	        changeMonth: true,
	       		   	        changeYear: true,
	       		   	        onClose: function() {
	       		   	        	$(dateobj).valid();
	       		   	        }
						});
	       		       	$(dateobj).next().addClass("errorClassForDate");
 
	        		}
	        		else if(fieldObject=="invoiceDate")
	    			{
	    			var dateObject = $(dateobj);
	

	   		   	 	dateObject.datepicker({
	   		   	        dateFormat: "dd-mm-yy",
	   		   	        maxDate: 0,
       		   	        changeMonth: true,
       		   	        changeYear: true,
       		   	        onClose: function() {
       		   	        	$(dateobj).valid();
       		   	        }
	   		   	 		});
	   		       	$(dateobj).next().addClass("errorClassForDate");
	    			}

	        		else if(fieldObject=="insuranceExpiryDate")
	        			{
	        			var dateObject = $(dateobj);


	       		   	 	dateObject.datepicker({
	       		   	        dateFormat: "dd-mm-yy",
	       		   	        changeMonth: true,
	       		   	        changeYear: true,
	       		   	        onClose: function() {
     		   	        	$(dateobj).valid();
     		   	        }
	       		   	 	});
	       		       	$(dateobj).next().addClass("errorClassForDate");
	        			}
	        		else
	        		{
	        			var dateObject = $(dateobj);
    	

	       		   	 	dateObject.datepicker({
	       		   	        dateFormat: "dd-mm-yy",
	       		   	        changeMonth: true,
	       		   	        changeYear: true,
	       		   	        onClose: function() {
	       		   	        	$(dateobj).valid();
	       		   	        }
	       		   	});
	       		       	$(dateobj).next().addClass("errorClassForDate");
    
           
	        		}
			    });
			    for(var i=0;i<validateFields.length;i++)
			    	{
			    	var validateFieldObj = '#'+validateFields[i];
			    	
			    	if(validateFields[i]=="shortText")
			    		{
			    		$(validateFieldObj).rules("add", {
				    	    required: true,
				    	    minlength: 3,
				    	    messages: {
				    	    	required: validateLabels[i]+" cannot be empty",
				    	    	minlength: "Enter minimum 3 characters"
				    	    }
				    	});
			    		}
			    	else
			    		{
			    			$(validateFieldObj).rules("add", {
				    	    	required: true,
				    	    	messages: {
				    	    		required: validateLabels[i]+" cannot be empty"
				    	    	}
				    		});
			    		}
			    	
			    	}
		
   	    });
          
       });



$('#addAssetForm').on('submit',function(e){e.preventDefault();}).validate({
	debug: true,
	rules:{
		procuredDate:{required:true}
		
	},
	messages:{
		procuredDate:{required:"Procured Date cannot be empty"}
		
	},
	submitHandler: function(form,event){
							submitAddAssetForm(form);
							return false;
						}
});
function submitAddAssetForm(form){
	
	$.ajax({

        url: "addAsset.do",
        context: document.body,
        type: 'post',
        dataType: 'json',
        data: $('#addAssetForm').serialize()

    }).done(function (res) 
    			{
                popupInfo(res.status, 'alert-info');
                $('#assetType').empty();
                $('<option>').val("0").text("Select Asset Type").appendTo('#assetType');
		        $("#addAssetDiv").hide();
		        $.ajax( "getTypeJson.do" )
		        .done(function(data) {
		        	var list = data.asset;
		        	
		        	for(var i=0;i<list.length;i++){
		        	$('<option>').val(list[i].assetTypeId).text(list[i].name).appendTo('#assetType');
		        	}
		        	$("#assetType").select2();
		        });
		        resetForm();
         
    		}).fail(function (data) {
	        popupInfo("Asset adding failed", 'alert-error');
	        
	        resetForm();
	    });
       return false;
	}
	
});

	
	$('#addAssetForm').bind("keyup keypress",function(e){
		var code = e.keyCode || e.which;
		if(code == 13){
			e.preventDefault();
			return false;
		}
	});

	$('#assetForm').bind("keyup keypress",function(e){
		var code = e.keyCode || e.which;
		if(code == 13){
			e.preventDefault();
			return false;
		}
	});
	
    function checkAlreadyExist(){
      	 var searchVal=  $( "#typeNameChange" ).val();
           if(inArray(searchVal,typeArray))
            {
                   $("#typeNameChangeSpan").css('display','inline-block');
                   $("#typeNameChangeMessage").html("Type name already exists");
                   return "0";
             }
             else{

                   $("#typeNameChangeSpan").css('display','none');
                  return "1";
             }
      }

    $(document).on('keyup','#typeNameChange',function(){
         	checkAlreadyExist();
                });
    $('.manage-icon').tooltip();
	var htmlContent = '';
    //create Text Input for type name
    //var TypeNameTextInput = '<div>' + '<label>Type Name</label>' + '<span class="tooltips" href="#">' + '<input type = "text" name="typeName"/>' + '<span><p></p></span>' + '</span>' + '</div>';
	 var TypeNameTextInput = '<div>' + '<label>Type Name</label>' + '<span class="tooltips" href="#">' + '<input type = "text" name="typeName" id="typeNameChange"/>' + '<span id="typeNameChangeSpan"><p id="typeNameChangeMessage"></p></span>' + '</span>' + '</div>';
	  
	var TypeNameSelectInput = '<div>' + '<label>Type Name</label>' + '<select name="dropDown" id="edit" style="width:257px;" ></select>'  + '</div>';
    //create Text Input for life time
    var LifeTimeTextInput = '<div>' + '<label>Life Time</label>' + '<span class="tooltips" href="#">' + '<input type = "text" placeholder="Life time in months" id="lifeTime" name="lifeTime"/>' + '<span>' + '<p>' + '</p>' + '</span>' + '</span>' + '</div>';
    var PrefixTextInput = '<div>' + '<label>Asset Prefix</label>' + '<span class="tooltips" href="#">' + '<input type = "text" name="prefix"/>'+'<i title="Enter any prefix for an asset type. For example, SCAN for Scanner" class="info-left icon-info manage-icon"></i>' + '<span><p></p></span>' + '</span>' + '</div>';
    var selected="add";
    first = '';

    //creating and appending the created checkbox contents to view
    $('#assetdiv').html(createManageAssetFormForAdd());

    $("input[name=method]:radio").change(function () {

    selected = $('input[name=method]:checked', '#assetForm').val();
        if (selected == "add") {

            //creating and appending the created checkbox contents to view
            //$( '#assetdiv' ).empty();
            $('#assetdiv').html(createManageAssetFormForAdd());
        }
        if (selected == "edit") {
        	
            //creating and appending the created checkbox contents to view
            //$( '#assetdiv' ).empty();
            $('#assetdiv').html(createManageAssetFormForEdit());

            // to check the checkboxes based on user's selection in edit
            $.ajax({
                async: false,
                dataType: 'json',
                url: "getTypeJson.do",
                type: 'GET',
                success: function (data) {
                    var assetTypeList = data.asset;
                    //populating the dropdown list
                   /* $.each(assetTypeList, function (index, assetType) {
                         dropdownSelectContent += '<option value="' + assetType.name + '">' + assetType.name + '</option>';
                    });
                    $('#edit').html(dropdownSelectContent);*/
                    
                    for(var i=0;i<assetTypeList.length;i++){
                    	$('<option>').val(assetTypeList[i].assetTypeId).text(assetTypeList[i].name).appendTo('#edit');
                    	
                    }
                    	 
                    	$("#edit").select2();	
                	//checkbox checking for default first select option
                    	var assetTypeAttributes = assetTypeList[0].fields;
                    //getting all checkboxes
                            var allDropdown = $("input[type= 'checkbox']");
                            $.each(assetTypeAttributes, function (assetTypeAttributeIndex, assetTypeAttributeObject) {
                                $.each(allDropdown, function (dropdownIndex, dropdownObject) {
                                    if (assetTypeAttributeObject == dropdownObject.value) {
                                        if (dropdownObject.checked == false) {
                                            dropdownObject.checked = true;
                                        } 
                                    }
                                });
                            });
                        
                    
                    $.ajax({
                        async: false,
                        url: "getLifeTime.do",
                        dataType: 'json',
                        type: 'GET',
                        data:"assetTypeId="+assetTypeList[0].assetTypeId,
                        success: function (data) {
                        	$('#lifeTime').val(data.lifeTime);
                        	if(data.inHouseFlag == 'n' || data.inHouseFlag == 'N')
                        		$('#carryno').prop("checked",true);
                        },
                        error: function(res){
                        	 popupInfo(res.status, 'alert-error');
                        }
                    });
                    
                   },//end of success
             
            	   error: function(data){
            		   popupInfo("Something went wrong. Please refresh", 'alert-error');
            	   }
            });


        }

        $('#edit').change(function () {

            $.ajax({
                async: false,
                dataType: 'json',
                url: "getTypeJson.do",
                type: 'GET',
                success: function (data) {


                    var assetTypeList = data.asset;
                    selectedDropDownValue = $('#edit').val();
                    $.ajax({
                        async: false,
                        url: "getLifeTime.do",
                        type: 'GET',
                        dataType:'json',
                        data:"assetTypeId="+selectedDropDownValue,
                        success: function (data) {
                        	$('#lifeTime').val(data.lifeTime);
                        	if(data.inHouseFlag == 'n' || data.inHouseFlag == 'N')
                        		$('#carryno').prop("checked",true);
                        
                        },
                        error: function(res){
                       	 popupInfo(res.status, 'alert-error');
                       }
                    });
                    ////resetting all checkboxes to unchecked state
                    var allDropDown = $("input[type= 'checkbox']");
                    $.each(allDropDown, function (aid, aobj) {
                        aobj.checked = false;
                    });
                    
                    //looping through all asset types
                    $.each(assetTypeList, function (id, assetType) {
                    	//checking if selected dropdown value matches with asset type in loop
                        if (assetType.assetTypeId == selectedDropDownValue) {
                            var assetTypeAttributes = assetType.fields;

                            //setting the matching attribute checkboxes to checked state
                            $.each(assetTypeAttributes, function (assetTypeAttributeIndex, assetTypeAttributeObject) {
                                $.each(allDropDown, function (dropdownIndex, dropdownObject) {
                                    if (assetTypeAttributeObject == dropdownObject.value) {
                                        if (dropdownObject.checked == false) {
                                        	dropdownObject.checked = true;
                                        } else {

                                        	dropdownObject.checked = false;
                                        }

                                    }
                                });
                            });

                        }
                    });

                },
            error: function(data){
     		   popupInfo("Something went wrong. Please refresh", 'alert-error');
     	   }
            });




        });

    });

    ////////////////////////////////

    //function to create form when radio add is clicked

    function createManageAssetFormForAdd() {
        htmlContent = '';
        htmlContent += TypeNameTextInput + PrefixTextInput + LifeTimeTextInput;
        //create static elements for attributes division
        htmlContent += '<div class="clearfix">' + '<label class="groupleft">Attributes</label>';

        var createdCheckboxDivision = createCheckboxDivision();
        htmlContent += createdCheckboxDivision + '</div>' + '</div>' + '</div>';
        return htmlContent;

    }

    //function to create form when radio edit is clicked

    function createManageAssetFormForEdit() {
        htmlContent = '';
        htmlContent += TypeNameSelectInput + LifeTimeTextInput;
        //create static elements for attributes division
        htmlContent += '<div class="clearfix">' + '<label class="groupleft">Attributes</label>';

        var createdCheckboxDivision = createCheckboxDivision();
        htmlContent += createdCheckboxDivision + '</div>' + '</div>' + '</div>';
        return htmlContent;

    }

    //function to create single checkbox

    function createCheckbox(labelName, attributeName) {
        var createdCheckboxContent = '<input type="checkbox" name="' + attributeName + '" value="' + attributeName + '" id="' + attributeName + '"/>' + '<label for="' + attributeName + '">' + labelName + '</label><br>';
        return createdCheckboxContent;
    }
    //function to create checked checkbox

    function createCheckedCheckbox(labelName, attributeName) {
        var createdCheckboxContent = '<input type="checkbox" name="' + attributeName + '" value="' + attributeName + '" id="' + attributeName + ' " checked disabled/>' + '<label for="' + attributeName + '">' + labelName + '</label><br>';
        return createdCheckboxContent;
    }

    //function to create whole checkbox holder division

    function createCheckboxDivision() {
        createCheckBoxGroupDiv = '<div class="grouping clearfix">';
        closeCheckBoxGroupDiv = '</div>';
        var leftDivOpen = '<div class="left">';
        var rightDivOpen = '<div class="right">';
        var leftDivClose = '</div>';
        var rightDivClose = '</div>';
        var createdCheckboxDivision = '';

        $.ajax({
            async: false,
            dataType: 'json',
            url: "getAttributesJson.do",
            type: 'GET',
            success: function (data) {
            
                var checkBoxContentLeft = '';
                var checkBoxContentRight = '';
                var attributeLabelList = data.attribute_labels; //JSON key is "attribute_labels"
                var attributeNameList = data.attribute_names; //JSON key is "attribute_names"
                //looping through JSON array
                //can use either attributeLabelList or attributeNameList to loop
                $.each(attributeLabelList, function (index, obj) {
                    if (index <= attributeLabelList.length / 2) {
                    	if(attributeLabelList[index] == "Short Text" || attributeLabelList[index] == "Product Name" || attributeLabelList[index]=="Invoice Number" || attributeLabelList[index] == "Invoice Date" || attributeLabelList[index]=="Cost"|| attributeLabelList[index] == "Supplier Name" || attributeLabelList[index] == "Specification" || attributeLabelList[index] == "Asset Location" || attributeLabelList[index] == "Serial Number" || attributeLabelList[index] == "Warranty Expiry Date" || attributeLabelList[index] == "Warranty Details" || attributeLabelList[index] == "Insurance" || attributeLabelList[index] =="Insurance Expiry Date" )
                    		checkBoxContentLeft +=createCheckedCheckbox(attributeLabelList[index], attributeNameList[index]);
                    	else
                    		checkBoxContentLeft += createCheckbox(attributeLabelList[index], attributeNameList[index]);
                    } else {
                    	if(attributeLabelList[index] == "Short Text" || attributeLabelList[index] == "Product Name" || attributeLabelList[index]=="Invoice Number" || attributeLabelList[index] == "Invoice Date" || attributeLabelList[index]=="Cost"|| attributeLabelList[index] == "Supplier Name" || attributeLabelList[index] == "Specification" || attributeLabelList[index] == "Asset Location" || attributeLabelList[index] == "Serial Number" || attributeLabelList[index] == "Warranty Expiry Date" || attributeLabelList[index] == "Warranty Details" || attributeLabelList[index] == "Insurance" || attributeLabelList[index] =="Insurance Expiry Date" )
                    		checkBoxContentRight +=createCheckedCheckbox(attributeLabelList[index], attributeNameList[index]);
                    	else
                    		checkBoxContentRight += createCheckbox(attributeLabelList[index], attributeNameList[index]);
                    }

                });
                // creating checkbox groun 'grouping' and apending left checkboxes to left div and right checkboxes to right div
                createdCheckboxDivision = createCheckBoxGroupDiv + leftDivOpen + checkBoxContentLeft + leftDivClose + rightDivOpen + checkBoxContentRight + rightDivClose + closeCheckBoxGroupDiv;
            }
        });
        return createdCheckboxDivision;
    }

    $.validator.addMethod("alphabet", function(value, element) {
        return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
    }, "Only alphabets are allowed in this field");

    $('#assetForm').on('submit',function(e){e.preventDefault();}).validate({
    	debug: true,
    	rules:{
    		typeName:{required:true,alphabet:true},
    		prefix:{required:true,alphabet:true},
    		lifeTime:{required:true,number:true}
    	},
    	messages:{
    		typeName:{required:"Type Name can't be empty",alphabet:"This field can't be alphanumeric"},
    		prefix:{required:"Prefix can't be empty",alphabet:"This field can't be alphanumeric"},
    		lifeTime:{required:"Lifetime field can't be empty",number:"This field should be numeric"}
    	},
    	submitHandler: function(form,event){
    		submitForm(form);
    		return false;
    	}
    });

    
    //form submit
    function submitForm(form){
    	 if(checkAlreadyExist()=="0"){
    			return false;
        	}
    	else if(selected == "add"){
    		$.ajax({
          	
              url: "manageAsset.do",
              context: document.body,
              type: 'post',
              dataType: 'json',
              data: $('#assetForm').serialize()
              
          }).done(function (res) {
        	  $('#assetForm')[0].reset();
        	  // The flag of the json object
        	  // determines whether the asset type is 
        	  // added or not added
        	  if(res.flag==="success")
              {
            	  // if the flag is success the th asset type is added
        		  // and the message is displayed as a info
        		  popupInfo(res.status, 'alert-info');
              }
              else if(res.flag==="failure")
              {
            	  // if the flag is failure the asset type is not added
        		  // and the message is displayed as an error
            	  popupInfo(res.status, 'alert-error');
              }
              $('#assetType').empty();

              $('<option>').val("0").text("Select Asset Type").appendTo('#assetType');
              $("#addAssetDiv").hide();
              $.ajax( "getTypeJson.do" )
              .done(function(data) {
              	var list = data.asset;
              	
              	for(var i=0;i<list.length;i++){
              	$('<option>').val(list[i].assetTypeId).text(list[i].name).appendTo('#assetType');
              	}
              	$("#assetType").select2();
              	
	    	  });
              resetForm();
              
             

          }).fail(function (res) {
              popupInfo(res.status, 'alert-error');
          });
          return false;
          }
    	
    	else if(selected == "edit"){
    		$.ajax({
              	
                url:"manageAsset.do",
                context: document.body,
                type: 'post',
                dataType: 'json',
                data: $('#assetForm').serialize()
                
            }).done(function (res) {
                popupInfo(res.status, 'alert-info');
                $('#assetType').empty();
                $('<option>').val("0").text("Select Asset Type").appendTo('#assetType');
                $("#addAssetDiv").hide();
                $.ajax( "getTypeJson.do" )
                .done(function(data) {
                	var list = data.asset;
                	
                	for(var i=0;i<list.length;i++){
                	$('<option>').val(list[i].assetTypeId).text(list[i].name).appendTo('#assetType');
                	}
                	$("#assetType").select2();
                	
	    	  });
                
                
                resetForm();

            }).fail(function (res) {
                popupInfo(res.status, 'alert-error');
            });
            return false;
    	}
    	return false;
    }
    //end of form submit
   
	var assetSearch=$("#searchtext");
	var autocompleteflag=0;    
    $("#assertId").on('change',function()
    {

		var searchtypevalue = $("#assertId").val();
		
		if(searchtypevalue!="")
		{
			assetSearch.prop('disabled', false);
			if(searchtypevalue=='TYPE')
			{
				assetSearch.unbind("keyup");
				if(autocompleteflag==1){
					assetSearch.autocomplete('destroy');
					autocompleteflag=0;
					}
				$.getJSON( "getAssetsTypesList.do", function( res ) 
				{
					for(var i=0;i<res.data.length;i++){	
						assetTypes.push(res.data[i].AssetType);
						assetIds.push(res.data[i].AssetID);
						}					
					assetSearch.autocomplete(
						{
						source: assetTypes
					    });
					autocompleteflag = 1;

				});
				assetSearch.attr('placeholder','Enter the type of asset');
				
			}
			if(searchtypevalue=='ASSET_ID')
				{
				if(autocompleteflag==1){
					assetSearch.autocomplete('destroy');
					autocompleteflag=0;
					}
				assetSearch.attr('placeholder','Enter the asset ID');
				assetSearch.keyup(function() {
					if(assetSearch.val().length==3){		
						var searchText=assetSearch.val();
						$.ajax({
								type: "GET",
								url: "getAssetIdentities.do?searchString="+searchText,
								async: false,
							}).done(function(res) {
								for(var i=0;i<res.data.length;i++){	
									availableArray.push(res.data[i].assetIdentity);
									}
								assetSearch.autocomplete({
								      source: availableArray
								    });
						    });
					autocompleteflag=1;
					}
				});
		}
		}
		else
			{
				assetSearch.attr('placeholder','Search');
				assetSearch.prop('disabled', true);
		}});

    
    var assetsTable = $("#Assets").on('xhr.dt', function ( e, settings, json ) {      
        if(json.recordsFiltered!=0){
        	hideAlert();
        }  
        
    	}).DataTable({
    	"async":false,
        "bFilter":false,

        responsive:true,
		"processing": true,
	    "serverSide": true,
	    "searching": false,
  		"responsive":true,
  		"pageLength":10,
  		"order": [[ 3, "desc" ]],
  		"lengthChange": false,
  	
  	
  		"sPaginationType": "input",
  		"ajax":
        {
        	"url":"getAssets.do?searchType=&searchString="
        },
        "timeout": 10000,
		"failure": function(){
			$("tbody tr td").html("Sorry ! Try Reloading the Page ! ");
			$("tbody tr td").html('color','red');
		},
		"error": function(){
			$("tbody tr td").html("Sorry ! Try Reloading the Page ! ");
			$("tbody tr td").css('color','red');
	    },
        "createdRow": function (row, data, index) {
            $('td', row).eq(0).html('<a href="editAsset.do?assetId=' + data.AssetID + '#assets">' + data.AssetIdentity + '</a>');
            $('td', row).eq(3).html(convertDate(data.PurchasedDate));
        },
        
        "columns": [{
            "data": "AssetIdentity"
        }, 
        {
            "data": "AssetType"
        },
        {
            "data": "Owner"
        },
        {
            "data": "PurchasedDate"
        }]

    });
    function convertDate(data){
    	if(data == null)
    		{
    		return data;
    		}
    	if(data=="")
    		{
    		return data;
    		}
       	var date = new Date(data);

		var curr_date = date.getDate();
	    var curr_month = date.getMonth() + 1;
	    var curr_year = date.getFullYear();
	    
		return ("0" + curr_date).slice(-2)+"-"+("0" + curr_month).slice(-2)+"-"+curr_year;
		
    }
    function searchAsset()
	{
		var searchType = $("#assertId option:selected").val();
		var searchString = assetSearch.val(); 
		var flag = "failure";
 		if(searchString=="")
 			{
 			assetSearch.val("").addClass("needed");
        

 			}
 	
 		if(searchType == "TYPE" && searchString!=""){
			for(var i=0;i<assetTypes.length;i++){
				if(searchString == assetTypes[i]){
					flag="success";
				}else{
					assetSearch.val("").addClass("needed");
				}
			}
		}
 		if(searchType == "ASSET_ID" && searchString!=""){
 			
 			if(availableArray.length==0)
 				{
 				assetSearch.val("").addClass("needed");
 				}
 			else
 				{
 				for(var j=0;j<availableArray.length;j++)
 	 			{
 					if(searchString == availableArray[j])
 					{
 						flag="success";
 			
 					}
 					else{
 			
 						assetSearch.val("").addClass("needed");
 					}
 				}
 				}
 			
		}
 		if(flag == "success")
 		{
 			assetSearch.val("").removeClass("needed");
 			flag = "failure";
 			for(var i=0;i<assetTypes.length;i++){
            	if(searchString==assetTypes[i])
            		{
            		searchString=assetIds[i];
            		}
            	}
            var u="getAssets.do?searchType="+searchType+"&searchString="+searchString;
            assetsTable.ajax.url(u).load();
 		}
	}

    
    $('#searchbtn').on('click',searchAsset);
    
    function enterKeyAssetSearch(e)
	{
    	// querying which key is pressed
    	var key=e.which;
    	// if enter key is pressed
    	if(key==13)
    	{
    		searchAsset();
    	}
	}
    
    // when enter key is pressed on the searchtext box
	// the results should be displayed
	// when any key is pressed on the searchtext this 
	// event occurs
    assetSearch.on('keypress',enterKeyAssetSearch);
    
       /** 
    $(document).keypress(function(event,ui){
      	 
    	var keycode = (event.keyCode ? event.keyCode : event.which);
    	if(keycode == '13'){
           		keyenter();
    	}
     
    });
    */
    function inArray(needle,haystack)
{
    var count=haystack.length;
    for(var i=0;i<count;i++)
    {
        if(haystack[i]===needle){return true;}
    }
    return false;
}


    $('#resetbtn').on('click',function (){
        assetsTable.clear().draw();
        assetsTable.ajax.url("getAssets.do?searchType=''&searchString=''").load();
        $("#assertId").select2("val","");
        assetSearch.prop('disabled', true);
        assetSearch.val("").removeClass("needed");
        assetSearch.val("").attr("placeholder","Search");
    });
    
    $('#resetAddAssetForm').on('click',function(){
    	hideAlert();
        $("#addAssetDiv").hide();
 		$('#assetType').select2("val","0");
    });
    
    function resetForm(){
    	$("#procuredDate").val("");
    	$("#addAssetDiv").hide();
 		$('#assetType').select2("val","0");
    }
});

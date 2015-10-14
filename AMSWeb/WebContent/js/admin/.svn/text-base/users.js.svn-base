/**
 * 
 */

var currentEmpID = "99999"; 
var loadedEmpDetails = "";
function handle(e){
    if(e.keyCode === 13){
        alert("Enter was pressed was presses");
    }

    return false;
}

function convertDate(data){
    	if(data=="-" || data =="NA"){

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


function fixedEncodeURI (str) {
    return encodeURI(str).replace(/%5B/g, '[').replace(/%5D/g, ']');
}
var tempId;
function removeCheckBox(empId){
	 var searchIDs = $("#"+empId+" input:checkbox:checked").map(function(){
      return $(this).val();
    }).get();
	 if(searchIDs.length>0){
		 $.ajax({
			  "url": "userAssetRemove.do",
			  "data":{
				  "assetId":fixedEncodeURI(searchIDs),
				  "empId":empId
			  },
			  async:false,
			 success:function(response){
				  
				  popupInfo(response, 'alert-success');
				  var temp_url="getAssetsEmployee.do?searchType=EMPLOYEE_ID&searchString="+empId;
				  $("#"+empId).DataTable().ajax.url(temp_url).load();
						  
			}
			});
	 }
}
var g;
$(document).ready(function()
{
	$("#searchtext").on('click',function(){
		$("#searchtext").removeClass("needed");
	});
	$('.contenttable').hide();
	
	$("#employeeSearch").select2({
		width:140
	}); 
	function format(data)
	{
		return "<div class='datatable-popdown'> "+
		"<div class='datatable-popdown-header'>User Details</div>"+
		"<div class='datatable-popdown-content clearfix'>"+
			"<div class='datatable-contentleft clearfix'>"+
			"	<div class='lefttext clearfix'>"+
			"		<div>Name</div><div>Employee No</div>"+
			"	</div>"+
			"<div class='righttext clearfix'>"+
			"	<div id='EmpName'>"+data.Name+"</div><div id='EmpNumber'>"+data.EmployeeID+"</div>"+
			"	</div>"+
			"</div>"+
			"<div class='datatable-contentright clearfix'>"+
			"<div class='righttext clearfix'>"+
			"	<div style='margin-top: -20px; float:right;'><div class='btn btn-primary "+data.EmployeeID+" ' onclick='removeCheckBox("+data.EmployeeID+");'>Remove</div></div>"+
			"	</div>"+
			"</div>" +
				"<table id="+data.EmployeeID+" class='innerTable' style='width:80%;'>" +
						"<thead>"+
						            "<tr>"+
										"<th></th>"+
										"<th>Asset Identity</th>"+
										"<th>Asset Type</th>"+
										"<th>Issued Date</th>"+
						            "</tr>"+
						        "</thead>"+

						"</table>"+
					"</div></div>";
		
	}
	
	
	
	table=$("#emptable")
	.DataTable({
		
		"processing": true,
	    "searching": false,
  		"responsive":true,
  		"lengthChange": false,
  		"rowCallback": function( row, data ) {
  		    $(row).addClass("parentRow");
  		  },
  		
  		"sPaginationType": "input",
  		"order":[[1,"asc"]],
		"columns": [
			{
				"data": null,
				"orderable":false,
				"class":"details-control",
				"defaultContent":'<i class="icon-plus"></i>',
				"width":"5%"
		    },
	        {	
	         	"data": "EmployeeID",
	        	"width":"5%"
	         },
	          { 
	        	"data": "Name",
	        	"width":"30%"
	        },
	        { 
	        	"data": "Designation" ,
	        	"width":"20%"
	        },
	       
	        { 
	        	"data": "BusinessUnit",
	        	"width":"20%"
	         }
	        ]
      
	});
	
	function searchUser(){
		var text = $("#searchtext").val();
		if(text.trim().length>0){
			$("#searchtext").removeClass("needed");
		$.ajax({
			  "url": "getEmployee.do",
			  "data":{
				  "search":$("#searchtext").val()
			  },
			  dataType:"json",
			  success:function(response){
					  
				  	 $('.contenttable').show();
				  	 errorDisplay(table,response);
					  table.clear();
					  table.rows.add(response.data);
					  table.order([1,"asc"]);
					  table.draw();
					  //addClickEventToParentRows();
					  
				  
			}
			});
		}else{
			$("#searchtext").addClass("needed");
		}
	}
	
	function errorDisplay(table,response){
		if (typeof response.errorMessage != 'undefined'){
			table.settings()[0].oLanguage.sEmptyTable=response.errorMessage;
		}
		
	}
	
		$("#emptable").on('click','tbody tr td.details-control',function () 
				{	
					
			var this_attr = $(this);
	 		var tr = this_attr.closest("tr");
			
			var row = table.row(tr);
					if (row.child.isShown()) {
						row.child.hide();
						tr.removeClass("shownChild");
						tr.children('td').children('i').removeClass('icon-minus');
						tr.children('td').children('i').addClass('icon-plus');
					
					}
					else
					{
						tr.addClass('opened');
						var innerTableID="#"+row.data().EmployeeID;
						row.child(format(row.data())).show();
						createInnerTable(row.data());
						tr.addClass("shownChild");
						tr.children('td').children('i').removeClass('icon-plus');
						tr.children('td').children('i').addClass('icon-minus');
					}		
			});
	
	$("#search").on('click',searchUser);	

	function enterKeyUserSearch(e){
		// querying which key is pressed
		var key=e.which;
		// when enter key is pressed,display the results
		if(key==13)
		{
			$("#search").focus();
			searchUser();
		}
	}
	// when enter key is pressed on the searchtext box
	// the results should be displayed
	// when any key is pressed on the searchtext this 
	// event occues
	$("input[type=text]").on('keypress',enterKeyUserSearch);	
	
	function loadDetails(EmpId){
		var resp = "";
			
		return resp;
	}
	function createInnerTable(data)
	{

		var innerTableID="#"+data.EmployeeID;
		currentEmpID = data.EmployeeID;
	
		var dash_table=$(innerTableID)
			
			.on('xhr.dt', function ( e, settings, json ) {
				if (typeof json.errorMessage != 'undefined'){
					
					settings.oLanguage.sZeroRecords=json.errorMessage;
					$("."+data.EmployeeID).hide();
					
				}
			    else{
			       $("."+data.EmployeeID).show();
				}
			    })
		
			.DataTable({
				"processing": true,
			    "searching": false,
			    "serverSide": true,
		  		"responsive":true,
		  		"lengthChange": false,
		  		"width":90,
		  		"order": [[ 1, "asc" ]],
		  		"ajax": {
		  			"url":"getAssetsEmployee.do?searchType=EMPLOYEE_ID&searchString="+data.EmployeeID
		  		},
		  		"sPaginationType": "input",
		  		"createdRow": function ( row, data, index ) 
		        {
		          $('td', row).eq(0).html("<input type='checkbox' name='remove' value="+data.AssetID+" >");
		          $('td', row).eq(3).html(convertDate(data.DateOfIssue));
		       	},
				"columns": [
					{
						"data": null,
						"orderable":false,
						"class":"details-control",
						"width":"5%"
				    },
			        {	
			         	"data": "AssetIdentity"
			        	
			         },
			          { 
			        	"data": "AssetType" 
			        },
			        { 
			        	"data": "DateOfIssue"
			        }
			        ]
		});
		
	}
	
	
	
});
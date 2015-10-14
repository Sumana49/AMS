<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>

<%@include file="header_secure.jspf" %>

<div class="error-container">
		<div class="alert alert-error" style="margin:0 auto; font-size:1.3em; display:none; margin-top:10px;" id="ErrorFieldContent">
		
		</div>
</div>


<div class="breadcrumbs">
		<span class="breadcrumb-icon icon-cabinet"></span> Assets</a>
</div>



<div class="contentcontainer clearfix">
			<div class="navigation">
				<ul class="menu" id="menu">
					<li class="current-link-selected"><a href="#viewAssets"><span class="icon-cabinet"></span>View Assets</a></li>
					<li><a href="#addAssets"><span class="icon-plus"></span>Add Asset</a></li>
					<li><a href="#typeAssets"><span class="icon-cog"></span>Asset Type</a></li>
				</ul>
			</div>
			
			<!-- Assets -->
	<div class="contentsection current" id="viewAssets">
				<div class="contentheader">
					<div class="contentheading">
						<h4><span class="icon-cabinet"></span>View Assets</h4>
					</div>
					<div class="contentbody clearfix">
						<select id="assertId">
						    <option value="" selected>Asset id</option>
						    <option value="" selected>Asset Type</option>
						</select>
					
						<input type="text" class="searchUser" name="searchtext" id="searchtext" placeholder="Search Term"/>
						<div class="btn btn-primary button-height"><span class="icon-search padding-none"></span></div>
					</div>
				</div>
				<div class="resize contenttable">
					<div class="table-resize">
						<table id="Assets" class="display" cellspacing="0" width="100%">						        <thead>
						            <tr>
									
						      		    <th>AssetId</th>
						                <th>AssetType</th>
						                <th>Owner</th>
						                <th>PurchasedDate</th>
						            </tr>
						        </thead>
						 
						        <tfoot>
						            <tr>
						         	    <th>AssetId</th>
						                <th>AssetType</th>
						                <th>Owner</th>
						                <th>PurchasedDate</th>						            
						            </tr>

						        </tfoot>
						</table>
					</div>
			</div>
			<div class="activity">
			<div class="activityheader">
				<span class="icon-stats"></span>Recent Activity
			</div>
			<div class="message">
				<div class="messageheading">
					<span class="icon-pushpin"></span>Test Message
				</div>
				<div class="message-body">Message Description</div>
			</div>
			</div>
		</div>

			
</div>
 
<script>

$(document).ready(function()
{
var table1 = $("#Assets").DataTable({
  		"bFilter":false,
  		"bServerSide": false,  		
  		"bLengthChange": false,
		"sAjaxSource": "viewAssets.do",
        "sAjaxDataProp": "assetData",
       	"createdRow": function ( row, data, index ) 
        {
          $('td', row).eq(0).html('<a href="jsp/edit.jsp?assetid='+data.assetId+'?assettype='+data.assetType+'">'+data.assetId+'</a>');
       	},
		"columns": [
		{ "mDataProp": "assetId"},
        { "mDataProp": "assetType"},
        { "mDataProp": "owner"},
        { "mDataProp": "purchasedDate"} 
        ]

});
$('.btn').on('click',function()
{
	console.log("veera");
	console.log(searchtext.value);
	var a = searchtext.value;
	table1.ajax.url("newassetAjax.do?id="+a).load();

});



});



</script>
</body>
</html>
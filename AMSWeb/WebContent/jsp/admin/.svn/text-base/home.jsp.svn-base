<!DOCTYPE html>
<%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<%@include file="header_secure.jspf" %>

<!-- Local Script Include -->
<script src="${pageContext.servletContext.contextPath}/js/admin/request.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/admin/notification.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/input.js"></script>



<div class="breadcrumbs">
		<span class="breadcrumb-icon icon-home2"></span> Home</a>
		 <span class="error-container">
                  <div class="alert alert-error" id="ErrorFieldContent">
                  
                  </div>
            </span>
		<a href="help.do"><span class="info-right icon-info"></span></a>
</div>

<div class="contentcontainer clearfix">

			<div class="navigation">
				<ul class="menu" id="menu">
					<li class="current-link-selected"><a href="#homeRequest"><span class="icon-ticket"></span>Requests</a></li>
				</ul>
			</div>
			
			<!-- Requests -->
			
			<div class="contentsection current" id="homeRequest">
				<div class="contentheader">
					<div class="contentheading">
						<h4><span class="icon-ticket"></span>Requests</h4>
					</div>
					<div class="contentbody clearfix">
						<input type="text" name="from" id="from" placeholder="From Date" readonly/> To
						<input type="text" name="to" id="to" placeholder="To Date" readonly/>
						<button class="btn btn-primary button-height" style="height:33px;" id="filter"><span class="icon-filter padding-none" ></span></button>
						<button class="btn btn-primary button-height" style="height:33px;" id="reset">Reset</button>
					</div>
				</div>
				<div class="resize contenttable">
					
					<div class="table-resize">
						<table id="Requests" class="cell-border hover row-border" cellspacing="0" width="100%">
						        <thead>
						            <tr>
						                <th></th>
						                <th>Emp Id</th>
						                <th>Name</th>
						                <th>Type</th>
						                <th>Status</th>
						                <th>Date Of Approval</th>
						            </tr>
						        </thead>
						 
						       
						</table>
					</div>
			</div>

			<!-- Due Date Notification -->

			<div class="activity">
				<div class="activityheader">
					<span class="icon-stats"></span>Due Date Notification
				</div>
				<div class="content-wrapper"></div>
			</div>
		</div>
			
</div>

<!-- Modal: Choose Asset -->
<div class="modal" id="modal-one" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-header">
      <h2>Choose an Asset</h2>
     
    </div>
    <div class="modal-body">
      <span style='padding-bottom:10px;'><span style="margin-right:10px;">Asset: </span>
       <select id="assignAsset">
       	<option value="0" >Choose the SearchType</option>
		<option value="1">Asset ShortText</option>
		<option value="2">Asset Identity</option>
       </select>
  	  </span>
	  <span style='padding-bottom:10px;'><input type="text" id="assignAssetTextBox" placeholder="Search" disabled='true' style="width:199px; margin-left:52px;"></span> 
       
</div>
    <div class="modal-footer">
    	<a href="#home" class="btn" id="makeAssignAsset">Select</a> <a href="#home" class="btn" id="cancelAssignAsset">Cancel</a>
    </div>
  </div>
</div>

<!-- Modal: Remarks -->
<div class="modal" id="modal-two" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-header">
      <h2>Add Comments</h2>
       <input type="button" class="btn-close" id="cancelComments" value="x"/>
    </div>
    <div class="modal-body">
     <textarea rows="4" cols="67" placeholder="Enter your Comments" id="commentText" ></textarea>
    </div>
    <div class="modal-footer">
    	<input type="button" class="btn" id="addComments" value="Post"/>
    </div>
  </div>
</div>

<!-- Error modal -->
<div class="error-container1">
	<div class="alert alert-error" id="ErrorFieldContentFixed"></div>
</div>

<%@include file="../footer.jspf" %>
 </body>
</html>
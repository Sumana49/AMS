<%@taglib uri="http://www.springframework.org/tags/form" prefix="formtag"%>
<html>
<%@include file="header_secure.jspf"  %>
   
  
<body>

	<div id="errordiv"></div>  
	
	<div class="breadcrumbs">
			<span class="breadcrumb-icon icon-home2"></span> Home</a>
			<a href="help.do"><span class="info-right icon-info"></span></a>
	</div>
	
	<div class="contentcontainer clearfix">
			<div class="navigation">
				<ul class="menu" id="menu">
					<li class="current-link-selected"><span class="icon-ticket"></span>Requests</li>
				</ul>
			</div>
			

			<!-- Requests -->
			
			<div class="contentsection current">
				<div class="contentheader">
					<div class="contentheading">
						<h4><span class="icon-ticket"></span>Requests</h4>
					</div>
					<div class="contentbody clearfix">
						<select id="searchtypeid">
					        	<option value="" >Select a search type</option>
					        	<option value="1">Asset Type</option>
					        	<option value="2">Status</option>
					        	<option value="3"> Date</option>    
			        	</select> 
						<input id="searchboxid"  type="text" placeholder="Search" disabled='true'/>
						<button id="searchbuttonid" class="btn btn-primary button-height" ><span class="icon-search padding-none" ></span></button>
					    <button id="resetbuttonid" class="btn btn-primary button-height" >Reset</button>
					</div>
				</div>
				<div class="searchedLabel">Results for : <span class="searchedValue"></span></div>
				<div class="resize historycontenttable">
					<div class="historytable">
						<table id="assetHistoryTable" class="alignLeft nowrap cell-border hover row-border" cellspacing="0" width="100%">
						        <thead>
						            <tr>
						               <th><i></i></th>
						                <th>Asset Type</th>
						                <th>Date of Request</th>
						                <th>Status</th>
						                <th>Action</th>
						            </tr>
       						 </thead>
						</table>
					</div>
				</div>	
			</div>		
	</div>
 
<div id="template" class="hide">
	<div>
	<div class="tabcontainer">
			<div class="head">
				<h3>Details</h3>
			</div>
			<div class="middle-content">
				<div class="displayInlineBlock">
	                <div class="leftfloat">
	                	<div class="info">
	                		 <div class="labelid"><label>Request Id</label></div>
	                		 <div class="value requestid"></div>
	                	</div>
	                	<div class="info">
	                	     <div class="labelid"><label>Note</label></div>
	                		 <div class="value requestnote">  </div>
	                	</div>
						<div class="info">
	                		<div class="labelid"><label>Severity</label></div>
	                		<div class="value requestseverity"></div>
						</div>
					
						
					</div>
					<div class="rightfloat">
						<div class="info">
	                		 <div class="labelid"><label>Due Date</label></div>
	                		 <div class="value requestduedate"></div>
	                	</div>
	                	<div class="info">
	                	     <div class="labelid"><label>Start Date</label></div>
	                		 <div class="value requeststartdate">  </div>
	                	</div>
						<div class="info">
	                		<div class="labelid"><label>Returned Date</label></div>
	                		<div class="value requestreturneddate"></div>
						</div>
						
					</div>
					</div>
			</div>
			<div class="aHorizontalLine"><hr/></div>
			<div class="head">
				<h3>Comments</h3>
			</div>
			<div class="middle-content">
				<div class="info">
	                		<div class="labelid commentlabel"><label>Admin Note</label></div>
	                		<div class="value requestcomment"></div>
						</div>
			</div>
		</div>
	    </div>
	
</div>

<div id="dialog-form" title="Confirmation">
  <p id="dialog-form-content" class="validateTips"></p>
</div>

<div id="dialog-form-sending" title="Please wait">
  <p class="validateTips">Sending... </p>
</div>

<div id="dialog-form-changing" title="Please wait">
  <p class="validateTips">Changing... </p>
</div>

<div id="dialog-form-loading" title="Please wait">
  <p class="validateTips">Loading... </p>
</div>
 
<%@include file="../footer.jspf" %> 
</body>
</html>
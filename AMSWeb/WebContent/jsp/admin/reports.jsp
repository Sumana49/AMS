<!DOCTYPE html>
<%@page
   language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<html>
   <%@include file="header_secure.jspf" %>
   <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/admin/jquery.multiselect.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/admin/jquery.multiselect.filter.css">
   <!-- Local Script Include -->
   <script src="${pageContext.servletContext.contextPath}/js/admin/reports.js"></script>
   <script src="${pageContext.servletContext.contextPath}/js/admin/jquery.multiselect.min.js"></script>
   <script src="${pageContext.servletContext.contextPath}/js/admin/jquery.multiselect.filter.min.js"></script>
   <script src="${pageContext.servletContext.contextPath}/js/input.js"></script>


   <div class="breadcrumbs">
      <span class="breadcrumb-icon icon-bars"></span> Reports</a>
       <span class="error-container">
                  <div class="alert alert-error" id="ErrorFieldContent">
                  
                  </div>
      </span>
      <a href="help.do"><span class="info-right icon-info"></span></a>
   </div>
   <div class="contentcontainer clearfix">
      <div class="navigation">
         <ul class="menu" id="menu">
            <li class="current-link-selected"><a href="#generateReport"><span class="icon-file-excel"></span>Generate Report</a></li>
            <li><a href="#customReport"><span class="icon-insert-template"></span>Custom Report</a></li>
            <li><a href="#assetHistory"><span class="icon-history"></span>Asset History</a></li>
            <li><a href="#financeData"><span class="icon-calculate"></span>Finance Data</a></li>
         </ul>
      </div>
      <!-- Generate Report -->
      <div class="contentsection current" id="generateReport">
         <div class="contentheader">
            <div class="contentheading">
               <h4><span class="icon-file-excel"></span>Generate Report</h4>
            </div>
            <form:form id="reportform" method="POST" action="getReportDetails.do" modelAttribute="getReportDetails">
               <div class="contentbody clearfix">
                  <select name="reportType" id="reportType" style="width:250px">
                  	<option value="0" selected>Select Report Type</option>
                  </select>
                  <a id="previewId" href="#reportpurchase" class="btn btn-primary leftMove"><span class="icon-binoculars"></span>Preview</a>
                  <a id="generateReportDownload" class="btn btn-primary posright" onclick='exportReport()' hidden><span class="icon-download"></span>Download</a>
               </div>
            </form:form>
         </div>
         <div class="contenttable">
            <div class="table-resize reporttable" id="reportpurchase">
               <div id="generateReportDiv"></div>
            </div>
         </div>
      </div>
      <!-- Custom Report -->
      <div class="contentsection" id="customReport">
         <div class="contentheader">
            <div class="contentheading">
               <h4><span class="icon-insert-template"></span>Custom Report</h4>
            </div>
            <div class="contentbody clearfix">
               <select id="customSelectAssetType">
                  <option value="0" selected>-- All Assets --</option>
               </select>&nbsp;
               <select id="customSelectAttributesList" multiple>
               </select>
               &nbsp;
               <input type="checkbox" id="checkboxSelectAll" disabled><label for="checkboxSelectAll">Select All</label>
               <a id="customReportPreview" class="btn btn-primary leftMove" href="#reportCustom" onclick='customReportPreview()'><span class="icon-binoculars"></span>Preview</a>
               <a id="customReportDownload" class="btn btn-primary posright" onclick='exportCustomReport()' hidden><span class="icon-download"></span>Download</a>
            </div>
         </div>
         <div class="contenttable">
            <div class="table-resize reporttable" id="reportCustom"> 
                   <a class="btn btn-primary posright" href="#modal-reports"><span class="icon-download"></span>Save Report</a><br/><br/><br/>
               <div id="customReportDiv"></div>
               </div>
               
            </div>
         </div>
      
      <!-- Asset History -->
      <div class="contentsection " id="assetHistory">
         <div class="contentheader">
            <div class="contentheading">
               <h4><span class="icon-history"></span>Asset History</h4>
            </div>
            <div class="contentbody clearfix">
               <select id="assetSelect" style="width:153px">  
                  <option value="">Select search type</option>
                  <option value="USER_ID">Emp Name/ID</option>
                  <option value="ASSET_ID">Asset ID</option>
               </select>&nbsp;
               <input type="text" class="SearchTerm" id="SearchTerm" name="SearchTerm" placeholder="SearchTerm" disabled="true"/>
               <a href="#historyData" id ="getAssetHistoryBtn" class="btn btn-primary leftMove"><span class="icon-binoculars"></span>Preview</a>
               <a class="btn btn-primary posright" id="exportAssetsHistory" hidden><span class="icon-download"></span>Download</a>
            </div>
         </div>
         <div class="contenttable">
            <div class="table-resize reporttable" id="historyData">
            
               <div id="userHistory" class="hideTable">
                  <table id="historyTableUser" class="cell-border hover row-border" cellspacing="0" width="100%">
                     <thead>
                        <tr>
                           <th>Asset Id</th>
						   <th>Asset Type</th>
                           <th>Issued Date</th>
                           <th>Returned Date</th>
                        </tr>
                     </thead>
                  </table>
               </div>
				<div id="assetHistoryUser" class="hideTable"> 
                  <table id="historyTableAsset" class="cell-border hover row-border" cellspacing="0" width="100%">
                     <thead>
                        <tr>
                           <th>User Id</th>
                           <th>Designation</th>
                           <th>Issued Date</th>
                           <th>Returned Date</th>
                        </tr>
                     </thead>
                  </table>
               </div>
            </div>
         </div>
      </div>
      <!-- Finance Data -->
      <div class="contentsection" id="financeData">
			<div class="contentheader financeHeader">
				<div class="contentheading">
					<h4><span class="icon-calculate"></span>Finance</h4>
				</div>
						<div class="contentbody finance clearfix ">
							
							<div>
								<label>Asset Type</label>
								<select id="financeAssetType" style="width:250px">
									<option value = "">Choose Asset Type</option>
								</select>
								<span class="tooltips" href="#">								
								<span id="typeNameChangeSpan" style="display: inline-block;">
									<p id="typeNameChangeMessage"></p></span>
								</span>
							</div>
							<div>
								<label>Depreciation Type</label>			
								<select id="depreciationType" style="width:250px">
									<option value="">Select depreciation type</option>
									<option value="SLM">Straight Line Method</option>
		                  			<option value="WDV">Written-down Value</option>
								</select>
								<span class="tooltips" href="#">								
								<span id="typeNameChangeSpan" style="display: inline-block;">
									<p id="typeNameChangeMessage"></p></span>
								</span>
							</div>
							<div>
								<label>Percentage</label>
								<span class="tooltips" href="#">
									<input type="text" class="percentage" id ="percentage" placeholder="Enter the percentage" style="width:224px;"/>
									<span id="typeNameChangeSpan" style="display: inline-block;">
									<p id="typeNameChangeMessage"></p></span>
								</span>
							</div>
							<div class="selectalign clearfix">
								<span><a id="previewId" href="#" class="btn btn-primary" onclick='financeReportPreview()'><span class="icon-binoculars"></span>Preview</a></span>
								<span><a id="financeReportDownload" class="btn btn-primary posright" onclick='exportFinanceReport()' hidden><span class="icon-download"></span>Download</a></span>
							</div>
						</div>					
				</div>
				<div class="contenttable financePosition">
						<div id="financeReportDetails"></div>
				</div>
		</div>
   </div>
   <!-- Modal: custom query name -->
  <div class="modal" id="modal-reports" aria-hidden="true">
      <div class="modal-dialog">
      <div class="modal-header">
        <h2>Save New Report</h2>
        <a href="#" class="btn-close" aria-hidden="true">x</a>
      </div>
     <div class="modal-body">
       <input id="newQuery" type="text"  placeholder="Enter report name"/>
       <span><p id="saveQueryErrorDisplay" style="display:inline; color:red;"></p></span>
     </div>
     <div class="modal-footer">
    	<a href="#home" class="btn" id="saveQuery">Save Report</a>
     </div>
  </div>
</div>
<%@include file="../footer.jspf" %>
   </body>
</html>
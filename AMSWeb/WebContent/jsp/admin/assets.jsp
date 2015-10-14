<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>

<%@include file="header_secure.jspf" %>

<!-- Local Script Include -->
<script src="${pageContext.servletContext.contextPath}/js/admin/asset.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/admin/notification.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/input.js"></script>




                
<div class="breadcrumbs">
		<span class="breadcrumb-icon icon-stack"></span> Assets</a>
		 <span class="error-container">
                  <div class="alert alert-error" id="ErrorFieldContent">
                  
                  </div>
            </span>
            <a href="help.do"><span class="info-right icon-info"></span></a>
</div>



<div class="contentcontainer clearfix">
			<div class="navigation">
				<ul class="menu" id="menu">
					<li class="current-link-selected"><a href="#viewAssets"><span class="icon-cabinet"></span>View Assets</a></li>
					<li><a href="#addAssets"><span class="icon-plus"></span>Add Asset</a></li>
					<li><a href="#typeAssets"><span class="icon-cog"></span>Asset Type</a></li>
				</ul>
			</div>

			<!-- View Asset -->


			<div class="contentsection current" id="viewAssets">
				<div class="contentheader">
					<div class="contentheading">
						<h4><span class="icon-cabinet"></span>View Assets</h4>
					</div>
					<div class="contentbody clearfix">
						<select id="assertId">
							<option value="">Select search type</option>
						    <option value="ASSET_ID">Asset ID</option>
						    <option value="TYPE">Asset Type</option>
						</select>
					
						<input type="text" class="searchUser" name="searchtext" id="searchtext" placeholder="Search" disabled='true'/>
						<button class="btn btn-primary button-height" id = "searchbtn" style="height:33px;"><span class="icon-search padding-none"></span></button>
					    <button class="btn btn-primary button-height" id = "resetbtn" style="height:33px;">Reset</button>
					</div>
				</div>
				<div class="contenttable">
					<div class="assets-table">
						<table id="Assets" class="cell-border hover row-border" cellspacing="0" width="100%">
						        <thead>
						            <tr>									
						      		    <th>Asset Identity</th>
						                <th>AssetType</th>
						                <th>Owner</th>
						                <th>PurchasedDate</th>
						            </tr>
						        </thead>
						 

						</table>
					</div>
				</div>
			</div>


			<!-- Add Assets -->

			<div class="contentsection" id="addAssets">
				<div class="contentheader short-header ">
					<div class="contentheading">
						<h4><span class="icon-plus"></span>Add Asset</h4>
					</div>
					<div class="contentbody clearfix">
						
					</div>
				</div>
				
				<div class="formcontainer">
					<form:form id="addAssetForm" novalidate="novalidate" method="POST" action="addAsset.do" class="addasset" modelAttribute="addAsset">
				     
				          <div>
					          <label>Asset Type</label>
					          <select id="assetType" name="assetType" style="width:257px">
					          <option value="0" selected>Select Asset Type</option>
					          </select>
				          </div>
				         
				          <div id="addAssetDiv" class="changing">
				          </div>
				          <div class="assetradio">
							<label>Carry out of office?</label>
							<input type="radio" name="carry" value="yes" checked="checked" id="yes"/><label style="width:100px;" for="yes">Yes</label>
							<input type="radio" name="carry" value="no" id="no"/><label  style="width:100px;" for="no">No</label>
						 </div>
						 <div class="assetradio">
							<label>Available for use?</label>
							<input type="radio" name="available" value="yes" checked="checked" id="use"/><label style="width:100px;" for="use">Yes</label>
							<input type="radio" name="available" value="no" id="nouse"/><label style="width:100px;" for="nouse">No</label>
						 </div>
						  <div><label for="procuredDate">Procured Date</label>
				          	<span class="tooltips"><input type="text" id="procuredDate" name="procuredDate"><span id="procuredSpan" class = "errorClassForDate" style="display : none;"><p></p></span></span>
				      	  </div>
				          
				      	  <div  class="poscenter">
				          	<input type="submit" class="btn btn-primary" value="Add"></input>
				          	<input type="reset" class="btn btn-primary" value="Reset" id="resetAddAssetForm">
				     	  </div>
					</form:form>
				</div>
			</div>
		
	<style>
		.errorClassForDate
		{
			display:none !important;
		
		}
		.errorClassForDateShow
		{
			display:inline-block !important;
		}
	</style>

			<!-- Assets Type-->

			<div class="contentsection" id="typeAssets">
				<div class="contentheader short-header ">
					<div class="contentheading">
						<h4><span class="icon-cog"></span>Asset Type</h4>
					</div>
					<div class="contentbody clearfix">
						
					</div>
				</div>
				
				<div class="formcontainer">
					<form:form id="assetForm" novalidate="novalidate" method="POST" class="assettypes" action="manageAsset.do" modelAttribute="manageAsset" >
						<div class="assetradio">
							<input type="radio" name="method" value="add" checked="checked" id="add"/><label for="add">Add</label>
							<input type="radio" name="method" value="edit" id="edit1"/><label for="edit1">Edit</label>

</div>
							<div class="manage" id="assetdiv">
							</div>
						<div class="assetradio">
							<label>Carry out of office?</label>
							<input type="radio" name="carry" value="yes" checked="checked" id="carryyes"/><label style="width:100px;" for="carryyes">Yes</label>
							<input type="radio" name="carry" value="no" id="carryno"/><label for="carryno">No</label>
						 </div>
						 
						<div  class="poscenter" style="width: 448px;">
							<input type="submit" class="btn btnsave" value="Save" id="save">
						</div>
					</form:form>
				</div>
			</div>
			
		</div>
<%@include file="../footer.jspf" %>
</body>
</html>
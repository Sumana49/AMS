<%@taglib uri="http://www.springframework.org/tags/form"
	prefix="formtag"%>
<html>
<%@include file="header_non-secure.jspf"%> 
<div class="contentcontainer clearfix"> 
	<div class="container"> 
		<div id="errormessages" class="errormessages"></div>
		<div id="reqstatus">${status}</div>
		<div class="introduction-block">
			<h1>Asset Management Portal</h1>
			Utilize this portal to procure the assets
		</div>
		<div class="formcontainer">
			<formtag:form method="POST" class="formreq" id="register-form"
				novalidate="novalidate" action="placerequest.do">
				<div class="field">
					<formtag:label path="employeeID">Employee ID*</formtag:label>
					<formtag:input path="employeeID" placeholder="Employee ID" autocomplete='off'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span id="errmsg_eid"></span>
				</div>
				<div class="field namefield">
				   <label>Employee Name</label>
					<label id="namelabel"></label>
				</div>
				<div class="selectfield">
					<formtag:label path="assetType">Type*</formtag:label>
					<formtag:select path="assetType" disabled='true'>
						<formtag:option value="" label="Select Type" />
					</formtag:select>
				</div>
				<div class="field">
					<formtag:label path="assetStartDate">Start Date*</formtag:label>
					<formtag:input path="assetStartDate" 
						placeholder="Select a start date" disabled='true' autocomplete='off'/>
				</div>
				<div class="field">
					<formtag:label path="assetNumberOfDays">Number of days</formtag:label>
					<formtag:input path="assetNumberOfDays"
						placeholder="Number of days" disabled='true' autocomplete='off'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span id="errmsgno_ofdays"></span>
					<label> </label>
				</div>
				<div class="selectfield">
					<formtag:label path="assetSeverity">Severity*</formtag:label>
					<formtag:select path="assetSeverity" disabled='true'>
						<formtag:option value="" label="Select Severity" />
						<formtag:option value="H" label="High" />
						<formtag:option value="M" label="Medium" />
						<formtag:option value="L" label="Low" />
					</formtag:select>
				</div>
				<div class="selectfield">
					<formtag:label path="assetApprover">Approver*</formtag:label>
					<formtag:select path="assetApprover" disabled='true'>
						<formtag:option value="" label="Select Approver" />
					</formtag:select>
				</div>
	 			<div class="notediv"> 
					<formtag:label path="assetNote" id="notelabel" height="57px" width="234">Note*</formtag:label>
					<formtag:textarea path="assetNote" placeholder="Specification & Purpose" disabled='true' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span id="errmsgnote"></span>
				</div>
				<div class="field">
					<div class="cleardiv">
						<input id="resetbtn" type="reset" class="btn btn-disable"
							value="Clear" />
					</div>
					<div class="submitdiv">
						<input id="submitbtn" type="submit" class="btn btn-disable"
							value="Submit" disabled='true' />
							
					</div>
				</div>
			</formtag:form>
		</div>

 
	</div>
</div>
<div id="submission-dialog" title="Confirmation" class="hide">
  <p id="dialog-form-content" class="validateTips">Are you Sure you want to place the request for device?</p>
</div>
</body>
<%
session.setAttribute("status","");
%>
<%@include file="../footer.jspf" %>
</html>
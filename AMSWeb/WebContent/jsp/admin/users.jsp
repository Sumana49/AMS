<!DOCTYPE html>
<%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>

<%@include file="header_secure.jspf" %>
<script src="${pageContext.servletContext.contextPath}/js/admin/users.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/input.js"></script>


<div class="breadcrumbs">
		<span class="breadcrumb-icon icon-users"></span> Users</a>
		 <span class="error-container">
                  <div class="alert alert-error" id="ErrorFieldContent">
                  
                  </div>
            </span>
            <a href="help.do"><span class="info-right icon-info"></span></a>
</div>

		<div class="contentcontainer clearfix">
			<div class="navigation">
				<ul class="menu" id="menu">
					<li  class="current-link-selected"><a href="#usersTab"><span class="icon-cart2"></span>Current Holdings</a></li>
				</ul>
			</div>

			<!-- User Current Holdings -->

			<div class="contentsection current" id="usersTab">
				<div class="contentheader">
					<div class="contentheading">
						<h4><span class="icon-cart2"></span>Current Holdings</h4>
					</div>

					<div class="contentbody clearfix">
						
						<input type="text" class="searchUser" name="searchtext" id="searchtext" placeholder="Search for Employee Id or Employee Name" />
						<div class="btn btn-primary button-height" id="search"><span class="icon-search padding-none"></span></div>
						<!-- <div class="btn posright"><span class="icon-download2"></span>Export</div> -->
					</div>
				</div>
				<div class=" contenttable" style="width:100%;">
				<div class="paddingAdjust">
						<table id="emptable" class="cell-border hover row-border" style="width:90%;">
							<thead>
						            <tr>
										<th></th>
						                <th>Emp. Id</th>
						                <th>Name</th>
						                <th>Designation</th>
						                <th>Business Unit</th>

						            </tr>
						        </thead>

						</table>
				</div>
				</div>
			</div>

		</div>
<%@include file="../footer.jspf" %>
	</body>
</html>
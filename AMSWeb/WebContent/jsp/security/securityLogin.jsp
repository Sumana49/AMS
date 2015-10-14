   <%@ include file="header.jsp" %>
		<script src="${pageContext.servletContext.contextPath}/js/jquery.validate.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/js/security/securityLogin.js"></script>
		<title>Security Login</title>
	</head> 
	
	<body>
             <div class="container">
                <div class="left">
                          <div class="logocontainer">
                              <div class="logo1"> 
                               </div>
                        </div>      
                </div>
                <div class="right">
                         <div class="securehead">
                                 <span class="icon-key2"></span>&nbsp;<span>ASSET SECURITY DESK</span>
                        </div>
                         <div class="securemiddle">
                                <div class="secureform">
                                   <form method="POST" action="home.do" id="secureLoginForm" modelAttribute="userLogin"> 
                                            <div> 
                                                  <input id="EmpID" name="EmpID" type="text" placeholder="Employee Id"/>
                                            </div>  
                                           <div class="buttoncontainer">
                                                 <input id="LoginSubmitID" type="submit" class="btn btn-primary" value="Enter"/>
                                            </div>    
                                    </form>
                                    
                                </div>
                                
                        </div>
                        <div class="formfooter">
                                  <p>&copy; All Rights Revserved. Avnet Inc.</p>
                        </div>
                </div>
                 
           </div>
          
		
	</body>
</html>
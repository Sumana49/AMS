package com.avnet.ams.login;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.avnet.ams.constants.AMSCommonConstants;
import com.avnet.ams.constants.LoggerConstants;
import com.avnet.ams.util.AMSREPUtil;
import com.avnet.ams.util.AMSWebServiceReflectionUtil;
import com.avnet.ams.util.WASGroupsExtractor;
import com.avnet.assetportal.webservice.usermanager.EmployeeInfo;
import com.avnet.assetportal.webservice.usermanager.UserManagerPortProxy;

/**
 * @author Atechian
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String userName, usertype;
	 UserManagerPortProxy webServiceAccessApprover = null;
	private  final String CLASS_NAME = LoginServlet.class.getName();

	private final  Logger LOGGER = Logger.getLogger(CLASS_NAME);
    public  String url = AMSREPUtil.getProperty(AMSCommonConstants.ldapURL);

    UserManagerPortProxy initwebServiceAccessApprover(){
    	if(webServiceAccessApprover!=null)
    		return webServiceAccessApprover;
    	webServiceAccessApprover=(UserManagerPortProxy) AMSWebServiceReflectionUtil.getProxyObject(AMSCommonConstants.USERMANAGERSERVICE);
    	return webServiceAccessApprover;
   }
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String METHOD_NAME="doPost";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccessApprover=initwebServiceAccessApprover();
		if (request.getRemoteUser() != null) {
			int size = WASGroupsExtractor.getGroupsForCurrentUser().size();
			List<String> groups=WASGroupsExtractor.getGroupsForCurrentUser();
			
			LOGGER.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1104D"),
					size));
			
			if (groups.contains(AMSCommonConstants.USER)) {
				// User belongs to Chennai Branch
				if (groups.contains(AMSCommonConstants.ADMIN)){
					usertype=AMSCommonConstants.ADMIN;
					LOGGER.fine(MessageFormat.format(
							LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1106D"),
							usertype));
					setSession(request, usertype);
					
					response.sendRedirect("admin/home.do");
					}
				
				else{
					usertype=AMSCommonConstants.USER;
					setSession(request, usertype);
					response.sendRedirect("login.do");
					}
			}
			else {
				response.sendRedirect("home.do");
				LOGGER.severe(MessageFormat.format(
								LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1101E"),"Invalid User"));
			}
		}
	
	LOGGER.exiting(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * @param request
	 * @param groupName
	 */
	public void setSession(HttpServletRequest request, String groupName) {
		String METHOD_NAME="setSession";
		String mailDomain="@atech.com";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		
		HttpSession session = request.getSession();
		userName = request.getRemoteUser();
		
		LOGGER.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1101D"),
				userName));
		userName=userName.concat(mailDomain);
		
		EmployeeInfo emp=null;
		try
		{
			emp=webServiceAccessApprover.getEmployeeInfo(userName.toLowerCase());
			LOGGER.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1102D"),
					emp.getEmployeeId()));
		}
		catch(Exception e){
			LOGGER.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1102E"),
				  	e.getMessage())); 
		  }
		
		session.setAttribute("userDetails",emp);
		session.setAttribute("userType", groupName);
		LOGGER.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1105D"),
				session.getAttribute("userDetails")));
		
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
	}

}
package com.avnet.ams.controller;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.avnet.ams.constants.AMSCommonConstants;
import com.avnet.ams.constants.AMSSecurityConstants;
import com.avnet.ams.delegate.AMSSecurityDelegate;
import com.avnet.ams.util.AMSREPUtil;
import com.avnet.ams.util.AMSWebServiceReflectionUtil;
import com.avnet.ams.vto.AMSSecurityLogin;
import com.avnet.assetportal.webservice.request.RequestManagerPortProxy;
import com.avnet.assetportal.webservice.usermanager.UserManagerPortProxy;
import com.ibm.json.java.JSONObject;

@Controller
@RequestMapping("/security")
public class SecurityController {

	private  final String CLASS_NAME =SecurityController.class.getName();
	private  Logger logger = Logger.getLogger("SecurityController");
	private UserManagerPortProxy webServiceAccessApprover = null;
	private RequestManagerPortProxy webServiceRequestAccess = null;
	@Autowired
	AMSSecurityDelegate securityDelegate;
    public  String url=AMSREPUtil.getProperty(AMSCommonConstants.WSDLURL);

	
  
	
	public UserManagerPortProxy initwebServiceAccessApprover(){
		if(webServiceAccessApprover!=null)
			return webServiceAccessApprover;
		webServiceAccessApprover=(UserManagerPortProxy) AMSWebServiceReflectionUtil.getProxyObject(AMSCommonConstants.USERMANAGERSERVICE);
		return webServiceAccessApprover;
}
	public RequestManagerPortProxy initWebServiceRequestAccess(){
		if(webServiceRequestAccess!=null)
			return webServiceRequestAccess;
		webServiceRequestAccess=(RequestManagerPortProxy) AMSWebServiceReflectionUtil.getProxyObject(AMSCommonConstants.USERREQUESTMANAGERSERVICE);
		return webServiceRequestAccess;
}
	
	/**
	 * Controller which takes the user to login screen
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return AMSSecurityConstants.LOGIN_VIEW;
	}
	/**
	 * 
	 * Controller for taking the valid user to home screen
	 * @param loginRequest
	 * @param EmpID
	 * @return
	 */
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("userLogin")AMSSecurityLogin loginRequest,@RequestParam String EmpID) {
		
		String METHOD_NAME = "doLogin";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccessApprover=initwebServiceAccessApprover();
		ModelAndView homeModel = new ModelAndView();
	    homeModel=securityDelegate.setSession(homeModel,webServiceAccessApprover,EmpID);
	    if(homeModel!=null)
	    {
	    	 homeModel.setViewName(AMSSecurityConstants.HOME_VIEW);
	    	 logger.exiting(CLASS_NAME, METHOD_NAME);
	    	 return homeModel;
	    }   
	    else
	    {
	    	ModelAndView loginModel=new ModelAndView();
	    	logger.exiting(CLASS_NAME, METHOD_NAME);
	    	loginModel.setViewName(AMSSecurityConstants.HOME_VIEW);
	    	return loginModel;
	    }
		
	}
	
	
	/**
	 * @return
	 */
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String doGetLogin() {
		return AMSSecurityConstants.LOGIN_VIEW ;
	}
	
	/**
	 * Controller to get the Security screen histroy details JSON object
	 * @return 
	 */
	@RequestMapping(value="/getAssetSecurity", headers="Accept=application/json")
	public @ResponseBody JSONObject getSecurityTable(){
	      String METHOD_NAME = "getSecurityTable";
	      webServiceRequestAccess=initWebServiceRequestAccess();
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return securityDelegate.getSecurityJson(webServiceRequestAccess);
		
	}
	/**
	 * Controller to get the employee details JSON object
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getEmployeeDetails", headers="Accept=application/json")
	public @ResponseBody JSONObject getEmployeeDetails(@RequestParam("empId") String empId ){
	    String METHOD_NAME = "getEmployeeDetails";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		webServiceAccessApprover=initwebServiceAccessApprover();
		return securityDelegate.getEmployeeDetails(webServiceAccessApprover,empId);
		
	}



	
}

package com.avnet.ams.controller;

/**
 * @author Sivanandham
 */

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.avnet.ams.constants.AMSCommonConstants;
import com.avnet.ams.delegate.AMSUserDelegate;
import com.avnet.ams.util.AMSREPUtil;
import com.avnet.ams.util.AMSWebServiceReflectionUtil;
import com.avnet.ams.vto.AssetRequest;
import com.avnet.assetportal.webservice.assetservice.AssetManagerServicePortProxy;
import com.avnet.assetportal.webservice.mail.MailServiceManagerSOAPProxy;
import com.avnet.assetportal.webservice.request.RequestManagerPortProxy;
import com.avnet.assetportal.webservice.usermanager.EmployeeDetails;
import com.avnet.assetportal.webservice.usermanager.EmployeeInfo;
import com.avnet.assetportal.webservice.usermanager.UserManagerPortProxy;
import com.ibm.json.java.JSONObject;


/**
 * @author Atechian
 *
 */
@Controller
@SessionAttributes({"userDetails","status"})
public class UserController {
	@Autowired
	public AMSUserDelegate userDelegate;
	private static final String CLASS_NAME = UserController.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private AssetManagerServicePortProxy webServiceAccess = null;
	private UserManagerPortProxy webServiceAccessApprover = null;
	private RequestManagerPortProxy webServiceRequestAccess = null;
	private MailServiceManagerSOAPProxy webServiceMailAccess =null;
    public String url=AMSREPUtil.getProperty(AMSCommonConstants.WSDLURL);
	
	public AssetManagerServicePortProxy initwebServiceAcess(){
		if(webServiceAccess!=null)
			return webServiceAccess;
		webServiceAccess= (AssetManagerServicePortProxy) AMSWebServiceReflectionUtil.getProxyObject(AMSCommonConstants.ASSETMANAGERSERVICE);//new AssetManagerServicePortProxy();
        return webServiceAccess;
	}
	
	public UserManagerPortProxy initwebServiceAccessApprover(){
		if(webServiceAccessApprover!=null)
			return webServiceAccessApprover;
	 webServiceAccessApprover=  (UserManagerPortProxy) AMSWebServiceReflectionUtil.getProxyObject(AMSCommonConstants.USERMANAGERSERVICE);
	 	return webServiceAccessApprover;
}
	public RequestManagerPortProxy initWebServiceRequestAccess(){
		if(webServiceRequestAccess!=null)
			return webServiceRequestAccess;
		webServiceRequestAccess= (RequestManagerPortProxy) AMSWebServiceReflectionUtil.getProxyObject(AMSCommonConstants.USERREQUESTMANAGERSERVICE);
		return webServiceRequestAccess;
}
	public MailServiceManagerSOAPProxy initWebServiceMailAccess(){
		if(webServiceMailAccess!=null)
			return webServiceMailAccess;
		webServiceMailAccess=  (MailServiceManagerSOAPProxy) AMSWebServiceReflectionUtil.getProxyObject(AMSCommonConstants.MAILSERVICEMANAGER);//new AssetManagerServicePortProxy();
		return webServiceMailAccess;
}
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/home")
	public ModelAndView showHome(ModelMap model) {
	     String METHOD_NAME = "showHome";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return new ModelAndView("jsp/user/assetRequest", "command",
				new AssetRequest());
	}

	/**
	 * @param assetRequestObject
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/placerequest", method = RequestMethod.POST)
	public String placeRequest(
			@ModelAttribute("SpringWeb") AssetRequest assetRequestObject,
			ModelMap model) {
		String METHOD_NAME = "addAssetRequest";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceRequestAccess=initWebServiceRequestAccess();
		userDelegate.submitRequest(webServiceRequestAccess, model,
				assetRequestObject);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return "redirect:/home.do";
	}

	
	@RequestMapping(value = "/placerequest", method = RequestMethod.GET)
	public ModelAndView placeRequestGet(@ModelAttribute("SpringWeb") AssetRequest assetRequestObject,
			ModelMap model) {
		return new ModelAndView("redirect:/home.do", "command",
				new AssetRequest());		
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String doLogin(ModelMap model) {
		String METHOD_NAME="doLogin";
		logger.entering(CLASS_NAME, METHOD_NAME);
		EmployeeInfo response = (EmployeeInfo)model.get("userDetails"); 
		if(response!=null)
		return "jsp/user/assetHistory";
		else
		return "redirect:/Login";
	}

	/**
	 * @return result
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/getApproverNames", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getApprovers(){
		String METHOD_NAME = "getApprovers";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccessApprover=initwebServiceAccessApprover();
		JSONObject result = null;
		try {
			result = userDelegate.getApprovers(webServiceAccessApprover);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return result;
	}

	/**
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/getEmployeeDetails", headers = "Accept=application/json")
	public @ResponseBody
	EmployeeDetails getEmployeeDetails(@RequestParam("empId") String empId)
		{
	     String METHOD_NAME = "getEmployeeDetails";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccessApprover=initwebServiceAccessApprover();
		//EmployeeDetails result=null;
		EmployeeDetails result=null; 
		try{
		result = userDelegate.getEmployeeDetails(webServiceAccessApprover,
				empId);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		//return result;
		}catch(Exception e){
			logger.exiting(CLASS_NAME, METHOD_NAME);
			//return result;
		}
		return result;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/getAssetTypes", headers = "Accept=application/json")
	public @ResponseBody
     JSONObject getAssetTypes(){
		String METHOD_NAME = "getAssetTypes";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccess=initwebServiceAcess();
		JSONObject result = null;
		try{
		result =  userDelegate.getAssetTypes(webServiceAccess);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		//return result;
		}catch(Exception e){
			e.printStackTrace();
			logger.exiting(CLASS_NAME, METHOD_NAME);
			//return result;
		}
		return result;
	}
	
	/**
	 * used to get all the available statuses
	 * 
	 * @return result
	 */
	@RequestMapping(value = "/getAssetStatus", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getStatus(){
		String METHOD_NAME = "getStatus";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccessApprover=initwebServiceAccessApprover();
		JSONObject result= userDelegate.getStatus(webServiceAccessApprover);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return result;
	}

	/**
	 * used to get the asset history of an employee
	 * 
	 * @param flowType
	 * @param start
	 * @param length
	 * @param searchType
	 * @param searchValue
	 * @param column
	 * @param dir
	 * @param model
	 * @return historyJson
	 */
	@RequestMapping(value = "/getAssetHistory")
	public @ResponseBody
	JSONObject getAssetHistory(
			@RequestParam(value = "flowType", required = true) String flowType,
			@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "length", required = true) String length,
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "order[0][column]", required = false) String column,
			@RequestParam(value = "order[0][dir]", required = false) String dir,
			ModelMap model){
		String METHOD_NAME = "getAssetHistory";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject historyJson=null;
		webServiceRequestAccess=initWebServiceRequestAccess();
		try{
			EmployeeInfo response = (EmployeeInfo) model.get("userDetails"); 
			if(response!=null){
				int id = response.getEmployeeId();
			    String empId=Integer.toString(id);
				historyJson=userDelegate.getAssetHistoryDetails(webServiceRequestAccess,empId, start, length, column, dir, searchType, searchValue,flowType);
			}
		}
		catch(RemoteException e){	
			if (logger.isLoggable(Level.SEVERE)) {
				logger.logp(Level.SEVERE, CLASS_NAME, METHOD_NAME, e.getMessage());
			}
		}
		
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return historyJson;
	}

	/**
	 * used to change the approver for an asset request
	 * 
	 * @param requestId
	 * @param approverId
	 * @param model
	 * @return result
	 */
	@RequestMapping(value = "/changeApprover")
	public @ResponseBody
	JSONObject changeApprover(
			@RequestParam(value = "requestId", required = false) String requestId,
			@RequestParam(value = "approverId", required = false) String approverId,
			ModelMap model) {
	    String METHOD_NAME = "changeApprover";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceMailAccess=initWebServiceMailAccess();
		JSONObject result = null;
		EmployeeInfo response = (EmployeeInfo) model.get("userDetails"); 
		if(response!=null){
			 result = userDelegate.changeApprover(webServiceMailAccess,requestId, approverId);
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return result;
		
	}

	/**
	 * used to trigger remind mail service 
	 * 
	 * @param requestId
	 * @param approverId
	 * @param remindString
	 * @param model
	 * @return result
	 */
	@RequestMapping(value = "/assetMail")
	public @ResponseBody
	JSONObject remindMail(
			@RequestParam(value = "requestId", required = true) String requestId,
			@RequestParam(value = "approverId", required = true) String approverId,
			@RequestParam(value = "remindString", required = true) String remindString,
			ModelMap model)
			{

				String METHOD_NAME = "remindMail";
				logger.entering(CLASS_NAME, METHOD_NAME);
			
				JSONObject result = null;
				webServiceMailAccess=initWebServiceMailAccess();
				EmployeeInfo response = (EmployeeInfo) model.get("userDetails"); 
				if(response!=null){
					result = userDelegate.remindMail(webServiceMailAccess,requestId, approverId, remindString);
				}
				logger.exiting(CLASS_NAME, METHOD_NAME);
				return result;

		}
	
	@RequestMapping(value = "/help")
	public String doRenderHelp(ModelMap model){
		return "jsp/user/help"	;
	
	}


}


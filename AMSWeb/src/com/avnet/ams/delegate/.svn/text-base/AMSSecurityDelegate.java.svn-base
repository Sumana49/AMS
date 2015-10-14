package com.avnet.ams.delegate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.avnet.ams.constants.AMSSecurityConstants;
import com.avnet.ams.constants.LoggerConstants;
import com.avnet.ams.util.AMSDateUtil;
import com.avnet.ams.util.AMSJsonUtil;
import com.avnet.ams.vto.AssetSecurityHistory;
import com.avnet.ams.vto.EmployeeDetailsClientVTO;
import com.avnet.assetportal.webservice.request.AssetDetails;
import com.avnet.assetportal.webservice.request.AssetPortalWSException;
import com.avnet.assetportal.webservice.request.EmployeeDetails;
import com.avnet.assetportal.webservice.request.EmployeeRequest;
import com.avnet.assetportal.webservice.request.EmployeeRequestDetails;
import com.avnet.assetportal.webservice.request.FlowTypeEnum;
import com.avnet.assetportal.webservice.request.RequestManagerPortProxy;
import com.avnet.assetportal.webservice.request.SearchByEnum;
import com.avnet.assetportal.webservice.usermanager.UserManagerPortProxy;
import com.ibm.json.java.JSONObject;
@Component
public class AMSSecurityDelegate {

	private static final String CLASS_NAME = AMSSecurityDelegate.class
			.getName();
	private static Logger logger = Logger.getLogger(AMSSecurityConstants.CLASSNAME);
	private static String empID;

	/**
	 * getSecurtiyBean returns employee current holdings JSON data
	 * 
	 * @param webServiceRequestAccess
	 * @return
	 */
	public JSONObject getSecurityJson(
			RequestManagerPortProxy webServiceRequestAccess) {

		String METHOD_NAME = "getSecurityBean";
		logger.entering(CLASS_NAME, METHOD_NAME);
		try {
			SearchByEnum searchenum = SearchByEnum.STATUS_NAME;
			FlowTypeEnum flowtypeenum = FlowTypeEnum.SECURITY_FLOW;
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString(AMSSecurityConstants.HISTROY_DEBUG_CODE1),
					empID));
			if(empID!=null ||empID!="")
			{
			EmployeeRequest employeeRequest = webServiceRequestAccess
					.getEmployeeRequests(empID, 1, 10, null, null, searchenum,
							AMSSecurityConstants.ISSUED, flowtypeenum);
			List<EmployeeRequestDetails> employeeRequestList = employeeRequest.getListOfEmpRequests();
			ArrayList<AssetSecurityHistory> assetHistoryWrapperList = new ArrayList<AssetSecurityHistory>();
			for (int i = 0; i < employeeRequestList.size(); i++) {
				AssetDetails assetDetailsObj = employeeRequestList.get(i).getAssetDetails();
				EmployeeDetails employeeDetailsObj = employeeRequestList.get(i).getEmployeeDetails();
				AssetSecurityHistory assetHistoryObj = new AssetSecurityHistory();
				assetHistoryObj.setAssetType(assetDetailsObj.getAssetType());
				assetHistoryObj.setMaxRowCount(employeeRequest.getMaxRowCount());
				assetHistoryObj.setInHouseFlag(employeeDetailsObj.getInHouseFlag());
				if(assetDetailsObj.getProductName()==""||assetDetailsObj.getProductName()==null)
				{
					assetHistoryObj.setAssetName(AMSSecurityConstants.NOT_SPECIFIED);	
				}
				else
				{
				    assetHistoryObj.setAssetName(assetDetailsObj.getProductName());
				}
				assetHistoryWrapperList.add(assetHistoryObj);
				assetHistoryObj.setDueDate(AMSDateUtil
						.getDate(AMSDateUtil.toDate(employeeDetailsObj
								.getDueDate())));
 
			 }
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString(AMSSecurityConstants.HISTROY_ACK_CODE1), assetHistoryWrapperList
							.toString()));
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return AMSJsonUtil.convertToSecurityHistoryDetailsJson(
					assetHistoryWrapperList, employeeRequest);
			}
			

		} catch (AssetPortalWSException e) {
			// TODO Auto-generated catch block
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString(AMSSecurityConstants.HISTROY_ERROR_CODE1),
					e.getMessage()));
			

		}
		catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString(AMSSecurityConstants.HISTROY_ERROR_CODE2),
					e.getMessage()));
			

		}
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return AMSJsonUtil.convertToSecurityErrorJson();
		

	}

	/**
	 * getEmployeeDetails returns employee details
	 * 
	 * @param webServiceAccessApprover
	 * @param empId
	 * @return
	 */
	public JSONObject getEmployeeDetails(
			UserManagerPortProxy webServiceAccessApprover, String empId) {
		String METHOD_NAME = "getEmployeeDetails";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString(AMSSecurityConstants.EMPDETIALS_DEBUG_CODE1),
				empId));

		List<com.avnet.assetportal.webservice.usermanager.EmployeeDetails> empDetails = null;
		JSONObject empJson = null;
		try {

			empDetails = webServiceAccessApprover.getEmployeeDetails(empId);
			EmployeeDetailsClientVTO empDetailsVTO = new EmployeeDetailsClientVTO();
			if (empDetails.size() != 0 && empDetails.get(0) != null) {
				
				if(empDetails.get(0).getName()!=null && empDetails.get(0).getEmpId()!=null )
				{
					empDetailsVTO.setName(empDetails.get(0).getName());
					empDetailsVTO.setEmpId(empDetails.get(0).getEmpId());
				}
				else
				{	
					return empJson;
				}
				if(empDetails.get(0).getDesignation()!=null && empDetails.get(0).getBusinessUnit()!=null )
				{
					empDetailsVTO.setDesignation(empDetails.get(0).getDesignation());
					empDetailsVTO.setBusinessUnit(empDetails.get(0).getBusinessUnit());
				}
				else
				{
					empDetailsVTO.setDesignation("");
					empDetailsVTO.setBusinessUnit("");
				}
						empJson = AMSJsonUtil.convertToSecurityEmployeeDetailsJson(empDetailsVTO);

				logger.fine(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString(AMSSecurityConstants.EMPDETIALS_ACK_CODE1), empJson.toString()));
				return empJson;

			}
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString(AMSSecurityConstants.EMPDETIALS_ACK_CODE1),
					AMSSecurityConstants.EMP_ERR));

		} 
		catch (com.avnet.assetportal.webservice.usermanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString(AMSSecurityConstants.EMPDETIALS_ERROR_CODE1),
					e.getMessage()));
			
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return empJson;
	}

	/**
	 * setSession sets the session for current user
	 * 
	 * @param model
	 * @param webServiceAccessApprover
	 * @param EmpID
	 * @return
	 */
	public ModelAndView setSession(ModelAndView model,
			UserManagerPortProxy webServiceAccessApprover, String EmpID) {

		JSONObject empDetails = getEmployeeDetails(webServiceAccessApprover,
				EmpID);

		if (empDetails != null) {
			model.addObject("empId", empDetails.get("EmployeeID"));
			model.addObject("empName", empDetails.get("Name"));
			model.addObject("designation", empDetails.get("Designation"));
			empID = empDetails.get("EmployeeID").toString();
		} else {
			model = null;
		}
		return model;
	}

}

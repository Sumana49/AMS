/**
 * 
 */
package com.avnet.ams.delegate;

import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.avnet.ams.constants.LoggerConstants;
import com.avnet.ams.constants.AMSUserConstants;
import com.avnet.ams.util.AMSDateUtil;
import com.avnet.ams.util.AMSJsonUtil;
import com.avnet.ams.util.AMSUserJsonUtil;
import com.avnet.ams.vto.AssetApprover;
import com.avnet.ams.vto.AssetHistoryDetails;
import com.avnet.ams.vto.AssetRequest;
import com.avnet.ams.vto.AssetTypeList;
import com.avnet.assetportal.webservice.assetservice.AssetManagerServicePortProxy;
import com.avnet.assetportal.webservice.assetservice.AssetType;
import com.avnet.assetportal.webservice.common.Acknowledgement;
import com.avnet.assetportal.webservice.common.SortOrderEnum;
import com.avnet.assetportal.webservice.mail.MailServiceManagerSOAPProxy;
import com.avnet.assetportal.webservice.mail.RemindRequestToEnum;
import com.avnet.assetportal.webservice.request.AssetDetails;
import com.avnet.assetportal.webservice.request.AssetPortalWSException;
import com.avnet.assetportal.webservice.request.EmployeeDetails;
import com.avnet.assetportal.webservice.request.EmployeeRequest;
import com.avnet.assetportal.webservice.request.EmployeeRequestDetails;
import com.avnet.assetportal.webservice.request.FlowTypeEnum;
import com.avnet.assetportal.webservice.request.RequestDetails;
import com.avnet.assetportal.webservice.request.RequestManagerPortProxy;
import com.avnet.assetportal.webservice.request.RequestSeverityEnum;
import com.avnet.assetportal.webservice.request.RequestSortTypeEnum;
import com.avnet.assetportal.webservice.request.SearchByEnum;
import com.avnet.assetportal.webservice.usermanager.ApproverDetails;
import com.avnet.assetportal.webservice.usermanager.StatusDetail;
import com.avnet.assetportal.webservice.usermanager.UserManagerPortProxy;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
 * @author Atechian
 * 
 */
@Component
@Qualifier("delegate")
public class AMSUserDelegate {

	private   final String CLASS_NAME = AMSUserDelegate.class.getName();
	private   Logger logger = Logger.getLogger("AMSUserDelegate");

	/**
	 * @param webServiceRequestAccess
	 * @param model
	 * @param assetRequestObject
	 */
		public  void submitRequest(
			RequestManagerPortProxy webServiceRequestAccess, ModelMap model,
			AssetRequest assetRequestObject) {
		final String METHOD_NAME = "submitRequest";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW10121D"),
				assetRequestObject.toString()));
		String requestId = AMSUserConstants.DEFAULT;
		//String requestId ="default";
		try {
			if(assetRequestObject.getEmployeeID()!=null && assetRequestObject.getAssetType()!=null
			&& assetRequestObject.getAssetStartDate()!=null && assetRequestObject.getAssetSeverity()!=null
			&& assetRequestObject.getAssetApprover()!=null && assetRequestObject.getAssetNote()!=null)
			{
			RequestDetails requestDetails = new RequestDetails();
			requestDetails.setEmpId(assetRequestObject.getEmployeeID());
			requestDetails.setApproverId(assetRequestObject.getAssetApprover());
			requestDetails.setAssetTypeId(assetRequestObject.getAssetType());
			if(!assetRequestObject.getAssetNumberOfDays().isEmpty())
 			{
   		    	requestDetails.setNoOfDays(Integer.parseInt(assetRequestObject.getAssetNumberOfDays()));
			}
			requestDetails.setNote(assetRequestObject.getAssetNote());
			RequestSeverityEnum severityEnum = RequestSeverityEnum
					.valueOf(assetRequestObject.getAssetSeverity());
			requestDetails.setSeverity(severityEnum);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate;
		
				startDate = dateFormat.parse(assetRequestObject.getAssetStartDate());
			
			requestDetails.setStartDate(AMSDateUtil
					.toXMLGregorianCalendar(startDate));
			Acknowledgement acknowledgement;
			acknowledgement = webServiceRequestAccess.placeRequest(requestDetails);
			if (acknowledgement!=null && acknowledgement.isFlag() == true) {
					requestId = acknowledgement.getStatus().toString();
					logger.fine(MessageFormat.format(
							LoggerConstants.APP_CONSTANTS_BUNDLE
									.getString("AMSW10121ACK"), acknowledgement
									.isFlag(), acknowledgement.getStatus(),
							acknowledgement.getMessage()));
					}
			else{
				//requestId ="ack_missing";
				requestId = AMSUserConstants.ACK_MISSING;
			}
			//model.addAttribute("status", requestId);
			
			}
			else{
				//requestId ="fields_missing";
				requestId = AMSUserConstants.FIELDS_MISSING;
			}
			logger.exiting(CLASS_NAME, METHOD_NAME);
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (AssetPortalWSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("status", requestId);
	}

	/**
	 * @param webServiceAccessApprover
	 * @return
	 * @throws Exception
	 */
	public   JSONObject getApprovers(
			UserManagerPortProxy webServiceAccessApprover) {
		String METHOD_NAME = "getApprovers";
		logger.entering(CLASS_NAME, METHOD_NAME);
		AssetApprover approverList = null;
		JSONObject getApproverJson = null;
		try {
			List<ApproverDetails> approverDetailsList = webServiceAccessApprover
					.getApprovers();
			approverList = new AssetApprover();
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int i = 0; i < approverDetailsList.size(); i++) {
				arrayList.add(approverDetailsList.get(i).getApproverName());
			}
			ArrayList<String> arrayListid = new ArrayList<String>();
			for (int i = 0; i < approverDetailsList.size(); i++) {
				arrayListid.add(approverDetailsList.get(i).getApproverId());
			}
			approverList.setApproverName(arrayList);
			approverList.setApproverId(arrayListid);
			getApproverJson=AMSJsonUtil.convertToAssetApproverJson(approverList);
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString("AMSW10122ACK"), approverList
							.getApproverName(), approverList.getApproverId()));
			logger.exiting(CLASS_NAME, METHOD_NAME);
			//return approverList;
			} catch (com.avnet.assetportal.webservice.usermanager.AssetPortalWSException e) {
				getApproverJson=AMSJsonUtil.convertToAssetApproverErrorJson();
				logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString("AMSW10122E"), e.getMessage()));
			logger.exiting(CLASS_NAME, METHOD_NAME);
			}
		//return approverList;
		//return AMSJsonUtil.convertToAssetApproverJson(approverList);
			return getApproverJson;
	}

	/**
	 * @param webServiceAccess
	 * @return
	 * @throws Exception
	 */
	public   JSONObject getAssetTypes(
			AssetManagerServicePortProxy webServiceAccess) {
		String METHOD_NAME = "getTypes";
		logger.entering(CLASS_NAME, METHOD_NAME);
		// List<AssetType> assetTypeList = null;
		AssetTypeList typeList = null;
		JSONObject getAssetTypeJson = null;
		try {
			List<AssetType> assetTypeList = webServiceAccess.getAssetType();
			assetTypeList = webServiceAccess.getAssetType();
			typeList = new AssetTypeList();
			ArrayList<String> assetType = new ArrayList<String>();
			for (int i = 0; i < assetTypeList.size(); i++) {
				assetType.add(assetTypeList.get(i).getAssetTypeName());
			}
			ArrayList<String> assetTypeId = new ArrayList<String>();
			for (int i = 0; i < assetTypeList.size(); i++) {
				assetTypeId.add(assetTypeList.get(i).getAssetTypeId());

			}
			typeList.setAssetType(assetType);
			typeList.setAssetId(assetTypeId);
			getAssetTypeJson=AMSJsonUtil.convertToAssetTypeJson(typeList);
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString("AMSW10123ACK"), typeList.getAssetId(),
					typeList.getAssetType()));
			logger.exiting(CLASS_NAME, METHOD_NAME);
		} catch (Exception e) {
			getAssetTypeJson=AMSJsonUtil.convertToAssetTypeErrorJson();
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString("AMSW10123E"), e.getMessage()));
			logger.exiting(CLASS_NAME, METHOD_NAME);

		}
		//return AMSJsonUtil.convertToAssetTypeJson(typeList);
		return getAssetTypeJson;
	}
	/**
	 * @param webServiceRequestAccess
	 * @param empId
	 * @param start
	 * @param length
	 * @param column
	 * @param dir
	 * @param searchType
	 * @param searchValue
	 * @param flowType
	 * @return
	 * @throws RemoteException
	 */
	public   JSONObject getAssetHistoryDetails(

	RequestManagerPortProxy webServiceRequestAccess, String empId,
			String start, String length, String column, String dir,
			String searchType, String searchValue, String flowType)
			throws RemoteException {

		final String METHOD_NAME = "getAssetHistoryDetails";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject jsonObjectWrapper = null;
		int Min = Integer.parseInt(start) + 1;
		int Max = (Integer.parseInt(start)) + (Integer.parseInt(length));

		try {

			String searchKey = AMSUserConstants.getSearchValue(searchType,searchValue.trim());
			RequestSortTypeEnum sortEnum = AMSUserConstants.getSortEnum(column);
			SortOrderEnum sortOrder = AMSUserConstants.getOrderEnum(dir);
			SearchByEnum searchEnum = AMSUserConstants.getSearchEnum(searchType);
			FlowTypeEnum flow = AMSUserConstants.getFlowtypeEnum(flowType.trim());

			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1044D"),
					empId, Min, Max, sortEnum, sortOrder, searchEnum,
					searchKey, flow));

			EmployeeRequest employeeRequest = webServiceRequestAccess
					.getEmployeeRequests(empId, Min, Max, sortEnum, sortOrder,
							searchEnum, searchKey, flow);

			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString("AMSW1041ACK"), employeeRequest
							.toString()));

			List<EmployeeRequestDetails> employeeRequestList = employeeRequest
					.getListOfEmpRequests();

			ArrayList<AssetHistoryDetails> assetHistoryWrapperList = new ArrayList<AssetHistoryDetails>();

			// FIXME - change it to iterator
			for (int i = 0; i < employeeRequestList.size(); i++) {
				AssetDetails assetDetailsObj = employeeRequestList.get(i)
						.getAssetDetails();
				AssetHistoryDetails assetHistoryObj = new AssetHistoryDetails();

				assetHistoryObj.setEmpId(assetDetailsObj.getEmpId());
				assetHistoryObj.setEmpname(assetDetailsObj.getEmpname());
				assetHistoryObj.setAssetType(assetDetailsObj.getAssetType());
				assetHistoryObj.setCurrentApproverId(assetDetailsObj
						.getCurrentApproverId());
				assetHistoryObj.setCurrentApproverName(assetDetailsObj
						.getCurrentApproverName());
				assetHistoryObj.setNote(assetDetailsObj.getNote());
				assetHistoryObj.setStatus(assetDetailsObj.getStatus());

				EmployeeDetails employeeDetailsObj = employeeRequestList.get(i)
						.getEmployeeDetails();
				assetHistoryObj.setBuCounter(employeeDetailsObj.getBucounter());
				assetHistoryObj.setStCounter(employeeDetailsObj.getStcounter());
				assetHistoryObj.setRequestId(employeeDetailsObj.getRequestId());
				assetHistoryObj.setSeverity(employeeDetailsObj.getSeverity());
				if (employeeDetailsObj.getAdminNote() != null) {
					assetHistoryObj.setComments(employeeDetailsObj
							.getAdminNote());
				} else {
					assetHistoryObj.setComments("Nil");
				}
				if (assetDetailsObj.getDayOfRequest() != null) {
					assetHistoryObj.setDayOfRequest(AMSDateUtil
							.getDate(AMSDateUtil.toDate(assetDetailsObj
									.getDayOfRequest())));

				} else {
					// FIXME - move to constants
					assetHistoryObj.setDayOfRequest("Nil");
				}

				if (employeeDetailsObj.getDateOfApproval() != null) {
					assetHistoryObj.setDateOfApproval(AMSDateUtil
							.getDate(AMSDateUtil.toDate(employeeDetailsObj
									.getDateOfApproval())));

				} else {
					assetHistoryObj.setDateOfApproval("Nil");
				}

				if (employeeDetailsObj.getDateOfIssue() != null) {

					assetHistoryObj.setDateOfIssue(AMSDateUtil
							.getDate(AMSDateUtil.toDate(employeeDetailsObj
									.getDateOfIssue())));

				} else {
					assetHistoryObj.setDateOfIssue("Nil");
				}

				if (employeeDetailsObj.getDateOfReturn() != null) {

					assetHistoryObj.setDateOfReturn(AMSDateUtil
							.getDate(AMSDateUtil
									.toDate(employeeDetailsObj
											.getDateOfReturn())));
				} else {
					assetHistoryObj.setDateOfReturn("Nil");
				}

				if (employeeDetailsObj.getDueDate() != null) {

					assetHistoryObj.setDueDate(AMSDateUtil
							.getDate(AMSDateUtil
									.toDate(employeeDetailsObj.getDueDate())));

				} else {
					assetHistoryObj.setDueDate("Nil");
				}

				assetHistoryObj
						.setMaxRowCount(employeeRequest.getMaxRowCount());

				assetHistoryWrapperList.add(assetHistoryObj);

			}

			jsonObjectWrapper = AMSUserJsonUtil.assetHistoryToJson(assetHistoryWrapperList,employeeRequest);

			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1045D"),
					jsonObjectWrapper.toString()));

			logger.exiting(CLASS_NAME, METHOD_NAME);
			return jsonObjectWrapper;

		} catch (AssetPortalWSException e) {

			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1041E"),
					e.getMessage()));
			// FIXME - move to util class
			jsonObjectWrapper = AMSUserJsonUtil.generateErrorJson();
			

		} catch (Exception e1) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1041E"),
					e1.getMessage()));
			jsonObjectWrapper = AMSUserJsonUtil.generateErrorJson();
		}
		// FIXME - one return statement
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;
	}

	/**
	 * @param webServiceMailAccess
	 * @param requestId
	 * @param approverId
	 * @return
	 */
	public   JSONObject changeApprover(MailServiceManagerSOAPProxy webServiceMailAccess, String requestId,String approverId) {
		String METHOD_NAME = "changeApprover";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject flagJSON=null;
		logger.fine(MessageFormat.format(LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1041D"),requestId, approverId));
		Boolean flag = null;
		String flagString = null;
		try {

			flagJSON=new JSONObject();
			Acknowledgement ack = webServiceMailAccess.changeApprover(requestId, approverId);
			flag = ack.isFlag();
			if (flag) {
				flagString = "true";
			} else {
				flagString = "false";
			}
			logger.fine(MessageFormat.format(LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1041ACK"), flag));
			
		} catch (com.avnet.assetportal.webservice.mail.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1041E"),e.getMessage()));
			flagString = "false";
		}
		
		flagJSON.put("flag", flagString);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return flagJSON;
	}

	/**
	 * @param webServiceAccessApprover
	 * @param empId
	 * @return
	 */
	public   com.avnet.assetportal.webservice.usermanager.EmployeeDetails getEmployeeDetails(
			UserManagerPortProxy webServiceAccessApprover, String empId) {
		String METHOD_NAME = "getEmployeeDetails";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1045D"),
				empId));
		com.avnet.assetportal.webservice.usermanager.EmployeeDetails employeeObject = null;
		List<com.avnet.assetportal.webservice.usermanager.EmployeeDetails> empdetails = null;
		try {

			empdetails = webServiceAccessApprover.getEmployeeDetails(empId);
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString("AMSW1045ACK"), empdetails.toString()));
			employeeObject = empdetails.get(0);
		} catch (com.avnet.assetportal.webservice.usermanager.AssetPortalWSException e) {

			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1045E"),
					e.getMessage()));
			logger.exiting(CLASS_NAME, METHOD_NAME);
		}
		return employeeObject;
	}

	/**
	 * @param webServiceMailAccess
	 * @param requestId
	 * @param approverId
	 * @param remindString
	 * @return
	 */
	public   JSONObject remindMail(MailServiceManagerSOAPProxy webServiceMailAccess, String requestId,String approverId, String remindString){

		String METHOD_NAME = "reminMail";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1042D"),requestId, approverId, remindString));
		String flagString = null;
		Boolean flag = null;
		RemindRequestToEnum remindEnum = AMSUserConstants.convertToEnum(remindString);
		JSONObject flagJSON = null;
		try {
			flagJSON = new JSONObject();
			Acknowledgement ack = webServiceMailAccess.remindRequest(
					remindEnum, requestId, approverId);
			flag = ack.isFlag();
			if (flag) {
				flagString = "true";
			} else {
				flagString = "false";
			}
			logger.fine(MessageFormat.format(LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1042ACK"), flag));
			

		} catch (com.avnet.assetportal.webservice.mail.AssetPortalWSException e) {

			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1042E"),
					e.getMessage()));
			flagString = "false";
			
		}
		
		flagJSON.put("flag", flagString);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return flagJSON;

	}

	/**
	 * used to get all the status from web service
	 * 
	 * @param webServiceAccess
	 * @return statusWrapperJSON
	 */
	public   JSONObject getStatus(UserManagerPortProxy webServiceAccess) {

		String METHOD_NAME = "getStatus";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject statusWrapperJSON = new JSONObject();
		JSONArray statusJSONArray = new JSONArray();
		try {
			List<StatusDetail> statusList  = webServiceAccess.getAllStatus();
			logger.fine(MessageFormat.format(LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1046ACK"), statusList.toString()));
			statusJSONArray = new JSONArray();
			for (StatusDetail obj : statusList) {
				statusJSONArray.add(obj.getStatusName());
			}
			statusWrapperJSON.put("assetStatus", statusJSONArray);
			
		} catch (Exception e) {
			logger.severe(MessageFormat.format(LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1046E"),e.getMessage()));
			statusJSONArray.add("Suggestions NOT available. Try after sometime.");
			
		}
		
		statusWrapperJSON.put("assetStatus", statusJSONArray);
		return statusWrapperJSON;

	}

}

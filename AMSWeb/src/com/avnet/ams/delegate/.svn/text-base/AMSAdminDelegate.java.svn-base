package com.avnet.ams.delegate;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceException;

import org.springframework.stereotype.Component;

import com.avnet.ams.constants.AMSAdminConstants;
import com.avnet.ams.constants.LoggerConstants;
import com.avnet.ams.util.AMSDateUtil;
import com.avnet.ams.util.AMSJsonUtil;
import com.avnet.ams.util.AMSNameUtil;
import com.avnet.ams.util.AcknowledgementJsonUtil;
import com.avnet.ams.vto.AddAssetVTO;
import com.avnet.ams.vto.AssetTypeAdapter;
import com.avnet.ams.vto.AssetTypeList;
import com.avnet.ams.vto.AssetTypeVTO;
import com.avnet.ams.vto.AssetsHistoryCountVTO;
import com.avnet.ams.vto.AssetsHistoryVTO;
import com.avnet.ams.vto.AttributeType;
import com.avnet.ams.vto.AttributesList;
import com.avnet.ams.vto.AvailableAssetVTO;
import com.avnet.ams.vto.EditAssetVTO;
import com.avnet.ams.vto.EmployeeDetailsClientVTO;
import com.avnet.ams.vto.EmployeeDetailsListVTO;
import com.avnet.ams.vto.EmployeeDetailsOwnedby;
import com.avnet.ams.vto.ResolveVTO;
import com.avnet.ams.vto.ViewAssetsAdminVTO;
import com.avnet.ams.vto.ViewAssetsCountVTO;
import com.avnet.ams.vto.ViewReportVTO;
import com.avnet.ams.vto.ViewRequestCountVTO;
import com.avnet.ams.vto.ViewRequestsVTO;
import com.avnet.assetportal.webservice.assetservice.Asset;
import com.avnet.assetportal.webservice.assetservice.AssetIdentityList;
import com.avnet.assetportal.webservice.assetservice.AssetList;
import com.avnet.assetportal.webservice.assetservice.AssetManagerServicePortProxy;
import com.avnet.assetportal.webservice.assetservice.AssetPortalWSException;
import com.avnet.assetportal.webservice.assetservice.AssetStatusEnum;
import com.avnet.assetportal.webservice.assetservice.AssetType;
import com.avnet.assetportal.webservice.assetservice.Employee;
import com.avnet.assetportal.webservice.assetservice.SearchTypeEnum;
import com.avnet.assetportal.webservice.assetservice.SortColumnEnum;
import com.avnet.assetportal.webservice.common.Acknowledgement;
import com.avnet.assetportal.webservice.common.Attribute;
import com.avnet.assetportal.webservice.common.SortOrderEnum;
import com.avnet.assetportal.webservice.reportmanager.AssetDetails;
import com.avnet.assetportal.webservice.reportmanager.AssetReport;
import com.avnet.assetportal.webservice.reportmanager.DepreciationTypeEnum;
import com.avnet.assetportal.webservice.reportmanager.ReportManagerPortProxy;
import com.avnet.assetportal.webservice.reportmanager.ReportResults;
import com.avnet.assetportal.webservice.reportmanager.ReportResultsList;
import com.avnet.assetportal.webservice.reportmanager.ReportType;
import com.avnet.assetportal.webservice.request.EmployeeRequestDetails;
import com.avnet.assetportal.webservice.request.GetAssetResponse;
import com.avnet.assetportal.webservice.request.RecentActivityDetails;
import com.avnet.assetportal.webservice.request.RequestManagerPortProxy;
import com.avnet.assetportal.webservice.request.RequestOperationEnum;
import com.avnet.assetportal.webservice.request.RequestSortTypeEnum;
import com.avnet.assetportal.webservice.request.UpdateRequestDetails;
import com.avnet.assetportal.webservice.usermanager.EmployeeDetails;
import com.avnet.assetportal.webservice.usermanager.EmployeeIds;
import com.avnet.assetportal.webservice.usermanager.UserManagerPortProxy;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.ws.objectManager.ObjectManagerException;

/**
 * @author Dinesh
 */
@Component
public class AMSAdminDelegate {
	private static final String CLASS_NAME = AMSAdminDelegate.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	/**
	 * @author Aditya
	 * @param webServiceAccess
	 * @return
	 * @throws AssetPortalWSException
	 *             returns all the attributes
	 */

	public JSONObject getAttributesListJson(
			AssetManagerServicePortProxy webServiceAccess) {
		final String METHOD_NAME = "getAttributesListJson";
		logger.entering(CLASS_NAME, METHOD_NAME);
		// getting values from service

		AttributesList attributesListObject = new AttributesList();

		ArrayList<String> tempAttr = new ArrayList<String>();
		ArrayList<String> tempId = new ArrayList<String>();
		List<Attribute> allAttr = null;
		try {
			allAttr = webServiceAccess.getAttributes("");
		} catch (AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW091E"),
					e.getMessage()));
			return AMSJsonUtil
					.convertToErrorJson("Problem Connecting to Service");
		}
		for (int i = 0; i < allAttr.size(); i++) {
			tempAttr.add(allAttr.get(i).getName());
			tempId.add(allAttr.get(i).getId());
		}
		String[] inputNameArray = tempAttr.toArray(new String[tempAttr.size()]);
		String[] inputIdArray = tempId.toArray(new String[tempId.size()]);
		attributesListObject.setAttributeName(inputNameArray);
		attributesListObject.setAttributeId(inputIdArray);

		JSONObject jsonObject = AMSJsonUtil
				.convertToAttributesJson(attributesListObject);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObject;

	}

	public String callServiceToUpdateUser(
			AssetManagerServicePortProxy webServiceAccess, String assetId,
			String empId) {

		String[] assetIdArray = assetId.split(",");
		List<Integer> listAssetId = new ArrayList<Integer>();

		for (int i = 0; i < assetIdArray.length; i++) {
			listAssetId.add(Integer.parseInt(assetIdArray[i]));
		}
		try {
			Acknowledgement acknowledgement = webServiceAccess.removeAsset(
					listAssetId, Integer.parseInt(empId));
			return acknowledgement.getMessage();
		} catch (Exception e) {
			return "Remove asset failed";
		}
	}

	/**
	 * 
	 * @return a list of requests from the WS to the view request UI
	 * @throws AssetPortalWSException
	 * @throws ObjectManagerException
	 */

	public JSONObject getAssetTypeListJson(
			AssetManagerServicePortProxy webServiceAccess) {
		final String METHOD_NAME = "getAssetTypeListJson";
		logger.entering(CLASS_NAME, METHOD_NAME);
		// getting values from service

		ArrayList<AssetTypeVTO> assetTypeAdminVTOList = new ArrayList<AssetTypeVTO>();

		List<AssetType> assetTypeFromWS = null;
		try {
			assetTypeFromWS = webServiceAccess.getAssetType();
		} catch (AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW091E"),
					e.getMessage()));
			return AMSJsonUtil
					.convertToErrorJson("Problem Connecting to Service");
		}
		Iterator<AssetType> iterator = assetTypeFromWS.iterator();
		AssetType assetType = null;
		while (iterator.hasNext()) {
			assetType = new AssetType();
			assetType = iterator.next();
			AssetTypeVTO asset = new AssetTypeVTO();
			asset.setAssetTypeName(assetType.getAssetTypeName());
			asset.setAssetTypeId(assetType.getAssetTypeId());

			Iterator<Attribute> iter = null;
			try {
				iter = webServiceAccess.getAttributes(
						"" + assetType.getAssetTypeId()).iterator();
			} catch (AssetPortalWSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<String> attrArray = new ArrayList<String>();
			ArrayList<String> attrArrayId = new ArrayList<String>();
			ArrayList<String> attrArrayType = new ArrayList<String>();
			while (iter.hasNext()) {
				Attribute attr = iter.next();
				attrArray.add(attr.getName());
				attrArrayId.add(attr.getId());
				attrArrayType.add(attr.getUnit());
			}
			String farray[] = new String[attrArray.size()];
			for (int j = 0; j < attrArray.size(); j++) {
				farray[j] = attrArray.get(j);
			}
			String idarray[] = new String[attrArrayId.size()];
			for (int j = 0; j < attrArrayId.size(); j++) {
				idarray[j] = attrArrayId.get(j);
			}
			String typearray[] = new String[attrArrayType.size()];
			for (int j = 0; j < attrArrayType.size(); j++) {
				typearray[j] = attrArrayType.get(j);
			}

			asset.setAttributeNameArray(AMSNameUtil
					.convertToVariableName(farray));
			asset.setAttributeLabelNameArray(AMSNameUtil
					.convertToLabelName(asset.getAttributeNameArray()));
			asset.setAttributeIdArray(idarray);
			asset.setAttributeDataTypeArray(typearray);
			assetTypeAdminVTOList.add(asset);
		}

		JSONObject jsonObject = AMSJsonUtil
				.convertToTypeJson(assetTypeAdminVTOList);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObject;

	}

	/**
	 * 
	 * @return a list of requests from the WS to the view request UI
	 * @throws AssetPortalWSException
	 * @throws ObjectManagerException
	 */

	public JSONObject getAssetTypeListWithAttributesJson(
			AssetManagerServicePortProxy webServiceAccess) {
		final String METHOD_NAME = "getAssetTypeListWithAttributesJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		ArrayList<AssetTypeVTO> assetTypeAdminVTOList = new ArrayList<AssetTypeVTO>();

		// getting Asset Types from WS
		List<AssetType> assetTypesFromWS = null;
		try {
			assetTypesFromWS = webServiceAccess.getAssetType();
		} catch (AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW091E"),
					e.getMessage()));
			return AMSJsonUtil
					.convertToErrorJson("Problem Connecting to Service");
		}

		// Adding Custom AssetType for Select All feature in Custom Report Page
		// AssetType selectAllAssetTypeObject = new AssetType();
		// selectAllAssetTypeObject.setAssetTypeId("0");
		// selectAllAssetTypeObject.setAssetTypeName("All Assets");
		// assetTypesFromWS.add(selectAllAssetTypeObject);

		// Sorting based on assetType Name
		Collections.sort(assetTypesFromWS, new Comparator<AssetType>() {
			@Override
			public int compare(AssetType obj1, AssetType obj2) {
				return obj1.getAssetTypeName().compareToIgnoreCase(
						obj2.getAssetTypeName());
			}
		});

		// Looping through AssetType object to set Attributes
		for (AssetType assetTypeLoopObject : assetTypesFromWS) {
			// setting VTO AssetType from Service AssetType
			AssetTypeVTO VTOassetTypeObject = new AssetTypeVTO();
			VTOassetTypeObject.setAssetTypeName(assetTypeLoopObject
					.getAssetTypeName());
			VTOassetTypeObject.setAssetTypeId(assetTypeLoopObject
					.getAssetTypeId());

			// getting Attribute for AssetType from WS
			List<Attribute> attributesFromWS = null;
			try {
				attributesFromWS = webServiceAccess.getAttributes(""
						+ assetTypeLoopObject.getAssetTypeId());
			} catch (AssetPortalWSException e) {
				logger.severe(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString("AMSW091E"), e.getMessage()));
				return AMSJsonUtil
						.convertToErrorJson("Problem Connecting to Service");
			}

			// ArrayList for JSON conversion
			ArrayList<String> attributeNameList = new ArrayList<String>();
			ArrayList<String> attributeIdList = new ArrayList<String>();
			ArrayList<String> attributeDataTypeList = new ArrayList<String>();

			// inner loop to loop through Attributes
			for (Attribute attributeLoopObject : attributesFromWS) {
				attributeNameList.add(attributeLoopObject.getName());
				attributeIdList.add(attributeLoopObject.getId());
				attributeDataTypeList.add(attributeLoopObject.getUnit());
			}

			// Converting ArrayList to be passed for JSON conversion
			String[] attributeNameArray = attributeNameList
					.toArray(new String[attributeNameList.size()]);
			String[] attributeIdArray = attributeIdList
					.toArray(new String[attributeIdList.size()]);
			String[] attributeDataTypeArray = attributeDataTypeList
					.toArray(new String[attributeDataTypeList.size()]);

			VTOassetTypeObject.setAttributeNameArray(AMSNameUtil
					.convertToVariableName(attributeNameArray));
			VTOassetTypeObject.setAttributeLabelNameArray(AMSNameUtil
					.convertToLabelName(attributeNameArray));
			VTOassetTypeObject.setAttributeIdArray(attributeIdArray);
			VTOassetTypeObject
					.setAttributeDataTypeArray(attributeDataTypeArray);

			// Adding generated values to VTO Object List
			assetTypeAdminVTOList.add(VTOassetTypeObject);
		}

		JSONObject jsonObject = AMSJsonUtil
				.convertToAssetTypeWithAttributesJson(assetTypeAdminVTOList);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObject;

	}

	/**
	 * 
	 * @return get values obtained from web service for View Requests page and
	 *         converting to JSON
	 * @throws DatatypeConfigurationException
	 * @throws ParseException
	 * @throws com.avnet.assetportal.webservice.request.AssetPortalWSException
	 */
	public JSONObject getViewRequestsJson(
			RequestManagerPortProxy webServiceRequestAccess, String fromDate,
			String toDate, String min, String max, String sortType,
			String sortMode) {

		final String METHOD_NAME = "getViewRequestsJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		
		XMLGregorianCalendar fromDateFormatted = null;
		XMLGregorianCalendar toDateFormatted = null;
		if (!fromDate.isEmpty()) {

			try {
				fromDateFormatted = AMSDateUtil
						.toXMLGregorianCalendar(fromDate);
			} catch (ParseException e) {
				return AMSJsonUtil.generateErrorJSON(e.getMessage());
			} catch (DatatypeConfigurationException e) {
				return AMSJsonUtil.generateErrorJSON(e.getMessage());
			}

		}
		if (!toDate.isEmpty()) {
			try {
				toDateFormatted = AMSDateUtil.toXMLGregorianCalendar(toDate);
			} catch (ParseException e) {

				return AMSJsonUtil.generateErrorJSON(e.getMessage());

			} catch (DatatypeConfigurationException e) {

				return AMSJsonUtil.generateErrorJSON(e.getMessage());
			}
		}

		ArrayList<ViewRequestsVTO> viewRequestsAddList = new ArrayList<ViewRequestsVTO>();

		GetAssetResponse getAssetResponse = null;
		try {

			getAssetResponse = webServiceRequestAccess
					.getAssetRequests(
							fromDateFormatted,
							toDateFormatted,
							Integer.parseInt(min) + 1,
							(Integer.parseInt(min)) + (Integer.parseInt(max)),
							RequestSortTypeEnum
									.valueOf(AMSAdminConstants.REQUESTS_SORT_COLUMN[(Integer
											.parseInt(sortType)) - 1]),
							SortOrderEnum.valueOf(sortMode.toUpperCase()));
		} catch (com.avnet.assetportal.webservice.request.AssetPortalWSException e1) {

			return AMSJsonUtil.generateErrorJSON(e1.getMessage());

		} catch (WebServiceException e) {

			return AMSJsonUtil.generateErrorJSON("AMS Service currently down");
		} catch (NullPointerException e) {
			return AMSJsonUtil.generateErrorJSON("Bad Request Sent");
		}

		List<EmployeeRequestDetails> recentAssetRequest = getAssetResponse
				.getEmployeeRequestDetails();
		ViewRequestCountVTO viewRequestCountVTO = new ViewRequestCountVTO();
		viewRequestCountVTO.setRecordsFiltered(getAssetResponse
				.getMaxRowCount());
		if (viewRequestCountVTO.getRecordsFiltered() > 0) {
			Iterator<EmployeeRequestDetails> iter = recentAssetRequest
					.iterator();
			EmployeeRequestDetails empReqDet = null;

			while (iter.hasNext()) {
				empReqDet = iter.next();
				ViewRequestsVTO assetReq = new ViewRequestsVTO();
				assetReq.setEmployeeID(empReqDet.getAssetDetails().getEmpId());
				assetReq.setType(empReqDet.getAssetDetails().getAssetType());
				assetReq.setName(empReqDet.getAssetDetails().getEmpname());
				assetReq.setStatus(empReqDet.getAssetDetails().getStatus());
				assetReq.setCategoryId(empReqDet.getAssetDetails()
						.getCategoryId());

				assetReq.setRequestID(empReqDet.getEmployeeDetails()
						.getRequestId());
				assetReq.setSeverity(empReqDet.getEmployeeDetails()
						.getSeverity());
				if (empReqDet.getAssetDetails().getNote() == null) {
					assetReq.setPurpose("-");
				} else {
					assetReq.setPurpose(empReqDet.getAssetDetails().getNote());
				}

				if (empReqDet.getEmployeeDetails().getAdminNote() == null) {
					assetReq.setComments("No comments available");
				} else {
					assetReq.setComments(empReqDet.getEmployeeDetails()
							.getAdminNote());
				}
				if (empReqDet.getEmployeeDetails().getDueDate() == null) {
					assetReq.setDueDate("-");
				} else {
					assetReq.setDueDate(empReqDet.getEmployeeDetails()
							.getDueDate().toString());
				}
				if (empReqDet.getEmployeeDetails().getDateOfIssue() == null) {
					assetReq.setDateOfIssue("-");
				} else {
					assetReq.setDateOfIssue(empReqDet.getEmployeeDetails()
							.getDateOfIssue().toString());
				}

				if (empReqDet.getEmployeeDetails().getDateOfApproval() == null) {
					assetReq.setDateOfApproval("-");

				} else {
					assetReq.setDateOfApproval(empReqDet.getEmployeeDetails()
							.getDateOfApproval().toString());

				}
				viewRequestsAddList.add(assetReq);
			}
		}

		viewRequestCountVTO.setViewRequestsAdminVTOList(viewRequestsAddList);

		JSONObject jsonObject = AMSJsonUtil
				.convertToRequestsJson(viewRequestCountVTO);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObject;

	}

	/**
	 * public JSONObject getEmpDetailsAutoCompleteJson() {
	 * ArrayList<EmpAutoCompleteDetails> empDetilsAdminVTOList =
	 * DummyClassGetValues .getValuesForEmpAutoCompleteList();
	 * 
	 * JSONObject jsonObject = AMSJsonUtil
	 * .convertToEmpDetailsAutoCompleteJson(empDetilsAdminVTOList);
	 * 
	 * return jsonObject;
	 * 
	 * }
	 **/

	/**
	 * get values from WS and populate the Edit Asset VTO
	 * 
	 * @param asset
	 *            ID from web page
	 * @return the Values to controller for Edit Asset UI page
	 * @throws ObjectManagerException
	 */
	public EditAssetVTO getEditAssetValuesFromService(
			AssetManagerServicePortProxy webServiceAccess, String assetId) {
		final String METHOD_NAME = "getEditAssetValuesFromService";
		logger.entering(CLASS_NAME, METHOD_NAME);

		EditAssetVTO editAssetAdminVTOObject = new EditAssetVTO();
		Asset asset = null;
		try {
			// Calling WS to get Asset Details
			asset = webServiceAccess.getAssetDetails(assetId);
		} catch (AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW081E"),
					e.getMessage()));
			return null;
		}
		// Setting values for VTO Object
		// setting asset ID and Identity
		editAssetAdminVTOObject.setAssetIdentity(asset.getAssetIdentity());
		editAssetAdminVTOObject.setAssetId(asset.getAssetId());
		// setting asset name
		editAssetAdminVTOObject.setAssetType(asset.getAssetType()
				.getAssetTypeName());
		// setting owner name
		editAssetAdminVTOObject.setOwnedBy(asset.getOwner().getEmpFirstName()
				+ " " + asset.getOwner().getEmpLastName());

		// setting ifCarryOut available
		if (asset.getAssetType().getInHouseFlag().equalsIgnoreCase("y")) {
			editAssetAdminVTOObject.setCarry(true);
		}
		// setting if available for use
		if (asset.getAvailableForUse().equalsIgnoreCase("y")) {
			editAssetAdminVTOObject.setAvailable(true);
		}
		// setting date of capitalisation
		editAssetAdminVTOObject.setDateOfCapitalisation(AMSDateUtil
				.convertDateToString(AMSDateUtil.toDate(asset
						.getCapitalisationDate())));
		// getting attribute list from service
		List<Attribute> attributeList = asset.getAttributeList();

		// creating attribute list for VTO
		List<AttributeType> attributeTypeList = new ArrayList<AttributeType>();

		// looping through attribute list from service
		// assigning it to VTO object attribute list
		for (Attribute attributeObject : attributeList) {

			AttributeType attributeTypeObject = new AttributeType();
			// setting attribute Id
			attributeTypeObject.setId(attributeObject.getId());
			// Setting field name to be used in VTO
			attributeTypeObject.setFieldName(AMSNameUtil
					.convertToVariableName(attributeObject.getName()));
			// setting label name to be used in VTO
			attributeTypeObject.setLabelName(AMSNameUtil
					.convertToLabelName(attributeObject.getName()));
			attributeTypeObject.setValue(attributeObject.getValue());
			attributeTypeObject.setUnit(attributeObject.getUnit());
			attributeTypeList.add(attributeTypeObject);
		}
		editAssetAdminVTOObject.setAttributeTypeList(attributeTypeList);

		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW081D"),
				editAssetAdminVTOObject.toString()));
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return editAssetAdminVTOObject;
	}

	/**
	 * Update Asset using service
	 * 
	 * @param asset
	 *            ID from web page
	 * @return the Values to controller for Edit Asset UI page
	 * @throws ObjectManagerException
	 */
	public String callServiceToEditAsset(
			AssetManagerServicePortProxy webServiceAccess,
			EditAssetVTO assetObject) {
		final String METHOD_NAME = "callServiceToEditAsset";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW082D"),
				assetObject.toString()));

		// create asset object to be passed as argument to WS
		Asset asset = new Asset();
		Acknowledgement acknowledgement = new Acknowledgement();
		// AssetType Object to set AssetType Name and ID
		asset.setAssetId(assetObject.getAssetId());
		AssetType assetType = new AssetType();
		AssetTypeAdapter assetAdaptor = new AssetTypeAdapter();
		if (assetObject.getAssetType() != null) {
			assetType.setAssetTypeId(assetAdaptor
					.getAssetTypeIdFromName(assetObject.getAssetType()));
		} else {
			acknowledgement.setMessage("Update Failed");
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return acknowledgement.getMessage();
		}
		// setting AssetType to Asset Object
		asset.setAssetType(assetType);
		// Getting Attribute List type reference from Asset Object
		List<Attribute> attributeList = asset.getAttributeList();

		// getting Attributes name and value from VTO
		List<AttributeType> attributeTypeList = assetObject
				.getAttributeTypeList();

		// Getting Id, Name, Value from Attributes
		for (AttributeType attributeTypeLoopObject : attributeTypeList) {
			Attribute a = new Attribute();
			// setting Id and Name
			a.setId(attributeTypeLoopObject.getId());
			a.setName(attributeTypeLoopObject.getFieldName());
			a.setValue(attributeTypeLoopObject.getValue());
			attributeList.add(a);

		}
		try {
			asset.setCapitalisationDate(AMSDateUtil
					.toXMLGregorianCalendar(assetObject
							.getDateOfCapitalisation()));
		} catch (ParseException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083E"),
					e.getMessage()));
		} catch (DatatypeConfigurationException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083E"),
					e.getMessage()));
		}

		// checking 'asset availability' boolean in VTO available and setting
		// asset object
		// mandatory for WS
		if (assetObject.isAvailable()) {
			asset.setAvailableForUse("Y");
		} else {
			asset.setAvailableForUse("N");
		}
		// checking if 'allowed to carry' boolean in VTO available and setting
		// asset object
		if (assetObject.isCarry()) {
			asset.setInHouseFlag("Y");
		} else {
			asset.setInHouseFlag("N");
		}

		// Creating Employee Object to set Owner Id
		Employee employee = new Employee();
		// Setting owner Id
		employee.setEmpId(assetObject.getOwnerId());
		// Setting owner in asset
		asset.setOwner(employee);

		try {
			acknowledgement = webServiceAccess.updateAsset(asset);
		} catch (AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW084E"),
					e.getMessage()));
			acknowledgement.setMessage("Update Failed");
		}

		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW081ACK"),
				acknowledgement.toString()));
		logger.exiting(CLASS_NAME, METHOD_NAME);

		return acknowledgement.getMessage();
	}

	/**
	 * @param resolveObject
	 * @param webServiceRequestAccess
	 * @return
	 */
	public String sendResolveActionService(ResolveVTO resolveObject,
			RequestManagerPortProxy webServiceRequestAccess) {
		String result = null;
		final String METHOD_NAME = "sendResolveActionService";
		logger.entering(CLASS_NAME, METHOD_NAME);

		try {
			UpdateRequestDetails updateRequestDetailsObject = new UpdateRequestDetails();
			String requestId = resolveObject.getRequestId();
			Integer reqId = 0;
			if (!requestId.equals(null)) {
				reqId = Integer.parseInt(requestId);
			}
			updateRequestDetailsObject.setOwnerId((Integer
					.parseInt(resolveObject.getOwnerId())));
			updateRequestDetailsObject.setRequestId(reqId);
			String resolveStatus = resolveObject.getAdminStatus();

			Acknowledgement ask1 = webServiceRequestAccess.updateAdminNotes(
					reqId, resolveObject.getComments());

			RequestOperationEnum requestEnum;
			if (resolveStatus.equals("resolved")) {
				updateRequestDetailsObject.setAssetId((Integer
						.parseInt(resolveObject.getAssetIdOriginal())));
				updateRequestDetailsObject
						.setStatusId(AMSAdminConstants.ASSIGNMENT_STATUS.ISSUED
								.getOrd());
				requestEnum = RequestOperationEnum.ADMIN_APPROVED;
			} else if (resolveStatus.equals("onHold")) {
				updateRequestDetailsObject
						.setStatusId(AMSAdminConstants.ASSIGNMENT_STATUS.ADMIN_HOLD
								.getOrd());
				requestEnum = RequestOperationEnum.ADMIN_HOLD;
			} else {

				updateRequestDetailsObject
						.setStatusId(AMSAdminConstants.ASSIGNMENT_STATUS.ADMIN_REJECTED
								.getOrd());
				requestEnum = RequestOperationEnum.ADMIN_REJECTED;
			}
			Acknowledgement ask = webServiceRequestAccess
					.updateEmployeeRequest(updateRequestDetailsObject,
							requestEnum);
			String acknowledge = ask.getMessage();
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1206D"),
					acknowledge));
			result = acknowledge.concat(ask1.getMessage());
			logger.exiting(CLASS_NAME, METHOD_NAME);

		} catch (com.avnet.assetportal.webservice.request.AssetPortalWSException e) {
			e.printStackTrace();
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1202E"),
					e.getMessage()));
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return result;
	}

	/**
	 * @param webServiceAccess
	 * @param searchString
	 * @param searchIdType
	 * @return
	 */
	public JSONObject getAvailableAssetValuesFromService(
			AssetManagerServicePortProxy webServiceAccess, String searchString,
			String searchIdType, String categoryId) {
		final String METHOD_NAME = "getAvailableAssetValuesFromService";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1201D"),
				webServiceAccess, searchString, searchIdType));
		AvailableAssetVTO availableAssetAdminVTOObject = new AvailableAssetVTO();
		AssetList assetListBean = null;
		SearchTypeEnum searchType;
		try {
			if (searchIdType.equals("1")) {
				searchType = SearchTypeEnum.ASSET_SHORTTEXT;
			} else {
				searchString = searchString.toUpperCase();
				searchType = SearchTypeEnum.ASSET_IDENTITY;
			}
			assetListBean = webServiceAccess.getAssets(SortOrderEnum.ASC,
					SortColumnEnum.ASSET_TYPE, 0, 10, searchType, searchString,
					categoryId);

			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1207D"),
					assetListBean));
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1202D"),
					availableAssetAdminVTOObject));
		} catch (AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1201E"),
					"Invalid User"));
		}

		if (assetListBean == null) {
			JSONObject jsonObject = AMSJsonUtil
					.availableAssetErrorDisplayJson(availableAssetAdminVTOObject);
			return jsonObject;
		}

		List<Asset> assetList = assetListBean.getAssetList();
		ArrayList<String> assetId = new ArrayList<String>();
		ArrayList<String> assetType = new ArrayList<String>();
		ArrayList<String> assetIdOriginal = new ArrayList<String>();
		for (Asset assetlist : assetList) {
			List<Attribute> attrList = assetlist.getAttributeList();
			for (Attribute attr : attrList) {
				assetType.add(attr.getValue());
				assetId.add(assetlist.getAssetIdentity());
				assetIdOriginal.add(assetlist.getAssetId());
			}
		}
		availableAssetAdminVTOObject.setAssetId(assetId);
		availableAssetAdminVTOObject.setAssetType(assetType);
		availableAssetAdminVTOObject.setAssetIdOriginal(assetIdOriginal);
		logger.exiting(CLASS_NAME, METHOD_NAME);

		JSONObject jsonObject = AMSJsonUtil
				.convertToAvailableAssetsJson(availableAssetAdminVTOObject);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1203D"),
				jsonObject));
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObject;
	}

	public int stringToInteger(String var) {
		final String METHOD_NAME = "stringToInteger";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return Integer.parseInt(var);
	}

	public JSONObject getOwnedByUser(
			UserManagerPortProxy webServiceAccessApprover) {
		// webServiceAccessApprover.getAllEmployees();
		final String METHOD_NAME = "getOwnedByUser";
		logger.entering(CLASS_NAME, METHOD_NAME);

		List<EmployeeIds> empIds = null;
		try {
			empIds = webServiceAccessApprover.getEmployeeIds("");
		} catch (com.avnet.assetportal.webservice.usermanager.AssetPortalWSException e) {
			// FIXME
		}
		Iterator<EmployeeIds> iter = empIds.iterator();
		List<EmployeeDetailsOwnedby> empList = new ArrayList<EmployeeDetailsOwnedby>();

		while (iter.hasNext()) {
			EmployeeIds iterationObject = iter.next();
			EmployeeDetailsOwnedby emp = new EmployeeDetailsOwnedby(
					iterationObject.getEmpId(), iterationObject.getName());
			empList.add(emp);
		}

		JSONObject jsonObject = AMSJsonUtil.convertToEmployeeNameJson(empList);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObject;
	}

	public JSONObject getAssetsOfEmployee(
			AssetManagerServicePortProxy webServiceAccess, String searchType,
			String searchString, String start, String length, String column,
			String dir) {

		final String METHOD_NAME = "getAssetsOfEmployee";
		logger.entering(CLASS_NAME, METHOD_NAME);
		start = Integer.toString((Integer.parseInt(start)) + 1);
		length = Integer.toString((Integer.parseInt(start))
				+ (Integer.parseInt(length)) - 1);
		dir = dir.toUpperCase();

		column = AMSAdminConstants.ASSETS_EMPLOYEE[(Integer.parseInt(column)) - 1];

		SearchTypeEnum searchTypeEnum = null;
		SortOrderEnum sortOrderEnum = null;
		SortColumnEnum sortColumnEnum = null;
		AssetList assets;
		try {
			assets = webServiceAccess.getAssets(sortOrderEnum.valueOf(dir),
					sortColumnEnum.valueOf(column), stringToInteger(start),
					stringToInteger(length),
					searchTypeEnum.valueOf(searchType), searchString, null);
		} catch (AssetPortalWSException e) {
			return AMSJsonUtil.generateErrorJSON(e.getMessage());

		} catch (WebServiceException e) {
			return AMSJsonUtil.generateErrorJSON("AMS Service currently down");
		} catch (NullPointerException e) {
			return AMSJsonUtil.generateErrorJSON("Bad Request Sent");
		}

		ViewAssetsCountVTO viewAssetsCountVTO = new ViewAssetsCountVTO();
		ArrayList<ViewAssetsAdminVTO> viewAssetsAdminTempList = new ArrayList<ViewAssetsAdminVTO>();
		viewAssetsCountVTO.setMaxCount(assets.getMaxCount());
		if (viewAssetsCountVTO.getMaxCount() > 0) {
			Iterator<Asset> assetIterator = assets.getAssetList().iterator();
			Asset asset = new Asset();
			while (assetIterator.hasNext()) {
				asset = assetIterator.next();
				ViewAssetsAdminVTO assetEmployee = new ViewAssetsAdminVTO();
				assetEmployee.setAssetId(asset.getAssetId());
				assetEmployee.setAssetIdentity(asset.getAssetIdentity());
				assetEmployee.setAssetType(asset.getAssetType()
						.getAssetTypeName().toString());
				try {
					assetEmployee.setIssuedDate(asset.getIssuedDate()
							.toString().split("T")[0]);
				} catch (Exception e) {
					assetEmployee.setIssuedDate("NA");
				}
				viewAssetsAdminTempList.add(assetEmployee);

			}

		}
		viewAssetsCountVTO.setViewAssetsAdminVTOList(viewAssetsAdminTempList);

		JSONObject jsonObject = AMSJsonUtil
				.convertToAssetsSearchJson(viewAssetsCountVTO);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObject;

	}

	/**
	 * submits the the Add Asset form to the webservice. Calls addAsset() for
	 * adding the asset. Passes Asset as parameter.
	 */

	public JSONObject setAddAsset(AddAssetVTO addAssetVTO,
			AssetManagerServicePortProxy webServiceAccess) {

		final String METHOD_NAME = "setAddAsset";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1061D"),
				addAssetVTO.toString()));
		Asset asset = new Asset();

		List<AttributeType> attrListInVTO = addAssetVTO.getAttributeTypeList();

		List<Attribute> attributeForService = asset.getAttributeList();
		Iterator<AttributeType> iterator = attrListInVTO.iterator();
		while (iterator.hasNext()) {
			Attribute attrServiceObject = new Attribute();
			AttributeType attrVTO = iterator.next();
			attrServiceObject.setId(attrVTO.getId());
			attrServiceObject.setValue(attrVTO.getValue());
			attributeForService.add(attrServiceObject);
		}

		AssetType assetType = new AssetType();
		assetType.setAssetTypeId(addAssetVTO.getAssetType());

		asset.setAssetType(assetType);
		asset.setAssetStatus(AssetStatusEnum.UNASSIGNED);

		try {
			asset.setProcuredDate(AMSDateUtil
					.toXMLGregorianCalendar(addAssetVTO.getProcuredDate()));
		} catch (ParseException e1) {
			return AcknowledgementJsonUtil
					.getJsonForAckAddAsset("dateFormatError");
		} catch (DatatypeConfigurationException e1) {
			return AcknowledgementJsonUtil
					.getJsonForAckAddAsset("dateTypeError");
		}
		asset.setCreatedBy("Support Team");
		if (addAssetVTO.getAvailable().equals("yes")) {
			asset.setAvailableForUse("Y");
		} else {
			asset.setAvailableForUse("N");
		}

		Employee employee = new Employee();
		employee.setEmpId(AMSAdminConstants.OWNEDBY);
		asset.setOwner(employee);

		Acknowledgement ack = null;
		try {
			ack = webServiceAccess.addAsset(asset);
		} catch (AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1061E"),
					e.getMessage()));
			return AcknowledgementJsonUtil
					.getJsonForAckAddAsset("addAssetError");
		}
		String returnString = ack.getMessage() + "|" + ack.getStatus();
		JSONObject ackJson = AcknowledgementJsonUtil.getJsonForAckAddAsset(ack
				.getMessage());
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1061ACK"),
				returnString));

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return ackJson;
	}

	/**
	 * returns the list of all attributes
	 * 
	 * @param webServiceAccess
	 * @return
	 * @throws AssetPortalWSException
	 */

	public HashMap<String, String> getAllAttributes(
			AssetManagerServicePortProxy webServiceAccess)
			throws AssetPortalWSException {
		// gets the HashMap of all the attributes from service
		HashMap<String, String> tempAttr = new HashMap<String, String>();
		List<Attribute> allAttr = null;

		allAttr = webServiceAccess.getAttributes("");

		for (int i = 0; i < allAttr.size(); i++) {
			String key = allAttr.get(i).getName();
			String value = allAttr.get(i).getId();
			tempAttr.put(key.replaceAll("\\s+", ""), value);
		}
		//
		return tempAttr;

	}

	/**
	 * populates the attribute list that needs to be set to asset type object
	 * 
	 * @param webServiceAccess
	 */
	public void populateAttrList(AssetManagerServicePortProxy webServiceAccess,
			HashMap<String, String> varMap, HashMap<String, String> tempAttr,
			AssetType assetType) {
		List<Attribute> attrList = assetType.getAttributeList();
		// populating attribute list to be passed as parameter for asset
		// type obj.
		for (Entry<String, String> varMapLoopObj : varMap.entrySet()) {
			Attribute attr = new Attribute();
			for (Entry<String, String> tempAttrLoopObj : tempAttr.entrySet()) {
				if (tempAttrLoopObj.getKey().toLowerCase()
						.equals(varMapLoopObj.getKey().toLowerCase())) {
					attr.setId(tempAttrLoopObj.getValue());
					attrList.add(attr);
				}
			}
		}
	}

	/**
	 * populates "AssetType" object to be given to the web service
	 * 
	 * @param assetType
	 * @param webServiceAccess
	 * @param manageAssetVTO
	 */

	public void populateAssetTypeObjForEdit(AssetType assetType,
			AssetManagerServicePortProxy webServiceAccess,
			com.avnet.ams.vto.ManageAssetVTO manageAssetVTO) {
		// setting all needed parameters in asset type obj
		List<AssetType> allAssets = null;
		try {
			allAssets = webServiceAccess.getAssetType();
		} catch (AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1002E"),
					e.getMessage()));
		}
		for (int i = 0; i < allAssets.size(); i++) {
			if (allAssets
					.get(i)
					.getAssetTypeId()
					.toLowerCase()
					.replaceAll("\\s+", AMSAdminConstants.BLANK_STRING)
					.equals(manageAssetVTO.getDropDown()
							.replaceAll("\\s+", AMSAdminConstants.BLANK_STRING)
							.toLowerCase())) {
				assetType.setAssetTypeId(allAssets.get(i).getAssetTypeId());
				assetType.setAssetTypeName(allAssets.get(i).getAssetTypeName());
				assetType.setLifeTime(manageAssetVTO.getLifeTime());
				assetType.setAssetTypePrefix(AMSAdminConstants.BLANK_STRING);
				String firstLetter = Character.toString(manageAssetVTO
						.getCarry().charAt(0));
				assetType.setInHouseFlag(firstLetter);
				break;
			}
		}
		//

		logger.fine(MessageFormat.format(LoggerConstants.APP_CONSTANTS_BUNDLE
				.getString("AMSW1002D"), assetType.getAssetTypeId(), assetType
				.getAssetTypeName(), assetType.getAssetTypePrefix(), assetType
				.getAttributeList().size(), assetType.getInHouseFlag(),
				assetType.getLifeTime()));
	}

	/**
	 * populates "AssetType" object to be given to the web service
	 * 
	 * @param assetType
	 * @param manageAssetVTO
	 */
	void populateAssetTypeObjForAdd(AssetType assetType,
			com.avnet.ams.vto.ManageAssetVTO manageAssetVTO) {
		// setting values for asset type obj
		assetType.setAssetTypeName(manageAssetVTO.getTypeName());
		assetType.setLifeTime(manageAssetVTO.getLifeTime());
		assetType.setAssetTypePrefix(manageAssetVTO.getPrefix());
		String firstLetter = Character.toString(manageAssetVTO.getCarry()
				.charAt(0));
		assetType.setInHouseFlag(firstLetter);
		//

		logger.fine(MessageFormat.format(LoggerConstants.APP_CONSTANTS_BUNDLE
				.getString("AMSW1003D"), assetType.getAssetTypeId(), assetType
				.getAssetTypeName(), assetType.getAssetTypePrefix(), assetType
				.getAttributeList().size(), assetType.getInHouseFlag(),
				assetType.getLifeTime()));

	}

	/**
	 * submits the the Manage Asset form to the webservice. Calls
	 * updateAssetType() for edit radio and addAssetType() for add radio. Passes
	 * AssetType as parameter.
	 */

	public JSONObject manageAsset(
			com.avnet.ams.vto.ManageAssetVTO manageAssetVTO,
			AssetManagerServicePortProxy webServiceAccess) {
		final String METHOD_NAME = "manageAsset";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1001D"),
				manageAssetVTO.toString()));

		HashMap<String, String> tempAttr;
		try {
			tempAttr = getAllAttributes(webServiceAccess);
		} catch (AssetPortalWSException e1) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1001E"),
					e1.getMessage()));
			return AcknowledgementJsonUtil
					.getJsonForAck("getAttributesException");

		}
		HashMap<String, String> varMap = manageAssetVTO
				.getAllCheckedAttributes();
		String edit = varMap.get("method");
		// updating asset type
		if (edit.equals("edit")) {
			AssetType assetType = new AssetType();
			populateAttrList(webServiceAccess, varMap, tempAttr, assetType);
			populateAssetTypeObjForEdit(assetType, webServiceAccess,
					manageAssetVTO);
			Acknowledgement ack = null;
			try {
				ack = webServiceAccess.updateAssetType(assetType);
			} catch (AssetPortalWSException e) {
				logger.severe(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString("AMSW1003E"), e.getMessage()));
				return AcknowledgementJsonUtil
						.getJsonForAck("updateAssetTypeError");
			}
			String returnString = ack.getMessage() + "|" + ack.getStatus();
			JSONObject ackJson = AcknowledgementJsonUtil.getJsonForAck(ack
					.getMessage());
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString("AMSW1001ACK"), returnString));

			logger.exiting(CLASS_NAME, METHOD_NAME);
			return ackJson;
		}

		// for adding asset type
		if (edit.equals("add")) {
			AssetType assetType = new AssetType();
			populateAttrList(webServiceAccess, varMap, tempAttr, assetType);
			populateAssetTypeObjForAdd(assetType, manageAssetVTO);
			Acknowledgement ack = null;
			try {
				ack = webServiceAccess.addAssetType(assetType);
			} catch (AssetPortalWSException e) {
				logger.severe(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString("AMSW1004E"), e.getMessage()));
				return AcknowledgementJsonUtil
						.getJsonForAck("addAssetTypeError");
			}
			String returnString = ack.getMessage() + "|" + ack.getStatus();
			JSONObject ackJson = AcknowledgementJsonUtil.getJsonForAck(ack
					.getMessage());
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE
							.getString("AMSW1001ACK"), returnString));

			logger.exiting(CLASS_NAME, METHOD_NAME);

			return ackJson;
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return AcknowledgementJsonUtil
				.getJsonForAck("Something went wrong. Plese try later");
	}

	public AssetTypeList getTypes(AssetManagerServicePortProxy webServiceAccess)
			throws Exception {
		List<AssetType> type1 = webServiceAccess.getAssetType();
		AssetTypeList typeList = new AssetTypeList();
		ArrayList<String> type = new ArrayList<String>();
		for (int i = 0; i < type1.size(); i++) {
			type.add(type1.get(i).getAssetTypeName());
		}
		typeList.setAssetType(type);
		return typeList;

	}

	public JSONObject getEmployeeDetails(
			UserManagerPortProxy webServiceAccessApprover, String search) {

		ArrayList<EmployeeDetailsClientVTO> employeeDetailsTempList = new ArrayList<EmployeeDetailsClientVTO>();

		List<EmployeeDetails> employeeDetailsFromServer = null;
		try {
			employeeDetailsFromServer = webServiceAccessApprover
					.getEmployeeDetails(search);
		} catch (com.avnet.assetportal.webservice.usermanager.AssetPortalWSException e1) {
			return AMSJsonUtil.generateErrorJSON(e1.getMessage());

		} catch (WebServiceException e) {
			return AMSJsonUtil.generateErrorJSON("AMS Service currently down");
		}

		EmployeeDetailsListVTO employeeDetailsListVTO = new EmployeeDetailsListVTO();

		Iterator<EmployeeDetails> iter = employeeDetailsFromServer.iterator();
		EmployeeDetails employeeDetails = new EmployeeDetails();

		while (iter.hasNext()) {
			employeeDetails = iter.next();
			EmployeeDetailsClientVTO employeeDetailsClientVTO = new EmployeeDetailsClientVTO();
			try {
				employeeDetailsClientVTO.setEmpId(employeeDetails.getEmpId());
			} catch (Exception e) {
				employeeDetailsClientVTO.setEmpId("--");
			}
			try {
				employeeDetailsClientVTO.setName(employeeDetails.getName());
			} catch (Exception e) {
				employeeDetailsClientVTO.setName("--");
			}
			try {
				employeeDetailsClientVTO.setBusinessUnit(employeeDetails
						.getBusinessUnit());
			} catch (Exception e) {
				employeeDetailsClientVTO.setBusinessUnit("--");
			}
			try {
				employeeDetailsClientVTO.setDesignation(employeeDetails
						.getDesignation());
			} catch (Exception e) {
				employeeDetailsClientVTO.setDesignation("--");
			}
			employeeDetailsTempList.add(employeeDetailsClientVTO);

		}
		employeeDetailsListVTO
				.setEmployeeDetailsListVTO(employeeDetailsTempList);

		// //
		JSONObject jsonObject = AMSJsonUtil
				.convertToEmployeeDetailsJson(employeeDetailsListVTO);

		return jsonObject;
	}

	public JSONObject getNotificationTicker(
			RequestManagerPortProxy webServiceRequestAccess) {
		List<RecentActivityDetails> notificationList = null;
		JSONObject notificationJSON = null, wrapperJSON = null;
		JSONArray notificationArray = null;

		try {
			notificationList = webServiceRequestAccess.getRecentActivity();
			notificationArray = new JSONArray();
			for (RecentActivityDetails recentActivityObject : notificationList) {
				notificationJSON = new JSONObject();
				notificationJSON.put("AssetIdentity",
						recentActivityObject.getAssetIdentity());
				notificationJSON.put("OwnedBy",
						recentActivityObject.getOwnedBy());
				notificationJSON.put("dueDate", AMSDateUtil.getDate(AMSDateUtil
						.toDate(recentActivityObject.getDueDate())));
				notificationArray.add(notificationJSON);
			}
			wrapperJSON = new JSONObject();
			wrapperJSON.put("notification", notificationArray);
			return wrapperJSON;

		} catch (com.avnet.assetportal.webservice.request.AssetPortalWSException e) {
			JSONArray errorJSONArray = new JSONArray();
			JSONObject errorwrapperJSON = new JSONObject();
			errorwrapperJSON.put("notification", errorJSONArray);
			e.printStackTrace();
			return errorwrapperJSON;
		} catch (Exception e) {
			JSONArray errorJSONArray = new JSONArray();
			JSONObject errorwrapperJSON = new JSONObject();
			errorwrapperJSON.put("notification", errorJSONArray);
			e.printStackTrace();
			return errorwrapperJSON;
		}

	}

	/**
	 * Creates a JSON for getting the report ID from the Report Name. Calls
	 * convertToReportListJson() for building the JSON and returns JSON errors
	 * if any problem occurs
	 */

	public JSONObject getReportTypeJson(ReportManagerPortProxy webServiceAccess) {
		final String METHOD_NAME = "getReportTypeJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		// getting values from service
		List<ViewReportVTO> reportArrayList = null;
		List<ReportType> allReports = null;
		try {
			allReports = webServiceAccess.getReportType();
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW084E"),
					e.getMessage()));
		}

		// Sorting based on Report Name
		Collections.sort(allReports, new Comparator<ReportType>() {
			@Override
			public int compare(ReportType obj1, ReportType obj2) {
				return obj1.getReportName().compareToIgnoreCase(
						obj2.getReportName());
			}
		});

		Iterator<ReportType> iter = allReports.iterator();
		ReportType reportType = null;
		ViewReportVTO viewReport = null;
		reportArrayList = new ArrayList<ViewReportVTO>();
		while (iter.hasNext()) {
			reportType = iter.next();
			viewReport = new ViewReportVTO();
			viewReport.setReportId(reportType.getReportId());
			viewReport.setReportName(reportType.getReportName());
			reportArrayList.add(viewReport);

		}

		JSONObject jsonObject = AMSJsonUtil
				.convertToReportListJson(reportArrayList);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObject;

	}

	/**
	 * Converts the report to the correct JSON format. Calls
	 * convertToReportDetails() for building the JSON and returns JSON errors if
	 * any problem occurs
	 */
	public JSONObject setReport(String reportType,
			ReportManagerPortProxy webServiceAccessReport) {
		final String METHOD_NAME = "setReport";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1062D"),
				reportType));

		int reportId = 0;
		List<ReportType> reportList = null;
		try {
			reportList = webServiceAccessReport.getReportType();
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1062E"),
					e.getMessage()));
		}

		Iterator<ReportType> reportListIterator = reportList.iterator();
		ReportType dummyReportType;
		while (reportListIterator.hasNext()) {
			dummyReportType = reportListIterator.next();
			if (dummyReportType.getReportName().equals(reportType)) {
				reportId = dummyReportType.getReportId();
			}
		}

		ReportResults reportResults = null;
		try {
			reportResults = webServiceAccessReport.generateReport(0, 0,
					reportId);
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1063E"),
					e.getMessage()));
			return AMSJsonUtil
					.convertToErrorJson("Data not available for the selected Report Type");
		}
		if (reportResults == null) {
			// return error JSON
			return AMSJsonUtil
					.convertToErrorJson("Data not available for the selected Report Type");
		}

		List<ReportResultsList> reportResultsList = reportResults
				.getReportResults();

		JSONObject jsonObjectWrapper = AMSJsonUtil
				.convertToReportDetails(reportResultsList);

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;
	}

	/**
	 * delegate method for getting assets for assets page
	 * 
	 * @param webServiceAccess
	 * @param searchType
	 * @param searchString
	 * @param start
	 * @param length
	 * @param column
	 * @param dir
	 * @return
	 */
	public JSONObject getAssets(AssetManagerServicePortProxy webServiceAccess,
			String searchType, String searchString, String start,
			String length, String column, String dir) {
		final String METHOD_NAME = "getAssets";
		logger.entering(CLASS_NAME, METHOD_NAME);

		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW141D"),
				searchType, searchString, start, length, column));

		ViewAssetsCountVTO viewAssetsAdminCountVTOObject = new ViewAssetsCountVTO();

		String min = "";
		String max = "";
		if (start != null && length != null && column != null && dir != null) {
			min = Integer.toString((Integer.parseInt(start)) + 1);
			max = Integer.toString((Integer.parseInt(start))
					+ (Integer.parseInt(length)));

			try {
				searchString = searchString.toUpperCase();
				AssetList assets = webServiceAccess.getAssets(
						AMSAdminConstants.getOrderEnum(dir),
						AMSAdminConstants.getSortColumn(column),
						stringToInteger(min), stringToInteger(max),
						AMSAdminConstants.getSearchType(searchType),
						searchString, null);
				viewAssetsAdminCountVTOObject.setMaxCount(assets.getMaxCount());

				if (viewAssetsAdminCountVTOObject.getMaxCount() > 0) {
					List<Asset> assetsList = assets.getAssetList();
					ViewAssetsAdminVTO[] viewAssetsAdminVTOObject = new ViewAssetsAdminVTO[assetsList
							.size()];
					ArrayList<ViewAssetsAdminVTO> viewAssetsTempList = new ArrayList<ViewAssetsAdminVTO>();
					for (int i = 0; i < viewAssetsAdminVTOObject.length; i++) {
						viewAssetsAdminVTOObject[i] = new ViewAssetsAdminVTO();
						viewAssetsAdminVTOObject[i].setAssetId(assetsList
								.get(i).getAssetId());
						viewAssetsAdminVTOObject[i].setAssetIdentity(assetsList
								.get(i).getAssetIdentity());
						viewAssetsAdminVTOObject[i].setAssetType(assetsList
								.get(i).getAssetType().getAssetTypeName());
						viewAssetsAdminVTOObject[i].setOwner(assetsList.get(i)
								.getOwner().getEmpFirstName());
						viewAssetsAdminVTOObject[i]
								.setPurchasedDate(AMSDateUtil
										.getDate(AMSDateUtil.toDate(assetsList
												.get(i).getProcuredDate())));
						viewAssetsTempList.add(viewAssetsAdminVTOObject[i]);
					}

					viewAssetsAdminCountVTOObject
							.setViewAssetsAdminVTOList(viewAssetsTempList);
				}

			} catch (WebServiceException e1) {
				JSONObject jsonErrorObject = AMSJsonUtil.assetErrorJson();
				logger.severe(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString("AMSW144E"), e1.getMessage()));
				return jsonErrorObject;
			} catch (AssetPortalWSException e) {
				logger.severe(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString("AMSW141E"), e.getMessage()));
				JSONObject jsonErrorObject = AMSJsonUtil
						.convertToAssetsSearchErrorJson();
				return jsonErrorObject;
			}

			JSONObject jsonObject = AMSJsonUtil
					.convertToAssetsSearchJson(viewAssetsAdminCountVTOObject);
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW143D"),
					jsonObject));
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return jsonObject;

		} else {
			JSONObject errorjsonObject = AMSJsonUtil.assetErrorJson();
			return errorjsonObject;
		}
	}

	/**
	 * Delegate method to perform auto complete on assets types for assets page
	 * 
	 * @param webServiceAccess
	 * @return
	 */
	public JSONObject getAssetTypesList(
			AssetManagerServicePortProxy webServiceAccess) {
		final String METHOD_NAME = "getTypes";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject assetTypesWrapperJSON = new JSONObject();
		JSONArray assetTypesJSONArray = new JSONArray();
		JSONObject assetTypeJsonObj;
		try {
			List<AssetType> assetTypes = webServiceAccess.getAssetType();

			for (AssetType assetTypeObj : assetTypes) {

				assetTypeJsonObj = new JSONObject();
				assetTypeJsonObj.put(AMSAdminConstants.ASSETID,
						assetTypeObj.getAssetTypeId());
				assetTypeJsonObj.put(AMSAdminConstants.ASSET_TYPE,
						assetTypeObj.getAssetTypeName());
				assetTypesJSONArray.add(assetTypeJsonObj);
			}
			assetTypesWrapperJSON.put("data", assetTypesJSONArray);
		} catch (WebServiceException e1) {
			JSONObject jsonErrorObject = AMSJsonUtil.assetTypesError();
			return jsonErrorObject;
		} catch (AssetPortalWSException e) {
			assetTypeJsonObj = new JSONObject();
			assetTypeJsonObj.put(AMSAdminConstants.ASSETID,
					"sorry no suggestion available");
			assetTypeJsonObj.put(AMSAdminConstants.ASSET_TYPE,
					"try reloading the page");
			assetTypesJSONArray.add(assetTypeJsonObj);
			assetTypesWrapperJSON.put("data", assetTypesJSONArray);
			return assetTypesWrapperJSON;
		}
		return assetTypesWrapperJSON;
	}

	/**
	 * delegate method for auto complete employee name/id in assets history page
	 * 
	 * @param webServiceAccessApprover
	 * @return
	 */
	public JSONObject getUsersList(UserManagerPortProxy webServiceAccessApprover) {

		final String METHOD_NAME = "getUsersList";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject employeeWrapperJSON = new JSONObject();
		JSONArray employeeJSONArray = new JSONArray();
		JSONObject employeeJsonObj;
		try {
			List<EmployeeIds> employeeIds = webServiceAccessApprover
					.getEmployeeIds("");

			for (EmployeeIds employeeIdsObj : employeeIds) {

				employeeJsonObj = new JSONObject();
				employeeJsonObj.put(AMSAdminConstants.EMPLOYEEID,
						employeeIdsObj.getEmpId());
				employeeJsonObj.put(AMSAdminConstants.EMPLOYEENAME,
						employeeIdsObj.getName());
				employeeJSONArray.add(employeeJsonObj);
			}
			employeeWrapperJSON.put("data", employeeJSONArray);

		} catch (WebServiceException e1) {
			JSONObject jsonErrorObject = AMSJsonUtil.usersListError();
			return jsonErrorObject;
		} catch (com.avnet.assetportal.webservice.usermanager.AssetPortalWSException e) {
			employeeJsonObj = new JSONObject();
			employeeJsonObj.put(AMSAdminConstants.EMPLOYEEID,
					"sorry no suggestion available");
			employeeJsonObj.put(AMSAdminConstants.EMPLOYEENAME,
					"try reloading the page");
			employeeJSONArray.add(employeeJsonObj);
			employeeWrapperJSON.put("data", employeeJSONArray);
			return employeeWrapperJSON;
		}
		return employeeWrapperJSON;
	}

	/**
	 * delegate method for getting assets for assets page
	 * 
	 * @param webServiceReportAccess
	 * @param searchType
	 * @param searchString
	 * @param start
	 * @param length
	 * @param column
	 * @param dir
	 * @return
	 */
	public JSONObject getAssetsHistoryReport(
			ReportManagerPortProxy webServiceReportAccess, String searchType,
			String searchString, String start, String length, String column,
			String dir) {
		final String METHOD_NAME = "getAssets";
		logger.entering(CLASS_NAME, METHOD_NAME);

		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW144D"),
				searchType, searchString, start, length, column));

		String min = "";
		String max = "";
		if (start != null && length != null && column != null && dir != null) {
			min = Integer.toString((Integer.parseInt(start)) + 1);
			max = Integer.toString((Integer.parseInt(start))
					+ (Integer.parseInt(length)));

			searchString = searchString.toUpperCase();
			AssetsHistoryCountVTO assetsHistoryVTO = new AssetsHistoryCountVTO();
			try {
				AssetReport assetReport = webServiceReportAccess
						.getAssetHistory(AMSAdminConstants.getOrderEnum(dir),
								AMSAdminConstants.getSortColumnReport(column,
										searchType), stringToInteger(min),
								stringToInteger(max), AMSAdminConstants
										.getSearchTypeReport(searchType),
								searchString);
				assetsHistoryVTO.setMaxCount(assetReport.getMaxCount());
				List<AssetDetails> assetReportDetails = assetReport
						.getAssetHistoryList();

				AssetsHistoryVTO[] assetsHistory = new AssetsHistoryVTO[assetReportDetails
						.size()];
				ArrayList<AssetsHistoryVTO> viewAssetsTempList = new ArrayList<AssetsHistoryVTO>();

				for (int i = 0; i < assetsHistory.length; i++) {
					assetsHistory[i] = new AssetsHistoryVTO();
					assetsHistory[i].setAssetId(assetReportDetails.get(i)
							.getAssetId());
					assetsHistory[i].setAssetIdentity(assetReportDetails.get(i)
							.getAssetIdentity());

					assetsHistory[i].setUserId(assetReportDetails.get(i)
							.getUserId());
					assetsHistory[i].setAssetType(assetReportDetails.get(i)
							.getAssetType());
					assetsHistory[i].setDesignation(assetReportDetails.get(i)
							.getDesignation());
					assetsHistory[i].setIssuedDate(AMSDateUtil
							.getDate(AMSDateUtil.toDate(assetReportDetails.get(
									i).getDateOfIssue())));
					assetsHistory[i].setReturnedDate(AMSDateUtil
							.getDate(AMSDateUtil.toDate(assetReportDetails.get(
									i).getDateOfReturn())));
					viewAssetsTempList.add(assetsHistory[i]);

				}

				assetsHistoryVTO.setGetAssetsHistory(viewAssetsTempList);

				JSONObject jsonObject = AMSJsonUtil
						.convertToAssetsHistoryJson(assetsHistoryVTO);

				return jsonObject;
			} catch (WebServiceException e1) {
				JSONObject jsonErrorObject = AMSJsonUtil
						.assetHistoryErrorJson();
				logger.severe(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString("AMSW143E"), e1.getMessage()));
				return jsonErrorObject;
			} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
				JSONObject jsonErrorObject = AMSJsonUtil
						.convertToAssetsSearchErrorJson();
				logger.severe(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString("AMSW142E"), e.getMessage()));
				return jsonErrorObject;
			}

		} else {
			JSONObject errorjsonObject = AMSJsonUtil.assetHistoryErrorJson();
			return errorjsonObject;
		}
	}

	/**
	 * @author Dinesh
	 * @return get values obtained from web service for attributes and
	 *         populating the List object
	 * @throws AssetPortalWSException
	 */
	public List<Attribute> getValuesForAttributesList(
			AssetManagerServicePortProxy webServiceAccess)
			throws AssetPortalWSException {

		// Calling WS to get Attribute type List
		List<Attribute> attributeList = webServiceAccess.getAttributes("");

		return attributeList;
	}

	/**
	 * @author Dinesh
	 * @return get values obtained from web service for Asset Types and
	 *         attributes and populating the List object
	 * @throws AssetPortalWSException
	 */
	public List<AssetType> getValuesForAssetTypesList(
			AssetManagerServicePortProxy webServiceAccess) {

		final String METHOD_NAME = "getValuesForAssetTypesList";
		logger.entering(CLASS_NAME, METHOD_NAME);

		// Calling WS to get AssetTypeList List
		List<AssetType> assetTypeList = null;
		try {
			assetTypeList = webServiceAccess.getAssetType();

		} catch (AssetPortalWSException e) {
			logger.fine(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW090E"),
					""));
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return assetTypeList;
	}

	public JSONObject getCustomReport(
			ReportManagerPortProxy webServiceAccessReport, String assetTypeId,
			String[] attributesArray) {

		final String METHOD_NAME = "getCustomReport";
		logger.entering(CLASS_NAME, METHOD_NAME);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW084D"),
				assetTypeId));

		// looping through attribute array to get attribute Id's
		List<Attribute> attributeList = new ArrayList<Attribute>();
		for (String s : attributesArray) {
			Attribute attribute = new Attribute();
			attribute.setId(s);
			attributeList.add(attribute);
		}

		ReportResults reportResults = null;
		try {
			// calling WS to get Report Data
			// FIXME //ask service for all reports
			reportResults = webServiceAccessReport.customReportRequest(
					Integer.parseInt(assetTypeId), attributeList, 0, 0);
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW084E"),
					e.getMessage()));
			return AMSJsonUtil
					.convertToErrorJson("Asset Not Available for the Selected Asset Type");
		}
		if (reportResults == null) {
			// return error JSON
			return AMSJsonUtil
					.convertToErrorJson("Asset Not Available for the Selected Asset Type");
		}
		List<ReportResultsList> reportResultsList = reportResults
				.getReportResults();
		JSONObject jsonObjectWrapper = AMSJsonUtil
				.convertToReportDetails(reportResultsList);

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;
	}

	/**
	 * Generate Finance Report
	 * 
	 * @param Asset
	 *            Type
	 * @param Depreciation
	 *            Type
	 * @param Percentage
	 * @param webServiceAccessReport
	 * @return
	 */
	public JSONObject getFinanceReport(String assetTypeIdString,
			String depreciationType, String percentageString,
			ReportManagerPortProxy webServiceAccessReport) {

		int assetTypeId = 0;
		int percentage = 0;
		DepreciationTypeEnum depEnum = null;
		ReportResults financeReport = new ReportResults();
		try {
			assetTypeId = Integer.parseInt(assetTypeIdString);
			percentage = Integer.parseInt(percentageString);
		} catch (NumberFormatException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1221E"),
					e));
		}
		try {
			if (depreciationType.equals(AMSAdminConstants.DEP_SLM)) {
				depEnum = DepreciationTypeEnum.UNIFORM;
			} else if (depreciationType.equals(AMSAdminConstants.DEP_WDV)) {
				depEnum = DepreciationTypeEnum.PROGRESSIVE;
			} else {
				logger.warning(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString("AMSW1222E"), ""));
			}
			financeReport = webServiceAccessReport.generateFinanceReport(0, 0,
					assetTypeId, depEnum, percentage);
		} catch (NumberFormatException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1221E"),
					e));
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW086E"),
					e.getMessage()));
		}
		if (financeReport == null) {
			return AMSJsonUtil.convertToErrorJson("No records found");
		}
		List<ReportResultsList> reportResultsList = financeReport
				.getReportResults();
		JSONObject jsonObjectWrapper = AMSJsonUtil
				.convertToFinanceReportDetails(reportResultsList);
		return jsonObjectWrapper;

	}

	public ReportResults exportReport(
			ReportManagerPortProxy webServiceReportManager, String reportType) {
		ReportResults reportResults = null;
		final String METHOD_NAME = "exportReport";
		logger.entering(CLASS_NAME, METHOD_NAME);
		int reportId = 1;

		try {
			// FIXME //resolve
			// getting report types from WS
			List<ReportType> reportTypeList = webServiceReportManager
					.getReportType();
			// setting report id to the user selected report
			for (ReportType reportTypeLoopObject : reportTypeList) {
				if (reportTypeLoopObject.getReportName().equalsIgnoreCase(
						reportType)) {
					reportId = reportTypeLoopObject.getReportId();
				}
			}
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW085E"),
					e.getMessage()));
		}

		try {
			// min row, max row is zero, to get all records
			reportResults = webServiceReportManager.generateReport(0, 0,
					reportId);
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW085E"),
					e.getMessage()));
		}
		if (reportResults == null) {
		}
		List<ReportResultsList> reportResultsList = reportResults
				.getReportResults();

		// first row from service contains column names
		ReportResultsList firstRowList = reportResultsList.get(0);
		List<String> columnHeadings = firstRowList.getReportParameters();
		List<String> formattedcolumnHeading = new ArrayList<String>();
		// setting formatted headings for export file
		for (String columnHeading : columnHeadings) {
			formattedcolumnHeading.add(AMSNameUtil
					.convertToLabelName(columnHeading));
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return reportResults;

	}

	public AssetReport exportAssetHistoryReport(
			ReportManagerPortProxy webServiceAccessReport,
			UserManagerPortProxy webServiceAccessApprover, String searchType,
			String searchString) {
		final String METHOD_NAME = "exportAssetHistoryReport";
		logger.entering(CLASS_NAME, METHOD_NAME);

		AssetReport assetReport = null;
		try {

			assetReport = webServiceAccessReport
					.getAssetHistory(
							SortOrderEnum.DESC,
							com.avnet.assetportal.webservice.reportmanager.SortColumnEnum.ISSUED_DATE,
							0, 0,
							AMSAdminConstants.getSearchTypeReport(searchType),
							searchString);

		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW085E"),
					e.getMessage()));
		}

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return assetReport;
	}

	public ReportResults exportCustomReport(
			ReportManagerPortProxy webServiceAccessReport, String assetTypeId,
			String[] attributesArray) {
		final String METHOD_NAME = "downloadCustomReport";
		logger.entering(CLASS_NAME, METHOD_NAME);
		// FIXME
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW084D"),
				assetTypeId));

		// looping through attribute array to get attribute Id's
		List<Attribute> attributeList = new ArrayList<Attribute>();
		for (String s : attributesArray) {
			Attribute attribute = new Attribute();
			attribute.setId(s);
			attributeList.add(attribute);
		}

		ReportResults reportResults = null;
		try {
			// calling WS to get Report Data
			// FIXME //ask service for all reports
			reportResults = webServiceAccessReport.customReportRequest(
					Integer.parseInt(assetTypeId), attributeList, 0, 0);
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW084E"),
					e.getMessage()));
		}
		if (reportResults == null) {
			// FIXME
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return reportResults;
	}

	/**
	 * 
	 * Export Finance Report
	 * 
	 * @param assetTypeIdString
	 * @param Depreciation
	 *            Type
	 * @param Percentage
	 *            String
	 * @param webServiceAccessReport
	 * @return
	 */
	public ReportResults exportFinanceReport(String assetTypeIdString,
			String depreciationType, String percentageString,
			ReportManagerPortProxy webServiceAccessReport) {

		int assetTypeId = 0;
		int percentage = 0;
		DepreciationTypeEnum depEnum = null;
		ReportResults financeReport = new ReportResults();
		try {
			assetTypeId = Integer.parseInt(assetTypeIdString);
		} catch (NumberFormatException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1221E"),
					e));
		}
		try {
			percentage = Integer.parseInt(percentageString);
		} catch (NumberFormatException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1221E"),
					e));
		}
		try {
			if (depreciationType.equals(AMSAdminConstants.DEP_SLM)) {
				depEnum = DepreciationTypeEnum.UNIFORM;
			} else if (depreciationType.equals(AMSAdminConstants.DEP_WDV)) {
				depEnum = DepreciationTypeEnum.PROGRESSIVE;
			} else {
				logger.warning(MessageFormat.format(
						LoggerConstants.APP_CONSTANTS_BUNDLE
								.getString("AMSW1222E"), ""));
			}
			financeReport = webServiceAccessReport.generateFinanceReport(0, 0,
					assetTypeId, depEnum, percentage);
		} catch (NumberFormatException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1221E"),
					e));
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW086E"),
					e.getMessage()));
		}
		if (financeReport == null) {
			logger.warning(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1223E"),
					""));
		}
		return financeReport;

	}

	public String saveCustomReportQuery(
			ReportManagerPortProxy webServiceAccessReport, String assetTypeId,
			String[] attributesArray, String queryName) {
		final String METHOD_NAME = "saveCustomReport";
		logger.entering(CLASS_NAME, METHOD_NAME);
		// FIXME
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW084D"),
				assetTypeId + " " + queryName));

		// looping through attribute array to get attribute Id's
		List<Attribute> attributeList = new ArrayList<Attribute>();
		for (String s : attributesArray) {
			Attribute attribute = new Attribute();
			attribute.setId(s);
			attributeList.add(attribute);
		}

		Acknowledgement acknowledgement = new Acknowledgement();
		;
		try {
			// calling WS to get Report Data
			// FIXME //ask service for all reports
			acknowledgement = webServiceAccessReport.saveQuery(assetTypeId,
					attributeList, queryName);
		} catch (com.avnet.assetportal.webservice.reportmanager.AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW086E"),
					e.getMessage()));
			return "error";
		}
		if (!acknowledgement.isFlag()) {
			// FIXME
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return "error";
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return queryName + " successfully created";
	}

	public JSONObject getLifeTimeForAssetType(
			AssetManagerServicePortProxy webServiceAccess, String assetTypeId) {
		final String METHOD_NAME = "getLifeTimeForAssetType";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject details = new JSONObject();
		try {

			List<AssetType> assetTypeList = webServiceAccess.getAssetType();
			for (int i = 0; i < assetTypeList.size(); i++) {
				if (assetTypeId.equals(assetTypeList.get(i).getAssetTypeId())) {
					details.put("lifeTime", assetTypeList.get(i).getLifeTime());
					details.put("inHouseFlag", assetTypeList.get(i)
							.getInHouseFlag());
				}
			}
		} catch (AssetPortalWSException e) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1004E"),
					e.getMessage()));
			details.put("status",
					"Problem in fetching life time.Please refresh");
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return details;
		}
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1004D"),
				details));

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return details;
	}

	public JSONObject getAssetIdentityList(
			AssetManagerServicePortProxy webServiceAccess, String searchString) {
		final String METHOD_NAME = "getAssetIdentityList";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject jsonObj = new JSONObject();
		JSONObject results;
		JSONArray jsonArray = new JSONArray();
		try {
			List<AssetIdentityList> list = webServiceAccess
					.getAssetIdentities(searchString);
			for (int i = 0; i < list.size(); i++) {
				results = new JSONObject();
				results.put("assetIdentity", list.get(i).getAssetIdentity());
				results.put("assetId", list.get(i).getAssetId());
				jsonArray.add(results);
			}
			jsonObj.put("data", jsonArray);
		} catch (WebServiceException e1) {
			JSONObject jsonErrorObject = AMSJsonUtil.assetIdentityError();
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return jsonErrorObject;
		} catch (AssetPortalWSException e) {
			JSONObject jsonErrorObject = AMSJsonUtil.assetIdentityError();
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return jsonErrorObject;
		}
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObj;
	}

}

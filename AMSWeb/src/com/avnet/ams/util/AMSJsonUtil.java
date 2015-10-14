package com.avnet.ams.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.avnet.ams.constants.AMSAdminConstants;
import com.avnet.ams.constants.LoggerConstants;
import com.avnet.ams.vto.AssetApprover;
import com.avnet.ams.vto.AssetSecurityHistory;
import com.avnet.ams.vto.AssetTypeList;
import com.avnet.ams.vto.AssetTypeVTO;
import com.avnet.ams.vto.AttributesList;
import com.avnet.ams.vto.AvailableAssetVTO;
import com.avnet.ams.vto.EmpAutoCompleteDetails;
import com.avnet.ams.vto.EmployeeDetailsClientVTO;
import com.avnet.ams.vto.EmployeeDetailsListVTO;
import com.avnet.ams.vto.EmployeeDetailsOwnedby;
import com.avnet.ams.vto.AssetsHistoryCountVTO;
import com.avnet.ams.vto.AssetsHistoryVTO;
import com.avnet.ams.vto.ViewAssetsCountVTO;
import com.avnet.ams.vto.ViewAssetsAdminVTO;
import com.avnet.ams.vto.ViewReportVTO;
import com.avnet.ams.vto.ViewRequestCountVTO;
import com.avnet.ams.vto.ViewRequestsVTO;
import com.avnet.assetportal.webservice.reportmanager.ReportResultsList;
import com.avnet.assetportal.webservice.request.EmployeeRequest;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
 * 
 * @author Dinesh
 * 
 */
public class AMSJsonUtil {
	private static final String CLASS_NAME = AMSJsonUtil.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	/**
	 * To convert input Attributes JSON for AJAX call consumption
	 * 
	 * @param Attribute
	 *            Object
	 * @return JSON Object
	 */
	public static JSONObject convertToAttributesJson(
			AttributesList attributesListObject) {

		final String METHOD_NAME = "convertToAttributesJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if (attributesListObject == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083J"),
					"Object Null"));
		}
		JSONObject AttributesListJsonObject = new JSONObject();
		JSONArray AttributesListJsonLabelNameArray = new JSONArray();
		JSONArray AttributesListJsonVariableNameArray = new JSONArray();
		JSONArray AttributesListJsonIdArray = new JSONArray();

		// getting values from attributes array and putting it in JSON into
		// label name array
		for (String attributeValue : AMSNameUtil
				.convertToLabelName(attributesListObject.getAttributeName())) {
			AttributesListJsonLabelNameArray.add(attributeValue);
		}
		// getting values from attributes array and putting it in JSON into
		// variable name array
		for (String attributeValue : AMSNameUtil
				.convertToVariableName(attributesListObject.getAttributeName())) {
			AttributesListJsonVariableNameArray.add(attributeValue);
		}

		// getting Id' from attributes array and putting it in JSON into
		// variable Id array
		for (String attributeId : attributesListObject.getAttributeId()) {
			AttributesListJsonIdArray.add(attributeId);
		}
		AttributesListJsonObject.put("attribute_names",
				AttributesListJsonVariableNameArray);
		AttributesListJsonObject.put("attribute_labels",
				AttributesListJsonLabelNameArray);
		AttributesListJsonObject.put("attribute_id", AttributesListJsonIdArray);
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083D"),
				AttributesListJsonObject));
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return AttributesListJsonObject;
	}

	/**
	 * To convert input Asset Types into JSON for jQuery consumption
	 * 
	 * @param assetTypeAdminVTOList
	 * @return JSON Object for Asset Type
	 */
	public static JSONObject convertToTypeJson(
			ArrayList<AssetTypeVTO> assetTypeAdminVTOList) {
		final String METHOD_NAME = "convertToTypeJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		JSONObject assetTypeAdminVTOJsonObject = null;
		JSONArray assetTypeAdminVTOJsonFieldsArray = null;
		JSONArray assetTypeAdminVTOJsonLabelsArray = null;
		JSONArray assetTypeAdminVTOJsonIdsArray = null;
		JSONArray assetTypeAdminVTOJsonTypesArray = null;
		JSONArray assetTypeAdminVTOJsonArray = new JSONArray();

		if (assetTypeAdminVTOList == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083J"),
					"Object Null"));
		}
		for (AssetTypeVTO AssetTypeAdminVTOLoopObject : assetTypeAdminVTOList) {
			assetTypeAdminVTOJsonObject = new JSONObject();

			// inner loop for getting and setting type JSON array

			// inner loop for getting and setting fields JSON array
			assetTypeAdminVTOJsonFieldsArray = new JSONArray();
			for (String fieldsArrayValue : AssetTypeAdminVTOLoopObject
					.getAttributeNameArray()) {
				assetTypeAdminVTOJsonFieldsArray.add(fieldsArrayValue);
			}

			assetTypeAdminVTOJsonIdsArray = new JSONArray();
			for (String idsArrayValue : AssetTypeAdminVTOLoopObject
					.getAttributeIdArray()) {
				assetTypeAdminVTOJsonIdsArray.add(idsArrayValue);
			}

			assetTypeAdminVTOJsonTypesArray = new JSONArray();
			for (String typesArrayValue : AssetTypeAdminVTOLoopObject
					.getAttributeDataTypeArray()) {
				assetTypeAdminVTOJsonTypesArray.add(typesArrayValue);
			}
			// inner loop for getting and setting labels JSON array
			assetTypeAdminVTOJsonLabelsArray = new JSONArray();
			for (String labelsArrayValue : AssetTypeAdminVTOLoopObject
					.getAttributeLabelNameArray()) {
				assetTypeAdminVTOJsonLabelsArray.add(labelsArrayValue);
			}

			assetTypeAdminVTOJsonObject.put("name",
					AssetTypeAdminVTOLoopObject.getAssetTypeName());
			assetTypeAdminVTOJsonObject.put("assetTypeId",
					AssetTypeAdminVTOLoopObject.getAssetTypeId());
			assetTypeAdminVTOJsonObject.put("ids",
					assetTypeAdminVTOJsonIdsArray);
			assetTypeAdminVTOJsonObject.put("types",
					assetTypeAdminVTOJsonTypesArray);
			assetTypeAdminVTOJsonObject.put("fields",
					assetTypeAdminVTOJsonFieldsArray);
			assetTypeAdminVTOJsonObject.put("labels",
					assetTypeAdminVTOJsonLabelsArray);
			assetTypeAdminVTOJsonArray.add(assetTypeAdminVTOJsonObject);

		}

		JSONObject jsonObjectWrapper = new JSONObject();
		jsonObjectWrapper.put("asset", assetTypeAdminVTOJsonArray);

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;
	}

	/**
	 * To convert input Asset Types into JSON for jQuery consumption
	 * 
	 * @param assetTypeAdminVTOList
	 * @return JSON Object for Asset Type
	 */
	public static JSONObject convertToAssetTypeWithAttributesJson(
			ArrayList<AssetTypeVTO> assetTypeAdminVTOList) {
		final String METHOD_NAME = "convertToAssetTypeWithAttributesJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		JSONObject assetTypeAdminVTOJsonObject = null;
		JSONArray assetTypeAdminVTOJsonAttributeNameArray = null;
		JSONArray assetTypeAdminVTOJsonAttributeLabelNameArray = null;
		JSONArray assetTypeAdminVTOJsonAttributeIdArray = null;
		JSONArray assetTypeAdminVTOJsonAttributeDataTypeArray = null;
		JSONArray assetTypeAdminVTOJsonArray = new JSONArray();

		if (assetTypeAdminVTOList == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083J"),
					"Object Null"));
		}
		// loop for setting Asset Type JSON Array
		for (AssetTypeVTO AssetTypeAdminVTOLoopObject : assetTypeAdminVTOList) {
			assetTypeAdminVTOJsonObject = new JSONObject();

			// inner loop for getting and setting Attribute Name JSON array
			assetTypeAdminVTOJsonAttributeNameArray = new JSONArray();
			for (String fieldsArrayValue : AssetTypeAdminVTOLoopObject
					.getAttributeNameArray()) {
				assetTypeAdminVTOJsonAttributeNameArray.add(fieldsArrayValue);
			}
			// inner loop for getting and setting Attribute Id JSON array
			assetTypeAdminVTOJsonAttributeIdArray = new JSONArray();
			for (String idsArrayValue : AssetTypeAdminVTOLoopObject
					.getAttributeIdArray()) {
				assetTypeAdminVTOJsonAttributeIdArray.add(idsArrayValue);
			}
			// inner loop for getting and setting Attribute Data Type JSON array
			assetTypeAdminVTOJsonAttributeDataTypeArray = new JSONArray();
			for (String typesArrayValue : AssetTypeAdminVTOLoopObject
					.getAttributeDataTypeArray()) {
				assetTypeAdminVTOJsonAttributeDataTypeArray
						.add(typesArrayValue);
			}
			// inner loop for getting and setting labels JSON array
			assetTypeAdminVTOJsonAttributeLabelNameArray = new JSONArray();
			for (String labelsArrayValue : AssetTypeAdminVTOLoopObject
					.getAttributeLabelNameArray()) {
				assetTypeAdminVTOJsonAttributeLabelNameArray
						.add(labelsArrayValue);
			}

			assetTypeAdminVTOJsonObject.put("assetTypeName",
					AssetTypeAdminVTOLoopObject.getAssetTypeName());
			assetTypeAdminVTOJsonObject.put("assetTypeId",
					AssetTypeAdminVTOLoopObject.getAssetTypeId());
			assetTypeAdminVTOJsonObject.put("attributeIdArray",
					assetTypeAdminVTOJsonAttributeIdArray);
			assetTypeAdminVTOJsonObject.put("attributeDataTypeArray",
					assetTypeAdminVTOJsonAttributeDataTypeArray);
			assetTypeAdminVTOJsonObject.put("attributeNameArray",
					assetTypeAdminVTOJsonAttributeNameArray);
			assetTypeAdminVTOJsonObject.put("attributeLabelNameArray",
					assetTypeAdminVTOJsonAttributeLabelNameArray);

			// Putting all JSON Object into JSON Array
			assetTypeAdminVTOJsonArray.add(assetTypeAdminVTOJsonObject);

		}

		JSONObject jsonObjectWrapper = new JSONObject();
		jsonObjectWrapper.put("assetType", assetTypeAdminVTOJsonArray);

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;
	}

	public static JSONObject convertToReportListJson(
			List<ViewReportVTO> reportArrayList) {
		final String METHOD_NAME = "convertToReportListJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if (reportArrayList == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083J"),
					"Object Null"));
		}

		JSONObject reportListJsonObject = new JSONObject();
		JSONArray reportListJsonNameArray = new JSONArray();
		JSONArray reportListJsonIdArray = new JSONArray();

		for (ViewReportVTO reportObject : reportArrayList) {
			reportListJsonNameArray.add(reportObject.getReportName());
			reportListJsonIdArray.add(reportObject.getReportId());
		}
		reportListJsonObject.put("reportName", reportListJsonNameArray);
		reportListJsonObject.put("reportId", reportListJsonIdArray);
		JSONObject jsonObjectWrapper = new JSONObject();
		jsonObjectWrapper.put("data", reportListJsonObject);

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;

	}

	/**
	 * To convert input Requests List into JSON for jQuery consumption in
	 * DataTable
	 * 
	 * @param viewRequestsAdminVTOList
	 * @return JSON Object for View Requests Page
	 */
	public static JSONObject convertToRequestsJson(
			ViewRequestCountVTO viewRequestCountVTO) {
		final String METHOD_NAME = "convertToRequestJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if (viewRequestCountVTO == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083J"),
					"Object Null"));
		}

		JSONObject viewRequestsAdminVTOJsonObject = null;

		JSONArray viewRequestsAdminVTOJsonArray = new JSONArray();
		if (viewRequestCountVTO.getRecordsFiltered() > 0) {
			ArrayList<ViewRequestsVTO> viewRequestsAdminVTOList = viewRequestCountVTO
					.getViewRequestsAdminVTOList();
			// loop for setting key, value in JSON Object to be put into JSON
			// array
			for (ViewRequestsVTO viewRequestsAdminVTOLoopObject : viewRequestsAdminVTOList) {

				// create new object for each loop
				viewRequestsAdminVTOJsonObject = new JSONObject();

				viewRequestsAdminVTOJsonObject.put("EmployeeID",
						viewRequestsAdminVTOLoopObject.getEmployeeID());
				viewRequestsAdminVTOJsonObject.put("Type",
						viewRequestsAdminVTOLoopObject.getType());
				viewRequestsAdminVTOJsonObject.put("Name",
						viewRequestsAdminVTOLoopObject.getName());
				viewRequestsAdminVTOJsonObject.put("Status",
						viewRequestsAdminVTOLoopObject.getStatus());
				viewRequestsAdminVTOJsonObject.put("Designation",
						viewRequestsAdminVTOLoopObject.getDesignation());
				viewRequestsAdminVTOJsonObject.put("RequestID",
						viewRequestsAdminVTOLoopObject.getRequestID());
				viewRequestsAdminVTOJsonObject.put("Purpose",
						viewRequestsAdminVTOLoopObject.getPurpose());
				viewRequestsAdminVTOJsonObject.put("Severity",
						viewRequestsAdminVTOLoopObject.getSeverity());
				viewRequestsAdminVTOJsonObject.put("DateOfApproval",
						viewRequestsAdminVTOLoopObject.getDateOfApproval());
				viewRequestsAdminVTOJsonObject.put("DateOfIssue",
						viewRequestsAdminVTOLoopObject.getDateOfIssue());
				viewRequestsAdminVTOJsonObject.put("DueDate",
						viewRequestsAdminVTOLoopObject.getDueDate());
				viewRequestsAdminVTOJsonObject.put("Comments",
						viewRequestsAdminVTOLoopObject.getComments());
				viewRequestsAdminVTOJsonObject.put("CategoryId",
						viewRequestsAdminVTOLoopObject.getCategoryId());
				viewRequestsAdminVTOJsonArray
						.add(viewRequestsAdminVTOJsonObject);

			}
		}
		JSONObject jsonObjectWrapper = new JSONObject();
		// DataTable in UI page requires JSON name to be "Data", so we're
		// setting the key of the wrapper to "Data"
		jsonObjectWrapper.put("data", viewRequestsAdminVTOJsonArray);

		jsonObjectWrapper.put("recordsFiltered",
				viewRequestCountVTO.getRecordsFiltered());

		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;
	}

	public static JSONObject convertToEmpDetailsAutoCompleteJson(
			ArrayList<EmpAutoCompleteDetails> empDetilsAdminVTOList) {
		final String METHOD_NAME = "convertToEmpDetailsAutoCompleteJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if (empDetilsAdminVTOList == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083J"),
					"Object Null"));
		}
		JSONObject empDetailsAdminVTOJsonObject = null;

		JSONArray empDetailsAdminVTOJsonArray = new JSONArray();

		// loop for setting key, value in JSON Object to be put into JSON array
		for (EmpAutoCompleteDetails empDetailsAdminVTOLoopObject : empDetilsAdminVTOList) {

			// create new object for each loop
			empDetailsAdminVTOJsonObject = new JSONObject();

			empDetailsAdminVTOJsonObject.put("ID",
					empDetailsAdminVTOLoopObject.getId());
			empDetailsAdminVTOJsonObject.put("Name",
					empDetailsAdminVTOLoopObject.getName());

			empDetailsAdminVTOJsonArray.add(empDetailsAdminVTOJsonObject);

		}

		JSONObject jsonObjectWrapper = new JSONObject();

		// DataTable in UI page requires JSON name to be "Data", so we're
		// setting the key of the wrapper to "Data"
		jsonObjectWrapper.put("data", empDetailsAdminVTOJsonArray);

		logger.exiting(CLASS_NAME, METHOD_NAME);

		return jsonObjectWrapper;
	}

	/**
	 * @param availableAssetAdminVTOObject
	 * @return
	 */
	public static JSONObject convertToAvailableAssetsJson(
			AvailableAssetVTO availableAssetAdminVTOObject) {

		final String METHOD_NAME = "convertToAvailableAssetsJson";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject AvailableAssetObject = new JSONObject();
		JSONArray AvailableAssetAssetIdArray = new JSONArray();
		JSONArray AvailableAssetAssetTypeArray = new JSONArray();
		JSONArray AvailableAssetAssetIdOriginalArray = new JSONArray();
		for (String assetId : availableAssetAdminVTOObject.getAssetId()) {
			AvailableAssetAssetIdArray.add(assetId);
		}
		// getting values from attributes array and putting it in JSON into
		// variable name array
		for (String assetType : availableAssetAdminVTOObject.getAssetType()) {
			AvailableAssetAssetTypeArray.add(assetType);
		}
		// getting values from attributes array and putting it in JSON into
		// variable name array
		for (String assetIdOriginal : availableAssetAdminVTOObject
				.getAssetIdOriginal()) {
			AvailableAssetAssetIdOriginalArray.add(assetIdOriginal);
		}

		AvailableAssetObject.put("assetId", AvailableAssetAssetIdArray);
		AvailableAssetObject.put("assetType", AvailableAssetAssetTypeArray);
		AvailableAssetObject.put("assetIdOriginal",
				AvailableAssetAssetIdOriginalArray);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return AvailableAssetObject;
	}

	public static JSONObject availableAssetErrorDisplayJson(
			AvailableAssetVTO availableAssetAdminVTOObject) {
		JSONObject AvailableAssetObject = new JSONObject();
		AvailableAssetObject.put("assetId", "no");
		return AvailableAssetObject;
	}

	public static JSONObject convertToEmployeeNameJson(
			List<EmployeeDetailsOwnedby> empList) {
		final String METHOD_NAME = "convertToEmployeeNameJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		if (empList == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083J"),
					"Object Null"));
		}
		JSONObject EmployeeDetailJson;
		JSONArray EmployeeDetailArrayJson = new JSONArray();
		for (EmployeeDetailsOwnedby obj : empList) {
			EmployeeDetailJson = new JSONObject();
			EmployeeDetailJson.put("id", obj.getEmployee_ID());
			EmployeeDetailJson.put("name", obj.getEmployee_Name());
			EmployeeDetailArrayJson.add(EmployeeDetailJson);
		}
		JSONObject EmployeeDetailwrapper = new JSONObject();
		EmployeeDetailwrapper.put("employee", EmployeeDetailArrayJson);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return EmployeeDetailwrapper;
	}

	public static JSONObject convertToAssetsSearchJson(
			ViewAssetsCountVTO viewAssetsCountVTO) {
		final String METHOD_NAME = "convertToAssetsSearchJson";
		logger.entering(CLASS_NAME, METHOD_NAME);
		if (viewAssetsCountVTO == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083J"),
					"Object Null"));
		}
		JSONObject viewAssetsAdminVTOJsonObject = null;
		JSONArray viewAssetsAdminVTOJsonArray = new JSONArray();
		ArrayList<ViewAssetsAdminVTO> viewAssetsAdminVTOList = viewAssetsCountVTO
				.getViewAssetsAdminVTOList();

		for (ViewAssetsAdminVTO viewAssetsAdminVTOLoopObject : viewAssetsAdminVTOList) {

			// create new object for each loop
			viewAssetsAdminVTOJsonObject = new JSONObject();

			viewAssetsAdminVTOJsonObject.put(AMSAdminConstants.ASSETID,
					viewAssetsAdminVTOLoopObject.getAssetId());
			viewAssetsAdminVTOJsonObject.put(AMSAdminConstants.ASSETIDENTITY,
					viewAssetsAdminVTOLoopObject.getAssetIdentity());
			viewAssetsAdminVTOJsonObject.put(AMSAdminConstants.ASSET_TYPE,
					viewAssetsAdminVTOLoopObject.getAssetType());
			viewAssetsAdminVTOJsonObject.put(AMSAdminConstants.OWNER,
					viewAssetsAdminVTOLoopObject.getOwner());
			viewAssetsAdminVTOJsonObject.put(AMSAdminConstants.PURCHASED_DATE,
					viewAssetsAdminVTOLoopObject.getPurchasedDate());
			viewAssetsAdminVTOJsonObject.put("DateOfIssue",
					viewAssetsAdminVTOLoopObject.getIssuedDate());
			viewAssetsAdminVTOJsonArray.add(viewAssetsAdminVTOJsonObject);
		}

		JSONObject jsonObjectWrapper = new JSONObject();

		// DataTable in UI page requires JSON name to be "Data", so we're
		// setting the key of the wrapper to "Data"
		jsonObjectWrapper.put("data", viewAssetsAdminVTOJsonArray);

		jsonObjectWrapper.put("recordsFiltered",
				viewAssetsCountVTO.getMaxCount());
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;

	}

	public static JSONObject convertToAssetsSearchErrorJson() {
		final String METHOD_NAME = "convertToAssetsSearchJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		JSONObject viewAssetsAdminVTOJsonObject = null;
		viewAssetsAdminVTOJsonObject = new JSONObject();
		JSONObject jsonObjectWrapper = new JSONObject();

		// DataTable in UI page requires JSON name to be "Data", so we're
		// setting the key of the wrapper to "Data"
		jsonObjectWrapper.put("data", viewAssetsAdminVTOJsonObject);
		jsonObjectWrapper.put("recordsFiltered", "0");
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;

	}

	public static JSONObject assetErrorJson() {
		JSONArray errorJsonArray = new JSONArray();
		JSONObject errorJsonObjectWrapper = new JSONObject();
		JSONObject errorJsonObject = new JSONObject();

		errorJsonObject.put(AMSAdminConstants.ASSETIDENTITY, "");
		errorJsonObject.put(AMSAdminConstants.ASSET_TYPE,
				"AMS Service Currently Down");
		errorJsonObject.put(AMSAdminConstants.OWNER, "");
		errorJsonObject.put(AMSAdminConstants.PURCHASED_DATE, "");
		errorJsonArray.add(errorJsonObject);

		errorJsonObjectWrapper.put("data", errorJsonArray);
		return errorJsonObjectWrapper;
	}

	public static JSONObject assetHistoryErrorJson() {
		JSONObject errorJsonObject = new JSONObject();
		JSONObject errorJsonObjectWrapper = new JSONObject();
		JSONArray errorJsonArray = new JSONArray();
		errorJsonObject.put(AMSAdminConstants.ASSETIDENTITY, "");
		errorJsonObject.put(AMSAdminConstants.ASSET_TYPE,
				"AMS Service Currently Down");
		errorJsonObject.put(AMSAdminConstants.USERID, "");
		errorJsonObject.put(AMSAdminConstants.DESIGNATION,
				"AMS Service Currently Down");
		errorJsonObject.put(AMSAdminConstants.DATE_OF_ISSUE, "");
		errorJsonObject.put(AMSAdminConstants.DATE_OF_RETURN, "");
		errorJsonArray.add(errorJsonObject);

		errorJsonObjectWrapper.put("data", errorJsonArray);
		return errorJsonObjectWrapper;
	}

	public static JSONObject assetTypesError() {
		JSONArray errorJsonArray = new JSONArray();
		JSONObject errorJsonObjectWrapper = new JSONObject();
		JSONObject errorJsonObject = new JSONObject();
		errorJsonObject.put(AMSAdminConstants.ASSETID,
				"sorry no suggestion available");
		errorJsonObject.put(AMSAdminConstants.ASSET_TYPE, "");
		errorJsonArray.add(errorJsonObject);
		errorJsonObjectWrapper.put("data", errorJsonArray);
		return errorJsonObjectWrapper;
	}

	public static JSONObject usersListError() {
		JSONArray errorJsonArray = new JSONArray();
		JSONObject errorJsonObjectWrapper = new JSONObject();
		JSONObject errorJsonObject = new JSONObject();
		errorJsonObject.put(AMSAdminConstants.EMPLOYEEID,
				"AMS Service Currently Down");
		errorJsonObject.put(AMSAdminConstants.EMPLOYEENAME, "");
		errorJsonObject.put(AMSAdminConstants.ASSETIDENTITY,
				"AMS Service Currently Down");
		errorJsonArray.add(errorJsonObject);
		errorJsonObjectWrapper.put("data", errorJsonArray);
		return errorJsonObjectWrapper;
	}

	public static JSONObject assetIdentityError() {
		JSONArray errorJsonArray = new JSONArray();
		JSONObject errorJsonObjectWrapper = new JSONObject();
		JSONObject errorJsonObject = new JSONObject();
		errorJsonObject.put(AMSAdminConstants.ASSETIDENTITY,
				"AMS Service Currently Down");
		errorJsonArray.add(errorJsonObject);
		errorJsonObjectWrapper.put("data", errorJsonArray);
		return errorJsonObjectWrapper;
	}

	public static JSONObject convertToEmployeeDetailsJson(
			EmployeeDetailsListVTO employeeDetailsListVTO) {
		final String METHOD_NAME = "convertToEmployeeDetailsJson";
		logger.entering(CLASS_NAME, METHOD_NAME);
		JSONObject employeeDetailsAdminVTOJsonObject = null;

		if (employeeDetailsListVTO == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW083J"),
					"Object Null"));
		}

		JSONArray employeeDetailsAdminVTOJsonArray = new JSONArray();
		ArrayList<EmployeeDetailsClientVTO> employeeDetailsListVTOarray = employeeDetailsListVTO
				.getEmployeeDetailsListVTO();
		// loop for setting key, value in JSON Object to be put into JSON array
		for (EmployeeDetailsClientVTO employeeDetailsAdminVTOLoopObject : employeeDetailsListVTOarray) {

			// create new object for each loop
			employeeDetailsAdminVTOJsonObject = new JSONObject();

			employeeDetailsAdminVTOJsonObject.put("EmployeeID",
					employeeDetailsAdminVTOLoopObject.getEmpId());
			employeeDetailsAdminVTOJsonObject.put("Name",
					employeeDetailsAdminVTOLoopObject.getName());
			employeeDetailsAdminVTOJsonObject.put("Designation",
					employeeDetailsAdminVTOLoopObject.getDesignation());
			employeeDetailsAdminVTOJsonObject.put("BusinessUnit",
					employeeDetailsAdminVTOLoopObject.getBusinessUnit());
			employeeDetailsAdminVTOJsonArray
					.add(employeeDetailsAdminVTOJsonObject);

		}
		JSONObject jsonObjectWrapper = new JSONObject();

		// DataTable in UI page requires JSON name to be "Data", so we're
		// setting the key of the wrapper to "Data"
		jsonObjectWrapper.put("data", employeeDetailsAdminVTOJsonArray);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;

	}

	public static JSONObject convertToSecurityEmployeeDetailsJson(
			EmployeeDetailsClientVTO employeeDetailsVTO) {

		JSONObject employeeDetailsJson = new JSONObject();

		employeeDetailsJson.put("EmployeeID", employeeDetailsVTO.getEmpId());
		employeeDetailsJson.put("Name", employeeDetailsVTO.getName());
		employeeDetailsJson.put("Designation",
				employeeDetailsVTO.getDesignation());
		employeeDetailsJson.put("BusinessUnit",
				employeeDetailsVTO.getBusinessUnit());

		return employeeDetailsJson;
	}

	public static JSONObject convertToSecurityErrorJson() {
		JSONArray assetHistoryJsonArrayError = new JSONArray();
		JSONObject jsonObjectWrapper = new JSONObject();
		jsonObjectWrapper
				.put("assetRequestHistory", assetHistoryJsonArrayError);
		jsonObjectWrapper.put("recordsFiltered", 0);
		return jsonObjectWrapper;

	}

	public static JSONObject convertToErrorJson(String errorString) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("error", errorString);
		return jsonObject;

	}

	public static JSONObject convertToEmployeeDetailsErrorJson() {

		JSONObject jsonObjectWrapper = null;

		return jsonObjectWrapper;

	}

	public static JSONObject convertToSecurityHistoryDetailsJson(
			ArrayList<AssetSecurityHistory> assetHistoryWrapperList,
			EmployeeRequest employeeRequest) {

		JSONObject assetHistoryJsonObject = null;
		JSONArray assetHistoryJsonArray = new JSONArray();
		for (AssetSecurityHistory obj : assetHistoryWrapperList) {
			assetHistoryJsonObject = new JSONObject();
			assetHistoryJsonObject.put("assetType", obj.getAssetType());
			assetHistoryJsonObject.put("maxRowCount", obj.getMaxRowCount());
			assetHistoryJsonObject.put("productName", obj.getAssetName());
			if (obj.getInHouseFlag().equalsIgnoreCase("Y"))
				assetHistoryJsonObject.put("inHouseFlag", "Yes");
			else if (obj.getInHouseFlag().equalsIgnoreCase("N"))
				assetHistoryJsonObject.put("inHouseFlag", "No");
			assetHistoryJsonArray.add(assetHistoryJsonObject);

		}

		JSONObject jsonObjectWrapper = new JSONObject();
		jsonObjectWrapper.put("assetRequestHistory", assetHistoryJsonArray);
		jsonObjectWrapper.put("recordsFiltered",
				employeeRequest.getMaxRowCount());

		return jsonObjectWrapper;

	}

	public static JSONObject convertToReportDetails(
			List<ReportResultsList> reportResultsList) {
		// initialize JSON objects
		JSONArray columnNameArray = new JSONArray();
		JSONObject jsonValueObject;
		JSONArray jsonRowArray = new JSONArray();
		JSONArray jsonValueArray = new JSONArray();

		// outer loop to iterate all rows
		for (int i = 0; i < reportResultsList.size(); i++) {
			jsonValueObject = new JSONObject();
			jsonValueArray = new JSONArray();

			// inner loop to iterate each value of every row
			for (int j = 0; j < reportResultsList.get(i).getReportParameters()
					.size(); j++) {

				// row(0) has all headings
				if (i == 0) {
					jsonValueObject = new JSONObject();
					jsonValueObject.put("title", reportResultsList.get(0)
							.getReportParameters().get(j));
					columnNameArray.add(jsonValueObject);
				} else {// row!=0 has values for the headings

					// if value is null put 'No Data', else send original data
					if (reportResultsList.get(i).getReportParameters().get(j) != null) {
						jsonValueArray.add(reportResultsList.get(i)
								.getReportParameters().get(j));
					} else {
						jsonValueArray.add("No Data");
					}
				}
			}
			// except row(0), add other rows to the data array
			if (i != 0) {
				jsonRowArray.add(jsonValueArray);
			}
		}

		JSONObject jsonObjectWrapper = new JSONObject();
		jsonObjectWrapper.put("columnName", columnNameArray);
		jsonObjectWrapper.put("data", jsonRowArray);

		return jsonObjectWrapper;

	}

	/**
	 * JSON Convertor to get Finance Report JSON
	 * 
	 * @param reportResultsList
	 * @return
	 */
	public static JSONObject convertToFinanceReportDetails(
			List<ReportResultsList> reportResultsList) {

		JSONArray columnArray = new JSONArray();
		JSONArray jsonRowArray = new JSONArray();
		List<String> columnNamesList = reportResultsList.get(0)
				.getReportParameters();
		for (int i = 1; i < reportResultsList.size(); i++) {
			JSONArray jsonData = new JSONArray();
			for (int j = 0; j < columnNamesList.size(); j++) {
				String columnName = columnNamesList.get(j);
				// To get the column names
				if (i < 2) {

					if (columnName
							.matches(AMSAdminConstants.FINANCE_COLUMN_NAMES)) {

						JSONObject columnNames = new JSONObject();
						columnNames.put("title", columnName);
						columnArray.add(columnNames);
					}
				}
				if (columnName.matches(AMSAdminConstants.FINANCE_COLUMN_NAMES)) {

					String paramValue = reportResultsList.get(i)
							.getReportParameters().get(j);
					if (paramValue != null) {
						jsonData.add(paramValue);
					} else {
						jsonData.add("No Data");
					}
				}
			}
			jsonRowArray.add(jsonData);
		}

		JSONObject jsonObjectWrapper = new JSONObject();
		jsonObjectWrapper.put("columnName", columnArray);
		jsonObjectWrapper.put("data", jsonRowArray);

		return jsonObjectWrapper;

	}

	public static JSONObject convertToAssetsHistoryJson(
			AssetsHistoryCountVTO getAssetsHistoryCountVTO) {
		JSONObject getAssetsHistoryVTOJsonObject = null;
		JSONArray getAssetsHistoryVTOJsonArray = new JSONArray();
		ArrayList<AssetsHistoryVTO> getAssetsHistoryVTOList = getAssetsHistoryCountVTO
				.getGetAssetsHistory();

		for (AssetsHistoryVTO GetAssetsHistoryVTOLoopObject : getAssetsHistoryVTOList) {

			// create new object for each loop
			getAssetsHistoryVTOJsonObject = new JSONObject();
			getAssetsHistoryVTOJsonObject.put(AMSAdminConstants.USERID,
					GetAssetsHistoryVTOLoopObject.getUserId());
			getAssetsHistoryVTOJsonObject.put(AMSAdminConstants.DATE_OF_ISSUE,
					GetAssetsHistoryVTOLoopObject.getIssuedDate());
			getAssetsHistoryVTOJsonObject.put(AMSAdminConstants.DATE_OF_RETURN,
					GetAssetsHistoryVTOLoopObject.getReturnedDate());
			getAssetsHistoryVTOJsonObject.put(AMSAdminConstants.ASSETID,
					GetAssetsHistoryVTOLoopObject.getAssetId());
			getAssetsHistoryVTOJsonObject.put(AMSAdminConstants.ASSETIDENTITY,
					GetAssetsHistoryVTOLoopObject.getAssetIdentity());
			getAssetsHistoryVTOJsonObject.put(AMSAdminConstants.ASSET_TYPE,
					GetAssetsHistoryVTOLoopObject.getAssetType());
			getAssetsHistoryVTOJsonObject.put(AMSAdminConstants.DESIGNATION,
					GetAssetsHistoryVTOLoopObject.getDesignation());
			getAssetsHistoryVTOJsonArray.add(getAssetsHistoryVTOJsonObject);

		}

		JSONObject jsonObjectWrapper = new JSONObject();

		// DataTable in UI page requires JSON name to be "Data", so we're
		// setting the key of the wrapper to "Data"
		jsonObjectWrapper.put("data", getAssetsHistoryVTOJsonArray);

		jsonObjectWrapper.put("recordsFiltered",
				getAssetsHistoryCountVTO.getMaxCount());
		return jsonObjectWrapper;

	}

	public static JSONObject convertToAssetTypeJson(AssetTypeList typeListObject) {

		if (typeListObject == null) {
			logger.severe(MessageFormat.format(
					LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSUI083J"),
					"Object Null"));
		}
		JSONObject TypeListJsonObject = new JSONObject();
		JSONArray TypeListJsonAssetTypeIdArray = new JSONArray();
		JSONArray TypeListJsonAssetTypeNameArray = new JSONArray();

		// getting values from attributes array and putting it in JSON into
		// variable name array
		for (String attributeValue : typeListObject.getAssetType()) {
			TypeListJsonAssetTypeNameArray.add(attributeValue);
		}

		// getting Id' from attributes array and putting it in JSON into
		// variable Id array
		for (String attributeId : typeListObject.getAssetId()) {
			TypeListJsonAssetTypeIdArray.add(attributeId);
		}
		TypeListJsonObject.put("type_names", TypeListJsonAssetTypeNameArray);
		TypeListJsonObject.put("type_id", TypeListJsonAssetTypeIdArray);

		return TypeListJsonObject;
	}

	public static JSONObject convertToAssetTypeErrorJson() {
		final String METHOD_NAME = "convertToAssetApproverErrorJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		JSONObject AssetTypeJsonObject = null;
		JSONObject jsonObjectWrapper = new JSONObject();

		jsonObjectWrapper.put("data", AssetTypeJsonObject);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;
	}

	public static JSONObject convertToAssetApproverJson(
			AssetApprover approverListObject) {

		JSONObject AssetApproverListJsonObject = new JSONObject();
		JSONArray AssetApproverIdJsonArray = new JSONArray();
		JSONArray AssetApproverNameJsonArray = new JSONArray();

		// getting values from attributes array and putting it in JSON into
		// variable name array
		for (String attributeValue : approverListObject.getApproverName()) {
			AssetApproverNameJsonArray.add(attributeValue);
		}

		// getting Id' from attributes array and putting it in JSON into
		// variable Id array
		for (String attributeId : approverListObject.getApproverId()) {
			AssetApproverIdJsonArray.add(attributeId);
		}
		AssetApproverListJsonObject.put("approverName",
				AssetApproverNameJsonArray);
		AssetApproverListJsonObject.put("approverId", AssetApproverIdJsonArray);
		return AssetApproverListJsonObject;
	}

	public static JSONObject convertToAssetApproverErrorJson() {
		final String METHOD_NAME = "convertToAssetApproverErrorJson";
		logger.entering(CLASS_NAME, METHOD_NAME);

		JSONObject AssetsApproverJsonObject = null;
		JSONObject jsonObjectWrapper = new JSONObject();

		jsonObjectWrapper.put("data", AssetsApproverJsonObject);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return jsonObjectWrapper;
	}

	public static JSONObject generateErrorJSON(String errorMessage) {
		JSONObject json = new JSONObject();
		json.put("errorMessage", errorMessage);
		json.put("data", new JSONArray());
		json.put("recordsFiltered", "0");
		return json;
	}

}

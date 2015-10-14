package com.avnet.ams.util;

import java.util.ArrayList;

import com.avnet.ams.vto.AssetHistoryDetails;
import com.avnet.ams.vto.AssetRequestHisory;
import com.avnet.assetportal.webservice.request.EmployeeRequest;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
 * @author Atechian
 * 
 */
public class AMSUserJsonUtil {

	/**
	 * @param assetHistoryWrapperList
	 * @param employeeRequest
	 * @return jsonObjectWrapper
	 */
	public static JSONObject assetHistoryToJson(
			ArrayList<AssetHistoryDetails> assetHistoryWrapperList,
			EmployeeRequest employeeRequest) {

		AssetRequestHisory assetRequestHistoryObj = new AssetRequestHisory();
		assetRequestHistoryObj.setAssetRequestHistory(assetHistoryWrapperList);

		JSONObject assetHistoryJsonObject = null;

		JSONArray assetHistoryJsonArray = new JSONArray();

		for (AssetHistoryDetails obj : assetHistoryWrapperList) {
			assetHistoryJsonObject = new JSONObject();

			assetHistoryJsonObject.put("empId", obj.getEmpId());
			assetHistoryJsonObject.put("empname", obj.getEmpname());
			assetHistoryJsonObject.put("assetType", obj.getAssetType());
			assetHistoryJsonObject.put("dayOfRequest", obj.getDayOfRequest());
			assetHistoryJsonObject.put("status", obj.getStatus());
			assetHistoryJsonObject.put("action", "Default Action");
			assetHistoryJsonObject.put("note", obj.getNote());
			assetHistoryJsonObject.put("currentApproverId",
					obj.getCurrentApproverId());
			assetHistoryJsonObject.put("currentApproverName",
					obj.getCurrentApproverName());
			assetHistoryJsonObject.put("requestId", obj.getRequestId());
			assetHistoryJsonObject.put("severity", obj.getSeverity());
			assetHistoryJsonObject.put("comments", obj.getComments());
			assetHistoryJsonObject.put("dueDate", obj.getDueDate());
			assetHistoryJsonObject.put("dateOfApproval",
					obj.getDateOfApproval());
			assetHistoryJsonObject.put("dateOfIssue", obj.getDateOfIssue());
			assetHistoryJsonObject.put("dateOfReturn", obj.getDateOfReturn());
			assetHistoryJsonObject.put("buCounter", obj.getBuCounter());
			assetHistoryJsonObject.put("stCounter", obj.getStCounter());

			assetHistoryJsonArray.add(assetHistoryJsonObject);

		}

		JSONObject jsonObjectWrapper = new JSONObject();
		jsonObjectWrapper.put("assetRequestHistory", assetHistoryJsonArray);
		jsonObjectWrapper.put("recordsFiltered",
				employeeRequest.getMaxRowCount());
		return jsonObjectWrapper;

	}
	
	public static JSONObject generateErrorJson(){
		JSONArray assetHistoryJsonArrayError = new JSONArray();
		JSONObject jsonObjectWrapper = new JSONObject();
		jsonObjectWrapper.put("assetRequestHistory",assetHistoryJsonArrayError);
		jsonObjectWrapper.put("recordsFiltered", 0);
		return jsonObjectWrapper;
		
	}

}

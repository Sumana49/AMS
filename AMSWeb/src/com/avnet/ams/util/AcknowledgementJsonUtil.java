package com.avnet.ams.util;

import java.text.MessageFormat;
import java.util.logging.Logger;

import com.avnet.ams.constants.LoggerConstants;
import com.ibm.json.java.JSONObject;

public class AcknowledgementJsonUtil {
	public static JSONObject getJsonForAck(String message) {
		final String CLASS_NAME = AcknowledgementJsonUtil.class.getName();
		final String METHOD_NAME = "getJsonForAck";
		Logger logger = Logger.getLogger(CLASS_NAME);
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		JSONObject JSONObj = new JSONObject();
		if (message.toLowerCase().contains("added")) {
			JSONObj.put("flag", "success");
			JSONObj.put("status", "Asset Type Added Successfully");
		} else if (message.toLowerCase().contains("updated")) {
			JSONObj.put("flag", "success");
			JSONObj.put("status", "Asset Type Updated Successfully");
		} else if (message.toLowerCase().contains("appropriate")) {
			JSONObj.put("flag", "failure");
			JSONObj.put("status", "Type Name/Asset type Prefix/lifetime is not alphanumeric");
		} else if (message.toLowerCase().contains("getAttributesException")) {
			JSONObj.put("flag", "failure");
			JSONObj.put("status", "Problem in fetching attributes");
		} else if (message.toLowerCase().contains("updateAssetTypeError")) {
			JSONObj.put("flag", "failure");
			JSONObj.put("status", "Problem in updating asset");
		} else if (message.toLowerCase().contains("addAssetTypeError")) {
			JSONObj.put("flag", "failure");
			JSONObj.put("status", "Problem in adding asset");
		}
		else if (message.toLowerCase().contains("getTypeJsonError")) {
			JSONObj.put("flag", "failure");
			JSONObj.put("status", "Could not fetch asset types from service.Please refresh");
		}
		else{
			JSONObj.put("flag", "failure");
			JSONObj.put("status", message);
		}
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1005D"),JSONObj));
		
		logger.entering(CLASS_NAME, METHOD_NAME);
		return JSONObj;

	}
	
	public static JSONObject getJsonForAckAddAsset(String message) {
		final String CLASS_NAME = AcknowledgementJsonUtil.class.getName();
		final String METHOD_NAME = "getJsonForAckAddAsset";
		Logger logger = Logger.getLogger(CLASS_NAME);
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		JSONObject JSONObj = new JSONObject();
		
		if (message.toLowerCase().contains("getAttributesError")) {
			JSONObj.put("status", "Problem in getting attributes");
		} else if (message.toLowerCase().contains("dateFormatError")) {
			JSONObj.put("status", "Problem in Date Format");
		} else if (message.toLowerCase().contains("dateTypeError")) {
			JSONObj.put("status", "Problem in Date Type");
		} else if (message.toLowerCase().contains("addAssetError")) {
			JSONObj.put("status", "Problem in adding asset");
		}
		
		else{
			JSONObj.put("status", message);
		}
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1063D"),JSONObj));
		
		logger.entering(CLASS_NAME, METHOD_NAME);
		return JSONObj;
	}
}

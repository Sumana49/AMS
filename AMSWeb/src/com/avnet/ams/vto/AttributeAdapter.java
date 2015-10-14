package com.avnet.ams.vto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.avnet.ams.constants.AMSCommonConstants;
import com.avnet.ams.delegate.AMSAdminDelegate;
import com.avnet.ams.util.AMSWebServiceReflectionUtil;
import com.avnet.assetportal.webservice.assetservice.AssetManagerServicePortProxy;
import com.avnet.assetportal.webservice.assetservice.AssetPortalWSException;
import com.avnet.assetportal.webservice.common.Attribute;

public class AttributeAdapter {

	private AssetManagerServicePortProxy webServiceAccess = null;
	
	private AMSAdminDelegate adminDelegate = new AMSAdminDelegate();

	private String id;
	private String name;
	private String value;
	static Map<String, String> attributeMap;

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getValue() {
		return value;
	}

	public final void setValue(String value) {
		this.value = value;
	}

	Map<String, String> getAttributes() throws AssetPortalWSException {

		// initialising Service Access Object
		if (webServiceAccess == null) {
			webServiceAccess = (AssetManagerServicePortProxy) AMSWebServiceReflectionUtil
					.getProxyObject(AMSCommonConstants.ASSETMANAGERSERVICE);
		}
		// Getting Attributes from Service
		List<Attribute> attrList = adminDelegate
				.getValuesForAttributesList(webServiceAccess);
		attributeMap = new HashMap<String, String>();

		// looping through attribute list and putting it into Map
		// Map Key is AttributeID Map Value is AttributeName
		for (Attribute a : attrList) {
			attributeMap.put(a.getId(), a.getName());
		}
		return attributeMap;
	}

	public String getAttributeIdFromName(String name)
			throws AssetPortalWSException {

		// Map Key is AttributeID Map Value is AttributeName
		Map<String, String> attributeMap = getAttributes();

		// Call function to get attributes from service
		// Entry Key is AttributeID Entry Value is AttributeName
		for (Entry<String, String> attributeIdAndNameEntry : attributeMap
				.entrySet()) {
			// if Value from VTO matches Value from Service
			if (attributeIdAndNameEntry.getValue().toLowerCase()
					.contains(name.toLowerCase())) {
				return attributeIdAndNameEntry.getKey();
			}
		}
		return null;
	}

	public Attribute getAttributeTypeFromName(String name)
			throws AssetPortalWSException {

		// Call function to get attributes from service
		// Map Key is AttributeID Map Value is AttributeName
		Map<String, String> attributeMap = getAttributes();

		Attribute attributeObject;
		// Entry Key is AttributeID Entry Value is AttributeName
		for (Entry<String, String> attributeIdAndNameEntry : attributeMap
				.entrySet()) {
			attributeObject = new Attribute();
			// if Value from VTO matches Value from Service
			if (attributeIdAndNameEntry.getValue().toLowerCase()
					.contains(name.toLowerCase())) {
				// setting Id and Value from Service
				attributeObject.setId(attributeIdAndNameEntry.getKey());
				attributeObject.setName(attributeIdAndNameEntry.getValue());
				return attributeObject;
			}
		}
		return null;
	}

}

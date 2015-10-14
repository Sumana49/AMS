package com.avnet.ams.vto;

import java.util.Collections;
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
import com.avnet.assetportal.webservice.assetservice.AssetType;

public class AssetTypeAdapter {

	private AssetManagerServicePortProxy webServiceAccess = null;
	
	private AMSAdminDelegate adminDelegate = new AMSAdminDelegate();

	private String id;
	private String name;
	private List<AttributeAdapter> attributeList;

	Map<String, String> assetTypeMap;

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

	public final List<AttributeAdapter> getAttributeList() {
		return attributeList;
	}

	public final void setAttributeList(List<AttributeAdapter> attributeList) {
		this.attributeList = attributeList;
	}

	Map<String, String> getAssetTypesList() {

		// initialising Service Access Object
		if (webServiceAccess == null) {
			webServiceAccess = (AssetManagerServicePortProxy) AMSWebServiceReflectionUtil
					.getProxyObject(AMSCommonConstants.ASSETMANAGERSERVICE);
		}

		List<AssetType> assetTypeList = adminDelegate
				.getValuesForAssetTypesList(webServiceAccess);
		// Map Key is Asset ID Map Value is Asset Name
		assetTypeMap = new HashMap<String, String>();

		// looping through AssetTypes list and putting it into Map
		// Map Key is Asset ID Map Value is Asset Name
		for (AssetType a : emptyIfNull(assetTypeList)) {
			assetTypeMap.put(a.getAssetTypeId(), a.getAssetTypeName());
		}
		return assetTypeMap;
	}

	public String getAssetTypeIdFromName(String name) {
		Map<String, String> assetTypeMap = getAssetTypesList();

		// Map Key is Asset ID Map Value is Asset Name
		for (Entry<String, String> entry : assetTypeMap.entrySet()) {
			if (entry.getValue().toLowerCase().equals(name.toLowerCase())) {
				return entry.getKey();
			}
		}
		return null;
	}

	public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
		return iterable == null ? Collections.<T> emptyList() : iterable;
	}

}

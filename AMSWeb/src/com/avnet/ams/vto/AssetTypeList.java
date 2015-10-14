/**
 * 
 */
package com.avnet.ams.vto;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Atechian
 *
 */
public class AssetTypeList {
	private static final String CLASS_NAME = AssetTypeList.class.getName();
	private static Logger logger = Logger.getLogger("AssetTypeList");
	private ArrayList<String> assetType;
	private ArrayList<String> assetId;

	/**
	 * @return
	 */
	public ArrayList<String> getAssetType() {
		final String METHOD_NAME = "getAssetType";
		logger.entering(CLASS_NAME, METHOD_NAME);
		
		return assetType;
	}

	/**
	 * @param assetType
	 */
	public void setAssetType(ArrayList<String> assetType) {
		final String METHOD_NAME = "setAssetType";
		logger.entering(CLASS_NAME, METHOD_NAME);
		this.assetType = assetType;
	}
	
	

	public ArrayList<String> getAssetId() {
		return assetId;
	}

	public void setAssetId(ArrayList<String> assetId) {
		this.assetId = assetId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final String METHOD_NAME = "toString";
		logger.entering(CLASS_NAME, METHOD_NAME);
		return "AssetTypeList [assetType=" + assetType + "]";
	}
	
	
}

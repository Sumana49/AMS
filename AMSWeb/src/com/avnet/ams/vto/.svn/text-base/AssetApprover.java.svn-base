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
public class AssetApprover {
	private static final String CLASS_NAME = AssetApprover.class.getName();
	private static Logger logger = Logger.getLogger("AssetApprover");
	
	private ArrayList<String> approverName;
	private ArrayList<String> approverId;

	/**
	 * @return the approverName
	 */
	public ArrayList<String> getApproverName() {
		final String METHOD_NAME = "getApproverName";
		logger.entering(CLASS_NAME, METHOD_NAME);
		return approverName;
	}

	/**
	 * @param approverName the approverName to set
	 */
	public void setApproverName(ArrayList<String> approverName) {
		final String METHOD_NAME = "setApproverName";
		logger.entering(CLASS_NAME, METHOD_NAME);
		this.approverName = approverName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final String METHOD_NAME = "toString";
		logger.entering(CLASS_NAME, METHOD_NAME);
		return "AssetApprover [approverName=" + approverName + "]";
	}

	public void setApproverId(ArrayList<String> approverId) {
		this.approverId = approverId;
	}

	public ArrayList<String> getApproverId() {
		return approverId;
	}
	
	
}

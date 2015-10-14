/**
 * 
 */
package com.avnet.ams.constants;

import com.avnet.assetportal.webservice.assetservice.SearchTypeEnum;
import com.avnet.assetportal.webservice.assetservice.SortColumnEnum;
import com.avnet.assetportal.webservice.common.SortOrderEnum;

/**
 * @author Atechian
 *
 */

public class AMSAdminConstants {
	public static final String ASSETID = "AssetID";
	public static final String ASSETIDENTITY = "AssetIdentity";
	public static final String EMPLOYEEID = "EmployeeId";
	public static final String EMPLOYEENAME = "EmployeeName";
	public static String OWNEDBY = "99999";
	public static String USERID = "UserID";
	public static String DESIGNATION = "Designation";
	public static String DATE_OF_ISSUE = "IssuedDate";
	public static String DATE_OF_RETURN = "ReturnDate";
	public static final String SHORT_TEXT = "ShortText";
	public static final int ADMIN_NOTES_SIZE = 500;
	public static final String ASSET_TYPE = "AssetType";
	public static final String OWNER = "Owner";
	public static final String PURCHASED_DATE = "PurchasedDate";
	public static final String ISSUED_DATE = "IssuedDate";
	public static final String[] REQUESTS_SORT_COLUMN={"USER_ID", "EMP_NAME", "CATEGORY_NAME", "ASSET_STATUS", "APPROVED_DATE"};
	public static final String[] REQUESTS_SORT_ORDER={"ASC,DESC"};
	public static final String[] ASSETS_SORT_COLUMN={"ASSET_ID","ASSET_TYPE","OWNER","PURCHASE_DATE"};
	public static final String[] ASSETS_SORT_ORDER={"ASC,DESC"};
	public static String[] ASSETS_HISTORY_SORT_COLUMN={"ISSUED_DATE","RETURNED_DATE","USER_ID","DESIGNATION","ASSET_ID","ASSET_TYPE"};
	public static final String[] ASSETS_EMPLOYEE={"ASSET_IDENTITY","ASSET_TYPE","ISSUED_DATE"};
	public static final String PRODUCT = "ProductName";
	public static final String EXPIRY_DATE = "ExpiryDate";
	static SortOrderEnum sortOrder = null; 
	static SortColumnEnum sortColumn = null;
	static com.avnet.assetportal.webservice.reportmanager.SortColumnEnum sortColumnReport = null;
	static SearchTypeEnum searchTypeValue = null;
	static com.avnet.assetportal.webservice.reportmanager.SearchTypeEnum searchTypeValueReport = null;
	
	public static final String DEP_SLM = "SLM";
	public static final String DEP_WDV = "WDV";
	public static final String FINANCE_COLUMN_NAMES="Asset ID|Product Name|Purchase Value|Accumulated Depreciation";
	public static final String BLANK_STRING = "";
	
	public static enum ASSIGNMENT_STATUS {
		ASSIGNED(1), UNASSIGNED(2), SERVICE(3), BROKEN(4), DEAD(5), REQUESTED(6), BU_APPROVED(7), 
		ADMIN_APPROVED(8), ISSUED(9), RETURNED(10), BU_REJECTED(11), ADMIN_REJECTED(12),
		ADMIN_HOLD(13);

		private int ord;

		ASSIGNMENT_STATUS(int ord) {
			this.ord = ord;
		}

		public int getOrd() {
			return this.ord;
		}
	}
	public static SortOrderEnum getOrderEnum(String dir){
		
		if(dir.equalsIgnoreCase("asc")){
			sortOrder = SortOrderEnum.ASC;
		}else if(dir.equalsIgnoreCase("desc")){
			sortOrder = SortOrderEnum.DESC;
		}
		
		return sortOrder;
		
	}
	public static SortColumnEnum getSortColumn(String column)
	{
		if(column.equalsIgnoreCase("0")){
			 sortColumn = SortColumnEnum.ASSET_IDENTITY;
		}else if(column.equalsIgnoreCase("1")){
			sortColumn = SortColumnEnum.ASSET_TYPE;
		}else if(column.equalsIgnoreCase("2")){
			sortColumn = SortColumnEnum.OWNER;
		}else{
			sortColumn = SortColumnEnum.PURCHASE_DATE;
		}
		return sortColumn;
	}
	public static com.avnet.assetportal.webservice.reportmanager.SortColumnEnum getSortColumnReport(String column , String searchType)
	{
		if(column.equalsIgnoreCase("0") && searchType.equalsIgnoreCase("USER_ID") ){
			sortColumnReport = com.avnet.assetportal.webservice.reportmanager.SortColumnEnum.ASSET_IDENTITY;
		}else if(column.equalsIgnoreCase("1") && searchType.equalsIgnoreCase("USER_ID")){
			sortColumnReport = com.avnet.assetportal.webservice.reportmanager.SortColumnEnum.ASSET_TYPE;
		}else if(column.equalsIgnoreCase("2") && searchType.equalsIgnoreCase("USER_ID")){
			sortColumnReport = com.avnet.assetportal.webservice.reportmanager.SortColumnEnum.ISSUED_DATE;
			
		}else if(column.equalsIgnoreCase("3") && searchType.equalsIgnoreCase("USER_ID")){
			
			sortColumnReport = com.avnet.assetportal.webservice.reportmanager.SortColumnEnum.RETURNED_DATE;
		}
		else if(column.equalsIgnoreCase("0") && searchType.equalsIgnoreCase("ASSET_ID")){
			
			sortColumnReport = com.avnet.assetportal.webservice.reportmanager.SortColumnEnum.USER_ID;
		}
		else if(column.equalsIgnoreCase("1") && searchType.equalsIgnoreCase("ASSET_ID")){
			
			sortColumnReport = com.avnet.assetportal.webservice.reportmanager.SortColumnEnum.DESIGNATION;
		}
		else if(column.equalsIgnoreCase("2") && searchType.equalsIgnoreCase("ASSET_ID")){
			
			sortColumnReport = com.avnet.assetportal.webservice.reportmanager.SortColumnEnum.ISSUED_DATE;
		}
		else if(column.equalsIgnoreCase("3") && searchType.equalsIgnoreCase("ASSET_ID")){
			
			sortColumnReport = com.avnet.assetportal.webservice.reportmanager.SortColumnEnum.RETURNED_DATE;
		}
		else
		{
			sortColumnReport = null;
		}
		return sortColumnReport;
	}

	public static SearchTypeEnum getSearchType(String searchType)
	{
		if(searchType.equalsIgnoreCase("ASSET_ID"))
		{
			searchTypeValue = SearchTypeEnum.ASSET_IDENTITY_ALL;
		}else if(searchType.equalsIgnoreCase("TYPE"))
		{
			searchTypeValue = SearchTypeEnum.TYPE;
		}
		
		else
		{
			searchTypeValue = null;
		}
		return searchTypeValue;
	}
	
	public static com.avnet.assetportal.webservice.reportmanager.SearchTypeEnum getSearchTypeReport(String searchType)
	{
		if(searchType.equalsIgnoreCase("ASSET_ID"))
		{
			searchTypeValueReport = com.avnet.assetportal.webservice.reportmanager.SearchTypeEnum.ASSET_IDENTITY_ALL;
		}else if(searchType.equalsIgnoreCase("USER_ID"))
		{
			searchTypeValueReport = com.avnet.assetportal.webservice.reportmanager.SearchTypeEnum.USER_ID;
		}else
		{
			searchTypeValueReport = null;
		}
		return searchTypeValueReport;
	}

}




/**
 * @author Atechian
 *
 */



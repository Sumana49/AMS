/**
 * 
 */
package com.avnet.ams.constants;

import com.avnet.assetportal.webservice.common.SortOrderEnum;
import com.avnet.assetportal.webservice.mail.RemindRequestToEnum;
import com.avnet.assetportal.webservice.request.FlowTypeEnum;
import com.avnet.assetportal.webservice.request.RequestSortTypeEnum;
import com.avnet.assetportal.webservice.request.SearchByEnum;

/**
 * @author Atechian
 *
 */
public class AMSUserConstants {
	public static final String REQUESTED = "Remind BU Head";
	public static final String APPROVED = "Remind Support Team";
	public static final String ISSUED = "None";
	static FlowTypeEnum flowType = null;
	static RemindRequestToEnum remindEnum = null;
	static SearchByEnum searchEnum = null;
	static SortOrderEnum sortOrder = null;
	static RequestSortTypeEnum sortType = null;
	
	static String searchValue = null;
	public static final String USER="user";
	public static final String SECURITY="security";
	public static final String REMINDBUHEAD = "Remind BU Head";
	public static final String REMINDSUPPORTTEAM = "Remind Support Team";
	public static final String CATEGORY="1";
	public static final String STATUS = "2";
	public static final String DATE = "3";
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static final String CATEGORY_NAME="1";
	public static final String REQUESTED_DATE="2";
	public static final String ASSET_STATUS="3";
	
	public static final String DEFAULT="default";
	public static final String ACK_MISSING="ack_missing";
	public static final String FIELDS_MISSING="fields_missing";
	
	public static RequestSortTypeEnum getSortEnum(String column){
		
		if(column.equalsIgnoreCase("1")){
			 sortType = RequestSortTypeEnum.CATEGORY_NAME;
		}else if(column.equalsIgnoreCase("2")){
			 sortType = RequestSortTypeEnum.REQUESTED_DATE;
		}else if(column.equalsIgnoreCase("3")){
			sortType = RequestSortTypeEnum.ASSET_STATUS;
		}else{
			sortType = RequestSortTypeEnum.REQUESTED_DATE;
		}
		
		
		return sortType;
	}
	
//	public static enum getSortEnum{
//		CATEGORY_NAME(RequestSortTypeEnum.CATEGORY_NAME),
//		REQUESTED_DATE(RequestSortTypeEnum.REQUESTED_DATE),
//		ASSET_STATUS(RequestSortTypeEnum.ASSET_STATUS),
//		DEFAULT(RequestSortTypeEnum.REQUESTED_DATE)
//		;
//		
//		private RequestSortTypeEnum text;
//		
//		private getSortEnum(RequestSortTypeEnum text){
//			this.text = text;
//		}
//		public RequestSortTypeEnum getText(){
//			return text;
//		}
//		
//	}
//	
	public static SortOrderEnum getOrderEnum(String dir){

		if(dir.equalsIgnoreCase("asc")){
		sortOrder = SortOrderEnum.ASC;
		}else if(dir.equalsIgnoreCase("desc")){
		sortOrder = SortOrderEnum.DESC;
		}
		
		
		return sortOrder;
		
		}

//	public static enum getOrderEnum{
//		ASC(SortOrderEnum.ASC),
//		DESC(SortOrderEnum.DESC)
//		;
//		
//		private SortOrderEnum text;
//		
//		private getOrderEnum(SortOrderEnum text){
//			this.text = text;
//		}
//		
//		public SortOrderEnum getText(){
//			return text;
//		}
//	}


	public static SearchByEnum getSearchEnum(String searchType) {
		
		if(searchType.contains("1")){
			searchEnum = SearchByEnum.CATEGORY_NAME;
		}else if(searchType.contains("2")){
			searchEnum = SearchByEnum.STATUS_NAME;
		}else if(searchType.contains("3")){
			searchEnum = SearchByEnum.REQUESTED_DATE;						
		}else{
			searchEnum = null;
		}
		
		return searchEnum;
	}

//	public static enum getSearchEnum{
//		CATEGORY(SearchByEnum.CATEGORY_NAME),
//		STATUS(SearchByEnum.STATUS_NAME),
//		DATE(SearchByEnum.REQUESTED_DATE)
//		;
//		
//		private SearchByEnum text;
//		
//		private getSearchEnum(SearchByEnum text){
//			this.text=text;
//		}
//		
//		public SearchByEnum getText(){
//			return text;
//		}
//	}
	
	public static String getSearchValue(String searchType, String searchValueParam) {
		
		if(searchValueParam.equalsIgnoreCase("")){
			searchValue = null;
		}else{
			
			searchValue = searchValueParam.substring(0,searchValueParam.length()-1).toString();
			
		}
		
		return searchValue;
	}
	
//	public static enum convertToEnum{
//		REMINDBUHEAD(RemindRequestToEnum.BU_HEAD),
//		REMINDSUPPORTTEAM(RemindRequestToEnum.SUPPORT_TEAM)
//		;
//		
//		private RemindRequestToEnum text;
//
//	    private convertToEnum( RemindRequestToEnum text) {
//	        this.text = text;
//	    }
//
//	    public  RemindRequestToEnum getText(){
//	    	return text;
//	    }
//	}
	// FIXME change to enum
	public static RemindRequestToEnum convertToEnum(String remindString){
		
		if(remindString.contains("BU")){
			remindEnum = RemindRequestToEnum.BU_HEAD;
		}else if(remindString.contains("Support")){
			remindEnum = RemindRequestToEnum.SUPPORT_TEAM;
		}
		
		return remindEnum;
		
	}
	
	public static FlowTypeEnum getFlowtypeEnum(String flow){
		if(flow.equalsIgnoreCase("user")){
			flowType = FlowTypeEnum.USER_FLOW;
		}else if(flow.equalsIgnoreCase("security")){
			flowType = FlowTypeEnum.SECURITY_FLOW;
		}else{
			flowType = null;
		}
		
		
		
		return flowType;
	}
	
//	public static enum getFlowtypeEnum {
//	    USER(FlowTypeEnum.USER_FLOW),
//	    SECURITY(FlowTypeEnum.SECURITY_FLOW)
//	    ;
//
//	    private FlowTypeEnum text;
//
//	    private getFlowtypeEnum( FlowTypeEnum text) {
//	        this.text = text;
//	    }
//
//	    public  FlowTypeEnum getText(){
//	    	return text;
//	    }
//	}

}



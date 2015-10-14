package com.avnet.ams.vto;

import java.util.List;

public class AddAssetVTO {
	
		private String assetType;
	    private String carry;
	    private String available;
	    private String procuredDate;
	    private String assetId;
	    
	    private List<AttributeType> attributeTypeList;
	    
		public final String getAssetType() {
			return assetType;
		}
		public final void setAssetType(String assetType) {
			this.assetType = assetType;
		}
		public final String getCarry() {
			return carry;
		}
		public final void setCarry(String carry) {
			this.carry = carry;
		}
		public final String getAvailable() {
			return available;
		}
		public final void setAvailable(String available) {
			this.available = available;
		}
		public final String getProcuredDate() {
			return procuredDate;
		}
		public final void setProcuredDate(String procuredDate) {
			this.procuredDate = procuredDate;
		}
		public final String getAssetId() {
			return assetId;
		}
		public final void setAssetId(String assetId) {
			this.assetId = assetId;
		}
		public final List<AttributeType> getAttributeTypeList() {
			return attributeTypeList;
		}
		public final void setAttributeTypeList(List<AttributeType> attributeTypeList) {
			this.attributeTypeList = attributeTypeList;
		}
	    

}

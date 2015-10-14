package com.avnet.ams.vto;


public class ViewAssetsAdminVTO {

	private String assetId;
	private String assetIdentity;
	private String assetType;
	private String owner;
	private String purchasedDate;
	private String issuedDate;
	private String shortText;
	
	 public String getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	int maxCount;
	
	
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public final String getAssetId() {
		return assetId;
	}
	public final void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public final String getAssetType() { 
		return assetType;
	}
	public final void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public final String getOwner() {
		return owner;
	}
	public final void setOwner(String owner) {
		this.owner = owner;
	}
	public void setPurchasedDate(String purchasedDate) {
		this.purchasedDate = purchasedDate;
	}
	public String getPurchasedDate() {
		return purchasedDate;
	}
	public void setAssetIdentity(String assetIdentity) {
		this.assetIdentity = assetIdentity;
	}
	public String getAssetIdentity() {
		return assetIdentity;
	}
	public void setShortText(String shortText) {
		this.shortText = shortText;
	}
	public String getShortText() {
		return shortText;
	}
	
	
	
}

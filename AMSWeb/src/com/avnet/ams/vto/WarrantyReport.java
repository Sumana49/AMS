package com.avnet.ams.vto;

public class WarrantyReport {
	private String ExpiryDate;
	private String AssetId;
	private String AssetType;
	private String ProductName;

	
	public void setAssetId(String assetId) {
		AssetId = assetId;
	}

	public String getAssetId() {
		return AssetId;
	}

	public void setAssetType(String assetType) {
		AssetType = assetType;
	}

	public String getAssetType() {
		return AssetType;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setExpiryDate(String expiryDate) {
		ExpiryDate = expiryDate;
	}

	public String getExpiryDate() {
		return ExpiryDate;
	}

}

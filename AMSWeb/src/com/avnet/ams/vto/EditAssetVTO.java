package com.avnet.ams.vto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dinesh
 */

public class EditAssetVTO {

	private String assetId;
	private String assetIdentity;
	private String assetType;
	private String ownedBy;
	private String ownerId;
	private String dateOfCapitalisation;
	private List<AttributeType> attributeTypeList;

	private boolean carry;
	private boolean available;

	Map<Integer, String> assetMap;
	Map<Integer, String> attributeMap;

	public EditAssetVTO() {

		attributeTypeList = new ArrayList<AttributeType>();

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

	public final String getOwnedBy() {
		return ownedBy;
	}

	public final void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}

	public final String getOwnerId() {
		return ownerId;
	}

	public final void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public final String getDateOfCapitalisation() {
		return dateOfCapitalisation;
	}

	public final void setDateOfCapitalisation(String dateOfCapitalisation) {
		this.dateOfCapitalisation = dateOfCapitalisation;
	}

	public final List<AttributeType> getAttributeTypeList() {
		return attributeTypeList;
	}

	public final void setAttributeTypeList(List<AttributeType> attributeTypeList) {
		this.attributeTypeList = attributeTypeList;
	}

	public final boolean isCarry() {
		return carry;
	}

	public final void setCarry(boolean carry) {
		this.carry = carry;
	}

	public final boolean isAvailable() {
		return available;
	}

	public final void setAvailable(boolean available) {
		this.available = available;
	}
	public String getAssetIdentity() {
		return assetIdentity;
	}

	public void setAssetIdentity(String assetIdentity) {
		this.assetIdentity = assetIdentity;
	}

}

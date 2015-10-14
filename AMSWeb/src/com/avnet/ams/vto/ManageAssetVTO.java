package com.avnet.ams.vto;

import java.util.HashMap;

public class ManageAssetVTO {
	
	private String method;
	private String carry;
	private String typeName;
	private String prefix;
	private String lifeTime;
	private String dropDown;
	private String dateOfPurchase;
	private String assetId;
	private String cost;
	private String serialNumber;
	private String model;
	private String hdd;
	private String assetLocation;
	private String warrantyDetails;
	private String insuranceExpiryDate;
	private String ram;
	private String lanMacAddress;
	private String wifiMacAddress;
	private String adaptorSerialNumber;
	private String specification;
	private String imeNumber;
	private String dataCardProviderName;
	private String batteryModel;
	private String insurance;
	private String simCardPhoneNumber;
	private String phoneNumber;
	private String warantyExpiryDate;
	private String adapterSerialNumber;
	private String invoiceNumber;
	private String invoiceDate;
	private String supplierName;
	private String productName;
	private String shortText;
	
	

	@Override
	public String toString() {
		return "ManageAssetVTO [method=" + method + ", carry=" + carry
				+ ", typeName=" + typeName + ", prefix=" + prefix
				+ ", lifeTime=" + lifeTime + ", dropDown=" + dropDown
				+ ", dateOfPurchase=" + dateOfPurchase + ", assetId=" + assetId
				+ ", cost=" + cost + ", serialNumber=" + serialNumber
				+ ", model=" + model + ", hdd=" + hdd + ", ram=" + ram
				+ ", lanMacAddress=" + lanMacAddress + ", wifiMacAddress="
				+ wifiMacAddress + ", adaptorSerialNumber="
				+ adaptorSerialNumber + ", specification=" + specification
				+ ", imeNumber=" + imeNumber + ", dataCardProviderName="
				+ dataCardProviderName + ", batteryModel=" + batteryModel
				+ ", insurance=" + insurance + ", simCardPhoneNumber="
				+ simCardPhoneNumber + ", phoneNumber=" + phoneNumber
				+ ", warantyExpiryDate=" + warantyExpiryDate
				+ ", adapterSerialNumber=" + adapterSerialNumber
				+ ", invoiceNumber=" + invoiceNumber + ", invoiceDate="
				+ invoiceDate + ", supplierName=" + supplierName
				+ ", productName=" + productName + ", shortText=" + shortText
				+ ", getInvoiceNumber()=" + getInvoiceNumber()
				+ ", getInvoiceDate()=" + getInvoiceDate()
				+ ", getSupplierName()=" + getSupplierName() + ", getMethod()="
				+ getMethod() + ", getCarry()=" + getCarry()
				+ ", getTypeName()=" + getTypeName() + ", getLifeTime()="
				+ getLifeTime() + ", getDropDown()=" + getDropDown()
				+ ", getDateOfPurchase()=" + getDateOfPurchase()
				+ ", getAssetId()=" + getAssetId() + ", getCost()=" + getCost()
				+ ", getSerialNumber()=" + getSerialNumber() + ", getModel()="
				+ getModel() + ", getHdd()=" + getHdd() + ", getRam()="
				+ getRam() + ", getLanMacAddress()=" + getLanMacAddress()
				+ ", getWifiMacAddress()=" + getWifiMacAddress()
				+ ", getAdaptorSerialNumber()=" + getAdaptorSerialNumber()
				+ ", getSpecification()=" + getSpecification()
				+ ", getBatteryModel()=" + getBatteryModel()
				+ ", getInsurance()=" + getInsurance() + ", getPhoneNumber()="
				+ getPhoneNumber() + ", getAllCheckedAttributes()="
				+ getAllCheckedAttributes() + ", getSimCardPhoneNumber()="
				+ getSimCardPhoneNumber() + ", getWarantyExpiryDate()="
				+ getWarantyExpiryDate() + ", getAdapterSerialNumber()="
				+ getAdapterSerialNumber() + ", getProductName()="
				+ getProductName() + ", getShortText()=" + getShortText()
				+ ", getImeNumber()=" + getImeNumber() + ", getPrefix()="
				+ getPrefix() + ", getDataCardProviderName()="
				+ getDataCardProviderName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCarry() {
		return carry;
	}
	public void setCarry(String carry) {
		this.carry = carry;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}
	public String getDropDown() {
		return dropDown;
	}
	public void setDropDown(String dropDown) {
		this.dropDown = dropDown;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getHdd() {
		return hdd;
	}
	public void setHdd(String hdd) {
		this.hdd = hdd;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getLanMacAddress() {
		return lanMacAddress;
	}
	public void setLanMacAddress(String lanMacAddress) {
		this.lanMacAddress = lanMacAddress;
	}
	public String getWifiMacAddress() {
		return wifiMacAddress;
	}
	public void setWifiMacAddress(String wifiMacAddress) {
		this.wifiMacAddress = wifiMacAddress;
	}
	public String getAdaptorSerialNumber() {
		return adaptorSerialNumber;
	}
	public void setAdaptorSerialNumber(String adaptorSerialNumber) {
		this.adaptorSerialNumber = adaptorSerialNumber;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	public String getBatteryModel() {
		return batteryModel;
	}
	public void setBatteryModel(String batteryModel) {
		this.batteryModel = batteryModel;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAssetLocation() {
		return assetLocation;
	}
	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}
	public String getWarrantyDetails() {
		return warrantyDetails;
	}
	public void setWarrantyDetails(String warrantyDetails) {
		this.warrantyDetails = warrantyDetails;
	}
	public String getInsuranceExpiryDate() {
		return insuranceExpiryDate;
	}
	public void setInsuranceExpiryDate(String insuranceExpiryDate) {
		this.insuranceExpiryDate = insuranceExpiryDate;
	}
	public void setSimCardPhoneNumber(String simCardPhoneNumber) {
		this.simCardPhoneNumber = simCardPhoneNumber;
	}
	public String getSimCardPhoneNumber() {
		return simCardPhoneNumber;
	}
	public void setWarantyExpiryDate(String warantyExpiryDate) {
		this.warantyExpiryDate = warantyExpiryDate;
	}
	public String getWarantyExpiryDate() {
		return warantyExpiryDate;
	}
	public void setAdapterSerialNumber(String adapterSerialNumber) {
		this.adapterSerialNumber = adapterSerialNumber;
	}
	public String getAdapterSerialNumber() {
		return adapterSerialNumber;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductName() {
		return productName;
	}
	public void setShortText(String shortText) {
		this.shortText = shortText;
	}
	public String getShortText() {
		return shortText;
	}
	public void setImeNumber(String imeNumber) {
		this.imeNumber = imeNumber;
	}
	public String getImeNumber() {
		return imeNumber;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setDataCardProviderName(String dataCardProviderName) {
		this.dataCardProviderName = dataCardProviderName;
	}
	public String getDataCardProviderName() {
		return dataCardProviderName;
	}
	public HashMap<String, String> getAllCheckedAttributes(){
		HashMap<String,String> allCheckedAttributesMap = new HashMap<String,String>();
		
		if(getAdaptorSerialNumber()!=null){
			allCheckedAttributesMap.put("adaptorSerialNumber", getAdaptorSerialNumber());
		}
		
		if(getAssetId()!=null){
			allCheckedAttributesMap.put("assetId", getAssetId());
		}
		if(getDataCardProviderName()!=null){
			allCheckedAttributesMap.put("dataCardProviderName", getDataCardProviderName());
		}
		if(getBatteryModel()!=null){
			allCheckedAttributesMap.put("batteryModel", getBatteryModel());
		}
		if(getCarry()!=null){
			allCheckedAttributesMap.put("carry", getCarry());
		}
	
		if(getDateOfPurchase()!=null){
			allCheckedAttributesMap.put("dateOfPurchase", getDateOfPurchase());
		}
		if(getDropDown()!=null){
			allCheckedAttributesMap.put("dropDown", getDropDown());
		}
		if(getHdd()!=null){
			allCheckedAttributesMap.put("hdd", getHdd());
		}
	
		if(getLanMacAddress()!=null){
			allCheckedAttributesMap.put("lanMacAddress", getLanMacAddress());
		}
		if(getLifeTime()!=null){
			allCheckedAttributesMap.put("lifeTime", getLifeTime());
		}
		if(getMethod()!=null){
			allCheckedAttributesMap.put("method", getMethod());
		}
		if(getModel()!=null){
			allCheckedAttributesMap.put("model", getModel());
		}

		if(getPhoneNumber()!=null){
			allCheckedAttributesMap.put("phoneNumber", getPhoneNumber());
		}
	
		if(getTypeName()!=null){
			allCheckedAttributesMap.put("typeName", getTypeName());
		}
		if(getWifiMacAddress()!=null){
			allCheckedAttributesMap.put("wifiMacAddress", getWifiMacAddress());
		}
		if(getRam()!=null){
			allCheckedAttributesMap.put("ram", getRam());
		}
		if(getAdapterSerialNumber()!=null){
			allCheckedAttributesMap.put("adapterSerialNumber", getAdapterSerialNumber());
		}
		
		if(getSimCardPhoneNumber()!=null){
			allCheckedAttributesMap.put("simCardPhoneNumber", getSimCardPhoneNumber());
		}
		if(getImeNumber()!=null){
			allCheckedAttributesMap.put("imeNumber", getImeNumber());
		}
		allCheckedAttributesMap.put("productName", "productName");
		allCheckedAttributesMap.put("shortText", "shortText");
		allCheckedAttributesMap.put("invoiceDate", "invoiceDate");
		allCheckedAttributesMap.put("invoiceNumber", "invoiceNumber");
		allCheckedAttributesMap.put("supplierName", "supplierName");
		allCheckedAttributesMap.put("specification", "specification");
		allCheckedAttributesMap.put("cost", "cost");
		allCheckedAttributesMap.put("specification", "specification");
		allCheckedAttributesMap.put("assetLocation", "assetLocation");
		allCheckedAttributesMap.put("serialNumber", "serialNumber");
		allCheckedAttributesMap.put("warrantyExpiryDate", "warrantyExpiryDate");
		allCheckedAttributesMap.put("warrantyDetails", "warrantyDetails");
		allCheckedAttributesMap.put("insurance", "insurance");
		allCheckedAttributesMap.put("insuranceExpiryDate", "insuranceExpiryDate");
		
		
		
		return allCheckedAttributesMap;
	}
	
	
}

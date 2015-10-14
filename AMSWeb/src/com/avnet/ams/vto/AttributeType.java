package com.avnet.ams.vto;

public class AttributeType {

	private String id;
	private String fieldName;
	private String labelName;
	private String value;
	private String unit;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getFieldName() {
		return fieldName;
	}
	public final void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public final String getLabelName() {
		return labelName;
	}
	public final void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public final String getValue() {
		return value;
	}
	public final void setValue(String value) {
		this.value = value;
	}
	
	
}

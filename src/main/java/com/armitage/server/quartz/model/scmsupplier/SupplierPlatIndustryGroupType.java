package com.armitage.server.quartz.model.scmsupplier;

public class SupplierPlatIndustryGroupType {
	
	private boolean required;
	private String typeCode;
	private String typeCodeName;
	
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeCodeName() {
		return typeCodeName;
	}
	public void setTypeCodeName(String typeCodeName) {
		this.typeCodeName = typeCodeName;
	}

}

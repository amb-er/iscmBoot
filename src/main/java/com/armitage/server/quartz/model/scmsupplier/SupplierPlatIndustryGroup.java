package com.armitage.server.quartz.model.scmsupplier;

import java.util.List;

public class SupplierPlatIndustryGroup {
	
	private long baId;
	private String groupCode;
	private String groupCodeName;
	private List<SupplierPlatIndustryGroupType> qualifieTypeList;
	public long getBaId() {
		return baId;
	}
	public void setBaId(long baId) {
		this.baId = baId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupCodeName() {
		return groupCodeName;
	}
	public void setGroupCodeName(String groupCodeName) {
		this.groupCodeName = groupCodeName;
	}
	public List<SupplierPlatIndustryGroupType> getQualifieTypeList() {
		return qualifieTypeList;
	}
	public void setQualifieTypeList(List<SupplierPlatIndustryGroupType> qualifieTypeList) {
		this.qualifieTypeList = qualifieTypeList;
	}
}

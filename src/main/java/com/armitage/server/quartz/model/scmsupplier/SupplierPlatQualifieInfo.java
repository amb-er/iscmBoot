package com.armitage.server.quartz.model.scmsupplier;

import java.util.List;

public class SupplierPlatQualifieInfo {
	
	private long baId;
	private long demanderId;
	private List<SupplierPlatQualifieInfoType> qualifieList;
	public long getBaId() {
		return baId;
	}
	public void setBaId(long baId) {
		this.baId = baId;
	}
	public long getDemanderId() {
		return demanderId;
	}
	public void setDemanderId(long demanderId) {
		this.demanderId = demanderId;
	}
	public List<SupplierPlatQualifieInfoType> getQualifieList() {
		return qualifieList;
	}
	public void setQualifieList(List<SupplierPlatQualifieInfoType> qualifieList) {
		this.qualifieList = qualifieList;
	}
	
}

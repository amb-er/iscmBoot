package com.armitage.server.quartz.model.controlunit;

import java.util.List;

public class SupplierPlatPurchase {

	private long baId;
	private List<SupplierPlatPurchaseEntry> orgPurchaseList;
	
	public long getBaId() {
		return baId;
	}
	public void setBaId(long baId) {
		this.baId = baId;
	}
	public List<SupplierPlatPurchaseEntry> getOrgPurchaseList() {
		return orgPurchaseList;
	}
	public void setOrgPurchaseList(List<SupplierPlatPurchaseEntry> orgPurchaseList) {
		this.orgPurchaseList = orgPurchaseList;
	}
	
}

package com.armitage.server.quartz.model.controlunit;

import java.util.List;

public class SupplierPlatStorage {

	private long baId;
	private List<SupplierPlatStorageEntry> orgStorageList;
	
	public long getBaId() {
		return baId;
	}
	public void setBaId(long baId) {
		this.baId = baId;
	}
	public List<SupplierPlatStorageEntry> getOrgStorageList() {
		return orgStorageList;
	}
	public void setOrgStorageList(List<SupplierPlatStorageEntry> orgStorageList) {
		this.orgStorageList = orgStorageList;
	}
}

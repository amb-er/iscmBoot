package com.armitage.server.iscm.inventorymanage.inventoryinitialization.model;

import java.util.List;

public class ScmInvInitBillImPort {
    private long wareHouseId;
    private String wareHouseName;
    private List<ScmInvInitBillImPortDetail> detailList;
    
	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public List<ScmInvInitBillImPortDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ScmInvInitBillImPortDetail> detailList) {
		this.detailList = detailList;
	}

}

package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;

public class ScmCstInitBillImPort {
    private String orgUnitNo;
    private String orgUnitName;
    private List<ScmCstInitBillImPortDetail> detailList;
    
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public List<ScmCstInitBillImPortDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ScmCstInitBillImPortDetail> detailList) {
		this.detailList = detailList;
	}

}

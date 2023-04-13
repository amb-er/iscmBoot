package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.Date;
import java.util.List;

public class ScmInvCostConsumeImPort {
    private String useOrgUnitNo;
    private String useOrgUnitName;
    private Date bizDate;
    private String dcNo;
    private String remarks;
    private String finOrgUnitNO;
    private List<ScmInvCostConsumeImPortDetail> detailList;
    
    public String getUseOrgUnitNo() {
        return useOrgUnitNo;
    }

    public void setUseOrgUnitNo(String useOrgUnitNo) {
        this.useOrgUnitNo = useOrgUnitNo;
    }

    public String getUseOrgUnitName() {
        return useOrgUnitName;
    }

    public void setUseOrgUnitName(String useOrgUnitName) {
        this.useOrgUnitName = useOrgUnitName;
    }

    public List<ScmInvCostConsumeImPortDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ScmInvCostConsumeImPortDetail> detailList) {
        this.detailList = detailList;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getDcNo() {
        return dcNo;
    }

    public void setDcNo(String dcNo) {
        this.dcNo = dcNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public String getFinOrgUnitNO() {
		return finOrgUnitNO;
	}

	public void setFinOrgUnitNO(String finOrgUnitNO) {
		this.finOrgUnitNO = finOrgUnitNO;
	}

    
}

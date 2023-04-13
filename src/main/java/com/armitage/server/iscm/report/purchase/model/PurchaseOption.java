package com.armitage.server.iscm.report.purchase.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

/**
 * 
 * 采购单据打印账单处预先设置一些参数。
 * 
 */
public class PurchaseOption extends BaseModel {
	private String billType;
	private String billNo;
	private String orgUnitNo;
	
    public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String orgUnitNo) {
        this.orgUnitNo = orgUnitNo;
    }

    @Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	public PurchaseOption(boolean defaultValue) {
		if (defaultValue) {

		}
	}

	public PurchaseOption() {

	}

}

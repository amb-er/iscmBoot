package com.armitage.server.iscm.basedata.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmsupplierAdvQuery extends BaseModel{
	private String mixParam;
	private String vendorNo;
	private String vendorName;

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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
	
	
}

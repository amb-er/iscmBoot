package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurPriceSupplier extends BaseModel{

	private static final String FN_VENDORNo = "vendorNo";
	private static final String FN_VENDORNAME = "vendorName";
	private static final String FN_MNEMONICCODE = "mnemonicCode";
	
	private String vendorNo;
	private String vendorName;
	private String mnemonicCode;
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
	public String getMnemonicCode() {
		return mnemonicCode;
	}
	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
	}
	public ScmPurPriceSupplier() {
		
	}
	public ScmPurPriceSupplier(boolean defaultValue){
		if(defaultValue){
		}
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

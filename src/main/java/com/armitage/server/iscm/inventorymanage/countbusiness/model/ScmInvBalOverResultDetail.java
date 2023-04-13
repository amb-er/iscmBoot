package com.armitage.server.iscm.inventorymanage.countbusiness.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvBalOverResultDetail extends BaseModel{
	public static final String FN_BILLTYPE = "billType";

	private String billType;	//单据类型
	private String msgInfo;
	private List<Long> list;
	
	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}

	public ScmInvBalOverResultDetail() {
		super();
	}

	public ScmInvBalOverResultDetail(boolean defaultValue) {
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

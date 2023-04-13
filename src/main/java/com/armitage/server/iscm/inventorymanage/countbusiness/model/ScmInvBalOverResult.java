package com.armitage.server.iscm.inventorymanage.countbusiness.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvBalOverResult extends BaseModel{
	public static final String FN_ERRORCODE = "errorCode";
	public static final String FN_MSGINFO = "msgInfo";

	private String type;		//类型：1、结转；2、反结转
	private String errorCode;	//错误代码
	private String msgInfo;		//信息
	private List<ScmInvBalOverResultDetail> detailList;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public List<ScmInvBalOverResultDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ScmInvBalOverResultDetail> detailList) {
		this.detailList = detailList;
	}

	public ScmInvBalOverResult() {
		super();
	}

	public ScmInvBalOverResult(boolean defaultValue) {
		if(defaultValue){
			this.errorCode="0";
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

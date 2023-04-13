package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class CommonAuditDetailParams extends BaseModel{
	private int lineId;
	private String rowOpinion;
	private String remarks;
	
	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getRowOpinion() {
		return rowOpinion;
	}

	public void setRowOpinion(String rowOpinion) {
		this.rowOpinion = rowOpinion;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

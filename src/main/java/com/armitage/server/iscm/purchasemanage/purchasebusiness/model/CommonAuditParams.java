package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class CommonAuditParams extends BaseModel {
	private long billId;
	private String billNo;
	private String opinion;
	private String opinionRemarks;
	private List<CommonAuditDetailParams> detailList;

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getOpinionRemarks() {
		return opinionRemarks;
	}

	public void setOpinionRemarks(String opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}

	public List<CommonAuditDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<CommonAuditDetailParams> detailList) {
		this.detailList = detailList;
	}

	public CommonAuditParams() {
		super();
	}

	public CommonAuditParams(boolean defaultValue) {
		if (defaultValue) {
			this.opinion="Y";
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

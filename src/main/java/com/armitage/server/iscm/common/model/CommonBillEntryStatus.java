package com.armitage.server.iscm.common.model;

public class CommonBillEntryStatus{
	private int lineId;
	private String rowStatus;
	
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public String getRowStatus() {
		return rowStatus;
	}
	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
}

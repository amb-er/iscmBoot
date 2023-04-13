package com.armitage.server.iscm.purchasemanage.reportParam.model;

public class ReportSelectData {
	
	private String name;
	private String id;
	private String pId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public ReportSelectData(String name, String id, String pId) {
		super();
		this.name = name;
		this.id = id;
		this.pId = pId;
	}
	public ReportSelectData() {
		super();
	}
}

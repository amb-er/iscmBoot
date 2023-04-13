package com.armitage.server.iscm.common.model;

public class CommonEventHistory2 extends CommonEventHistory {
	private String operName;
	private String activeTypeName;
	private String opinionName;

	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getActiveTypeName() {
		return activeTypeName;
	}
	public void setActiveTypeName(String activeTypeName) {
		this.activeTypeName = activeTypeName;
	}
	public String getOpinionName() {
		return opinionName;
	}
	public void setOpinionName(String opinionName) {
		this.opinionName = opinionName;
	}
	public CommonEventHistory2() {
		super();
	}
	public CommonEventHistory2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue) {
		}
	}
}

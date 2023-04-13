package com.armitage.server.iscm.common.model;

public class ScmAuditDetailHistory2 extends ScmAuditDetailHistory{
	private String operName;
	private String opinionName;

	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getOpinionName() {
		return opinionName;
	}
	public void setOpinionName(String opinionName) {
		this.opinionName = opinionName;
	}
	public ScmAuditDetailHistory2() {
		super();
	}
	public ScmAuditDetailHistory2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue) {
		}
	}
}

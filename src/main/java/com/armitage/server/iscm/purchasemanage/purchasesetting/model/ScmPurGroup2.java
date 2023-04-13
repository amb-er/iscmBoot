package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

public class ScmPurGroup2 extends ScmPurGroup{
	
	public static final String FN_PARENTNAME ="parentName";
	
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public ScmPurGroup2() {
		super();
	}
	public ScmPurGroup2(boolean defaultValue) {
		super(defaultValue);
	}
	
}

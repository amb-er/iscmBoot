package com.armitage.server.iscm.inventorymanage.countbusiness.model;

public class ScmInvCountingListMaterialGroup2 extends ScmInvCountingListMaterialGroup{
	
	public static final String FN_CLASSCODE = "classCode";
	public static final String FN_CLASSNAME = "className";
	
	private String classCode;
	private String className;
	
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

}

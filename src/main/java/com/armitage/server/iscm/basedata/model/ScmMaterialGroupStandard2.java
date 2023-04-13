package com.armitage.server.iscm.basedata.model;

public class ScmMaterialGroupStandard2 extends ScmMaterialGroupStandard {
	public static final String FN_CLASSID ="classId";
	
	private long classId;

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public ScmMaterialGroupStandard2(boolean defaultValue) {
		super(defaultValue);
	}
	
	public ScmMaterialGroupStandard2(){
		super();
	}
}

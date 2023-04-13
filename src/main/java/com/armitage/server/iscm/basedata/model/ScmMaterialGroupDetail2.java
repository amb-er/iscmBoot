package com.armitage.server.iscm.basedata.model;

public class ScmMaterialGroupDetail2 extends ScmMaterialGroupDetail {
    public static final String FN_STANDARDTYPE ="standardType";
	
    private String standardType;

	public String getStandardType() {
		return standardType;
	}

	public void setStandardType(String standardType) {
		this.standardType = standardType;
	}

	public ScmMaterialGroupDetail2(boolean defaultValue) {
		super(defaultValue);
	}
	
	public ScmMaterialGroupDetail2(){
		super();
	}
	
}

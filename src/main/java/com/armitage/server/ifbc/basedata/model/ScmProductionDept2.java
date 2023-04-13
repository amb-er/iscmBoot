package com.armitage.server.ifbc.basedata.model;

public class ScmProductionDept2 extends ScmProductionDept{
    public static final String FN_CODE ="code";
   
    private String code;
    private String type;
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ScmProductionDept2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmProductionDept2() {
		super();
	}
}

package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

public class ScmInvWareHouse2 extends ScmInvWareHouse{
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

	public ScmInvWareHouse2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvWareHouse2() {
		super();
	}
}

package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

public class ScmInvWareHouseUsr2 extends ScmInvWareHouseUsr{

    public static final String FN_USRWHNAME ="usrName";
    
    private String usrName;
    
	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public ScmInvWareHouseUsr2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvWareHouseUsr2() {
		super();
	}
}

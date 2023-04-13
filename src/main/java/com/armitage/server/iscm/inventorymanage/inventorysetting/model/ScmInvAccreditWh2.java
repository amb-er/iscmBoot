package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

public class ScmInvAccreditWh2 extends ScmInvAccreditWh{
	public static final String FN_WHNO ="whNo";
    public static final String FN_WHNAME ="whName";
    public static final String FN_USEPERMISSION ="usePermission";
   
    private String whNo;
    private String whName;
    private boolean usePermission;
   
	public String getWhNo() {
		return whNo;
	}
	public void setWhNo(String whNo) {
		this.whNo = whNo;
	}
	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
	}
    
	public boolean isUsePermission() {
		return usePermission;
	}
	public void setUsePermission(boolean usePermission) {
		this.usePermission = usePermission;
	}
	public ScmInvAccreditWh2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvAccreditWh2() {
		super();
	}
}

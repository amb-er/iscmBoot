package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

public class ScmInvCountingListUserOrg2 extends ScmInvCountingListUserOrg {
	public static final String FN_USEORGUNITNONAME ="useOrgUnitNoName";
	
	private String useOrgUnitNoName;
	
    public String getUseOrgUnitNoName() {
		return useOrgUnitNoName;
	}

	public void setUseOrgUnitNoName(String useOrgUnitNoName) {
		this.useOrgUnitNoName = useOrgUnitNoName;
	}

	public ScmInvCountingListUserOrg2() {
        super();
    }

    public ScmInvCountingListUserOrg2(boolean defaultValue) {
        super(defaultValue);
    }
}

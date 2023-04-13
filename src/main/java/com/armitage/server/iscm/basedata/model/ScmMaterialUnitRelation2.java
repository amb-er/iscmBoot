package com.armitage.server.iscm.basedata.model;

public class ScmMaterialUnitRelation2 extends ScmMaterialUnitRelation {
    public static final String FN_UNITNo ="unitNo";
    public static final String FN_UNITNAME ="unitName";

    private String unitNo;
	private String unitName;
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public ScmMaterialUnitRelation2(boolean defaultValue) {
		super(defaultValue);
	}
	
	public ScmMaterialUnitRelation2(){
		super();
	}
}

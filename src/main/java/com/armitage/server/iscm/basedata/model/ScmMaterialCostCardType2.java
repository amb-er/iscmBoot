package com.armitage.server.iscm.basedata.model;

public class ScmMaterialCostCardType2 extends ScmMaterialCostCardType {
	public static final String FN_CSTUNITNAME = "cstUnitName";			//成本核算计量单位名称
	
	private String cstUnitName;
	private String unitName;

	public String getCstUnitName() {
		return cstUnitName;
	}

	public void setCstUnitName(String cstUnitName) {
		this.cstUnitName = cstUnitName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public ScmMaterialCostCardType2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}
	
	public ScmMaterialCostCardType2(){
		super();
	}
}

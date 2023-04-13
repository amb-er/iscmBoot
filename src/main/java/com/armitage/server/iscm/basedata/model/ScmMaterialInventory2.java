package com.armitage.server.iscm.basedata.model;


public class ScmMaterialInventory2 extends ScmMaterialInventory {
	public static final String FN_SALETAXRATESTR = "saleTaxRateStr";	//采购资料税率
	
	private String saleTaxRateStr;
	
	public String getSaleTaxRateStr() {
		return saleTaxRateStr;
	}

	public void setSaleTaxRateStr(String saleTaxRateStr) {
		this.saleTaxRateStr = saleTaxRateStr;
	}

	public ScmMaterialInventory2(boolean defaultValue) {
		super(defaultValue);
	}
	
	public ScmMaterialInventory2(){
		super();
	}
}

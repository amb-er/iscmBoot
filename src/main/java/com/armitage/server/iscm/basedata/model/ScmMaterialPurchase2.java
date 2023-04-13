package com.armitage.server.iscm.basedata.model;


public class ScmMaterialPurchase2 extends ScmMaterialPurchase {
    public static final String FN_TAXRATE ="taxRate";
	private String taxRate;
	
	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public ScmMaterialPurchase2(boolean defaultValue) {
		super(defaultValue);
	}
	
	public ScmMaterialPurchase2(){
		super();
	}
}

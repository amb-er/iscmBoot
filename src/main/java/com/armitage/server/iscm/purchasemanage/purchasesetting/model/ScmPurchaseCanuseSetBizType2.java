package com.armitage.server.iscm.purchasemanage.purchasesetting.model;


public class ScmPurchaseCanuseSetBizType2 extends ScmPurchaseCanuseSetBizType{

    public static final String FN_NAME ="name";
	
    private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ScmPurchaseCanuseSetBizType2() {
		super();
	}
	public ScmPurchaseCanuseSetBizType2(boolean defaultValue) {
		super(defaultValue);
	}
}

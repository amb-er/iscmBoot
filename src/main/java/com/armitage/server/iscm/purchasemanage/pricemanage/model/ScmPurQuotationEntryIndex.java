package com.armitage.server.iscm.purchasemanage.pricemanage.model;

public enum ScmPurQuotationEntryIndex {
	ITEM_NO(0, "物资编码"), 
	SPEC(2, "品名规格"), 
	PUR_UNIT_NAME(4, "采购单位"), 
	TAX_RATE(6, "税率"), 
	TAX_PRICE(7, "含税报价"), 
	REMARKS(10, "备注");
	

	/**
	 * Excel表第x列（从0开始）
	 */
	private int x;
	/**
	 * 描述信息
	 */
	private String desc;

	private ScmPurQuotationEntryIndex(int x, String desc) {
		this.x = x;
		this.desc = desc;
	}

	public int getX() {
		return x;
	}

	public String getDesc() {
		return desc;
	}
}

package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

public enum ScmInvSalePriceEntryIndex {
	ITEM_NO(0, "物资编码"), 
	SPEC(2, "品名规格"), 
	PUR_UNIT_NAME(4, "采购单位"), 
	SALE_TAX_PRICE(6, "本期销售含税定价"), 
	REMARKS(8, "备注");
	

	/**
	 * Excel表第x列（从0开始）
	 */
	private int x;
	/**
	 * 描述信息
	 */
	private String desc;

	private ScmInvSalePriceEntryIndex(int x, String desc) {
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

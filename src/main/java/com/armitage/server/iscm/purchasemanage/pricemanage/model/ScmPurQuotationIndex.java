package com.armitage.server.iscm.purchasemanage.pricemanage.model;

public enum ScmPurQuotationIndex {
	VENDOR_CODE(2, 1, "供应商编号"), 
	PQ_NO(12, 1, "报价单号"), 
	VENDOR_NAME(2, 2, "供应商名称"), 
	CREATE_DATE(12, 2, "制单日期"), 
	BEG_DATE(2, 3, "有效期起"), 
	END_DATE(6, 3, "有效期止"), 
	PQ_DATE(12, 3, "报价日期"), 
	REMARKS(2, 4, "备注"),
	BUYER_CODE(1, -1,"采购员"),
	CREATOR(5, -1,"制单人"),
	PRINT_DATE(11, -1,"打印日期");

	/**
	 * Excel表第x列（从0开始，-1表示不固定）
	 */
	private int x;
	/**
	 * Excel表第y行（从0开始，-1表示不固定）
	 */
	private int y;
	/**
	 * 描述信息
	 */
	private String desc;

	private ScmPurQuotationIndex(int x, int y, String desc) {
		this.x = x;
		this.y = y;
		this.desc = desc;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getDesc() {
		return desc;
	}
}

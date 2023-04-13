package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

public enum ScmInvSalePriceIndex {
	PM_NO(2, 1, "报价单号"), 
	CREATE_DATE(12, 2, "制单日期"), 
	BEG_DATE(2, 2, "有效期起"), 
	END_DATE(6, 2, "有效期止"), 
	PM_DATE(10, 2, "定价日期"), 
	REMARKS(2, 3, "备注"),
	BUYER_CODE(1, -1,"定价员"),
	CREATOR(5, -1,"制单人"),
	PRINT_DATE(9, -1,"打印日期");

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

	private ScmInvSalePriceIndex(int x, int y, String desc) {
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

package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;

public class ScmStandardRatio2 extends ScmStandardRatio{
	
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_NETITEMID = "netItemId";
	public static final String FN_WOOLQTY ="woolQty";
	public static final String FN_NETRATE = "netRate";
	
    private String itemNo;
    private String itemName;
	private long netItemId;
    private BigDecimal woolQty;
	private BigDecimal netRate;
	
	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getNetItemId() {
		return netItemId;
	}

	public void setNetItemId(long netItemId) {
		this.netItemId = netItemId;
	}

	public BigDecimal getWoolQty() {
		return woolQty;
	}

	public void setWoolQty(BigDecimal woolQty) {
		this.woolQty = woolQty;
	}

	public BigDecimal getNetRate() {
		return netRate;
	}

	public void setNetRate(BigDecimal netRate) {
		this.netRate = netRate;
	}

	public ScmStandardRatio2() {
		super();
	}

	public ScmStandardRatio2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.woolQty=BigDecimal.ZERO;
			this.netRate=new BigDecimal("100");
		}
	}
	
	public String[] getRequiredFields() {
	    /*DEMO:
		return new String[] {FN_CODE };
        */
		return new String[] {FN_ITEMNO,FN_INVUNIT,FN_QTY,FN_ITEMID };
	}
}

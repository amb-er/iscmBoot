package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;


public class ScmPurCheck2 extends ScmPurCheck {
    public static final String FN_VENDORID = "vendorId";
    public static final String FN_VENDORNAME = "vendorName";
    public static final String FN_INVORGUNITNO = "invOrgUnitNo";
    public static final String FN_TOTALCHECKAMT = "totalCheckAmt";
    
    private long vendorId;
    private String vendorName;
    private String invOrgUnitNo;
	private BigDecimal totalCheckAmt;	//验收单合计金额
    
    public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    
    public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

	public BigDecimal getTotalCheckAmt() {
		return totalCheckAmt;
	}

	public void setTotalCheckAmt(BigDecimal totalCheckAmt) {
		this.totalCheckAmt = totalCheckAmt;
	}

	public ScmPurCheck2(boolean defaultValue) {
        super(defaultValue);
    }

    public ScmPurCheck2() {
        super();
    }
}

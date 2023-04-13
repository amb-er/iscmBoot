package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmPurReturns2 extends ScmPurReturns{
	public static final String FN_VENDORNAME = "vendorName";
	public static final String FN_ORGUNITNAME = "orgUnitName";
	public static final String FN_VENDORCODE = "vendorCode";
	public static final String FN_PURORGUNITNAME = "purOrgUnitName";
	public static final String FN_BIZTYPENAME = "bizTypeName";
	public static final String FN_CREATORNAME = "creatorName";
	public static final String FN_EDITORNAME = "editorName";
	public static final String FN_CHECKERNAME = "checkerName";
	public static final String FN_STATUSNAME = "statusName";
	public static final String FN_PENDINGUSR = "pendingUsr";
	public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	public static final String FN_TAXAMOUNT = "taxAmount";
	
	private String vendorName;
	private String orgUnitName;
	private boolean existsSource;
	/*
	 * 采购情况退货表 
	 */
	private String itemName;
	private String itemNo;
	private String unitName;
	private BigDecimal qty;
	private BigDecimal taxPrice;
	private BigDecimal taxAmt;
	private String invOrgUnitNo;
	private long unit;
	private String invOrgUnitName;
	private BigDecimal totalAmt;
	private BigDecimal totalQty;
	private String ftype;
	private BigDecimal amt;
	private BigDecimal outQty;
	private BigDecimal outAmt;
	private BigDecimal outTaxAmt;
	private BigDecimal totalTaxAmt;
    private BigDecimal totalOutQty;
    private BigDecimal totalOutAmt;
    private BigDecimal totalOutTaxAmt;
    private long itemId;
    private String vendorCode;
    private String purOrgUnitName;
    private String bizTypeName;
    private String creatorName;
    private String editorName;
    private String checkerName;
    private String statusName;
    private String pendingUsr;
    private String pendingUsrName;
    private BigDecimal taxAmount;
    private List<ScmPurReturnsEntry2> scmPurReturnsEntryList;
	
	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public long getUnit() {
        return unit;
    }

    public void setUnit(long unit) {
        this.unit = unit;
    }

    public String getInvOrgUnitName() {
        return invOrgUnitName;
    }

    public void setInvOrgUnitName(String invOrgUnitName) {
        this.invOrgUnitName = invOrgUnitName;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(BigDecimal totalQty) {
        this.totalQty = totalQty;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public BigDecimal getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public BigDecimal getTotalOutQty() {
        return totalOutQty;
    }

    public void setTotalOutQty(BigDecimal totalOutQty) {
        this.totalOutQty = totalOutQty;
    }

    public BigDecimal getTotalOutAmt() {
        return totalOutAmt;
    }

    public void setTotalOutAmt(BigDecimal totalOutAmt) {
        this.totalOutAmt = totalOutAmt;
    }

    public BigDecimal getTotalOutTaxAmt() {
        return totalOutTaxAmt;
    }

    public void setTotalOutTaxAmt(BigDecimal totalOutTaxAmt) {
        this.totalOutTaxAmt = totalOutTaxAmt;
    }

    public BigDecimal getOutQty() {
        return outQty;
    }

    public void setOutQty(BigDecimal outQty) {
        this.outQty = outQty;
    }

    public BigDecimal getOutAmt() {
        return outAmt;
    }

    public void setOutAmt(BigDecimal outAmt) {
        this.outAmt = outAmt;
    }

    public BigDecimal getOutTaxAmt() {
        return outTaxAmt;
    }

    public void setOutTaxAmt(BigDecimal outTaxAmt) {
        this.outTaxAmt = outTaxAmt;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public boolean isExistsSource() {
		return existsSource;
	}

	public void setExistsSource(boolean existsSource) {
		this.existsSource = existsSource;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getPurOrgUnitName() {
		return purOrgUnitName;
	}

	public void setPurOrgUnitName(String purOrgUnitName) {
		this.purOrgUnitName = purOrgUnitName;
	}

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPendingUsr() {
		return pendingUsr;
	}

	public void setPendingUsr(String pendingUsr) {
		this.pendingUsr = pendingUsr;
	}

	public String getPendingUsrName() {
		return pendingUsrName;
	}

	public void setPendingUsrName(String pendingUsrName) {
		this.pendingUsrName = pendingUsrName;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public List<ScmPurReturnsEntry2> getScmPurReturnsEntryList() {
		return scmPurReturnsEntryList;
	}

	public void setScmPurReturnsEntryList(
			List<ScmPurReturnsEntry2> scmPurReturnsEntryList) {
		this.scmPurReturnsEntryList = scmPurReturnsEntryList;
	}

	public ScmPurReturns2(boolean defaultValue) {
		super(defaultValue);
	}
	
	public ScmPurReturns2() {
		super();
	}
}
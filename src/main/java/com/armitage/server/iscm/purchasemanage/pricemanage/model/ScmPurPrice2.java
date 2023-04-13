package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmPurPrice2 extends ScmPurPrice{

	public static final String FN_VENDORNAME = "vendorName";
	public static final String FN_VENDORNAME1 = "vendorName1";
	public static final String FN_VENDORNAME2 = "vendorName2";
	public static final String FN_VENDORNAME3 = "vendorName3";
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_PRICE = "price";
	public static final String FN_PRICETYPE = "priceType";
	public static final String FN_PQNO = "pqNo";
	public static final String FN_BILLNO = "billNo";
	public static final String FN_SELVNDRCODE = "selVndrCode";
	public static final String FN_SELVNDRNAME = "selVndrName";
	public static final String FN_ORGUNITNAME = "orgUnitName";
	public static final String FN_CREATORNAME = "creatorName";
	public static final String FN_EDITORNAME = "editorName";
	public static final String FN_CHECKERNAME = "checkerName";
	public static final String FN_STATUSNAME = "statusName";
	public static final String FN_PENDINGUSR = "pendingUsr";
	public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	
	private String vendorName;
	private String vendorName1;
	private String vendorName2;
	private String vendorName3;
	private String groupName1;
	private String groupName2;
	private String groupName3;
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnit;
	private String unitName;
	private String purOrgUnitNo;
	private BigDecimal price;
	private BigDecimal taxRate;
	private String priceType;
	private String pqNo;
	private String billNo;
	private BigDecimal totalAmt;
	
	private String selVndrCode;
	private String selVndrName;
	private String orgUnitName;
	private String creatorName;
	private String editorName;
	private String checkerName;
	private String statusName;
	private String priceOfficer;
	private String pendingUsr;
	private String pendingUsrName;
	private String selVndrIdDtl;
	private String selVndrIdDtlName;
	private String finOrgUnitName;
	private String businessQuotationSign;
	
	private List<ScmPurPriceEntry2> scmPurPriceEntryList;
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public String getGroupName1() {
		return groupName1;
	}

	public void setGroupName1(String groupName1) {
		this.groupName1 = groupName1;
	}

	public String getGroupName2() {
		return groupName2;
	}

	public void setGroupName2(String groupName2) {
		this.groupName2 = groupName2;
	}

	public String getGroupName3() {
		return groupName3;
	}

	public void setGroupName3(String groupName3) {
		this.groupName3 = groupName3;
	}

	public String getVendorName1() {
		return vendorName1;
	}

	public void setVendorName1(String vendorName1) {
		this.vendorName1 = vendorName1;
	}

	public String getVendorName2() {
		return vendorName2;
	}

	public void setVendorName2(String vendorName2) {
		this.vendorName2 = vendorName2;
	}

	public String getVendorName3() {
		return vendorName3;
	}

	public void setVendorName3(String vendorName3) {
		this.vendorName3 = vendorName3;
	}
	
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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getPurUnit() {
        return purUnit;
    }

    public void setPurUnit(String purUnit) {
        this.purUnit = purUnit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPurOrgUnitNo() {
        return purOrgUnitNo;
    }

    public void setPurOrgUnitNo(String purOrgUnitNo) {
        this.purOrgUnitNo = purOrgUnitNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPqNo() {
        return pqNo;
    }

    public void setPqNo(String pqNo) {
        this.pqNo = pqNo;
    }

	public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }
    
    public String getSelVndrCode() {
		return selVndrCode;
	}

	public void setSelVndrCode(String selVndrCode) {
		this.selVndrCode = selVndrCode;
	}

	public String getSelVndrName() {
		return selVndrName;
	}

	public void setSelVndrName(String selVndrName) {
		this.selVndrName = selVndrName;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
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

	public String getPriceOfficer() {
		return priceOfficer;
	}

	public void setPriceOfficer(String priceOfficer) {
		this.priceOfficer = priceOfficer;
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
	public String getSelVndrIdDtl() {
		return selVndrIdDtl;
	}

	public void setSelVndrIdDtl(String selVndrIdDtl) {
		this.selVndrIdDtl = selVndrIdDtl;
	}

	public String getSelVndrIdDtlName() {
		return selVndrIdDtlName;
	}

	public void setSelVndrIdDtlName(String selVndrIdDtlName) {
		this.selVndrIdDtlName = selVndrIdDtlName;
	}

	public String getFinOrgUnitName() {
		return finOrgUnitName;
	}

	public void setFinOrgUnitName(String finOrgUnitName) {
		this.finOrgUnitName = finOrgUnitName;
	}

	public String getBusinessQuotationSign() {
		return businessQuotationSign;
	}

	public void setBusinessQuotationSign(String businessQuotationSign) {
		this.businessQuotationSign = businessQuotationSign;
	}

	public List<ScmPurPriceEntry2> getScmPurPriceEntryList() {
		return scmPurPriceEntryList;
	}

	public void setScmPurPriceEntryList(List<ScmPurPriceEntry2> scmPurPriceEntryList) {
		this.scmPurPriceEntryList = scmPurPriceEntryList;
	}

	public ScmPurPrice2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ScmPurPrice2(boolean defaultValue) {
		super(defaultValue);
		// TODO Auto-generated constructor stub
	}
	
	
}

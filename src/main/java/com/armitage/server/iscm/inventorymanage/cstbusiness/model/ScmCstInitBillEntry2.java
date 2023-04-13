package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;

public class ScmCstInitBillEntry2 extends ScmCstInitBillEntry{
	
    public static final String FN_ITEMNO = "itemNo";
    public static final String FN_ITEMNAME = "itemName";
    public static final String FN_SPEC = "spec";
    public static final String FN_UNITNAME = "unitName";
    public static final String FN_BASEUNITNAME = "baseUnitName";
    public static final String FN_CONVRATE = "convrate";
    public static final String FN_TAXRATESTR = "taxRateStr";
    public static final String PIEUNITNAME = "pieUnitName";
    private String itemNo;
    private String itemName;
    private String spec;
    private String unitName;
    private String baseUnitName;
    private BigDecimal convrate;
    private String taxRateStr;
	private BigDecimal invQty;
	private String pieUnitName;
	
	public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getBaseUnitName() {
        return baseUnitName;
    }

    public void setBaseUnitName(String baseUnitName) {
        this.baseUnitName = baseUnitName;
    }

    public BigDecimal getConvrate() {
        return convrate;
    }

    public void setConvrate(BigDecimal convrate) {
        this.convrate = convrate;
    }

    public String getTaxRateStr() {
        return taxRateStr;
    }

    public void setTaxRateStr(String taxRateStr) {
        this.taxRateStr = taxRateStr;
    }

    public BigDecimal getInvQty() {
		return invQty;
	}

	public void setInvQty(BigDecimal invQty) {
		this.invQty = invQty;
	}

	public ScmCstInitBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
		    this.convrate=BigDecimal.ZERO;
            this.taxRateStr="0%";
            this.invQty=BigDecimal.ZERO;
		}
	}

	public ScmCstInitBillEntry2() {
		super();
	}
}

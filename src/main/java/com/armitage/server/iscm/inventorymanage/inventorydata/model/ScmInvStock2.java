package com.armitage.server.iscm.inventorymanage.inventorydata.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

public class ScmInvStock2 extends ScmInvStock {
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_VENDORNAME = "vendorName";
	public static final String FN_WHNAME = "whName";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_PIEUNITNAME = "pieUnitName";
	public static final String FN_STOTALAMT = "sTotalAmt";
	public static final String FN_ORGUNITNAME = "orgUnitName";
	public static final String FN_ORGORWHNAME = "orgOrWhName";
	public static final String FN_LEVEL = "level";
	public static final String FN_TYPE = "type";
	public static final String FN_INVSTOCKNO = "invStockNo";
	public static final String FN_INVSTOCKNOID = "invStockNoId";
	
	private String unitName;
	private String itemNo;
	private String itemName;
	private String spec;
	private String vendorName;
	private String whName;
	private String baseUnitName;
	private String pieUnitName;
	private BigDecimal sTotalAmt;
	private String orgUnitName;
	private String orgOrWhName;
	private int level;
	private String type;
	private BigDecimal totalAmt;
	private BigDecimal totalTaxAmt;
	private BigDecimal avgPrice;
	private String invStockNo;
	private long invStockNoId;
	
    public long getInvStockNoId() {
		return invStockNoId;
	}

	public void setInvStockNoId(long invStockNoId) {
		this.invStockNoId = invStockNoId;
	}

	public String getInvStockNo() {
		return invStockNo;
	}

	public void setInvStockNo(String invStockNo) {
		this.invStockNo = invStockNo;
	}

	public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

	public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getBaseUnitName() {
        return baseUnitName;
    }

    public void setBaseUnitName(String baseUnitName) {
        this.baseUnitName = baseUnitName;
    }

    public String getPieUnitName() {
        return pieUnitName;
    }

    public void setPieUnitName(String pieUnitName) {
        this.pieUnitName = pieUnitName;
    }

    public BigDecimal getsTotalAmt() {
        return sTotalAmt;
    }

    public void setsTotalAmt(BigDecimal sTotalAmt) {
        this.sTotalAmt = sTotalAmt;
    }

    public String getOrgUnitName() {
        return orgUnitName;
    }

    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

    public String getOrgOrWhName() {
        return orgOrWhName;
    }

    public void setOrgOrWhName(String orgOrWhName) {
        this.orgOrWhName = orgOrWhName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public ScmInvStock2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}

	public ScmInvStock2() {
		super();
	}
}

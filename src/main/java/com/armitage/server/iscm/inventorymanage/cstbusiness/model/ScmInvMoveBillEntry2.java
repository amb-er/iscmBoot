package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScmInvMoveBillEntry2 extends ScmInvMoveBillEntry{
    
    public static final String FN_ITEMNO = "itemNo";
    public static final String FN_ITEMNAME = "itemName";
    public static final String FN_SPEC = "spec";
    public static final String FN_UNITNAME = "unitName";
    public static final String FN_BASEUNITNAME = "baseUnitName";
    public static final String FN_CONVRATE = "convrate";
    public static final String FN_TAXRATESTR = "taxRateStr";
    public static final String FN_PIEUNITNAME = "pieUnitName";
    public static final String FN_GROUPID = "groupId";
    public static final String FN_GROUPNAME = "groupName";
    public static final String FN_GROUPCODE = "groupCode";
    public static final String FN_LONGNO = "longNo";
    private String finOrgUnitNo;
    private String wtNo;
	private Date bizDate;
	private Date postDate;
	private String status;
    private String outCstOrgUnitNo;
    private String outOrgUnitNo;
    private String inCstOrgUnitNo;
    private String inOrgUnitNo;
    private String itemNo;
    private String itemName;
    private String spec;
    private String unitName;
    private String baseUnitName;
    private BigDecimal convrate;
    private String taxRateStr;
    private String groupName;
    private String pieUnitName;
    private long periodId;
	private long groupId;
    private String groupCode;
    private String longNo;
    
    
    public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getLongNo() {
		return longNo;
	}

	public void setLongNo(String longNo) {
		this.longNo = longNo;
	}

    public String getWtNo() {
		return wtNo;
	}

	public void setWtNo(String wtNo) {
		this.wtNo = wtNo;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOutCstOrgUnitNo() {
		return outCstOrgUnitNo;
	}

	public void setOutCstOrgUnitNo(String outCstOrgUnitNo) {
		this.outCstOrgUnitNo = outCstOrgUnitNo;
	}

	public String getOutOrgUnitNo() {
		return outOrgUnitNo;
	}

	public void setOutOrgUnitNo(String outOrgUnitNo) {
		this.outOrgUnitNo = outOrgUnitNo;
	}

	public String getInCstOrgUnitNo() {
		return inCstOrgUnitNo;
	}

	public void setInCstOrgUnitNo(String inCstOrgUnitNo) {
		this.inCstOrgUnitNo = inCstOrgUnitNo;
	}

	public String getInOrgUnitNo() {
		return inOrgUnitNo;
	}

	public void setInOrgUnitNo(String inOrgUnitNo) {
		this.inOrgUnitNo = inOrgUnitNo;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}

	public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}

	public ScmInvMoveBillEntry2(boolean defaultValue) {
        super(defaultValue);
    }

    public ScmInvMoveBillEntry2() {
        super();
    }
}

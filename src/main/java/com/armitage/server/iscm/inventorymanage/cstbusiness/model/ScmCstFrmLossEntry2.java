package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScmCstFrmLossEntry2 extends ScmCstFrmLossEntry {
	
	public static final String FN_ITEMNO = "itemNo";
    public static final String FN_ITEMNAME = "itemName";
    public static final String FN_SPEC = "spec";
    public static final String FN_UNITNAME = "unitName";
    public static final String FN_BASEUNITNAME = "baseUnitName";
    public static final String FN_CONVRATE = "convrate";
    public static final String FN_TAXRATESTR = "taxRateStr";
    public static final String FN_BIZDATE = "bizDate";
    public static final String FN_POSTDATE = "postDate";
    public static final String FN_COSTORGUNITNO = "costOrgUnitNo";
    public static final String FN_USEORGUNITNO = "useOrgUnitNo";
    public static final String FN_BILLNO = "billNo";
    public static final String FN_STATUS = "status";
    public static final String FN_GROUPID = "groupId";
    public static final String FN_GROUPNAME = "groupName";
    public static final String FN_GROUPCODE = "groupCode";
    public static final String FN_LONGNO = "longNo";
    public static final String FN_ATTACHMENTNAME = "attachmentName";
    public static final String FN_PIEUNITNAME = "pieUnitName";
	private String pieUnitName;
    private String itemNo;
    private String itemName;
    private String spec;
    private String unitName;
    private String baseUnitName;
    private BigDecimal convrate;
    private String taxRateStr;
    private Date bizDate;
    private Date postDate;
    private String costOrgUnitNo;
    private String useOrgUnitNo;
    private String billNo;
    private String status;
    private long groupId;
    private String groupName;
    private String groupCode;
    private String longNo;
	private long periodId;
    private String attachmentName;
    
    
    
	public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
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

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}

	public ScmCstFrmLossEntry2(boolean defaultValue) {
        super(defaultValue);
        if (defaultValue) {
		}
    }

    public ScmCstFrmLossEntry2() {
        super();
    }
}

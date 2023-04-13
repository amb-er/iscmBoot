package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScmInvMaterialReqBillEntry2 extends ScmInvMaterialReqBillEntry{
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_BIZDATE = "bizDate";
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_GROUPNAME = "groupName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_INVQTY = "invQty";
	public static final String FN_TAXRATESTR = "taxRateStr";
	public static final String FN_BIZTYPE = "bizType";
	public static final String FN_CREATOR = "creator";
	public static final String FN_STATUS = "status";
	public static final String FN_USEORGUNITNO = "useOrgUnitNo";
	public static final String FN_USEORGUNITNAME = "useOrgUnitName";
	public static final String FN_COSTORGUNITNO = "costOrgUnitNo";
	public static final String FN_GROUPID = "groupId";
	public static final String FN_GROUPCODE = "groupCode";
	public static final String FN_LONGNO = "longNo";
	public static final String FN_WHNAME = "whName";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	
	private String finOrgUnitNo;
    private String otNo;
    private String bizType;
	private Date bizDate;
	private Date postDate;
	private String status;
    private String creator;
	private String itemNo;
	private String itemName;
	private String spec;
	private String groupName;
	private String unitName;
	private String pieUnitName;
	private boolean choosed;
	private BigDecimal convrate;
	private String baseUnitName;
	private BigDecimal invQty;
	private String whName;
	private long periodId;
	private String className;
	private long brandId;
	private String brandName;
	
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
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

	private String taxRateStr;
	private String useOrgUnitNo;
	private String costOrgUnitNo;
	private String orgUnitNo;
	private long wareHouseId;
	private String useOrgUnitName;
	private long groupId;
	private String groupCode;
	private String longNo;
	private String resultCode;
	private String resultText;
	
	private String attachmentName;
	private long attachmentId;
	
	
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public long getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultText() {
		return resultText;
	}
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}
	public BigDecimal getInvQty() {
		return invQty;
	}
	public void setInvQty(BigDecimal invQty) {
		this.invQty = invQty;
	}
	public String getOtNo() {
		return otNo;
	}
	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public boolean isChoosed() {
		return choosed;
	}
	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
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
	
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public String getTaxRateStr() {
		return taxRateStr;
	}
	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}
	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}
	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}
	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}
	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public long getWareHouseId() {
		return wareHouseId;
	}
	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}
	public String getUseOrgUnitName() {
		return useOrgUnitName;
	}
	public void setUseOrgUnitName(String useOrgUnitName) {
		this.useOrgUnitName = useOrgUnitName;
	}
	public long getPeriodId() {
		return periodId;
	}
	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public long getBrandId() {
		return brandId;
	}
	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public ScmInvMaterialReqBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.convrate=BigDecimal.ZERO;
			this.invQty=BigDecimal.ZERO;
		}
	}

	public ScmInvMaterialReqBillEntry2() {
		super();
	}
}

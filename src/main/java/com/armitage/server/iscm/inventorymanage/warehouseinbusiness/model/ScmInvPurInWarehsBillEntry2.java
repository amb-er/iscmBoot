package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScmInvPurInWarehsBillEntry2 extends ScmInvPurInWarehsBillEntry{

	public static final String FN_BIZDATE = "bizDate";
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_GROUPNAME = "groupName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_TAXRATESTR = "taxRateStr";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_VENDORNAME ="vendorName";
	public static final String FN_INVQTY = "invQty";
	public static final String FN_BIZTYPE = "bizType";
	public static final String FN_CREATOR = "creator";
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_STATUS = "status";
	public static final String FN_REQUIREORGUNITNAME = "requireOrgUnitName";
	public static final String FN_WAREHOUSENAME = "wareHouseName";
	public static final String FN_USEORGUNITNAME = "useOrgUnitName";
	public static final String FN_WHNAME = "whName";
	private String wrNo;
	private String bizType;
	private Date bizDate;
	private Date postDate;
	private String status;
	private String creator;
	private long vendorId;
	private String itemNo;
	private String itemName;
	private String spec;
	private String groupCode;
	private String groupName;
	private String unitName;
	private boolean choosed;
	private BigDecimal convrate;
	private String taxRateStr;
	private String baseUnitName;
	private long periodId;
	private String vendorName;
	private BigDecimal invQty;
	private BigDecimal invPieQty;
	private String pieUnitName;
	private boolean notWriteBack;
	private String requireOrgUnitName;
	private String wareHouseName;
	private String useOrgUnitName;
	private String longNo;//物资所属类别级别串
	private long groupId;
	private String whName;
	private String wareHouseNo;
	private String finOrgUnitName;
	private String attachmentName;
	private long attachmentId;
	private String periodValid;
	private int periodValidDays;
	
	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
	}
	
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
	public BigDecimal getInvPieQty() {
		return invPieQty;
	}
	public void setInvPieQty(BigDecimal invPieQty) {
		this.invPieQty = invPieQty;
	}
	public BigDecimal getInvQty() {
		return invQty;
	}
	public void setInvQty(BigDecimal invQty) {
		this.invQty = invQty;
	}
	public String getWrNo() {
		return wrNo;
	}
	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
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
	public String getTaxRateStr() {
		return taxRateStr;
	}
	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
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
	public boolean isNotWriteBack() {
		return notWriteBack;
	}
	public void setNotWriteBack(boolean notWriteBack) {
		this.notWriteBack = notWriteBack;
	}
	public String getRequireOrgUnitName() {
		return requireOrgUnitName;
	}
	public void setRequireOrgUnitName(String requireOrgUnitName) {
		this.requireOrgUnitName = requireOrgUnitName;
	}
	public String getWareHouseName() {
		return wareHouseName;
	}
	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
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
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	public String getFinOrgUnitName() {
		return finOrgUnitName;
	}
	public void setFinOrgUnitName(String finOrgUnitName) {
		this.finOrgUnitName = finOrgUnitName;
	}
	
	public String getPeriodValid() {
		return periodValid;
	}
	public void setPeriodValid(String periodValid) {
		this.periodValid = periodValid;
	}
	public int getPeriodValidDays() {
		return periodValidDays;
	}
	public void setPeriodValidDays(int periodValidDays) {
		this.periodValidDays = periodValidDays;
	}
	public ScmInvPurInWarehsBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.convrate=BigDecimal.ZERO;
			this.invQty=BigDecimal.ZERO;
			this.taxRateStr="0%";
		}
	}

	public ScmInvPurInWarehsBillEntry2() {
		super();
	}
}

package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.armitage.server.iscm.common.model.ScmAuditDetailHistory2;

public class ScmPurRequireEntry2 extends ScmPurRequireEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_PURUNITNAME = "purUnitName";
	public static final String FN_SUPPLYSOURCENAME = "supplySourceName";
	public static final String FN_FROMSTORAGE = "fromStorage";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_PRNO = "prNo";
	public static final String FN_GROUPID = "groupId";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_REQQTY = "reqQty";
	public static final String FN_RECEIVEQTY = "receiveQty";
	public static final String FN_INQTY = "inQty";
	public static final String FN_PURORDERNO = "purOrderNo";
	public static final String FN_PURORDERSTATUS = "purOrderStatus";
	public static final String FN_PURORDERSTATUSNAME = "purOrderStatusName";
	public static final String FN_PURRECEIVENO = "purReceiveNo";
	public static final String FN_PURRECEIVESTATUS = "purReceiveStatus";
	public static final String FN_PURRECEIVESTATUSNAME = "purReceiveStatusName";
	public static final String FN_INVPURINWAREHSNO = "invPurInwarehsNo";
	public static final String FN_INVPURINWAREHSSTATUS = "invPurInwarehsStatus";
	public static final String FN_INVPURINWAREHSSTATUSNAME = "invPurInwarehsStatusName";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
    public static final String FN_PRICEBILLSTATUS ="priceBillStatus";
    public static final String FN_BIZTYPE = "bizType";
    public static final String FN_ATTACHMENTNAME = "attachmentName";
    public static final String FN_BUYERNAME = "buyerName";
	public static final String FN_PURORDERQTY = "purOrderQty";
	public static final String FN_APPLYDATE = "applyDate";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnitName;
	private BigDecimal convrate;
	private String prNo;
	private long groupId;
	private boolean choosed;
	private long receiveWareHouseId;
	private BigDecimal reqQty;
	private BigDecimal receiveQty;
	private BigDecimal inQty;
	private boolean unified;
	private String opinion;
	private String buyerName;
	/*供应商订货汇总表 */
	private String vendorName;
	private String className;
	private String unitName;
	private BigDecimal totalAmt;
	private Date applyDate;
	
	/*
	 *	请购物资情况查询 
	 */    
	private String classCode;
	private String vendorNo;
	private String finOrgUnitNo;
	private String purOrderNo;
	private String purOrderStatus;
	private String purOrderStatusName;
	private String purReceiveNo;
	private String purReceiveStatus;
	private String purReceiveStatusName;
	private String invPurInwarehsNo;
	private String invPurInwarehsStatus;
	private String invPurInwarehsStatusName;
	
    private String priceBillStatus;
    private String bizType;
    private String attachmentName;
    //主，备选供应商
    private long masterVendorId;
    private BigDecimal masterPrice;
    private long preVendorId1;
    private BigDecimal prePrice1;
    private long preVendorId2;
    private BigDecimal prePrice2;
    private long preVendorId3;
    private BigDecimal prePrice3;
    private String bizTypeName;
    private String refuseReason;
    private String vendorEditType;
    private String priceEditType;
    private BigDecimal purOrderQty;
    private String rowAuditRemarks;
    private String pieUnitName;
    private List<ScmAuditDetailHistory2> auditDetailHistoryList;
	private String editColumn;
    
	public List<ScmAuditDetailHistory2> getAuditDetailHistoryList() {
		return auditDetailHistoryList;
	}
	public void setAuditDetailHistoryList(List<ScmAuditDetailHistory2> auditDetailHistoryList) {
		this.auditDetailHistoryList = auditDetailHistoryList;
	}
	public String getRowAuditRemarks() {
		return rowAuditRemarks;
	}
	public void setRowAuditRemarks(String rowAuditRemarks) {
		this.rowAuditRemarks = rowAuditRemarks;
	}
	public long getPreVendorId1() {
		return preVendorId1;
	}
	public void setPreVendorId1(long preVendorId1) {
		this.preVendorId1 = preVendorId1;
	}
	public BigDecimal getPrePrice1() {
		return prePrice1;
	}
	public void setPrePrice1(BigDecimal prePrice1) {
		this.prePrice1 = prePrice1;
	}
	public long getPreVendorId2() {
		return preVendorId2;
	}
	public void setPreVendorId2(long preVendorId2) {
		this.preVendorId2 = preVendorId2;
	}
	public BigDecimal getPrePrice2() {
		return prePrice2;
	}
	public void setPrePrice2(BigDecimal prePrice2) {
		this.prePrice2 = prePrice2;
	}
	public long getPreVendorId3() {
		return preVendorId3;
	}
	public void setPreVendorId3(long preVendorId3) {
		this.preVendorId3 = preVendorId3;
	}
	public BigDecimal getPrePrice3() {
		return prePrice3;
	}
	public void setPrePrice3(BigDecimal prePrice3) {
		this.prePrice3 = prePrice3;
	}
	public long getMasterVendorId() {
		return masterVendorId;
	}
	public void setMasterVendorId(long masterVendorId) {
		this.masterVendorId = masterVendorId;
	}
	public BigDecimal getMasterPrice() {
		return masterPrice;
	}
	public void setMasterPrice(BigDecimal masterPrice) {
		this.masterPrice = masterPrice;
	}
    
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getPriceBillStatus() {
		return priceBillStatus;
	}
	public void setPriceBillStatus(String priceBillStatus) {
		this.priceBillStatus = priceBillStatus;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public boolean isUnified() {
		return unified;
	}
	public void setUnified(boolean unified) {
		this.unified = unified;
	}
	public BigDecimal getReceiveQty() {
		return receiveQty;
	}
	public void setReceiveQty(BigDecimal receiveQty) {
		this.receiveQty = receiveQty;
	}
	public BigDecimal getInQty() {
		return inQty;
	}
	public void setInQty(BigDecimal inQty) {
		this.inQty = inQty;
	}
	public BigDecimal getReqQty() {
		return reqQty;
	}
	public void setReqQty(BigDecimal reqQty) {
		this.reqQty = reqQty;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public boolean isChoosed() {
		return choosed;
	}
	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getPrNo() {
		return prNo;
	}
	public void setPrNo(String prNo) {
		this.prNo = prNo;
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
	public String getPurUnitName() {
		return purUnitName;
	}
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}
	public BigDecimal getConvrate() {
		return convrate;
	}
	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}

	public long getReceiveWareHouseId() {
		return receiveWareHouseId;
	}
	public void setReceiveWareHouseId(long receiveWareHouseId) {
		this.receiveWareHouseId = receiveWareHouseId;
	}
	public BigDecimal getTotalAmt() {
        return totalAmt;
    }
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }
    public String getPurOrderNo() {
		return purOrderNo;
	}
	public void setPurOrderNo(String purOrderNo) {
		this.purOrderNo = purOrderNo;
	}
	public String getPurOrderStatus() {
		return purOrderStatus;
	}
	public void setPurOrderStatus(String purOrderStatus) {
		this.purOrderStatus = purOrderStatus;
	}
	public String getPurReceiveNo() {
		return purReceiveNo;
	}
	public void setPurReceiveNo(String purReceiveNo) {
		this.purReceiveNo = purReceiveNo;
	}
	public String getPurReceiveStatus() {
		return purReceiveStatus;
	}
	public void setPurReceiveStatus(String purReceiveStatus) {
		this.purReceiveStatus = purReceiveStatus;
	}
	public String getInvPurInwarehsNo() {
		return invPurInwarehsNo;
	}
	public void setInvPurInwarehsNo(String invPurInwarehsNo) {
		this.invPurInwarehsNo = invPurInwarehsNo;
	}
	public String getInvPurInwarehsStatus() {
		return invPurInwarehsStatus;
	}
	public void setInvPurInwarehsStatus(String invPurInwarehsStatus) {
		this.invPurInwarehsStatus = invPurInwarehsStatus;
	}
	public String getPurOrderStatusName() {
		return purOrderStatusName;
	}
	public void setPurOrderStatusName(String purOrderStatusName) {
		this.purOrderStatusName = purOrderStatusName;
	}
	public String getPurReceiveStatusName() {
		return purReceiveStatusName;
	}
	public void setPurReceiveStatusName(String purReceiveStatusName) {
		this.purReceiveStatusName = purReceiveStatusName;
	}
	public String getInvPurInwarehsStatusName() {
		return invPurInwarehsStatusName;
	}
	public void setInvPurInwarehsStatusName(String invPurInwarehsStatusName) {
		this.invPurInwarehsStatusName = invPurInwarehsStatusName;
	}
	
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBizTypeName() {
		return bizTypeName;
	}
	
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	
	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}
	
	public String getVendorEditType() {
		return vendorEditType;
	}
	public void setVendorEditType(String vendorEditType) {
		this.vendorEditType = vendorEditType;
	}
	public String getPriceEditType() {
		return priceEditType;
	}
	public void setPriceEditType(String priceEditType) {
		this.priceEditType = priceEditType;
	}
	public BigDecimal getPurOrderQty() {
		return purOrderQty;
	}
	public void setPurOrderQty(BigDecimal purOrderQty) {
		this.purOrderQty = purOrderQty;
	}
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getEditColumn() {
		return editColumn;
	}
	public void setEditColumn(String editColumn) {
		this.editColumn = editColumn;
	}
	public ScmPurRequireEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.convrate=BigDecimal.ZERO;
			this.prePrice1=BigDecimal.ZERO;
			this.prePrice2=BigDecimal.ZERO;
			this.prePrice3=BigDecimal.ZERO;
			this.masterPrice=BigDecimal.ZERO;
		}
	}

	public ScmPurRequireEntry2() {
		super();
	}
}

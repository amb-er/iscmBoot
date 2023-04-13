package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.armitage.server.iscm.common.model.ScmAuditDetailHistory2;


public class ScmPurOrderEntry2 extends ScmPurOrderEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_PURUNITNAME = "purUnitName";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_TAXRATESTR = "taxRateStr";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_PRICEBILLNO ="priceBillNo";
    public static final String FN_PRICEBILLSTATUS ="priceBillStatus";
    
	

	/*
	 * 采购历史价格查询表
	 */
	private String purGroupName;
	private String minOrderDate;
	private String maxOrderDate;
	private String vendorName;
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnitName;
	private boolean choosed;
	private BigDecimal convrate;
	private String taxRateStr;
	private String baseUnitName;
	private String orderDate ;
	private String poNo ;
	private String unitName;
	private String classCode;
	/*
	 * 供应商交易明细表
	 */
	private String orgUnitNo;
	private String buyerName;
	private BigDecimal addInQty;
	private BigDecimal outQty;

	/*供应商交易汇总表*/
	private long vendorId;
	private String vendorNo;
	private BigDecimal purAmount;//采购金额
	private BigDecimal storageAmount;//入库金额
	private BigDecimal refundAmount;//退货金额
	private String finOrgUnitNo;//财务组织
	
	private BigDecimal returnAmt;
	private BigDecimal returnTaxAmt;
	private BigDecimal addInAmt;
	private BigDecimal addInTaxAmt;
	private BigDecimal outAmt;
	private BigDecimal outTaxAmt;
	private BigDecimal totalOrderAmt;
	private BigDecimal totalAddInAmt;
	private BigDecimal totalAddInTaxAmt;
	private BigDecimal totalReturnAmt;
	private BigDecimal totalReturnTaxAmt;
	private BigDecimal totalOutAmt;
	private BigDecimal totalOutTaxAmt;
	private BigDecimal totalAmt;
	private BigDecimal totalTaxAmt;
	private BigDecimal totalReceiveAmt;
	private BigDecimal totalReceiveTaxAmt;
	
	/*供应商综合情况表*/
	private BigDecimal oweAmt;//应付款余额
	private BigDecimal orderAmt;//采购金额 
	private BigDecimal receiveAmt;//收货金额
	private BigDecimal receiveTaxAmt;//收货金额
	private BigDecimal puroweAmt;//采购应付金额
	private String isFlag;//查询条件
	
	private BigDecimal totalReceiveQty;//收货总数量
	private BigDecimal totalAddInQty;//入库总数量
	private BigDecimal totalReturnQty;//退货总数量
	private BigDecimal totalOutQty;//退货出库总数量
	private BigDecimal totalQty;//采购应付金额
	private int rowCount;
	private int closeCount;
	
	private String receiveOrgUnitName;
	private String reqOrgUnitName;
	private String reqStorageOrgUnitName;
	private String reqFinOrgUnitName;
	private String receiveFinOrgUnitName;
	private String receiveWareHouseName;
	private String mstorageOrgUnitName;
	private String balanceSupplierName;
	private String storageOrgUnitName;
	private String rowStatusName;
	private String checkerName;
	private String opinion;
	private String priceBillNo;
    private String priceBillStatus;
    private String purOrgUnitNo;
    private Date purOrderDate;
    private String pieUnitName;
    private String attachmentName;
    //是否闲置
    private boolean idle;
    private String refuseReason;
    
    private String rowAuditRemarks;
    private List<ScmAuditDetailHistory2> auditDetailHistoryList;
    
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
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public Date getPurOrderDate() {
		return purOrderDate;
	}
	public void setPurOrderDate(Date purOrderDate) {
		this.purOrderDate = purOrderDate;
	}
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}
	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}
	public String getPriceBillNo() {
		return priceBillNo;
	}
	public void setPriceBillNo(String priceBillNo) {
		this.priceBillNo = priceBillNo;
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
	public String getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}
	
	public BigDecimal getOweAmt() {
        return oweAmt;
    }
    public void setOweAmt(BigDecimal oweAmt) {
        this.oweAmt = oweAmt;
    }
    public BigDecimal getOrderAmt() {
        return orderAmt;
    }
    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }
    public BigDecimal getReceiveAmt() {
        return receiveAmt;
    }
    public void setReceiveAmt(BigDecimal receiveAmt) {
        this.receiveAmt = receiveAmt;
    }
    public BigDecimal getPuroweAmt() {
        return puroweAmt;
    }
    public void setPuroweAmt(BigDecimal puroweAmt) {
        this.puroweAmt = puroweAmt;
    }
    public BigDecimal getPurAmount() {
        return purAmount;
    }
    public void setPurAmount(BigDecimal purAmount) {
        this.purAmount = purAmount;
    }
    public BigDecimal getStorageAmount() {
        return storageAmount;
    }
    public void setStorageAmount(BigDecimal storageAmount) {
        this.storageAmount = storageAmount;
    }
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }
    public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public BigDecimal getAddInQty() {
		return addInQty;
	}
	public void setAddInQty(BigDecimal addInQty) {
		this.addInQty = addInQty;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getPurGroupName() {
		return purGroupName;
	}
	public void setPurGroupName(String purGroupName) {
		this.purGroupName = purGroupName;
	}
	public String getMinOrderDate() {
		return minOrderDate;
	}
	public void setMinOrderDate(String minOrderDate) {
		this.minOrderDate = minOrderDate;
	}
	public String getMaxOrderDate() {
		return maxOrderDate;
	}
	public void setMaxOrderDate(String maxOrderDate) {
		this.maxOrderDate = maxOrderDate;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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
	public String getPurUnitName() {
		return purUnitName;
	}
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}
	
	public BigDecimal getTotalOrderAmt() {
        return totalOrderAmt;
    }
    public void setTotalOrderAmt(BigDecimal totalOrderAmt) {
        this.totalOrderAmt = totalOrderAmt;
    }
    public BigDecimal getTotalAddInAmt() {
        return totalAddInAmt;
    }
    public void setTotalAddInAmt(BigDecimal totalAddInAmt) {
        this.totalAddInAmt = totalAddInAmt;
    }
    public BigDecimal getTotalReturnAmt() {
        return totalReturnAmt;
    }
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }
    public void setTotalReturnAmt(BigDecimal totalReturnAmt) {
        this.totalReturnAmt = totalReturnAmt;
    }
    public BigDecimal getReturnAmt() {
        return returnAmt;
    }
    public void setReturnAmt(BigDecimal returnAmt) {
        this.returnAmt = returnAmt;
    }
    public BigDecimal getAddInAmt() {
        return addInAmt;
    }
    public void setAddInAmt(BigDecimal addInAmt) {
        this.addInAmt = addInAmt;
    }
    public String getFinOrgUnitNo() {
        return finOrgUnitNo;
    }
    public void setFinOrgUnitNo(String finOrgUnitNo) {
        this.finOrgUnitNo = finOrgUnitNo;
    }
    public String getVendorNo() {
        return vendorNo;
    }
    public void setVendorNo(String vendorNo) {
        this.vendorNo = vendorNo;
    }
    public BigDecimal getTotalReceiveQty() {
        return totalReceiveQty;
    }
    public void setTotalReceiveQty(BigDecimal totalReceiveQty) {
        this.totalReceiveQty = totalReceiveQty;
    }
    public BigDecimal getTotalAddInQty() {
        return totalAddInQty;
    }
    public void setTotalAddInQty(BigDecimal totalAddInQty) {
        this.totalAddInQty = totalAddInQty;
    }
    public BigDecimal getTotalReturnQty() {
        return totalReturnQty;
    }
    public void setTotalReturnQty(BigDecimal totalReturnQty) {
        this.totalReturnQty = totalReturnQty;
    }
    public BigDecimal getTotalQty() {
        return totalQty;
    }
    public void setTotalQty(BigDecimal totalQty) {
        this.totalQty = totalQty;
    }
    public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public BigDecimal getOutAmt() {
		return outAmt;
	}
	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}
	public BigDecimal getTotalOutAmt() {
		return totalOutAmt;
	}
	public void setTotalOutAmt(BigDecimal totalOutAmt) {
		this.totalOutAmt = totalOutAmt;
	}
	public BigDecimal getOutQty() {
		return outQty;
	}
	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}
	public BigDecimal getTotalOutQty() {
		return totalOutQty;
	}
	public void setTotalOutQty(BigDecimal totalOutQty) {
		this.totalOutQty = totalOutQty;
	}
	
	public BigDecimal getTotalTaxAmt() {
		return totalTaxAmt;
	}
	public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
		this.totalTaxAmt = totalTaxAmt;
	}
	public BigDecimal getAddInTaxAmt() {
		return addInTaxAmt;
	}
	public void setAddInTaxAmt(BigDecimal addInTaxAmt) {
		this.addInTaxAmt = addInTaxAmt;
	}
	public BigDecimal getOutTaxAmt() {
		return outTaxAmt;
	}
	public void setOutTaxAmt(BigDecimal outTaxAmt) {
		this.outTaxAmt = outTaxAmt;
	}
	public BigDecimal getTotalOutTaxAmt() {
		return totalOutTaxAmt;
	}
	public void setTotalOutTaxAmt(BigDecimal totalOutTaxAmt) {
		this.totalOutTaxAmt = totalOutTaxAmt;
	}
	public BigDecimal getReturnTaxAmt() {
		return returnTaxAmt;
	}
	public void setReturnTaxAmt(BigDecimal returnTaxAmt) {
		this.returnTaxAmt = returnTaxAmt;
	}
	public BigDecimal getTotalAddInTaxAmt() {
		return totalAddInTaxAmt;
	}
	public void setTotalAddInTaxAmt(BigDecimal totalAddInTaxAmt) {
		this.totalAddInTaxAmt = totalAddInTaxAmt;
	}
	public BigDecimal getTotalReturnTaxAmt() {
		return totalReturnTaxAmt;
	}
	public void setTotalReturnTaxAmt(BigDecimal totalReturnTaxAmt) {
		this.totalReturnTaxAmt = totalReturnTaxAmt;
	}
	public BigDecimal getTotalReceiveAmt() {
		return totalReceiveAmt;
	}
	public void setTotalReceiveAmt(BigDecimal totalReceiveAmt) {
		this.totalReceiveAmt = totalReceiveAmt;
	}
	public BigDecimal getTotalReceiveTaxAmt() {
		return totalReceiveTaxAmt;
	}
	public void setTotalReceiveTaxAmt(BigDecimal totalReceiveTaxAmt) {
		this.totalReceiveTaxAmt = totalReceiveTaxAmt;
	}
	public BigDecimal getReceiveTaxAmt() {
		return receiveTaxAmt;
	}
	public void setReceiveTaxAmt(BigDecimal receiveTaxAmt) {
		this.receiveTaxAmt = receiveTaxAmt;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getCloseCount() {
		return closeCount;
	}
	public void setCloseCount(int closeCount) {
		this.closeCount = closeCount;
	}
	public String getReceiveOrgUnitName() {
		return receiveOrgUnitName;
	}
	public void setReceiveOrgUnitName(String receiveOrgUnitName) {
		this.receiveOrgUnitName = receiveOrgUnitName;
	}
	public String getReqOrgUnitName() {
		return reqOrgUnitName;
	}
	public void setReqOrgUnitName(String reqOrgUnitName) {
		this.reqOrgUnitName = reqOrgUnitName;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getReqStorageOrgUnitName() {
		return reqStorageOrgUnitName;
	}
	public void setReqStorageOrgUnitName(String reqStorageOrgUnitName) {
		this.reqStorageOrgUnitName = reqStorageOrgUnitName;
	}
	public String getReqFinOrgUnitName() {
		return reqFinOrgUnitName;
	}
	public void setReqFinOrgUnitName(String reqFinOrgUnitName) {
		this.reqFinOrgUnitName = reqFinOrgUnitName;
	}
	public String getReceiveFinOrgUnitName() {
		return receiveFinOrgUnitName;
	}
	public void setReceiveFinOrgUnitName(String receiveFinOrgUnitName) {
		this.receiveFinOrgUnitName = receiveFinOrgUnitName;
	}
	public String getReceiveWareHouseName() {
		return receiveWareHouseName;
	}
	public void setReceiveWareHouseName(String receiveWareHouseName) {
		this.receiveWareHouseName = receiveWareHouseName;
	}
	public String getMstorageOrgUnitName() {
		return mstorageOrgUnitName;
	}
	public void setMstorageOrgUnitName(String mstorageOrgUnitName) {
		this.mstorageOrgUnitName = mstorageOrgUnitName;
	}
	public String getBalanceSupplierName() {
		return balanceSupplierName;
	}
	public void setBalanceSupplierName(String balanceSupplierName) {
		this.balanceSupplierName = balanceSupplierName;
	}
	public String getStorageOrgUnitName() {
		return storageOrgUnitName;
	}
	public void setStorageOrgUnitName(String storageOrgUnitName) {
		this.storageOrgUnitName = storageOrgUnitName;
	}
	public String getRowStatusName() {
		return rowStatusName;
	}
	public void setRowStatusName(String rowStatusName) {
		this.rowStatusName = rowStatusName;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public boolean isIdle() {
		return idle;
	}
	public void setIdle(boolean idle) {
		this.idle = idle;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public ScmPurOrderEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.convrate=BigDecimal.ZERO;
			this.taxRateStr="0%";
			this.addInQty=BigDecimal.ZERO;
		}
	}

	public ScmPurOrderEntry2() {
		super();
	}
}

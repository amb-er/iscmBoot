package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ScmPurOrder2 extends ScmPurOrder {
    public static final String FN_GROUPNAME = "groupName";
    public static final String FN_CHOOSED = "choosed";
    
    
    public static final String FN_RECEIVEDATE = "receiveDate";
    public static final String FN_ROWSTATUS = "rowStatus";
    public static final String FN_VENDORNAME = "vendorName";
    public static final String FN_ITEMNO = "itemNo";
    public static final String FN_ITEMNAME = "itemName";
    public static final String FN_SPEC = "spec";
    public static final String FN_UNITNAME = "unitName";
    public static final String FN_CONVRATE = "convrate";
    public static final String FN_qty = "qty";
    public static final String FN_receiveQty = "receiveQty";
    public static final String FN_NOTRECEIVEQTY = "notReceiveQty";
    public static final String FN_FLAG = "flag";
    public static final String FN_ADDINDATE = "addinDate";
    public static final String FN_ADDINQTY = "addinQty";
    public static final String FN_PURORGUNITNO = "purOrgUnitNo";
    public static final String FN_RECORGUNITNO = "recOrgUnitNo";
    public static final String FN_BUYERNAME = "buyerName";
    public static final String FN_PENDINGUSRNAME = "pendingUsrName";
    public static final String FN_TAXAMOUNT = "taxAmount";
    public static final String FN_CONFIRMSTATUS = "confirmStatus";
    public static final String FN_CONFIRMTIME = "confirmTime";
    
    private String groupName;
    private boolean choosed;
    private String flag;
    private Date addinDate;
    private Date receiveDate;
    private String rowStatus;
    private String vendorName;
    private String purOrgUnitNo;
    private String recOrgUnitNo;
    private String itemNo;
    private String itemName;
    private String spec;
    private String unitName;
    private BigDecimal convrate;
    private BigDecimal qty;
    private BigDecimal receiveQty;
    private BigDecimal addinQty;
    private BigDecimal notReceiveQty;
    private boolean existsSource;
    private BigDecimal totalAmt;
    /*  供应商综合情况表  */
	 
   	private String vendorNo;
   	private String oweAmt;
   	private BigDecimal orderAmt;
   	private BigDecimal receiveAmt;
   	private String puroweAmt;
   	private String code;
    private BigDecimal storageAmt;
    private BigDecimal returnAmt;
    private String averagePrice;
    private BigDecimal orderQty;
    private BigDecimal storageQty;
    private BigDecimal returnQty;
    
    private BigDecimal totalTaxAmt;
    private BigDecimal totalStorageAmt;
    private BigDecimal totalReturnAmt;
    private BigDecimal totalOrderAmt;
    
    private BigDecimal totalOrderQty;
    private BigDecimal totalStorageQty;
    private BigDecimal totalReturnQty;
    
    private long purUnit;
    private BigDecimal totalQty;
    private BigDecimal totalRecOrInQty;
    private BigDecimal totalNotRecQty;
    
    private String purOrgUnitName;
    private String creatorName;
    private String checkerName;
    private String statusName;
    private String settlementName;
    private String paymentName;
    private String bizTypeName;
    
    private String finOrgUnitName;
    private String storageOrgUnitName;
    private String purGroupName;
    private String editorName;
    private String controlUnitName;

    private String buyerName;
    private String queryType;//1代表查指定组织，其他查指定单据
    private boolean locked;//是否锁定
    private String pendingUsrName;
    private BigDecimal taxAmount;
    private String buyerPhone;
    private String confirmStatus;
    private Date confirmTime;

    private List<ScmPurOrderEntry2> scmPurOrderEntryList;
    
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getTotalOrderAmt() {
        return totalOrderAmt;
    }

    public void setTotalOrderAmt(BigDecimal totalOrderAmt) {
        this.totalOrderAmt = totalOrderAmt;
    }

    public BigDecimal getTotalOrderQty() {
        return totalOrderQty;
    }

    public void setTotalOrderQty(BigDecimal totalOrderQty) {
        this.totalOrderQty = totalOrderQty;
    }

    public BigDecimal getTotalStorageQty() {
        return totalStorageQty;
    }

    public void setTotalStorageQty(BigDecimal totalStorageQty) {
        this.totalStorageQty = totalStorageQty;
    }

    public BigDecimal getTotalReturnQty() {
        return totalReturnQty;
    }

    public void setTotalReturnQty(BigDecimal totalReturnQty) {
        this.totalReturnQty = totalReturnQty;
    }

    public String getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getStorageQty() {
        return storageQty;
    }

    public void setStorageQty(BigDecimal storageQty) {
        this.storageQty = storageQty;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public BigDecimal getStorageAmt() {
        return storageAmt;
    }

    public void setStorageAmt(BigDecimal storageAmt) {
        this.storageAmt = storageAmt;
    }

    public BigDecimal getReturnAmt() {
        return returnAmt;
    }

    public void setReturnAmt(BigDecimal returnAmt) {
        this.returnAmt = returnAmt;
    }

    public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getOweAmt() {
		return oweAmt;
	}

	public void setOweAmt(String oweAmt) {
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

    public String getPuroweAmt() {
		return puroweAmt;
	}

	public void setPuroweAmt(String puroweAmt) {
		this.puroweAmt = puroweAmt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }
    
    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(String rowStatus) {
        this.rowStatus = rowStatus;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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

    public BigDecimal getConvrate() {
        return convrate;
    }

    public void setConvrate(BigDecimal convrate) {
        this.convrate = convrate;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    public BigDecimal getNotReceiveQty() {
        return notReceiveQty;
    }

    public void setNotReceiveQty(BigDecimal notReceiveQty) {
        this.notReceiveQty = notReceiveQty;
    }

    
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getAddinDate() {
        return addinDate;
    }

    public void setAddinDate(Date addinDate) {
        this.addinDate = addinDate;
    }

    public BigDecimal getAddinQty() {
        return addinQty;
    }

    public void setAddinQty(BigDecimal addinQty) {
        this.addinQty = addinQty;
    }

    public String getPurOrgUnitNo() {
        return purOrgUnitNo;
    }

    public void setPurOrgUnitNo(String purOrgUnitNo) {
        this.purOrgUnitNo = purOrgUnitNo;
    }

    public String getRecOrgUnitNo() {
        return recOrgUnitNo;
    }

    public void setRecOrgUnitNo(String recOrgUnitNo) {
        this.recOrgUnitNo = recOrgUnitNo;
    }

    public BigDecimal getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public BigDecimal getTotalStorageAmt() {
        return totalStorageAmt;
    }

    public void setTotalStorageAmt(BigDecimal totalStorageAmt) {
        this.totalStorageAmt = totalStorageAmt;
    }

    public BigDecimal getTotalReturnAmt() {
        return totalReturnAmt;
    }

    public void setTotalReturnAmt(BigDecimal totalReturnAmt) {
        this.totalReturnAmt = totalReturnAmt;
    }

    public long getPurUnit() {
        return purUnit;
    }

    public void setPurUnit(long purUnit) {
        this.purUnit = purUnit;
    }

    public BigDecimal getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(BigDecimal totalQty) {
        this.totalQty = totalQty;
    }

    public BigDecimal getTotalRecOrInQty() {
        return totalRecOrInQty;
    }

    public void setTotalRecOrInQty(BigDecimal totalRecOrInQty) {
        this.totalRecOrInQty = totalRecOrInQty;
    }

    public BigDecimal getTotalNotRecQty() {
        return totalNotRecQty;
    }

    public void setTotalNotRecQty(BigDecimal totalNotRecQty) {
        this.totalNotRecQty = totalNotRecQty;
    }

    public boolean isExistsSource() {
		return existsSource;
	}

	public void setExistsSource(boolean existsSource) {
		this.existsSource = existsSource;
	}
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public String getPurOrgUnitName() {
		return purOrgUnitName;
	}

	public void setPurOrgUnitName(String purOrgUnitName) {
		this.purOrgUnitName = purOrgUnitName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	
	public String getSettlementName() {
		return settlementName;
	}

	public void setSettlementName(String settlementName) {
		this.settlementName = settlementName;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getFinOrgUnitName() {
		return finOrgUnitName;
	}

	public void setFinOrgUnitName(String finOrgUnitName) {
		this.finOrgUnitName = finOrgUnitName;
	}

	public String getStorageOrgUnitName() {
		return storageOrgUnitName;
	}

	public void setStorageOrgUnitName(String storageOrgUnitName) {
		this.storageOrgUnitName = storageOrgUnitName;
	}

	public String getPurGroupName() {
		return purGroupName;
	}

	public void setPurGroupName(String purGroupName) {
		this.purGroupName = purGroupName;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public String getControlUnitName() {
		return controlUnitName;
	}

	public void setControlUnitName(String controlUnitName) {
		this.controlUnitName = controlUnitName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
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

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public List<ScmPurOrderEntry2> getScmPurOrderEntryList() {
		return scmPurOrderEntryList;
	}

	public void setScmPurOrderEntryList(List<ScmPurOrderEntry2> scmPurOrderEntryList) {
		this.scmPurOrderEntryList = scmPurOrderEntryList;
	}

	public ScmPurOrder2(boolean defaultValue) {
        super(defaultValue);
    }

    public ScmPurOrder2() {
        super();
    }
}

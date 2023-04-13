package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmInvPurInWarehsBill2 extends ScmInvPurInWarehsBill{
	
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_TAXINAMT = "taxInAmt";
	public static final String FN_VENDORNO = "vendorNo";
	public static final String FN_PURORGUNITNAME = "purOrgUnitName";
	public static final String FN_BUYERCODE = "buyerCode";
	public static final String FN_BUYERNAME = "buyerName";
	public static final String FN_CREATORNAME = "creatorName";
	public static final String FN_EDITORNAME = "editorName";
	public static final String FN_CHECKERNAME = "checkerName";
	public static final String FN_STATUSNAME = "statusName";
	public static final String FN_BIZTYPENAME = "bizTypeName";
	public static final String FN_RECEIVERNAME = "ReceiverName";
	public static final String FN_WAREHOUSENAME = "wareHouseName";
	public static final String FN_USEORGUNITNAME = "useOrgUnitName";
	public static final String FN_PVNO = "pvNo";
	public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	public static final String FN_TASKID = "taskId";
    public static final String FN_FREEITEM = "freeItem";
	
	private boolean choosed;
	private String storageOrgUnitNo;
	private BigDecimal storageQty;
	private BigDecimal averagePrice;
	private BigDecimal storageAmt;
	private String vendorName;
	private long itemId;
	private String itemNo;
	private String itemName;
	private String spec;
	private String unitName;
	private BigDecimal price;
	private BigDecimal qty;
	private String className;
	private String whName;
	private String invOrgUnitName;
	private long wareHouseId;
	private long unit;
	private String longNo;
	private long groupId;
	private BigDecimal taxInAmt;	//税额
	
	private String costOrgUnitNo;
	private String useOrgUnitNo;
	private String materialClassName;
	private String materialName;
	
	//即时库存汇总表
	private BigDecimal inQty;
	private BigDecimal inPieQty;
	private BigDecimal inAmt;
	private BigDecimal outQty;
	private BigDecimal outPieQty;
	private String classCode;
	private BigDecimal outAmt;
	private BigDecimal pieUnit;
	private BigDecimal stockQty;
	private BigDecimal stockPieQty;
	private BigDecimal stockAmt;
	private BigDecimal totalQty;
	private BigDecimal totalPieQty;
	private BigDecimal totalAmt;
	private BigDecimal storageUnit;
	
	// 是否生成应付 
	private boolean buildAP;
	
	private String vendorNo;
	private String purOrgUnitName;
	private String buyerCode;
	private String buyerName;
	private String creatorName;
	private String editorName;
	private String checkerName;
	private String statusName;
	private String bizTypeName;
	private String ReceiverName;
	private String contractNo;
	private String wareHouseName;
	private String useOrgUnitName;
	private String whNo;
	private String pvNo;
	private String pendingUsrName;
	private long taskId;
    private boolean freeItem;
    private String orgUnitName;
    private String poNo;
    private String buyerPhone;
    private long platSupplierId;
    private BigDecimal taxPrice;
    private String taxRateStr;
    
	private List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList;
	
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getWhNo() {
		return whNo;
	}

	public void setWhNo(String whNo) {
		this.whNo = whNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getInQty() {
		return inQty;
	}

	public void setInQty(BigDecimal inQty) {
		this.inQty = inQty;
	}

	public BigDecimal getInPieQty() {
		return inPieQty;
	}

	public void setInPieQty(BigDecimal inPieQty) {
		this.inPieQty = inPieQty;
	}

	public BigDecimal getInAmt() {
		return inAmt;
	}

	public void setInAmt(BigDecimal inAmt) {
		this.inAmt = inAmt;
	}

	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public BigDecimal getOutPieQty() {
		return outPieQty;
	}

	public void setOutPieQty(BigDecimal outPieQty) {
		this.outPieQty = outPieQty;
	}

	public BigDecimal getOutAmt() {
		return outAmt;
	}

	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	public BigDecimal getPieUnit() {
		return pieUnit;
	}

	public void setPieUnit(BigDecimal pieUnit) {
		this.pieUnit = pieUnit;
	}

	public BigDecimal getStockQty() {
		return stockQty;
	}

	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}

	public BigDecimal getStockPieQty() {
		return stockPieQty;
	}

	public void setStockPieQty(BigDecimal stockPieQty) {
		this.stockPieQty = stockPieQty;
	}

	public BigDecimal getStockAmt() {
		return stockAmt;
	}

	public void setStockAmt(BigDecimal stockAmt) {
		this.stockAmt = stockAmt;
	}

	public BigDecimal getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}

	public BigDecimal getTotalPieQty() {
		return totalPieQty;
	}

	public void setTotalPieQty(BigDecimal totalPieQty) {
		this.totalPieQty = totalPieQty;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getStorageUnit() {
		return storageUnit;
	}

	public void setStorageUnit(BigDecimal storageUnit) {
		this.storageUnit = storageUnit;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getMaterialClassName() {
		return materialClassName;
	}

	public void setMaterialClassName(String materialClassName) {
		this.materialClassName = materialClassName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String cstOrgUnitNo) {
		this.costOrgUnitNo = cstOrgUnitNo;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
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

	public BigDecimal getStorageQty() {
		return storageQty;
	}

	public void setStorageQty(BigDecimal storageQty) {
		this.storageQty = storageQty;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getStorageAmt() {
		return storageAmt;
	}

	public void setStorageAmt(BigDecimal storageAmt) {
		this.storageAmt = storageAmt;
	}

	public String getStorageOrgUnitNo() {
		return storageOrgUnitNo;
	}

	public void setStorageOrgUnitNo(String storageOrgUnitNo) {
		this.storageOrgUnitNo = storageOrgUnitNo;
	}

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public BigDecimal getTaxInAmt() {
		return taxInAmt;
	}

	public void setTaxInAmt(BigDecimal taxInAmt) {
		this.taxInAmt = taxInAmt;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getInvOrgUnitName() {
        return invOrgUnitName;
    }

    public void setInvOrgUnitName(String invOrgUnitName) {
        this.invOrgUnitName = invOrgUnitName;
    }

    public long getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(long wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public long getUnit() {
        return unit;
    }

    public void setUnit(long unit) {
        this.unit = unit;
    }

    public boolean isBuildAP() {
        return buildAP;
    }

    public void setBuildAP(boolean buildAP) {
        this.buildAP = buildAP;
    }
    
    public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getPurOrgUnitName() {
		return purOrgUnitName;
	}

	public void setPurOrgUnitName(String purOrgUnitName) {
		this.purOrgUnitName = purOrgUnitName;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getReceiverName() {
		return ReceiverName;
	}

	public void setReceiverName(String receiverName) {
		ReceiverName = receiverName;
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

	public String getPvNo() {
		return pvNo;
	}

	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}

	public String getPendingUsrName() {
		return pendingUsrName;
	}

	public void setPendingUsrName(String pendingUsrName) {
		this.pendingUsrName = pendingUsrName;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public boolean isFreeItem() {
		return freeItem;
	}

	public void setFreeItem(boolean freeItem) {
		this.freeItem = freeItem;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public long getPlatSupplierId() {
		return platSupplierId;
	}

	public void setPlatSupplierId(long platSupplierId) {
		this.platSupplierId = platSupplierId;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public String getTaxRateStr() {
		return taxRateStr;
	}

	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}

	public String getLongNo() {
		return longNo;
	}

	public void setLongNo(String longNo) {
		this.longNo = longNo;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public List<ScmInvPurInWarehsBillEntry2> getScmInvPurInWarehsBillEntryList() {
		return scmInvPurInWarehsBillEntryList;
	}

	public void setScmInvPurInWarehsBillEntryList(
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList) {
		this.scmInvPurInWarehsBillEntryList = scmInvPurInWarehsBillEntryList;
	}

	public ScmInvPurInWarehsBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.taxInAmt = BigDecimal.ZERO;
		}
	}

	public ScmInvPurInWarehsBill2() {
		super();
	}
}

package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmInvCountingTask2 extends ScmInvCountingTask{

	public static final String FN_ACCOUNTAMT = "accountAmt";
	public static final String FN_AMT = "amt";
	public static final String FN_ACCOUNTTAXAMT = "accountTaxAmt";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_USEORGUNITNO ="useOrgUnitNo";
	
	
	private BigDecimal accountAmt;
	private BigDecimal amt;
	private BigDecimal accountTaxAmt;
	private BigDecimal taxAmt;
	private String useOrgUnitNo;
	private String wareHouseNo;
	private String statusName;
	private String wrOrDept;
	private String whName;
	private long itemClassId;
	/*
	 * 成本中心物资进出存汇总(含税)
	 */
	private BigDecimal sumCode;
	private String className;
	private String itemNo;
	private String itemName;
	private String spec;
	private String unit;
	private String costOrgUnitNo;
	private BigDecimal initQty;
	private BigDecimal initPrice;
	private BigDecimal initAmt;
	private BigDecimal initTax;
	private BigDecimal taxInitAmt;
	private BigDecimal inQty;
	private BigDecimal inPrice;
	private BigDecimal inAmt;
	private BigDecimal inTax;
	private BigDecimal taxInAmt;
	private BigDecimal outQty;
	private BigDecimal outPrice;
	private BigDecimal outAmt;
	private BigDecimal outTax;
	private BigDecimal taxOutAmt;
	private BigDecimal endQty;
	private BigDecimal endPrice;
	private BigDecimal endAmt;
	private BigDecimal endTax;
	private BigDecimal taxEndAmt;
	
	/*
	 * 成本中心物资进出存明细表（含税）
	 */
	private String billNo;
	private String bizType;
	private String baseUnit;
	private String lot;
	private String orderNo;
	private String vendorName;
	private String custNo;
	private BigDecimal inBaseQty;
	private BigDecimal outBaseQty;
	private BigDecimal endBaseQty;
	private BigDecimal initBaseQty;
	private String bizTypeName;
	
	private List<String> list;
	private boolean showSum;
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public long getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(long itemClassId) {
		this.itemClassId = itemClassId;
	}

	public BigDecimal getInitBaseQty() {
		return initBaseQty;
	}

	public void setInitBaseQty(BigDecimal initBaseQty) {
		this.initBaseQty = initBaseQty;
	}

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public BigDecimal getInBaseQty() {
		return inBaseQty;
	}

	public void setInBaseQty(BigDecimal inBaseQty) {
		this.inBaseQty = inBaseQty;
	}

	public BigDecimal getOutBaseQty() {
		return outBaseQty;
	}

	public void setOutBaseQty(BigDecimal outBaseQty) {
		this.outBaseQty = outBaseQty;
	}

	public BigDecimal getEndBaseQty() {
		return endBaseQty;
	}

	public void setEndBaseQty(BigDecimal endBaseQty) {
		this.endBaseQty = endBaseQty;
	}

	public String getWhName() {
		return whName;
	}

	public BigDecimal getSumCode() {
		return sumCode;
	}

	public void setSumCode(BigDecimal sumCode) {
		this.sumCode = sumCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}

	public BigDecimal getInitQty() {
		return initQty;
	}

	public void setInitQty(BigDecimal initQty) {
		this.initQty = initQty;
	}

	public BigDecimal getInitPrice() {
		return initPrice;
	}

	public void setInitPrice(BigDecimal initPrice) {
		this.initPrice = initPrice;
	}

	public BigDecimal getInitAmt() {
		return initAmt;
	}

	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	public BigDecimal getInitTax() {
		return initTax;
	}

	public void setInitTax(BigDecimal initTax) {
		this.initTax = initTax;
	}

	public BigDecimal getTaxInitAmt() {
		return taxInitAmt;
	}

	public void setTaxInitAmt(BigDecimal taxInitAmt) {
		this.taxInitAmt = taxInitAmt;
	}

	public BigDecimal getInQty() {
		return inQty;
	}

	public void setInQty(BigDecimal inQty) {
		this.inQty = inQty;
	}

	public BigDecimal getInPrice() {
		return inPrice;
	}

	public void setInPrice(BigDecimal inPrice) {
		this.inPrice = inPrice;
	}

	public BigDecimal getInAmt() {
		return inAmt;
	}

	public void setInAmt(BigDecimal inAmt) {
		this.inAmt = inAmt;
	}

	public BigDecimal getInTax() {
		return inTax;
	}

	public void setInTax(BigDecimal inTax) {
		this.inTax = inTax;
	}

	public BigDecimal getTaxInAmt() {
		return taxInAmt;
	}

	public void setTaxInAmt(BigDecimal taxInAmt) {
		this.taxInAmt = taxInAmt;
	}

	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public BigDecimal getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(BigDecimal outPrice) {
		this.outPrice = outPrice;
	}

	public BigDecimal getOutAmt() {
		return outAmt;
	}

	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	public BigDecimal getOutTax() {
		return outTax;
	}

	public void setOutTax(BigDecimal outTax) {
		this.outTax = outTax;
	}

	public BigDecimal getTaxOutAmt() {
		return taxOutAmt;
	}

	public void setTaxOutAmt(BigDecimal taxOutAmt) {
		this.taxOutAmt = taxOutAmt;
	}

	public BigDecimal getEndQty() {
		return endQty;
	}

	public void setEndQty(BigDecimal endQty) {
		this.endQty = endQty;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public BigDecimal getEndAmt() {
		return endAmt;
	}

	public void setEndAmt(BigDecimal endAmt) {
		this.endAmt = endAmt;
	}

	public BigDecimal getEndTax() {
		return endTax;
	}

	public void setEndTax(BigDecimal endTax) {
		this.endTax = endTax;
	}

	public BigDecimal getTaxEndAmt() {
		return taxEndAmt;
	}

	public void setTaxEndAmt(BigDecimal taxEndAmt) {
		this.taxEndAmt = taxEndAmt;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}

	public BigDecimal getAccountAmt() {
		return accountAmt;
	}

	public void setAccountAmt(BigDecimal accountAmt) {
		this.accountAmt = accountAmt;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getAccountTaxAmt() {
		return accountTaxAmt;
	}

	public void setAccountTaxAmt(BigDecimal accountTaxAmt) {
		this.accountTaxAmt = accountTaxAmt;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getWrOrDept() {
		return wrOrDept;
	}

	public void setWrOrDept(String wrOrDept) {
		this.wrOrDept = wrOrDept;
	}

	public boolean isShowSum() {
		return showSum;
	}

	public void setShowSum(boolean showSum) {
		this.showSum = showSum;
	}

	public ScmInvCountingTask2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.accountAmt = BigDecimal.ZERO;
			this.amt = BigDecimal.ZERO;
			this.accountTaxAmt = BigDecimal.ZERO;
			this.taxAmt = BigDecimal.ZERO;
			this.showSum=false;
		}
	}

	public ScmInvCountingTask2() {
		super();
	}
	
}

package com.armitage.server.iscm.report.costcenter.model;

import java.math.BigDecimal;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmNewCostTransferOccurSum extends BaseModel{
	private String classCode;
    private String className;
    private long itemId;
    private String itemNo;
    private String itemName;
    private String spec;
    private long unit;
    private String unitName;
    private String costOrgUnitNo;
    private String costOrgUnitName;
    private int orderNo;
    private String bizType;
    private BigDecimal qty;
    private BigDecimal amt;
    private BigDecimal tax;
    private BigDecimal taxAmt;
    private String intervaltype;
    private String classNameTwo;
    
    
    
	public String getIntervaltype() {
		return intervaltype;
	}

	public void setIntervaltype(String intervaltype) {
		this.intervaltype = intervaltype;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
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

	public long getUnit() {
		return unit;
	}

	public void setUnit(long unit) {
		this.unit = unit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}

	public String getCostOrgUnitName() {
		return costOrgUnitName;
	}

	public void setCostOrgUnitName(String costOrgUnitName) {
		this.costOrgUnitName = costOrgUnitName;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	 public String getClassNameTwo() {
		return classNameTwo;
	}

	public void setClassNameTwo(String classNameTwo) {
		this.classNameTwo = classNameTwo;
	}

	public ScmNewCostTransferOccurSum(boolean flag) {
	        super();
	        if(flag) {
	            this.qty = BigDecimal.ZERO;
	            this.amt = BigDecimal.ZERO;
	            this.tax = BigDecimal.ZERO;
	            this.taxAmt = BigDecimal.ZERO;

	        }
	    }

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}

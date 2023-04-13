package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvInWareMonthAnalysis extends BaseModel{
    private Long itemId;
    
    private String itemNo;
    private String itemName;
    private String vendorName;
    private String vendorNo;
    private BigDecimal amt;
    private BigDecimal qty;
    private BigDecimal taxAmt;
    private BigDecimal price;
    private BigDecimal taxPrice;
    private String intervaltype;
    private Long classId;
    private String className;
    private String unitName;
    private String brandCode;
    private String brandName;
    private BigDecimal brandId;

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public BigDecimal getBrandId() {
		return brandId;
	}

	public void setBrandId(BigDecimal brandId) {
		this.brandId = brandId;
	}
	// 	产品月累计销售
    private Long custId;
    private String custName;
    
    public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
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

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public String getIntervaltype() {
		return intervaltype;
	}

	public void setIntervaltype(String intervaltype) {
		this.intervaltype = intervaltype;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ScmInvInWareMonthAnalysis(boolean flag) {
        super();
        if(flag) {
            this.taxPrice = BigDecimal.ZERO;
            this.qty = BigDecimal.ZERO;
            this.taxAmt = BigDecimal.ZERO;
            this.price = BigDecimal.ZERO;
            this.qty = BigDecimal.ZERO;
            this.amt = BigDecimal.ZERO;
        }
    }
	public ScmInvInWareMonthAnalysis() {
    }

}

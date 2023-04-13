package com.armitage.server.iscm.report.purchase.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
/*
 * 供应商考核明细表
 */
@XmlRootElement(name = "scmPurSupplierAppraiseDetails")  
public class ScmPurSupplierAppraiseDetails  extends BaseModel {
	private long vendorId;
	private String vendorName;
	private long appraiseTypeId;
	private String appraiseType;
	private String billType;
	private String orgUnitNo;
	private String orgUnitName;
	private Date bizDate;
	private String billNo;
	private String className;
	private long itemId;
	private String itemNo;
	private String itemName;
	private String spec;
	private long unit;
	private String unitName;
	private BigDecimal orderQty;
	private BigDecimal receiveQty;
	private BigDecimal diffQty;
	private String remarks;
	
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
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

	public long getAppraiseTypeId() {
		return appraiseTypeId;
	}

	public void setAppraiseTypeId(long appraiseTypeId) {
		this.appraiseTypeId = appraiseTypeId;
	}

	public String getAppraiseType() {
		return appraiseType;
	}

	public void setAppraiseType(String appraiseType) {
		this.appraiseType = appraiseType;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public BigDecimal getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	public BigDecimal getReceiveQty() {
		return receiveQty;
	}

	public void setReceiveQty(BigDecimal receiveQty) {
		this.receiveQty = receiveQty;
	}

	public BigDecimal getDiffQty() {
		return diffQty;
	}

	public void setDiffQty(BigDecimal diffQty) {
		this.diffQty = diffQty;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ScmPurSupplierAppraiseDetails() {
		super();
	}

	public ScmPurSupplierAppraiseDetails(boolean flag) {
		super();
		if(flag) {
			this.orderQty = BigDecimal.ZERO;
			this.receiveQty = BigDecimal.ZERO;
			this.diffQty = BigDecimal.ZERO;
		}
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
}
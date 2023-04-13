package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;

@XmlRootElement(name = "scmInvInWarehsItemClass")  
public class ScmInvInWarehsItemClass2  extends BaseModel {
	private String classCode;
	private String className;
	private long vendorId;
	private String vendorNo;
	private String vendorName;
	private BigDecimal totalAmt;
	private BigDecimal amt;
	private BigDecimal taxTotalAmt;
	private BigDecimal taxAmt;
	private BigDecimal taxAmount;
	private BigDecimal taxTotalAmount;
	
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

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
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

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTaxTotalAmt() {
		return taxTotalAmt;
	}

	public void setTaxTotalAmt(BigDecimal taxTotalAmt) {
		this.taxTotalAmt = taxTotalAmt;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxTotalAmount() {
		return taxTotalAmount;
	}

	public void setTaxTotalAmount(BigDecimal taxTotalAmount) {
		this.taxTotalAmount = taxTotalAmount;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
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
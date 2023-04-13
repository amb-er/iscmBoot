package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;

/**
 * 日营业汇总表
 * @author luo
 *
 */
public class ScmInvSaleBusiness {
	
	private String date;//日期
	private String vendorId;//供应商类别
	private String vendorType;
	
	private Long custId;//客户ID
	private String custName;
	private String custNo;
	private BigDecimal purAmt;//入库不含税金额
	private BigDecimal purTaxAmt;//入库含税金额
	private BigDecimal saleAmt;	//销售成本含税金额
	private BigDecimal saleTaxAmt;//销售含税金额
	private String brandCode;
	private String brandName;
	private BigDecimal brandId;
	
	
	
	
	@Override
	public String toString() {
		return "ScmInvSaleBusiness [date=" + date + ", vendorId=" + vendorId + ", vendorType=" + vendorType
				+ ", custId=" + custId + ", custName=" + custName + ", custNo=" + custNo + ", purAmt=" + purAmt
				+ ", purTaxAmt=" + purTaxAmt + ", saleAmt=" + saleAmt + ", saleTaxAmt=" + saleTaxAmt + "]";
	}
	
	
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
	public BigDecimal getSaleAmt() {
		return saleAmt;
	}
	public void setSaleAmt(BigDecimal saleAmt) {
		this.saleAmt = saleAmt;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public BigDecimal getPurTaxAmt() {
		return purTaxAmt;
	}
	public void setPurTaxAmt(BigDecimal purTaxAmt) {
		this.purTaxAmt = purTaxAmt;
	}
	public BigDecimal getSaleTaxAmt() {
		return saleTaxAmt;
	}
	public void setSaleTaxAmt(BigDecimal saleTaxAmt) {
		this.saleTaxAmt = saleTaxAmt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public BigDecimal getPurAmt() {
		return purAmt;
	}
	public void setPurAmt(BigDecimal purAmt) {
		this.purAmt = purAmt;
	}
	
	


}

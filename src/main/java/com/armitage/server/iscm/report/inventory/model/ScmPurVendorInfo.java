package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;

public class ScmPurVendorInfo {

	private String vendorCode;
	private String vendorName;
	private String orgUnitName;
	private String orgUnitCode;
	private BigDecimal purAmt;
	private BigDecimal purTaxAmt;
	private BigDecimal receiveAmt;
	private BigDecimal receiveTaxAmt;
	
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getOrgUnitName() {
		return orgUnitName;
	}
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}
	public String getOrgUnitCode() {
		return orgUnitCode;
	}
	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}
	public BigDecimal getPurAmt() {
		return purAmt;
	}
	public void setPurAmt(BigDecimal purAmt) {
		this.purAmt = purAmt;
	}
	public BigDecimal getPurTaxAmt() {
		return purTaxAmt;
	}
	public void setPurTaxAmt(BigDecimal purTaxAmt) {
		this.purTaxAmt = purTaxAmt;
	}
	public BigDecimal getReceiveAmt() {
		return receiveAmt;
	}
	public void setReceiveAmt(BigDecimal receiveAmt) {
		this.receiveAmt = receiveAmt;
	}
	public BigDecimal getReceiveTaxAmt() {
		return receiveTaxAmt;
	}
	public void setReceiveTaxAmt(BigDecimal receiveTaxAmt) {
		this.receiveTaxAmt = receiveTaxAmt;
	}

}

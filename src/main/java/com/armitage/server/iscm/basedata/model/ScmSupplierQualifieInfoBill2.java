package com.armitage.server.iscm.basedata.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmSupplierQualifieInfoBill2 extends ScmSupplierQualifieInfoBill {
	private String className;
	private String contactPerson;
	private String mobile;
	private String taxpayerTypeName;
	private BigDecimal taxRate;
	private String taxNo;
	private String vendorName;
	private String creatorName;
	private String statusName;
	private String pendingUsr;
	private List<ScmSupplierQualifieInfoBillEntry2> scmSupplierQualifieInfoBillEntryList;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTaxpayerTypeName() {
		return taxpayerTypeName;
	}

	public void setTaxpayerTypeName(String taxpayerTypeName) {
		this.taxpayerTypeName = taxpayerTypeName;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPendingUsr() {
		return pendingUsr;
	}

	public void setPendingUsr(String pendingUsr) {
		this.pendingUsr = pendingUsr;
	}

	public List<ScmSupplierQualifieInfoBillEntry2> getScmSupplierQualifieInfoBillEntryList() {
		return scmSupplierQualifieInfoBillEntryList;
	}

	public void setScmSupplierQualifieInfoBillEntryList(
			List<ScmSupplierQualifieInfoBillEntry2> scmSupplierQualifieInfoBillEntryList) {
		this.scmSupplierQualifieInfoBillEntryList = scmSupplierQualifieInfoBillEntryList;
	}

	public ScmSupplierQualifieInfoBill2(boolean defaultValue) {
		super(defaultValue);
	}
	
	public ScmSupplierQualifieInfoBill2(){
		super();
	}
}

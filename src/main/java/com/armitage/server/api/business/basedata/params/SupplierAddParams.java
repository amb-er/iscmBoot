package com.armitage.server.api.business.basedata.params;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierAddParams",description="供应商新增参数对象")
public class SupplierAddParams {
	@ApiModelProperty(value="供应商名称",dataType="String",example="",required=true)
    private String vendorName;
	
	@ApiModelProperty(value="供应商类别",dataType="String",example="00001",required=true)
	private String role;

	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=false)
	private String remarks;
	
	@ApiModelProperty(value="纳税人类型",dataType="String",example="A",required=true)
	private String taxpayerType;
	
	@ApiModelProperty(value="税率",dataType="BigDecimal",example="10",required=true)
	private BigDecimal vatRate;
	
	@ApiModelProperty(value="联系人",dataType="String",example="",required=true)
	private String contactPerson;
	
	@ApiModelProperty(value="联系电话",dataType="String",example="",required=false)
	private String tel;
	
	@ApiModelProperty(value="地址",dataType="String",example="",required=false)
	private String address;
	
	@ApiModelProperty(value="供应商开户行",dataType="String",example="",required=true)
	private String bankName;
	
	@ApiModelProperty(value="供应商收款账号",dataType="String",example="",required=true)
	private String bankAccNo;
	
	@ApiModelProperty(value="银行地址",dataType="String",example="",required=false)
	private String bankAddress;
	
	@ApiModelProperty(value="外部系统档案编码",dataType="String",example="",required=false)
	private String externalCode;
	
	@ApiModelProperty(value="第三方供应商编码是否必须",dataType="boolean",example="",required=false)
	private boolean external;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTaxpayerType() {
		return taxpayerType;
	}

	public void setTaxpayerType(String taxpayerType) {
		this.taxpayerType = taxpayerType;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getExternalCode() {
		return externalCode;
	}

	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}
	
}

package com.armitage.server.api.business.datareport.result;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SupSupplyOfMaterialDetailsResult", description = "供应商供货明细查询返回结果")
public class SupSupplyOfMaterialDetailsResult {

	@ApiModelProperty(value = "业务日期", dataType = "Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Date bizDate;
	
	@ApiModelProperty(value = "供应商编码", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String vendorNo;
	
	@ApiModelProperty(value = "供应商名称", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String vendorName;
	
	@ApiModelProperty(value = "业务类型", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String bizTypeName;
	
	@ApiModelProperty(value = "部门编码", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String useOrgUnitNo;
	
	@ApiModelProperty(value = "部门名称", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String useOrgUnitNoName;
	
	@ApiModelProperty(value = "仓库名称", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String wareHouseName;
	
	@ApiModelProperty(value = "单号", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String wrNo;
	
	@ApiModelProperty(value = "物资编码", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String itemNo;
	
	@ApiModelProperty(value = "物资ID", dataType = "Long")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private long itemId;
	
	@ApiModelProperty(value = "物资名称", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String itemName;
	
	@ApiModelProperty(value = "物资类别编号", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String classCode;

	@ApiModelProperty(value = "物资类别名称", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String itemGroupName;
	
	@ApiModelProperty(value = "规格", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String spec;
	
	@ApiModelProperty(value = "计量单位", dataType = "BigDecimal")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String unit;
	
	@ApiModelProperty(value = "数量", dataType = "BigDecimal")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal qty;
	
	@ApiModelProperty(value = "单价", dataType = "BigDecimal")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price;

	@ApiModelProperty(value = "金额", dataType = "BigDecimal")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;
	
	@ApiModelProperty(value = "含税金额", dataType = "BigDecimal")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxAmt;

	@ApiModelProperty(value = "税率", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String taxRateStr;

	@ApiModelProperty(value = "含税价", dataType = "BigDecimal")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxPrice;
	
	@ApiModelProperty(value = "创建日期", dataType = "Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Date createDate;
	
	@ApiModelProperty(value = "创建人", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String creator;
	
	@ApiModelProperty(value = "修改日期", dataType = "Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Date editDate;
	
	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
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

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}

	public String getUseOrgUnitNoName() {
		return useOrgUnitNoName;
	}

	public void setUseOrgUnitNoName(String useOrgUnitNoName) {
		this.useOrgUnitNoName = useOrgUnitNoName;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getWrNo() {
		return wrNo;
	}

	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
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

	public String getItemGroupName() {
		return itemGroupName;
	}

	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public String getTaxRateStr() {
		return taxRateStr;
	}

	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	
}

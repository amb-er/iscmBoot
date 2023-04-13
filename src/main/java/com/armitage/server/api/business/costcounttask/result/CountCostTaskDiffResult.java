package com.armitage.server.api.business.costcounttask.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="CountCostTaskDiffResult",description="盘存差异返回结果")
public class CountCostTaskDiffResult {
	
	@ApiModelProperty(value="部门编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String deptNo;

	@ApiModelProperty(value="部门名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String deptName;

	@ApiModelProperty(value="物资编码",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemNo;

	@ApiModelProperty(value="物资名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemName;
	
	@ApiModelProperty(value="规格",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String spec;

	@ApiModelProperty(value="计量单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String unit;
	
	@ApiModelProperty(value="批次",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String lot;

	@ApiModelProperty(value="差异数",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal differQty;

	@ApiModelProperty(value="差异金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal differAmt;
	
	@ApiModelProperty(value="差异含税金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal differTaxAmt;
	
	@ApiModelProperty(value="辅助计量单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String pieUnit;
	
	@ApiModelProperty(value="辅助差异数",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal differPieQty;

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public BigDecimal getDifferQty() {
		return differQty;
	}

	public void setDifferQty(BigDecimal differQty) {
		this.differQty = differQty;
	}

	public BigDecimal getDifferAmt() {
		return differAmt;
	}

	public void setDifferAmt(BigDecimal differAmt) {
		this.differAmt = differAmt;
	}

	public BigDecimal getDifferTaxAmt() {
		return differTaxAmt;
	}

	public void setDifferTaxAmt(BigDecimal differTaxAmt) {
		this.differTaxAmt = differTaxAmt;
	}

	public String getPieUnit() {
		return pieUnit;
	}

	public void setPieUnit(String pieUnit) {
		this.pieUnit = pieUnit;
	}

	public BigDecimal getDifferPieQty() {
		return differPieQty;
	}

	public void setDifferPieQty(BigDecimal differPieQty) {
		this.differPieQty = differPieQty;
	}
	
}

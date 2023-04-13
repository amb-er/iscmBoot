package com.armitage.server.iscm.inventorymanage.countbusiness.model;

import java.math.BigDecimal;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvCountInvTaskDiff extends BaseModel  {
	
	private String wareHouseNo;
	private String wareHouseName;
	private String itemNo;
	private String itemName;
	private String spec;
	private String unit;
	private String unitName;
	private String lot;
	private BigDecimal differQty;
	private BigDecimal differAmt;
	private BigDecimal differTaxAmt;
	private String pieUnitName;
	private BigDecimal differPieQty;

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}

	public BigDecimal getDifferPieQty() {
		return differPieQty;
	}

	public void setDifferPieQty(BigDecimal differPieQty) {
		this.differPieQty = differPieQty;
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

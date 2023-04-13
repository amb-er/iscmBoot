package com.armitage.server.quartz.model.invpurinwarehs;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierPlatInvPurInWarehsBillEntry {

	private BigDecimal amt;
	private long balanceDemanderId;
	private long id;
	private String itemName;
	private String itemNo;
	private int lineId;
	private String lot;
	private BigDecimal price;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date productDate;
	private BigDecimal qty;
	private String remarks;
	private String spec;
	private BigDecimal taxAmt;
	private BigDecimal taxPrice;
	private BigDecimal taxRate;
	private String unitName;
	private String wareDept;
	private String wareHouse;
	private String wareHouseNo;
	private String wareDeptNo;
	private String reqOrgUnitNo;
	private String reqOrgName;
	private String finOrgUnitNo;
	private String finOrgName;
	private long wrId;
	
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public long getBalanceDemanderId() {
		return balanceDemanderId;
	}
	public void setBalanceDemanderId(long balanceDemanderId) {
		this.balanceDemanderId = balanceDemanderId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getWareDept() {
		return wareDept;
	}
	public void setWareDept(String wareDept) {
		this.wareDept = wareDept;
	}
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	public String getWareDeptNo() {
		return wareDeptNo;
	}
	public void setWareDeptNo(String wareDeptNo) {
		this.wareDeptNo = wareDeptNo;
	}
	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}
	public void setReqOrgUnitNo(String reqOrgUnitNo) {
		this.reqOrgUnitNo = reqOrgUnitNo;
	}
	public String getReqOrgName() {
		return reqOrgName;
	}
	public void setReqOrgName(String reqOrgName) {
		this.reqOrgName = reqOrgName;
	}
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public String getFinOrgName() {
		return finOrgName;
	}
	public void setFinOrgName(String finOrgName) {
		this.finOrgName = finOrgName;
	}
	public long getWrId() {
		return wrId;
	}
	public void setWrId(long wrId) {
		this.wrId = wrId;
	}
}

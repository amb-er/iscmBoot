package com.armitage.server.quartz.model.purorder;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierPlatPurOrderEntry {

	private BigDecimal amt;
	private long balanceDemanderId;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date deliveryDate;
	private long id;
	private String itemName;
	private String itemNo;
	private int lineId;
	private long poId;
	private BigDecimal price;
	private BigDecimal qty;
	private String receiveAddress;
	private String receiveNo;
	private String reqUnitNo;
	private String reqUnitName;
	private String finUnitNo;
	private String finUnitName;
	private String remarks;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date reqDate;
	private String spec;
	private BigDecimal taxAmt;
	private BigDecimal taxPrice;
	private BigDecimal taxRate;
	private String unitName;
	private String attachData;
	private String attachMD5;
	private BigDecimal pieQty;
	private String pieUnitName;
	
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
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
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
	public long getPoId() {
		return poId;
	}
	public void setPoId(long poId) {
		this.poId = poId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getReceiveNo() {
		return receiveNo;
	}
	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}
	public String getReqUnitNo() {
		return reqUnitNo;
	}
	public void setReqUnitNo(String reqUnitNo) {
		this.reqUnitNo = reqUnitNo;
	}
	public String getReqUnitName() {
		return reqUnitName;
	}
	public void setReqUnitName(String reqUnitName) {
		this.reqUnitName = reqUnitName;
	}
	public String getFinUnitNo() {
		return finUnitNo;
	}
	public void setFinUnitNo(String finUnitNo) {
		this.finUnitNo = finUnitNo;
	}
	public String getFinUnitName() {
		return finUnitName;
	}
	public void setFinUnitName(String finUnitName) {
		this.finUnitName = finUnitName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
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
	public String getAttachData() {
		return attachData;
	}
	public void setAttachData(String attachData) {
		this.attachData = attachData;
	}
	public String getAttachMD5() {
		return attachMD5;
	}
	public void setAttachMD5(String attachMD5) {
		this.attachMD5 = attachMD5;
	}
	public BigDecimal getPieQty() {
		return pieQty;
	}
	public void setPieQty(BigDecimal pieQty) {
		this.pieQty = pieQty;
	}
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	
}

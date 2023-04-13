package com.armitage.server.iscm.report.purchase.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;

@XmlRootElement(name = "scmPurOrderTransInfo")  
public class ScmPurOrderTransInfo extends BaseModel {

	private Date bizDate;
	private String poNo ;
	private Date orderDate ;
	private long vendorId;
	private String vendorNo;
	private String vendorName;
	private String purOrgUnitNo;
	private String purOrgUnitName;
	private long purGroupId;
	private String purGroupName;
	private long buyerId;
	private String buyerName;
	private long itemId;
	private String itemNo;
	private String itemName;
	private String spec;
	private long purUnit;
	private String purUnitName;
	private String storageOrgUnitNo;
	private String storageOrgUnitName;
	private String finOrgUnitNo;
	private String finOrgUnitName;
	private BigDecimal qty;
	private BigDecimal price;
	private BigDecimal amt;
	private BigDecimal taxPrice;
	private BigDecimal taxAmt;
	private BigDecimal receiveQty;
	private BigDecimal receiveAmt;
	private BigDecimal receiveTaxAmt;
	private BigDecimal addInQty;
	private BigDecimal addInAmt;
	private BigDecimal addInTaxAmt;
	private BigDecimal returnQty;
	private BigDecimal returnAmt;
	private BigDecimal returnTaxAmt;
	private BigDecimal outQty;
	private BigDecimal outAmt;
	private BigDecimal outTaxAmt;
	private BigDecimal notAddInQty;
	private BigDecimal notAddInAmt;
	private BigDecimal notAddInTaxAmt;
	private BigDecimal realAddInQty;
	private BigDecimal realAddInAmt;
	private BigDecimal realAddInPrice;
	private BigDecimal realAddInTaxPrice;
	private BigDecimal realAddInTaxAmt;
	
	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}

	public String getPurOrgUnitName() {
		return purOrgUnitName;
	}

	public void setPurOrgUnitName(String purOrgUnitName) {
		this.purOrgUnitName = purOrgUnitName;
	}

	public long getPurGroupId() {
		return purGroupId;
	}

	public void setPurGroupId(long purGroupId) {
		this.purGroupId = purGroupId;
	}

	public String getPurGroupName() {
		return purGroupName;
	}

	public void setPurGroupName(String purGroupName) {
		this.purGroupName = purGroupName;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
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

	public long getPurUnit() {
		return purUnit;
	}

	public void setPurUnit(long purUnit) {
		this.purUnit = purUnit;
	}

	public String getPurUnitName() {
		return purUnitName;
	}

	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}

	public String getStorageOrgUnitNo() {
		return storageOrgUnitNo;
	}

	public void setStorageOrgUnitNo(String storageOrgUnitNo) {
		this.storageOrgUnitNo = storageOrgUnitNo;
	}

	public String getStorageOrgUnitName() {
		return storageOrgUnitName;
	}

	public void setStorageOrgUnitName(String storageOrgUnitName) {
		this.storageOrgUnitName = storageOrgUnitName;
	}

	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public String getFinOrgUnitName() {
		return finOrgUnitName;
	}

	public void setFinOrgUnitName(String finOrgUnitName) {
		this.finOrgUnitName = finOrgUnitName;
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

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getReceiveQty() {
		return receiveQty;
	}

	public void setReceiveQty(BigDecimal receiveQty) {
		this.receiveQty = receiveQty;
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

	public BigDecimal getAddInQty() {
		return addInQty;
	}

	public void setAddInQty(BigDecimal addInQty) {
		this.addInQty = addInQty;
	}

	public BigDecimal getAddInAmt() {
		return addInAmt;
	}

	public void setAddInAmt(BigDecimal addInAmt) {
		this.addInAmt = addInAmt;
	}

	public BigDecimal getAddInTaxAmt() {
		return addInTaxAmt;
	}

	public void setAddInTaxAmt(BigDecimal addInTaxAmt) {
		this.addInTaxAmt = addInTaxAmt;
	}

	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal returnQty) {
		this.returnQty = returnQty;
	}

	public BigDecimal getReturnAmt() {
		return returnAmt;
	}

	public void setReturnAmt(BigDecimal returnAmt) {
		this.returnAmt = returnAmt;
	}

	public BigDecimal getReturnTaxAmt() {
		return returnTaxAmt;
	}

	public void setReturnTaxAmt(BigDecimal returnTaxAmt) {
		this.returnTaxAmt = returnTaxAmt;
	}

	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public BigDecimal getOutAmt() {
		return outAmt;
	}

	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	public BigDecimal getOutTaxAmt() {
		return outTaxAmt;
	}

	public void setOutTaxAmt(BigDecimal outTaxAmt) {
		this.outTaxAmt = outTaxAmt;
	}

	public BigDecimal getNotAddInQty() {
		return notAddInQty;
	}

	public void setNotAddInQty(BigDecimal notAddInQty) {
		this.notAddInQty = notAddInQty;
	}

	public BigDecimal getNotAddInAmt() {
		return notAddInAmt;
	}

	public void setNotAddInAmt(BigDecimal notAddInAmt) {
		this.notAddInAmt = notAddInAmt;
	}

	public BigDecimal getNotAddInTaxAmt() {
		return notAddInTaxAmt;
	}

	public void setNotAddInTaxAmt(BigDecimal notAddInTaxAmt) {
		this.notAddInTaxAmt = notAddInTaxAmt;
	}

	public BigDecimal getRealAddInQty() {
		return realAddInQty;
	}

	public void setRealAddInQty(BigDecimal realAddInQty) {
		this.realAddInQty = realAddInQty;
	}

	public BigDecimal getRealAddInAmt() {
		return realAddInAmt;
	}

	public void setRealAddInAmt(BigDecimal realAddInAmt) {
		this.realAddInAmt = realAddInAmt;
	}

	public BigDecimal getRealAddInPrice() {
		return realAddInPrice;
	}

	public void setRealAddInPrice(BigDecimal realAddInPrice) {
		this.realAddInPrice = realAddInPrice;
	}

	public BigDecimal getRealAddInTaxPrice() {
		return realAddInTaxPrice;
	}

	public void setRealAddInTaxPrice(BigDecimal realAddInTaxPrice) {
		this.realAddInTaxPrice = realAddInTaxPrice;
	}

	public BigDecimal getRealAddInTaxAmt() {
		return realAddInTaxAmt;
	}

	public void setRealAddInTaxAmt(BigDecimal realAddInTaxAmt) {
		this.realAddInTaxAmt = realAddInTaxAmt;
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


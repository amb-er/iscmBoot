package com.armitage.server.api.business.purorder.result;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderDetailOAResult",description="返回结果集resultList")
public class PurOrderDetailOAResult {
	@ApiModelProperty(value="dhId",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long dhId;
	
	@ApiModelProperty(value="订货单Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long poId;
	
	@ApiModelProperty(value="行号",dataType="Int")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int lineId;
	
	@ApiModelProperty(value="物资Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long itemId;

	@ApiModelProperty(value="物资编码",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemNo;

	@ApiModelProperty(value="物资名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemName;

//	@ApiModelProperty(value="规格",dataType="String")
//	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
//	private String spec;
	
	@ApiModelProperty(value="订货单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String purUnit;
	
	@ApiModelProperty(value="需求日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date reqDate;
	
	@ApiModelProperty(value="交货日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date deliveryDate;
	
	@ApiModelProperty(value="需求基本数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal reqQty;
	
	@ApiModelProperty(value="基本计量单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String baseUnit;
	
	@ApiModelProperty(value="订货基本数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal baseQty;

	@ApiModelProperty(value="订货数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal qty;

	@ApiModelProperty(value="单价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price;

	@ApiModelProperty(value="金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;
	
	@ApiModelProperty(value="税率值",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxRate;
	
	@ApiModelProperty(value="税率",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String taxRateStr;
	
	@ApiModelProperty(value="含税价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxPrice;

	@ApiModelProperty(value="含税金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxAmt;
	
	@ApiModelProperty(value="最新单价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal priceNew;
	
	@ApiModelProperty(value="累计已收数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal receiveQty;
	
	@ApiModelProperty(value="累计调拨基本数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal movedQty;
	
	@ApiModelProperty(value="累计退货数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal returnQty;
	
	@ApiModelProperty(value="申购组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String reqOrgUnitName;
	
	@ApiModelProperty(value="需求方库存组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String reqStorageOrgUnitName;
	
	@ApiModelProperty(value="需求方财务组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String reqFinOrgUnitName;

	@ApiModelProperty(value="收货组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String reveiveOrgUnitName;
	
	@ApiModelProperty(value="收货财务组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String receiveFinOrgUnitName;
	
	@ApiModelProperty(value="是否代收",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean instead;
	
	@ApiModelProperty(value="代收仓库",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String receiveWareHouseName;
	
	@ApiModelProperty(value="代收库存组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String mstorageOrgUnitName;
	
	@ApiModelProperty(value="结算供应商",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String balanceSupplierName;
	
	@ApiModelProperty(value="虚拟出库组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String storageOrgUnitName;
	
	@ApiModelProperty(value="采购员",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String buyer;
	
	@ApiModelProperty(value="所属采购组",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String purGroupName;
	
	@ApiModelProperty(value="请购单明细行号",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long prDtlId;
	
	@ApiModelProperty(value="价格源单号",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long priceBillId;
	
	@ApiModelProperty(value="单价来源状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String refPriceStatus;
	
	@ApiModelProperty(value="是否订货核定价格",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean apprPriceByPo;
	
	@ApiModelProperty(value="行状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String rowStatus;
	
	@ApiModelProperty(value="审核人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String checker;
	
	@ApiModelProperty(value="审核日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date checkDate;
	
	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;

	public long getDhId() {
		return dhId;
	}

	public void setDhId(long dhId) {
		this.dhId = dhId;
	}

	public long getPoId() {
		return poId;
	}

	public void setPoId(long poId) {
		this.poId = poId;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
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

	public String getPurUnit() {
		return purUnit;
	}

	public void setPurUnit(String purUnit) {
		this.purUnit = purUnit;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getReqQty() {
		return reqQty;
	}

	public void setReqQty(BigDecimal reqQty) {
		this.reqQty = reqQty;
	}

	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	public BigDecimal getBaseQty() {
		return baseQty;
	}

	public void setBaseQty(BigDecimal baseQty) {
		this.baseQty = baseQty;
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

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
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

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getPriceNew() {
		return priceNew;
	}

	public void setPriceNew(BigDecimal priceNew) {
		this.priceNew = priceNew;
	}

	public BigDecimal getReceiveQty() {
		return receiveQty;
	}

	public void setReceiveQty(BigDecimal receiveQty) {
		this.receiveQty = receiveQty;
	}

	public BigDecimal getMovedQty() {
		return movedQty;
	}

	public void setMovedQty(BigDecimal movedQty) {
		this.movedQty = movedQty;
	}

	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal returnQty) {
		this.returnQty = returnQty;
	}

	public String getReqOrgUnitName() {
		return reqOrgUnitName;
	}

	public void setReqOrgUnitName(String reqOrgUnitName) {
		this.reqOrgUnitName = reqOrgUnitName;
	}

	public String getReqStorageOrgUnitName() {
		return reqStorageOrgUnitName;
	}

	public void setReqStorageOrgUnitName(String reqStorageOrgUnitName) {
		this.reqStorageOrgUnitName = reqStorageOrgUnitName;
	}

	public String getReqFinOrgUnitName() {
		return reqFinOrgUnitName;
	}

	public void setReqFinOrgUnitName(String reqFinOrgUnitName) {
		this.reqFinOrgUnitName = reqFinOrgUnitName;
	}

	public String getReveiveOrgUnitName() {
		return reveiveOrgUnitName;
	}

	public void setReveiveOrgUnitName(String reveiveOrgUnitName) {
		this.reveiveOrgUnitName = reveiveOrgUnitName;
	}

	public String getReceiveFinOrgUnitName() {
		return receiveFinOrgUnitName;
	}

	public void setReceiveFinOrgUnitName(String receiveFinOrgUnitName) {
		this.receiveFinOrgUnitName = receiveFinOrgUnitName;
	}

	public boolean isInstead() {
		return instead;
	}

	public void setInstead(boolean instead) {
		this.instead = instead;
	}

	public String getReceiveWareHouseName() {
		return receiveWareHouseName;
	}

	public void setReceiveWareHouseName(String receiveWareHouseName) {
		this.receiveWareHouseName = receiveWareHouseName;
	}

	public String getMstorageOrgUnitName() {
		return mstorageOrgUnitName;
	}

	public void setMstorageOrgUnitName(String mstorageOrgUnitName) {
		this.mstorageOrgUnitName = mstorageOrgUnitName;
	}

	public String getBalanceSupplierName() {
		return balanceSupplierName;
	}

	public void setBalanceSupplierName(String balanceSupplierName) {
		this.balanceSupplierName = balanceSupplierName;
	}

	public String getStorageOrgUnitName() {
		return storageOrgUnitName;
	}

	public void setStorageOrgUnitName(String storageOrgUnitName) {
		this.storageOrgUnitName = storageOrgUnitName;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getPurGroupName() {
		return purGroupName;
	}

	public void setPurGroupName(String purGroupName) {
		this.purGroupName = purGroupName;
	}

	public long getPrDtlId() {
		return prDtlId;
	}

	public void setPrDtlId(long prDtlId) {
		this.prDtlId = prDtlId;
	}

	public long getPriceBillId() {
		return priceBillId;
	}

	public void setPriceBillId(long priceBillId) {
		this.priceBillId = priceBillId;
	}

	public String getRefPriceStatus() {
		return refPriceStatus;
	}

	public void setRefPriceStatus(String refPriceStatus) {
		this.refPriceStatus = refPriceStatus;
	}

	public boolean isApprPriceByPo() {
		return apprPriceByPo;
	}

	public void setApprPriceByPo(boolean apprPriceByPo) {
		this.apprPriceByPo = apprPriceByPo;
	}

	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}

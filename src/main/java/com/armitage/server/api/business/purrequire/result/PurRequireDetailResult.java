package com.armitage.server.api.business.purrequire.result;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireDetailResult",description="返回结果集resultList")
public class PurRequireDetailResult {
	@ApiModelProperty(value="行号",dataType="Int")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int lineId;

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
	private String purUnit;

	@ApiModelProperty(value="申购数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal qty;

	@ApiModelProperty(value="辅助计量单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String pieUnitName;

	@ApiModelProperty(value="辅助数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal pieQty;

	@ApiModelProperty(value="单价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price;

	@ApiModelProperty(value="金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;

	@ApiModelProperty(value="供应商名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorName;
	
	@ApiModelProperty(value="建议订货日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date orderDate;
	
	@ApiModelProperty(value="行状态",dataType="String",example="N")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String rowStatus;

	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;
	
	@ApiModelProperty(value="审批备注明细",dataType="List")
	private List<ScmAuditDetailHistoryResult> auditDetailHistoryResultList;
	
	@ApiModelProperty(value="最近入库单价",dataType="BigDecimal")
	private BigDecimal recentPrice;
	
	@ApiModelProperty(value="库存量",dataType="BigDecimal")
	private BigDecimal stockQty;
	
	@ApiModelProperty(value="供应商是否可修改（1：可修改0不可修改）",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorEditType;
	
	@ApiModelProperty(value="价格是否可修改（1：可修改0不可修改）",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String priceEditType;
	
	@ApiModelProperty(value="供应商编码",dataType="String",example="001",required=true)
	private String vendorCode;
	
	@ApiModelProperty(value="可修改字段",dataType="String",example="Q,P,V")
	private String editColumn;

	public BigDecimal getRecentPrice() {
		return recentPrice;
	}
	public void setRecentPrice(BigDecimal recentPrice) {
		this.recentPrice = recentPrice;
	}
	public BigDecimal getStockQty() {
		return stockQty;
	}
	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
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
	public String getPurUnit() {
		return purUnit;
	}
	public void setPurUnit(String purUnit) {
		this.purUnit = purUnit;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public BigDecimal getPieQty() {
		return pieQty;
	}
	public void setPieQty(BigDecimal pieQty) {
		this.pieQty = pieQty;
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
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getRowStatus() {
		return rowStatus;
	}
	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getVendorEditType() {
		return vendorEditType;
	}
	public void setVendorEditType(String vendorEditType) {
		this.vendorEditType = vendorEditType;
	}
	public String getPriceEditType() {
		return priceEditType;
	}
	public void setPriceEditType(String priceEditType) {
		this.priceEditType = priceEditType;
	}
	public List<ScmAuditDetailHistoryResult> getAuditDetailHistoryResultList() {
		return auditDetailHistoryResultList;
	}
	public void setAuditDetailHistoryResultList(List<ScmAuditDetailHistoryResult> auditDetailHistoryResultList) {
		this.auditDetailHistoryResultList = auditDetailHistoryResultList;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getEditColumn() {
		return editColumn;
	}
	public void setEditColumn(String editColumn) {
		this.editColumn = editColumn;
	}
	
}

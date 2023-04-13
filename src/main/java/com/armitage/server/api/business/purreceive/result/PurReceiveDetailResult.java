package com.armitage.server.api.business.purreceive.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="PurReceiveDetailResult",description="返回结果集resultList")
public class PurReceiveDetailResult {
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

	@ApiModelProperty(value="采购计量单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String purUnitName;

	@ApiModelProperty(value="订货数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal orderQty;

	@ApiModelProperty(value="已收货数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal receiveQty;

	@ApiModelProperty(value="送货数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal deliveryQty;

	@ApiModelProperty(value="本次收货数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal qty;

	@ApiModelProperty(value="不合格数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal unqualifiedQty;

	@ApiModelProperty(value="让步接收率",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal concessiveRecRate;

	@ApiModelProperty(value="让步接收数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal concessiveRecQty;

	@ApiModelProperty(value="单价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price;

	@ApiModelProperty(value="金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;

	@ApiModelProperty(value="税率",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxRate;

	@ApiModelProperty(value="含税价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxPrice;

	@ApiModelProperty(value="含税金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxAmt;

	@ApiModelProperty(value="辅助计量单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String pieUnitName;

	@ApiModelProperty(value="辅助数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal pieQty;

	@ApiModelProperty(value="超收比例",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    private BigDecimal receiveTopRatio;

	@ApiModelProperty(value="收货库存组织",dataType="String")
	private String invOrgUnitNo;

	@ApiModelProperty(value="收货部门",dataType="String")
	private String useOrgUnitNo;

	@ApiModelProperty(value="收货部门名称",dataType="String")
	private String useOrgUnitName;
	
	@ApiModelProperty(value="收货仓库",dataType="String")
	private String wareHouseNo;

	@ApiModelProperty(value="收货仓库名称",dataType="String")
	private String wareHouseName;

	@ApiModelProperty(value="价格来源单Id",dataType="String")
	private long priceBillId;

	@ApiModelProperty(value="备注",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;

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
	public String getPurUnitName() {
		return purUnitName;
	}
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}
	public BigDecimal getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(BigDecimal orderQty) {
		this.orderQty = orderQty;
	}
	public BigDecimal getReceiveQty() {
		return receiveQty;
	}
	public void setReceiveQty(BigDecimal receiveQty) {
		this.receiveQty = receiveQty;
	}
	public BigDecimal getDeliveryQty() {
		return deliveryQty;
	}
	public void setDeliveryQty(BigDecimal deliveryQty) {
		this.deliveryQty = deliveryQty;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public BigDecimal getUnqualifiedQty() {
		return unqualifiedQty;
	}
	public void setUnqualifiedQty(BigDecimal unqualifiedQty) {
		this.unqualifiedQty = unqualifiedQty;
	}
	public BigDecimal getConcessiveRecRate() {
		return concessiveRecRate;
	}
	public void setConcessiveRecRate(BigDecimal concessiveRecRate) {
		this.concessiveRecRate = concessiveRecRate;
	}
	public BigDecimal getConcessiveRecQty() {
		return concessiveRecQty;
	}
	public void setConcessiveRecQty(BigDecimal concessiveRecQty) {
		this.concessiveRecQty = concessiveRecQty;
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
	public BigDecimal getReceiveTopRatio() {
		return receiveTopRatio;
	}
	public void setReceiveTopRatio(BigDecimal receiveTopRatio) {
		this.receiveTopRatio = receiveTopRatio;
	}
	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}
	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}
	
	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}
	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}
	public String getUseOrgUnitName() {
		return useOrgUnitName;
	}
	public void setUseOrgUnitName(String useOrgUnitName) {
		this.useOrgUnitName = useOrgUnitName;
	}
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
	public long getPriceBillId() {
		return priceBillId;
	}
	public void setPriceBillId(long priceBillId) {
		this.priceBillId = priceBillId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}

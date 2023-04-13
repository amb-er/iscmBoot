package com.armitage.server.api.business.apinvoice.result;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApinvoiceDetailOAResult",description="返回结果集resultList")
public class ApinvoiceDetailOAResult {
	@ApiModelProperty(value="单据Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long billId;
	
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
	
	@ApiModelProperty(value="规格名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String spec;
	
	@ApiModelProperty(value="计量单位名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String unit;
	
	@ApiModelProperty(value="数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal qty;
	
	@ApiModelProperty(value="基本计量单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String baseUnit;
	
	@ApiModelProperty(value="基本数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal baseQty;
	
	@ApiModelProperty(value="辅助单位",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long pieUnit;
	
	@ApiModelProperty(value="辅助数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal pieQty;
	
	@ApiModelProperty(value="单价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price;
	
	@ApiModelProperty(value="折扣方式",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String discountTypeName;
	
	@ApiModelProperty(value="单位折扣率/额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal discountRate;
	
	@ApiModelProperty(value="折扣金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal discountAmt;
	
	@ApiModelProperty(value="实际单价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal realPrice;
	
	@ApiModelProperty(value="原币应付金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;
	
	@ApiModelProperty(value="本位币应付金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amtLocal;
	
	@ApiModelProperty(value="原币已结算金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal verifyAmt;
	
	@ApiModelProperty(value="本位币已结算金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal verifyAmtLocal;
	
	@ApiModelProperty(value="原币未结算金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal unVerifyAmt;
	
	@ApiModelProperty(value="本位币未结算金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal unVerifyAmtLocal;
	
	@ApiModelProperty(value="业务日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date bizDate;
	
	@ApiModelProperty(value="应收日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date payDate;
	
	@ApiModelProperty(value="是否被转移",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean transferred;
	
	@ApiModelProperty(value="锁定金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal lockVerifyAmt;
	
	@ApiModelProperty(value="锁定本币金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal lockVerifyAmtLocal;
	
	@ApiModelProperty(value="应付科目",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long accId;
	
	@ApiModelProperty(value="对方科目",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long oppAccId;
	
	@ApiModelProperty(value="是否已开发票",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean invoiced;
	
	@ApiModelProperty(value="发票编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String invoiceNo;
	
	@ApiModelProperty(value="已开发票数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal invoicedQty;
	
	@ApiModelProperty(value="已开发票金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal invoicedAmt;
	
	@ApiModelProperty(value="结算状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String writeOffStateName;
	
	@ApiModelProperty(value="核心单据明细Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long coreBillIdDtlId;
	
	@ApiModelProperty(value="来源单明细Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long sourceBillDtlId;
	
	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
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

	public long getPieUnit() {
		return pieUnit;
	}

	public void setPieUnit(long pieUnit) {
		this.pieUnit = pieUnit;
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

	public String getDiscountTypeName() {
		return discountTypeName;
	}

	public void setDiscountTypeName(String discountTypeName) {
		this.discountTypeName = discountTypeName;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getAmtLocal() {
		return amtLocal;
	}

	public void setAmtLocal(BigDecimal amtLocal) {
		this.amtLocal = amtLocal;
	}

	public BigDecimal getVerifyAmt() {
		return verifyAmt;
	}

	public void setVerifyAmt(BigDecimal verifyAmt) {
		this.verifyAmt = verifyAmt;
	}

	public BigDecimal getVerifyAmtLocal() {
		return verifyAmtLocal;
	}

	public void setVerifyAmtLocal(BigDecimal verifyAmtLocal) {
		this.verifyAmtLocal = verifyAmtLocal;
	}

	public BigDecimal getUnVerifyAmt() {
		return unVerifyAmt;
	}

	public void setUnVerifyAmt(BigDecimal unVerifyAmt) {
		this.unVerifyAmt = unVerifyAmt;
	}

	public BigDecimal getUnVerifyAmtLocal() {
		return unVerifyAmtLocal;
	}

	public void setUnVerifyAmtLocal(BigDecimal unVerifyAmtLocal) {
		this.unVerifyAmtLocal = unVerifyAmtLocal;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public boolean isTransferred() {
		return transferred;
	}

	public void setTransferred(boolean transferred) {
		this.transferred = transferred;
	}

	public BigDecimal getLockVerifyAmt() {
		return lockVerifyAmt;
	}

	public void setLockVerifyAmt(BigDecimal lockVerifyAmt) {
		this.lockVerifyAmt = lockVerifyAmt;
	}

	public BigDecimal getLockVerifyAmtLocal() {
		return lockVerifyAmtLocal;
	}

	public void setLockVerifyAmtLocal(BigDecimal lockVerifyAmtLocal) {
		this.lockVerifyAmtLocal = lockVerifyAmtLocal;
	}

	public long getAccId() {
		return accId;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}

	public long getOppAccId() {
		return oppAccId;
	}

	public void setOppAccId(long oppAccId) {
		this.oppAccId = oppAccId;
	}

	public boolean isInvoiced() {
		return invoiced;
	}

	public void setInvoiced(boolean invoiced) {
		this.invoiced = invoiced;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getInvoicedQty() {
		return invoicedQty;
	}

	public void setInvoicedQty(BigDecimal invoicedQty) {
		this.invoicedQty = invoicedQty;
	}

	public BigDecimal getInvoicedAmt() {
		return invoicedAmt;
	}

	public void setInvoicedAmt(BigDecimal invoicedAmt) {
		this.invoicedAmt = invoicedAmt;
	}

	public String getWriteOffStateName() {
		return writeOffStateName;
	}

	public void setWriteOffStateName(String writeOffStateName) {
		this.writeOffStateName = writeOffStateName;
	}

	public long getCoreBillIdDtlId() {
		return coreBillIdDtlId;
	}

	public void setCoreBillIdDtlId(long coreBillIdDtlId) {
		this.coreBillIdDtlId = coreBillIdDtlId;
	}

	public long getSourceBillDtlId() {
		return sourceBillDtlId;
	}

	public void setSourceBillDtlId(long sourceBillDtlId) {
		this.sourceBillDtlId = sourceBillDtlId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}

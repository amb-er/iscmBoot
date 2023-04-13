package com.armitage.server.api.business.appaymentbill.result;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApPaymentBillDetailOAResult",description="返回结果集resultList")
public class ApPaymentBillDetailOAResult {
	@ApiModelProperty(value="单据Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long billId;
	
	@ApiModelProperty(value="应付单号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String apinvoiceNo;
	
	@ApiModelProperty(value="采购入库单号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String sourceWrNo;
	
	@ApiModelProperty(value="行号",dataType="Int")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int lineId;
	
	@ApiModelProperty(value="折扣金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal discountAmt;
	
	@ApiModelProperty(value="折扣本币金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal discountAmtLocal;
	
	@ApiModelProperty(value="原币应付金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;
	
	@ApiModelProperty(value="本位币应付金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amtLocal;
	
	@ApiModelProperty(value="实收金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal actualAmt;
	
	@ApiModelProperty(value="实收本币金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal actualAmtLocal;
	
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
	
	@ApiModelProperty(value="结算状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String writeOffStateName;
	
	@ApiModelProperty(value="核心单据明细Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long coreBillIdDtlId;
	
	@ApiModelProperty(value="来源单明细Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long sourceBillDtlId;
	
	@ApiModelProperty(value="已退金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal returnAmt;
	
	@ApiModelProperty(value="已退本币金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal returnAmtLocal;
	
	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public String getApinvoiceNo() {
		return apinvoiceNo;
	}

	public void setApinvoiceNo(String apinvoiceNo) {
		this.apinvoiceNo = apinvoiceNo;
	}

	public String getSourceWrNo() {
		return sourceWrNo;
	}

	public void setSourceWrNo(String sourceWrNo) {
		this.sourceWrNo = sourceWrNo;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}

	public BigDecimal getDiscountAmtLocal() {
		return discountAmtLocal;
	}

	public void setDiscountAmtLocal(BigDecimal discountAmtLocal) {
		this.discountAmtLocal = discountAmtLocal;
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

	public BigDecimal getActualAmt() {
		return actualAmt;
	}

	public void setActualAmt(BigDecimal actualAmt) {
		this.actualAmt = actualAmt;
	}

	public BigDecimal getActualAmtLocal() {
		return actualAmtLocal;
	}

	public void setActualAmtLocal(BigDecimal actualAmtLocal) {
		this.actualAmtLocal = actualAmtLocal;
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

	public BigDecimal getReturnAmt() {
		return returnAmt;
	}

	public void setReturnAmt(BigDecimal returnAmt) {
		this.returnAmt = returnAmt;
	}

	public BigDecimal getReturnAmtLocal() {
		return returnAmtLocal;
	}

	public void setReturnAmtLocal(BigDecimal returnAmtLocal) {
		this.returnAmtLocal = returnAmtLocal;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}

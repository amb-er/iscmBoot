package com.armitage.server.api.business.appaymentbill.result;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApPaymentBillOAResult",description="返回结果集resultList")
public class ApPaymentBillOAResult {
	@ApiModelProperty(value="单据Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long billId;
	
	@ApiModelProperty(value="单据编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String billNo;
	
	@ApiModelProperty(value="财务组织",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitNo;
	
	@ApiModelProperty(value="财务组织编码",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitName;
	
	@ApiModelProperty(value="单据日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date billDate;
	
	@ApiModelProperty(value="合同编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String contractNo;
	
	@ApiModelProperty(value="单据类型",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String billTypeName;
	
	@ApiModelProperty(value="业务类型",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String bizTypeName;
	
	@ApiModelProperty(value="供应商名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorName;
	
	@ApiModelProperty(value="付款方式",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String payment;

	@ApiModelProperty(value="部门名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String deptName;
	
	@ApiModelProperty(value="人员",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String empName;
	
	@ApiModelProperty(value="摘要",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String paycomment;
	
	@ApiModelProperty(value="币别",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String currency;
	
	@ApiModelProperty(value="汇率",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal exchangeRate;
	
	@ApiModelProperty(value="期间",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long periodId;
	
	@ApiModelProperty(value="会计年度",dataType="Int")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int accountYear;
	
	@ApiModelProperty(value="会计期间",dataType="Int")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int accountPeriod;
	
	@ApiModelProperty(value="应付金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;
	
	@ApiModelProperty(value="应付本币金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amtLocal;
	
	@ApiModelProperty(value="折扣金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal discountAmt;
	
	@ApiModelProperty(value="折扣本币金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal discountAmtLocal;
	
	@ApiModelProperty(value="实付原币金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal actPayAmt;
	
	@ApiModelProperty(value="实付本位币金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal actPayAmtLocal;
	
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
	
	@ApiModelProperty(value="收款科目",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long payAccId;
	
	@ApiModelProperty(value="收款账号",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long bankAccId;
	
	@ApiModelProperty(value="结算号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String settlementNo;
	
	@ApiModelProperty(value="对方科目",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long oppAccId;
	
	@ApiModelProperty(value="是否冲销单据",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean reverseBill;
	
	@ApiModelProperty(value="是否已被冲销",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean reversed;
	
	@ApiModelProperty(value="结算状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String writeOffStatusName;
	
	@ApiModelProperty(value="是否生成凭证",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean vouchered;
	
	@ApiModelProperty(value="凭证Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long voucherId;
	
	@ApiModelProperty(value="是否调汇",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean exchanged;
	
	@ApiModelProperty(value="是否期初单据",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean initBill;
	
	@ApiModelProperty(value="出纳",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String cashier;
	
	@ApiModelProperty(value="会计",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String accountant;
	
	@ApiModelProperty(value="制单人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String creator;

	@ApiModelProperty(value="制单日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date createDate;

	@ApiModelProperty(value="修改人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String editor;

	@ApiModelProperty(value="修改日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date editorDate;

	@ApiModelProperty(value="审核人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String checker;

	@ApiModelProperty(value="审核日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date checkDate;
	
	@ApiModelProperty(value="单据状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String statusName;
	
	@ApiModelProperty(value="控制单元",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String controlUnitName;

	@ApiModelProperty(value="单据明细",dataType="List")
	private List<ApPaymentBillDetailOAResult> detailList;

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getBillTypeName() {
		return billTypeName;
	}

	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPaycomment() {
		return paycomment;
	}

	public void setPaycomment(String paycomment) {
		this.paycomment = paycomment;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}

	public int getAccountYear() {
		return accountYear;
	}

	public void setAccountYear(int accountYear) {
		this.accountYear = accountYear;
	}

	public int getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(int accountPeriod) {
		this.accountPeriod = accountPeriod;
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

	public BigDecimal getActPayAmt() {
		return actPayAmt;
	}

	public void setActPayAmt(BigDecimal actPayAmt) {
		this.actPayAmt = actPayAmt;
	}

	public BigDecimal getActPayAmtLocal() {
		return actPayAmtLocal;
	}

	public void setActPayAmtLocal(BigDecimal actPayAmtLocal) {
		this.actPayAmtLocal = actPayAmtLocal;
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

	public long getPayAccId() {
		return payAccId;
	}

	public void setPayAccId(long payAccId) {
		this.payAccId = payAccId;
	}

	public long getBankAccId() {
		return bankAccId;
	}

	public void setBankAccId(long bankAccId) {
		this.bankAccId = bankAccId;
	}

	public String getSettlementNo() {
		return settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	public long getOppAccId() {
		return oppAccId;
	}

	public void setOppAccId(long oppAccId) {
		this.oppAccId = oppAccId;
	}

	public boolean isReverseBill() {
		return reverseBill;
	}

	public void setReverseBill(boolean reverseBill) {
		this.reverseBill = reverseBill;
	}

	public boolean isReversed() {
		return reversed;
	}

	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}

	public String getWriteOffStatusName() {
		return writeOffStatusName;
	}

	public void setWriteOffStatusName(String writeOffStatusName) {
		this.writeOffStatusName = writeOffStatusName;
	}

	public boolean isVouchered() {
		return vouchered;
	}

	public void setVouchered(boolean vouchered) {
		this.vouchered = vouchered;
	}

	public long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(long voucherId) {
		this.voucherId = voucherId;
	}

	public boolean isExchanged() {
		return exchanged;
	}

	public void setExchanged(boolean exchanged) {
		this.exchanged = exchanged;
	}

	public boolean isInitBill() {
		return initBill;
	}

	public void setInitBill(boolean initBill) {
		this.initBill = initBill;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getAccountant() {
		return accountant;
	}

	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditorDate() {
		return editorDate;
	}

	public void setEditorDate(Date editorDate) {
		this.editorDate = editorDate;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getControlUnitName() {
		return controlUnitName;
	}

	public void setControlUnitName(String controlUnitName) {
		this.controlUnitName = controlUnitName;
	}

	public List<ApPaymentBillDetailOAResult> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ApPaymentBillDetailOAResult> detailList) {
		this.detailList = detailList;
	}
}

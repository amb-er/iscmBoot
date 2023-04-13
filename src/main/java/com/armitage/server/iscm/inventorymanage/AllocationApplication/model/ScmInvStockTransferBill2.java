package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;

import java.math.BigDecimal;

public class ScmInvStockTransferBill2 extends ScmInvStockTransferBill{
	
	public static final String FN_AMT = "amt";
    public static final String FN_TAXAMOUNT = "taxAmount";
    public static final String FN_TAXAMT = "taxAmt";
	
	private boolean choosed;
	private BigDecimal amt;
    private BigDecimal taxAmount;
    private BigDecimal taxAmt;
	
	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}
	
	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public ScmInvStockTransferBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}

	public ScmInvStockTransferBill2() {
		super();
	}
}

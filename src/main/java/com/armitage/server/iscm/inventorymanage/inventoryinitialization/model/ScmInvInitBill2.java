package com.armitage.server.iscm.inventorymanage.inventoryinitialization.model;

import java.math.BigDecimal;

public class ScmInvInitBill2 extends ScmInvInitBill {
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_AMT = "amt";
	public static final String FN_TAXAMT = "taxAmt";
	
	private boolean choosed;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	private BigDecimal taxInAmt;	//税额

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

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getTaxInAmt() {
		return taxInAmt;
	}

	public void setTaxInAmt(BigDecimal taxInAmt) {
		this.taxInAmt = taxInAmt;
	}

	public ScmInvInitBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue) {
			this.amt = BigDecimal.ZERO;
			this.taxAmt = BigDecimal.ZERO;
			this.taxInAmt = BigDecimal.ZERO;
		}
		
	}

	public ScmInvInitBill2() {
		super();
	}
}

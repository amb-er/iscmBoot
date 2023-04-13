package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;

import java.math.BigDecimal;

public class ScmInvMoveReqBill2 extends ScmInvMoveReqBill{
	
	public static final String FN_AMT = "amt";
	
	private BigDecimal amt;
	
	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public ScmInvMoveReqBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.amt=BigDecimal.ZERO;
		}
	}

	public ScmInvMoveReqBill2() {
		super();
	}
}

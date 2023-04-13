package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScmCookCostCard2 extends ScmCookCostCard{
	
	public static final String FN_INVQTY ="invQty";
	public static final String FN_COSTRATIO = "costRatio";
	
	private Date newEffectiveDate;
	private BigDecimal qty;
	private BigDecimal costRatio;
	
	
	

	public Date getNewEffectiveDate() {
		return newEffectiveDate;
	}

	public void setNewEffectiveDate(Date newEffectiveDate) {
		this.newEffectiveDate = newEffectiveDate;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getCostRatio() {
		return costRatio;
	}

	public void setCostRatio(BigDecimal costRatio) {
		this.costRatio = costRatio;
	}

	public ScmCookCostCard2() {
		super();
	}

	public ScmCookCostCard2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.qty=BigDecimal.ZERO;
			this.costRatio=BigDecimal.ZERO;
		}
	}
}

package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScmCostCard2 extends ScmCostCard{
	public static final String FN_PROFIT = "profit";
	public static final String FN_PROFITRATE = "profitRate";
	public static final String FN_COSTRATIO = "costRatio";
	public static final String FN_BEGDATE = "begDate";
	public static final String FN_ENDDATE = "endDate";
	public static final String FN_INVQTY ="invQty";
	public static final String FN_CHOOSED = "choosed";
	
	private BigDecimal profit;
	private BigDecimal profitRate;
	private BigDecimal costRatio;
	private Date begDate;
    private Date endDate;
    private boolean choosed;
	private BigDecimal qty;
	private Date effectDay;
	private ScmCostCardDetail2 scmCostCardDetail;
	private Date newEffectiveDate;
	
	
	
	public Date getNewEffectiveDate() {
		return newEffectiveDate;
	}

	public void setNewEffectiveDate(Date newEffectiveDate) {
		this.newEffectiveDate = newEffectiveDate;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public BigDecimal getCostRatio() {
		return costRatio;
	}

	public void setCostRatio(BigDecimal costRatio) {
		this.costRatio = costRatio;
	}

	public Date getBegDate() {
		return begDate;
	}

	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public Date getEffectDay() {
		return effectDay;
	}

	public void setEffectDay(Date effectDay) {
		this.effectDay = effectDay;
	}

	public ScmCostCardDetail2 getScmCostCardDetail() {
		return scmCostCardDetail;
	}

	public void setScmCostCardDetail(ScmCostCardDetail2 scmCostCardDetail) {
		this.scmCostCardDetail = scmCostCardDetail;
	}

	public ScmCostCard2() {
		super();
	}

	public ScmCostCard2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.profit=BigDecimal.ZERO;
			this.profitRate=BigDecimal.ZERO;
			this.costRatio=BigDecimal.ONE;
			this.qty=BigDecimal.ZERO;
			this.choosed=false;
		}
	}
	

}

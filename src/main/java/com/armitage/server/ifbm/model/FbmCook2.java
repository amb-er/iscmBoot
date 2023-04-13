package com.armitage.server.ifbm.model;

import java.math.BigDecimal;

public class FbmCook2 extends FbmCook  {

	public static final String FN_COSTRATIO = "costRatio";
	public static final String FN_ORGUNITNO = "orgUnitNo";
    
    private BigDecimal costRatio;
    private String orgUnitNo;
    private long cardId;
    
	public BigDecimal getCostRatio() {
		return costRatio;
	}
	public void setCostRatio(BigDecimal costRatio) {
		this.costRatio = costRatio;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	public FbmCook2() {
		super();
	}
	public FbmCook2(boolean defaultValue) {
		super(defaultValue);
	}

}

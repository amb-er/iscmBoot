package com.armitage.server.ifbm.model;

public class FbmDish2 extends FbmDish  {

	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_STATCODE = "statCode";
	public static final String FN_STATNAME = "statName";
	public static final String FN_PRODUCTIONTIME = "productionTime";
    
    private String orgUnitNo;
    private long statId;
    private String statCode;
    private String statName;
	private int productionTime;

	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public long getStatId() {
		return statId;
	}
	public void setStatId(long statId) {
		this.statId = statId;
	}
	public String getStatCode() {
		return statCode;
	}
	public void setStatCode(String statCode) {
		this.statCode = statCode;
	}
	public String getStatName() {
		return statName;
	}
	public void setStatName(String statName) {
		this.statName = statName;
	}
    public int getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(int productionTime) {
		this.productionTime = productionTime;
	}

	public FbmDish2() {
		super();
	}
	public FbmDish2(boolean defaultValue) {
		super(defaultValue);
	}

}

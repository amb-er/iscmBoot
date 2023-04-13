package com.armitage.server.iscm.basedata.model;

public class ScmPurchaseType2 extends ScmPurchaseType {
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_SORT = "sort";
	
	private String orgUnitNo;
	private int sort;

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public ScmPurchaseType2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}
	
	public ScmPurchaseType2(){
		super();
	}
}

package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;

public class ScmProductCostCardDetail2 extends ScmProductCostCardDetail {

	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_CSTUNITNAME = "cstUnitName";
	public static final String FN_INVUNITNAME = "invUnitName";
	
	
	private String itemNo;
	private String itemName;
	private String cstUnitName;
	private String invUnitName;
	private String creatorName;
	private String checkerName;
	
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getCstUnitName() {
		return cstUnitName;
	}

	public void setCstUnitName(String cstUnitName) {
		this.cstUnitName = cstUnitName;
	}
	
	public String getInvUnitName() {
		return invUnitName;
	}

	public void setInvUnitName(String invUnitName) {
		this.invUnitName = invUnitName;
	}

	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    public ScmProductCostCardDetail2(){
     }
    public ScmProductCostCardDetail2(boolean defaultValue){
        super(true);
     }
}

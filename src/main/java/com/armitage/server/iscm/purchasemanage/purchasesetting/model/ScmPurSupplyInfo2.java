package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

import java.util.List;


public class ScmPurSupplyInfo2 extends ScmPurSupplyInfo{

    public static final String FN_VENDORNAME = "vendorName";
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_ORGUNITNAMES = "orgUnitNames";
	public static final String FN_PVENDORID = "pvendorId";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String orgUnitNos;
	private String orgUnitNames;
	private String vendorName;
	private String purUnitName;
    private long pvendorId;		//用于构造树
    private List<ScmPurSupplyInfo2> scmPurSupplyInfoList;
    private List<ScmPurSupplyRecOrg2> scmPurSupplyRecOrgList;
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getOrgUnitNos() {
		return orgUnitNos;
	}
	public void setOrgUnitNos(String orgUnitNos) {
		this.orgUnitNos = orgUnitNos;
	}
	public String getOrgUnitNames() {
		return orgUnitNames;
	}
	public void setOrgUnitNames(String orgUnitNames) {
		this.orgUnitNames = orgUnitNames;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getPurUnitName() {
		return purUnitName;
	}
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}
	public long getPvendorId() {
		return pvendorId;
	}
	public void setPvendorId(long pvendorId) {
		this.pvendorId = pvendorId;
	}
	public List<ScmPurSupplyInfo2> getScmPurSupplyInfoList() {
		return scmPurSupplyInfoList;
	}
	public void setScmPurSupplyInfoList(List<ScmPurSupplyInfo2> scmPurSupplyInfoList) {
		this.scmPurSupplyInfoList = scmPurSupplyInfoList;
	}
	public List<ScmPurSupplyRecOrg2> getScmPurSupplyRecOrgList() {
		return scmPurSupplyRecOrgList;
	}
	public void setScmPurSupplyRecOrgList(
			List<ScmPurSupplyRecOrg2> scmPurSupplyRecOrgList) {
		this.scmPurSupplyRecOrgList = scmPurSupplyRecOrgList;
	}
	public ScmPurSupplyInfo2() {
		super();
	}
	public ScmPurSupplyInfo2(boolean defaultValue) {
		super(defaultValue);
	}
}

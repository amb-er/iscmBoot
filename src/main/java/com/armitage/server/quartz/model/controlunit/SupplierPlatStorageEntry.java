package com.armitage.server.quartz.model.controlunit;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierPlatStorageEntry {

	private String address;
	private int flag;
	private int isBizUnit;
	private int isSealUp;
	private String leader;
	private String longNo;
	private String orgUnitNo;
	private String porgUnitNo;
	private String refOrgUnitName;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date sealUpDate;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getIsBizUnit() {
		return isBizUnit;
	}
	public void setIsBizUnit(int isBizUnit) {
		this.isBizUnit = isBizUnit;
	}
	public int getIsSealUp() {
		return isSealUp;
	}
	public void setIsSealUp(int isSealUp) {
		this.isSealUp = isSealUp;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getLongNo() {
		return longNo;
	}
	public void setLongNo(String longNo) {
		this.longNo = longNo;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public String getPorgUnitNo() {
		return porgUnitNo;
	}
	public void setPorgUnitNo(String porgUnitNo) {
		this.porgUnitNo = porgUnitNo;
	}
	public String getRefOrgUnitName() {
		return refOrgUnitName;
	}
	public void setRefOrgUnitName(String refOrgUnitName) {
		this.refOrgUnitName = refOrgUnitName;
	}
	public Date getSealUpDate() {
		return sealUpDate;
	}
	public void setSealUpDate(Date sealUpDate) {
		this.sealUpDate = sealUpDate;
	}
}

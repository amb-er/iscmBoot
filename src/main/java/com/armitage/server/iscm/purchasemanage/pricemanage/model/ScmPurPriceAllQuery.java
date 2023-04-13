package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurPriceAllQuery  extends BaseModel{
	private String itemIds;	//多个物资
	private long itemId;
	private long vendor1;
	private long vendor2;
	private long vendor3;
	private long group1;
	private long group2;
	private long group3;
	private Date begDate;
	private Date endDate;

	public String getItemIds() {
		return itemIds;
	}
	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	
	public long getVendor1() {
		return vendor1;
	}
	public void setVendor1(long vendor1) {
		this.vendor1 = vendor1;
	}
	public long getVendor2() {
		return vendor2;
	}
	public void setVendor2(long vendor2) {
		this.vendor2 = vendor2;
	}
	public long getVendor3() {
		return vendor3;
	}
	public void setVendor3(long vendor3) {
		this.vendor3 = vendor3;
	}
	public long getGroup1() {
		return group1;
	}
	public void setGroup1(long group1) {
		this.group1 = group1;
	}
	public long getGroup2() {
		return group2;
	}
	public void setGroup2(long group2) {
		this.group2 = group2;
	}
	public long getGroup3() {
		return group3;
	}
	public void setGroup3(long group3) {
		this.group3 = group3;
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
	@Override
	public String getPkKey() {
		return null;
	}
	@Override
	public long getPK() {
		return 0;
	}
	@Override
	public String[] getRequiredFields() {
		return null;
	}
	@Override
	public String[] getFieldNames() {
		return null;
	}
	@Override
	public List<String[]> getUniqueKeys() {
		return null;
	}
}

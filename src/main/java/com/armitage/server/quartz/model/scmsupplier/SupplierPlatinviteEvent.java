package com.armitage.server.quartz.model.scmsupplier;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierPlatinviteEvent {
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date beginDate;
	private String code;
	private long demanderId;
	private String description;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	private boolean flag;
	private String name;
	
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getDemanderId() {
		return demanderId;
	}
	public void setDemanderId(long demanderId) {
		this.demanderId = demanderId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

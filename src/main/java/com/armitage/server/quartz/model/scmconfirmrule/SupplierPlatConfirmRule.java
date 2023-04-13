package com.armitage.server.quartz.model.scmconfirmrule;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierPlatConfirmRule {
	
	private boolean allowAssistConfirm;
	private boolean autoConfirm;
	private String receiveNo;
	private String autoConfirmTime;
	private long baId;
	private String billStatus;
	private String billType;
	private String compareBy;
	private String confirmType;
	private int days;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date lastConfirmTime;
	private boolean needSendBack;
	private String sendPoint;
	private String taskSource;
	public boolean isAllowAssistConfirm() {
		return allowAssistConfirm;
	}
	public void setAllowAssistConfirm(boolean allowAssistConfirm) {
		this.allowAssistConfirm = allowAssistConfirm;
	}
	public boolean isAutoConfirm() {
		return autoConfirm;
	}
	public void setAutoConfirm(boolean autoConfirm) {
		this.autoConfirm = autoConfirm;
	}
	public String getReceiveNo() {
		return receiveNo;
	}
	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}
	public String getAutoConfirmTime() {
		return autoConfirmTime;
	}
	public void setAutoConfirmTime(String autoConfirmTime) {
		this.autoConfirmTime = autoConfirmTime;
	}
	public long getBaId() {
		return baId;
	}
	public void setBaId(long baId) {
		this.baId = baId;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getCompareBy() {
		return compareBy;
	}
	public void setCompareBy(String compareBy) {
		this.compareBy = compareBy;
	}
	public String getConfirmType() {
		return confirmType;
	}
	public void setConfirmType(String confirmType) {
		this.confirmType = confirmType;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public Date getLastConfirmTime() {
		return lastConfirmTime;
	}
	public void setLastConfirmTime(Date lastConfirmTime) {
		this.lastConfirmTime = lastConfirmTime;
	}
	public boolean isNeedSendBack() {
		return needSendBack;
	}
	public void setNeedSendBack(boolean needSendBack) {
		this.needSendBack = needSendBack;
	}
	public String getSendPoint() {
		return sendPoint;
	}
	public void setSendPoint(String sendPoint) {
		this.sendPoint = sendPoint;
	}
	public String getTaskSource() {
		return taskSource;
	}
	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
	}
}

package com.armitage.server.iscm.common.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmMsginfoAdvQuery extends BaseModel{
	public static final String FN_GENTIME = "genTime";
	public static final String FN_GENTIME_ = "genTime_";
	public static final String FN_SENDTIME = "sendTime";
	public static final String FN_SENDTIME_ = "sendTime_";
	public static final String FN_MSGTYPECODE = "msgTypeCode";
	public static final String FN_SUBJECT = "subject";
	public static final String FN_SENDMODE = "sendMode";
	public static final String FN_POSTORGUNITNO = "postOrgUnitNo";
	
	/**
	 * 财务组织(提交组织委托的财务组织)
	 */
	private String orgUnitNo;
	/**
	 * 提交组织(可以是资源组织、积分组织、营销组织、服务组织)
	 */
	private String postOrgUnitNo;
	/**
	 * 消息类型
	 */
	private String msgTypeCode;
	/**
	 * 发送方式
	 */
	private String sendMode;
	/**
	 * 消息状态
	 */
	private String state;
	/**
	 * 生成时间开始
	 */
	private Date genTime;
	/**
	 * 生成时间结束
	 */
	private Date genTime_;
	/**
	 * 系统ID
	 */
	private long systemId;
	/**
	 * 业务对象标识，格式为“系统id_业务对象_业务对象id”
	 */
	private String bizId;
	/**
	 * 发送时间结束
	 */
	private Date sendTime;
	/**
	 * 生成时间结束
	 */
	private Date sendTim_; 
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 消息内容
	 */
	private String message;
	/**
	 * 接收地址
	 */
	private String address;

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getPostOrgUnitNo() {
		return postOrgUnitNo;
	}

	public void setPostOrgUnitNo(String postOrgUnitNo) {
		this.postOrgUnitNo = postOrgUnitNo;
	}

	public String getMsgTypeCode() {
		return msgTypeCode;
	}

	public void setMsgTypeCode(String msgTypeCode) {
		this.msgTypeCode = msgTypeCode;
	}

	public String getSendMode() {
		return sendMode;
	}

	public void setSendMode(String sendMode) {
		this.sendMode = sendMode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public long getSystemId() {
		return systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getGenTime_() {
		return genTime_;
	}

	public void setGenTime_(Date genTime_) {
		this.genTime_ = genTime_;
	}

	public Date getSendTim_() {
		return sendTim_;
	}

	public void setSendTim_(Date sendTim_) {
		this.sendTim_ = sendTim_;
	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}

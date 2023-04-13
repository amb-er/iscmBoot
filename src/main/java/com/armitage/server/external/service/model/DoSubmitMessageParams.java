package com.armitage.server.external.service.model;

import java.util.Date;
import java.util.HashMap;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.armitage.server.common.base.model.CommonRequest;
import com.armitage.server.common.util.DateAdapter;

public class DoSubmitMessageParams extends CommonRequest {
	
	public static final String FN_MSGTYPECODE = "msgTypeCode";
	public static final String FN_SENDMODE = "sendMode";
	public static final String FN_SENDER = "sender";
	public static final String FN_RECEIVER = "receiver";
	public static final String FN_ADDRESS = "address";
	public static final String FN_MEMBERID = "memberId";
	public static final String FN_PROFILEID = "profileId";
	public static final String FN_SUBJECT = "subject";
	public static final String FN_MESSAGE = "message";
	public static final String FN_GENTIME = "genTime";
	public static final String FN_TIMETOSEND = "timeToSend";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_SYSTEMID = "systemId";
	public static final String FN_BIZID = "bizId";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_TEMPLATEID = "templateId";
	public static final String FN_MESSAGETEMPLATEID = "messageTemplateId";
	public static final String FN_APPSERVICECODE = "appServiceCode";
	public static final String FN_ATTACHMENTS = "attachments";

	private String postOrgUnitNo;// *postOrgUnitNo 提交组织(可以是资源组织、积分组织、营销组织、服务组织)
	private String msgTypeCode;// msgTypeCode 消息类型
	private String sendMode;// *sendMode 发送方式，1-短信, 2-邮件，3-微信 默认为 1 String
	private String sender;// sender发送人 String
	private String receiver;// receiver接收人 String
	private String address;// *address接收地址，短信为手机号，邮件为邮箱地址,微信为OPENID
	private long memberId;// memberId 会员 id Long
	private long profileId;// profileId 客历 id Long
	private String subject;// subject 消息主题 String
	private String message;// *message消息内容，（如果是微信要以
							// List<TemplateParam>的json格式传进来）详细模版说明
	private Date genTime;// *genTime 生成时间 Datetime
	private Date timeToSend;// timeToSend 定制发送时间，传入则可定制发送时间 Datetime
	private String remarks;// remarks 备注 String
	private long systemId;// *systemId 系统 id Long
	private String bizId;// *bizId 业务对象标识，格式为“系统
							// id_业务对象_业务对象id”，用于记录业务系统的对象相关的消息
	private String templateId;// **templateId 微信模版号，（发送方式是微信时必填）详细模版说明	
	private long messageTemplateId;//消息模板id
	private String appServiceCode;//指定发送此消息的钉钉应用的应用服务代码
	private String detailUrl;
	private HashMap<String, String> attachments = new HashMap<String, String>();
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

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
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

	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getTimeToSend() {
		return timeToSend;
	}

	public void setTimeToSend(Date timeToSend) {
		this.timeToSend = timeToSend;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getPostOrgUnitNo() {
		return postOrgUnitNo;
	}

	public void setPostOrgUnitNo(String postOrgUnitNo) {
		this.postOrgUnitNo = postOrgUnitNo;
	}

	public DoSubmitMessageParams() {

	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public long getMessageTemplateId() {
		return messageTemplateId;
	}

	public void setMessageTemplateId(long messageTemplateId) {
		this.messageTemplateId = messageTemplateId;
	}

	public String getAppServiceCode() {
		return appServiceCode;
	}

	public void setAppServiceCode(String appServiceCode) {
		this.appServiceCode = appServiceCode;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public HashMap<String, String> getAttachments() {
		return attachments;
	}

	public void setAttachments(HashMap<String, String> attachments) {
		this.attachments = attachments;
	}

	
}

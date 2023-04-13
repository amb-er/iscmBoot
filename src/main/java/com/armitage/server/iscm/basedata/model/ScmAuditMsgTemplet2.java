package com.armitage.server.iscm.basedata.model;

import java.util.List;

public class ScmAuditMsgTemplet2 extends ScmAuditMsgTemplet {
	public static final String FN_CHANNELCODE ="channelCode";
	public static final String FN_TEMPLATEID ="templateId";
	
	private long channelId;
	private String channelCode;
	private String templateId;
	
	List<ScmAuditMsgTempletChannel> scmAuditMsgTempletChannelList;

	
	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public List<ScmAuditMsgTempletChannel> getScmAuditMsgTempletChannelList() {
		return scmAuditMsgTempletChannelList;
	}

	public void setScmAuditMsgTempletChannelList(List<ScmAuditMsgTempletChannel> scmAuditMsgTempletChannelList) {
		this.scmAuditMsgTempletChannelList = scmAuditMsgTempletChannelList;
	}

	public ScmAuditMsgTemplet2(boolean defaultValue) {
		super(defaultValue);
	}
	
	public ScmAuditMsgTemplet2(){
		super();
	}
	
	public String[] getRequiredFields() {
	    /*DEMO:
		return new String[] {FN_CODE };
        */
		return new String[] {FN_TEMPLETNAME,FN_TEMPLETTYPE,FN_TEMPLETCODE,FN_CHANNELCODE,FN_CONTENT,FN_TITLE};
	}
}

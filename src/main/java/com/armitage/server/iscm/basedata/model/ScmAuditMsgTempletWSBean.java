package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmAuditMsgTempletWSBean")
public class ScmAuditMsgTempletWSBean extends BaseWSBean {
	
	private ScmAuditMsgTemplet2 x;

	public ScmAuditMsgTemplet2 getX() {
		return x;
	}

	public void setX(ScmAuditMsgTemplet2 x) {
		this.x = x;
	}

}
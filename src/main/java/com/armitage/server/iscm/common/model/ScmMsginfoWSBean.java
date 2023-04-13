package com.armitage.server.iscm.common.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmMsginfoWSBean")
public class ScmMsginfoWSBean extends BaseWSBean{
	private ScmMsginfo x;
	private ScmMsginfoAdvQuery z;
	
	public ScmMsginfo getX() {
		return x;
	}
	public void setX(ScmMsginfo x) {
		this.x = x;
	}
	public ScmMsginfoAdvQuery getZ() {
		return z;
	}
	public void setZ(ScmMsginfoAdvQuery z) {
		this.z = z;
	}
	
}

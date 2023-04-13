package com.armitage.server.iscm.inventorymanage.countbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmInvBalOverWSBean")
public class ScmInvBalOverWSBean extends BaseWSBean {
	
	private ScmInvBalOver x;
	private ScmInvBalOverResult y;

	public ScmInvBalOver getX() {
		return x;
	}

	public void setX(ScmInvBalOver x) {
		this.x = x;
	}

	public ScmInvBalOverResult getY() {
		return y;
	}

	public void setY(ScmInvBalOverResult y) {
		this.y = y;
	}

}

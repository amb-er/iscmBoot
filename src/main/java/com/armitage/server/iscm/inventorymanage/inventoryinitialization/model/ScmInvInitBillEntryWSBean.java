package com.armitage.server.iscm.inventorymanage.inventoryinitialization.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmInvInitBillEntryWSBean")
public class ScmInvInitBillEntryWSBean extends BaseWSBean {
	
	private ScmInvInitBillEntry x;
	
	private ScmInvInitBillEntry2 y;

	public ScmInvInitBillEntry getX() {
		return x;
	}

	public void setX(ScmInvInitBillEntry x) {
		this.x = x;
	}

	public ScmInvInitBillEntry2 getY() {
		return y;
	}

	public void setY(ScmInvInitBillEntry2 y) {
		this.y = y;
	}

	
}

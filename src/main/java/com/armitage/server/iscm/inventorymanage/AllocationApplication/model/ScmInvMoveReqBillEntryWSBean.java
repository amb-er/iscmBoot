package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmInvMoveReqBillEntryWSBean")
public class ScmInvMoveReqBillEntryWSBean extends BaseWSBean{

	private ScmInvMoveReqBillEntry x;
	private ScmInvMoveReqBillEntry2 y;
	
	public ScmInvMoveReqBillEntry getX() {
		return x;
	}
	
	public void setX(ScmInvMoveReqBillEntry x) {
		this.x = x;
	}

	public ScmInvMoveReqBillEntry2 getY() {
		return y;
	}

	public void setY(ScmInvMoveReqBillEntry2 y) {
		this.y = y;
	}
}

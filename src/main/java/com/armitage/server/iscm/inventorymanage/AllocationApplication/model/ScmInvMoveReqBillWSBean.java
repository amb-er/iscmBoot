package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmInvMoveReqBillWSBean")
public class ScmInvMoveReqBillWSBean extends BaseWSBean{
	private ScmInvMoveReqBill x;
	private ScmInvMoveReqBillEntry2 y;
	private ScmInvMoveReqBill2 z;

	public ScmInvMoveReqBill getX() {
		return x;
	}

	public void setX(ScmInvMoveReqBill x) {
		this.x = x;
	}

	public ScmInvMoveReqBillEntry2 getY() {
		return y;
	}

	public void setY(ScmInvMoveReqBillEntry2 y) {
		this.y = y;
	}

	public ScmInvMoveReqBill2 getZ() {
		return z;
	}

	public void setZ(ScmInvMoveReqBill2 z) {
		this.z = z;
	}
	
}

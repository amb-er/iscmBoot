package com.armitage.server.iscm.inventorymanage.inventoryinitialization.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;


@XmlRootElement(name = "ScmInvInitBillWSBean")
public class ScmInvInitBillWSBean extends BaseWSBean {
	
	private ScmInvInitBill x;
	private ScmInvInitBill2 y;
	private ScmInvInitBillEntry2 z;
	private ScmInvInitBillImPort a;

	public ScmInvInitBill getX() {
		return x;
	}

	public void setX(ScmInvInitBill x) {
		this.x = x;
	}

	public ScmInvInitBill2 getY() {
		return y;
	}

	public void setY(ScmInvInitBill2 y) {
		this.y = y;
	}

	public ScmInvInitBillEntry2 getZ() {
		return z;
	}

	public void setZ(ScmInvInitBillEntry2 z) {
		this.z = z;
	}

	public ScmInvInitBillImPort getA() {
		return a;
	}

	public void setA(ScmInvInitBillImPort a) {
		this.a = a;
	}
	
}

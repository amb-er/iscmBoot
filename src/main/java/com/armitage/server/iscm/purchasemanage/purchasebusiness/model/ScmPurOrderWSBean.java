package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurOrderWSBean")
public class ScmPurOrderWSBean extends BaseWSBean {
	
	private ScmPurOrder x;
	private ScmPurOrder2 y;
	private ScmPurOrderEntry2 z;
	private ScmPurRequireEntry2 a;
	private ScmPurOrderAdvQuery b;
	private CommonAuditParams c;
	private ScmPurBillDrillResult d;
	
	public ScmPurOrder getX() {
		return x;
	}
	
	public void setX(ScmPurOrder x) {
		this.x = x;
	}

	public ScmPurOrder2 getY() {
		return y;
	}

	public void setY(ScmPurOrder2 y) {
		this.y = y;
	}

	public ScmPurOrderEntry2 getZ() {
		return z;
	}

	public void setZ(ScmPurOrderEntry2 z) {
		this.z = z;
	}

	public ScmPurRequireEntry2 getA() {
		return a;
	}

	public void setA(ScmPurRequireEntry2 a) {
		this.a = a;
	}

	public ScmPurOrderAdvQuery getB() {
		return b;
	}

	public void setB(ScmPurOrderAdvQuery b) {
		this.b = b;
	}

	public CommonAuditParams getC() {
		return c;
	}

	public void setC(CommonAuditParams c) {
		this.c = c;
	}

	public ScmPurBillDrillResult getD() {
		return d;
	}

	public void setD(ScmPurBillDrillResult d) {
		this.d = d;
	}

}

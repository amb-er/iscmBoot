package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmPurRequireWSBean")
public class ScmPurRequireWSBean extends BaseWSBean {
	
	private ScmPurRequire2 x;
	private ScmPurRequireEntry2 y;
	private ScmPurRequireAdvQuery z;
	private CommonAuditParams a;
	private ScmPurBillDrillResult b;
	
	public ScmPurRequireEntry2 getY() {
		return y;
	}

	public void setY(ScmPurRequireEntry2 y) {
		this.y = y;
	}

	public ScmPurRequire2 getX() {
		return x;
	}
	
	public void setX(ScmPurRequire2 x) {
		this.x = x;
	}

	public ScmPurRequireAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmPurRequireAdvQuery z) {
		this.z = z;
	}

	public CommonAuditParams getA() {
		return a;
	}

	public void setA(CommonAuditParams a) {
		this.a = a;
	}

	public ScmPurBillDrillResult getB() {
		return b;
	}

	public void setB(ScmPurBillDrillResult b) {
		this.b = b;
	}

}

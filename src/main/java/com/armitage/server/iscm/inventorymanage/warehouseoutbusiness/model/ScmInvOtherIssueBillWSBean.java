package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

@XmlRootElement(name = "ScmInvOtherIssueBillWSBean")
public class ScmInvOtherIssueBillWSBean extends BaseWSBean{

	private ScmInvOtherIssueBill x;
	private ScmInvOtherIssueBillEntry y;
	private ScmInvOtherIssueBillAdvQuery a;
	private ScmInvOtherIssueBill2 z;
	private ScmInvOtherIssueBillEntry2 w;
	private CommonAuditParams b;
	
	public ScmInvOtherIssueBill getX() {
		return x;
	}
	public void setX(ScmInvOtherIssueBill x) {
		this.x = x;
	}
	public ScmInvOtherIssueBillEntry getY() {
		return y;
	}
	public void setY(ScmInvOtherIssueBillEntry y) {
		this.y = y;
	}
	public ScmInvOtherIssueBill2 getZ() {
		return z;
	}
	public void setZ(ScmInvOtherIssueBill2 z) {
		this.z = z;
	}
	public ScmInvOtherIssueBillEntry2 getW() {
		return w;
	}
	public void setW(ScmInvOtherIssueBillEntry2 w) {
		this.w = w;
	}
	public ScmInvOtherIssueBillAdvQuery getA() {
		return a;
	}
	public void setA(ScmInvOtherIssueBillAdvQuery a) {
		this.a = a;
	}
	public CommonAuditParams getB() {
		return b;
	}
	public void setB(CommonAuditParams b) {
		this.b = b;
	}
	
}

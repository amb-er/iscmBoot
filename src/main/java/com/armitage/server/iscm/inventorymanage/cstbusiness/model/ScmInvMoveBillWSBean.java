package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams; 

@XmlRootElement(name = "ScmInvMoveBillWSBean")
public class ScmInvMoveBillWSBean extends BaseWSBean {
	
	private ScmInvMoveBill x;
	private ScmInvMoveBill2 y;
	private ScmInvMoveBillEntry2 z;
	private CommonAuditParams a;
	private ScmInvMoveBillAdvQuery b;
	
	public ScmInvMoveBill getX() {
		return x;
	}
	
	public void setX(ScmInvMoveBill x) {
		this.x = x;
	}

    public ScmInvMoveBill2 getY() {
        return y;
    }

    public void setY(ScmInvMoveBill2 y) {
        this.y = y;
    }

    public ScmInvMoveBillEntry2 getZ() {
        return z;
    }

    public void setZ(ScmInvMoveBillEntry2 z) {
        this.z = z;
    }

	public CommonAuditParams getA() {
		return a;
	}

	public void setA(CommonAuditParams a) {
		this.a = a;
	}

	public ScmInvMoveBillAdvQuery getB() {
		return b;
	}

	public void setB(ScmInvMoveBillAdvQuery b) {
		this.b = b;
	}
	
}
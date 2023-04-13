package com.armitage.server.iscm.inventorymanage.internaltrans.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams; 

@XmlRootElement(name = "ScmInvSaleOrderWSBean")
public class ScmInvSaleOrderWSBean extends BaseWSBean {
	
	private ScmInvSaleOrder x;
	private ScmInvSaleOrder2 y;
	private ScmInvSaleOrderEntry2 z;
	private ScmInvSaleOrderAdvQuery a;
	private CommonAuditParams b;

	public ScmInvSaleOrder getX() {
		return x;
	}
	
	public void setX(ScmInvSaleOrder x) {
		this.x = x;
	}

    public ScmInvSaleOrder2 getY() {
        return y;
    }

    public void setY(ScmInvSaleOrder2 y) {
        this.y = y;
    }

    public ScmInvSaleOrderEntry2 getZ() {
        return z;
    }

    public void setZ(ScmInvSaleOrderEntry2 z) {
        this.z = z;
    }

	public ScmInvSaleOrderAdvQuery getA() {
		return a;
	}

	public void setA(ScmInvSaleOrderAdvQuery a) {
		this.a = a;
	}

	public CommonAuditParams getB() {
		return b;
	}

	public void setB(CommonAuditParams b) {
		this.b = b;
	}

}
package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill2;

@XmlRootElement(name = "scmInvMoveIssueBillWSBean")
public class ScmInvMoveIssueBillWSBean extends BaseWSBean {
	
	private ScmInvMoveIssueBill x;
	private ScmInvMoveIssueBillEntry y;
	private ScmInvMoveIssueBillEntry2 w;
	private ScmInvMoveIssueBill2 z;
	private ScmInvMoveIssueBillAdvQuery a;
	public ScmInvMoveIssueBill getX() {
		return x;
	}
	
	public void setX(ScmInvMoveIssueBill x) {
		this.x = x;
	}

    public ScmInvMoveIssueBillEntry getY() {
        return y;
    }

    public void setY(ScmInvMoveIssueBillEntry y) {
        this.y = y;
    }

    public ScmInvMoveIssueBillEntry2 getW() {
        return w;
    }

    public void setW(ScmInvMoveIssueBillEntry2 w) {
        this.w = w;
    }

    public ScmInvMoveIssueBill2 getZ() {
        return z;
    }

    public void setZ(ScmInvMoveIssueBill2 z) {
        this.z = z;
    }

	public ScmInvMoveIssueBillAdvQuery getA() {
		return a;
	}

	public void setA(ScmInvMoveIssueBillAdvQuery a) {
		this.a = a;
	}


}
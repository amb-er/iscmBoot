package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvMoveInWarehsBillEntryWSBean")
public class ScmInvMoveInWarehsBillEntryWSBean extends BaseWSBean {
	
	private ScmInvMoveInWarehsBillEntry x;
	private ScmInvMoveInWarehsBillEntry2 y;
	private ScmInvMoveInWarehsBill2 z;
	private ScmInvMoveInWarehsBillEntryAdvQuery a;
	public ScmInvMoveInWarehsBillEntry getX() {
		return x;
	}
	
	public void setX(ScmInvMoveInWarehsBillEntry x) {
		this.x = x;
	}

	public ScmInvMoveInWarehsBillEntry2 getY() {
		return y;
	}

	public void setY(ScmInvMoveInWarehsBillEntry2 y) {
		this.y = y;
	}

    public ScmInvMoveInWarehsBill2 getZ() {
        return z;
    }

    public void setZ(ScmInvMoveInWarehsBill2 z) {
        this.z = z;
    }

	public ScmInvMoveInWarehsBillEntryAdvQuery getA() {
		return a;
	}

	public void setA(ScmInvMoveInWarehsBillEntryAdvQuery a) {
		this.a = a;
	}

}

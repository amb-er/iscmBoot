package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvMoveInWarehsBillWSBean")
public class ScmInvMoveInWarehsBillWSBean extends BaseWSBean {
	
	private ScmInvMoveInWarehsBill x;
	private ScmInvMoveInWarehsBill2 y;
	private ScmInvMoveInWarehsBillEntry2 z;
	private ScmInvMoveInWarehsBillAdvQuery a;
	public ScmInvMoveInWarehsBill getX() {
		return x;
	}
	
	public void setX(ScmInvMoveInWarehsBill x) {
		this.x = x;
	}

	public ScmInvMoveInWarehsBill2 getY() {
		return y;
	}

	public void setY(ScmInvMoveInWarehsBill2 y) {
		this.y = y;
	}

    public ScmInvMoveInWarehsBillEntry2 getZ() {
        return z;
    }

    public void setZ(ScmInvMoveInWarehsBillEntry2 z) {
        this.z = z;
    }

	public ScmInvMoveInWarehsBillAdvQuery getA() {
		return a;
	}

	public void setA(ScmInvMoveInWarehsBillAdvQuery a) {
		this.a = a;
	}

}

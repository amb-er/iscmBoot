package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvOtherInWarehsBillWSBean")
public class ScmInvOtherInWarehsBillWSBean extends BaseWSBean {
	
	private ScmInvOtherInWarehsBill x;
	private ScmInvOtherInWarehsBill2 y;
	private ScmInvOtherInWarehsBillEntry2 z;
	private ScmInvOtherInWarehsBillAdvQuery a;
	public ScmInvOtherInWarehsBill getX() {
		return x;
	}
	
	public void setX(ScmInvOtherInWarehsBill x) {
		this.x = x;
	}

	public ScmInvOtherInWarehsBill2 getY() {
		return y;
	}

	public void setY(ScmInvOtherInWarehsBill2 y) {
		this.y = y;
	}

	public ScmInvOtherInWarehsBillEntry2 getZ() {
		return z;
	}

	public void setZ(ScmInvOtherInWarehsBillEntry2 z) {
		this.z = z;
	}

	public ScmInvOtherInWarehsBillAdvQuery getA() {
		return a;
	}

	public void setA(ScmInvOtherInWarehsBillAdvQuery a) {
		this.a = a;
	}

}

package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvOtherInWarehsBillEntryWSBean")
public class ScmInvOtherInWarehsBillEntryWSBean extends BaseWSBean {
	
	private ScmInvOtherInWarehsBillEntry x;
	private ScmInvOtherInWarehsBillEntry2 y;
	private ScmInvOtherInWarehsDetailAdvQuery z;
	public ScmInvOtherInWarehsBillEntry getX() {
		return x;
	}
	
	public void setX(ScmInvOtherInWarehsBillEntry x) {
		this.x = x;
	}

	public ScmInvOtherInWarehsBillEntry2 getY() {
		return y;
	}

	public void setY(ScmInvOtherInWarehsBillEntry2 y) {
		this.y = y;
	}

	public ScmInvOtherInWarehsDetailAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmInvOtherInWarehsDetailAdvQuery z) {
		this.z = z;
	}

}

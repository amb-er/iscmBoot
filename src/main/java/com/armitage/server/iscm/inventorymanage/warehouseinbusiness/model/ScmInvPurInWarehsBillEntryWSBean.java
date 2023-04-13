package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvPurInWarehsBillEntryWSBean")
public class ScmInvPurInWarehsBillEntryWSBean extends BaseWSBean {
	
	private ScmInvPurInWarehsBillEntry x;
	private ScmInvPurInWarehsBillEntry2 y;
	private ScmInvPurInWarehsBillEntryAdvQuery z;
	
	public ScmInvPurInWarehsBillEntry getX() {
		return x;
	}
	
	public void setX(ScmInvPurInWarehsBillEntry x) {
		this.x = x;
	}

	public ScmInvPurInWarehsBillEntry2 getY() {
		return y;
	}

	public void setY(ScmInvPurInWarehsBillEntry2 y) {
		this.y = y;
	}

	public ScmInvPurInWarehsBillEntryAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmInvPurInWarehsBillEntryAdvQuery z) {
		this.z = z;
	}

}

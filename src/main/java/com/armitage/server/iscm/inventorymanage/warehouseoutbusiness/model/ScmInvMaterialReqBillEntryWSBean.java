package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmInvMaterialReqBillEntryWSBean")
public class ScmInvMaterialReqBillEntryWSBean extends BaseWSBean {
	
	private ScmInvMaterialReqBillEntry x;
	private ScmInvMaterialReqBillEntry2 y;
	private ScmInvMaterialReqDetailAdvQuery z;
	public ScmInvMaterialReqBillEntry getX() {
		return x;
	}
	
	public void setX(ScmInvMaterialReqBillEntry x) {
		this.x = x;
	}

	public ScmInvMaterialReqBillEntry2 getY() {
		return y;
	}

	public void setY(ScmInvMaterialReqBillEntry2 y) {
		this.y = y;
	}

	public ScmInvMaterialReqDetailAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmInvMaterialReqDetailAdvQuery z) {
		this.z = z;
	}

}

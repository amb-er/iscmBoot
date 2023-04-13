package com.armitage.server.iscm.inventorymanage.countbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCountingListWarehouseWSBean")
public class ScmInvCountingListWarehouseWSBean extends BaseWSBean {
	
	private ScmInvCountingListWarehouse x;
	private ScmInvCountingListWarehouse2 y;
	
	public ScmInvCountingListWarehouse getX() {
		return x;
	}
	
	public void setX(ScmInvCountingListWarehouse x) {
		this.x = x;
	}

	public ScmInvCountingListWarehouse2 getY() {
		return y;
	}

	public void setY(ScmInvCountingListWarehouse2 y) {
		this.y = y;
	}

}

package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmInvWareHouseAndDeptWSBean")
public class ScmInvWareHouseAndDeptWSBean extends BaseWSBean {
	
	private ScmInvWareHouse2 x;
	
	public ScmInvWareHouse2 getX() {
		return x;
	}
	
	public void setX(ScmInvWareHouse2 x) {
		this.x = x;
	}

}
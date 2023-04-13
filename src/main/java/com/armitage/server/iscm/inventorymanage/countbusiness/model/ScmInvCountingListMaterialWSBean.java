package com.armitage.server.iscm.inventorymanage.countbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCountingListMaterialWSBean")
public class ScmInvCountingListMaterialWSBean extends BaseWSBean {
	
	private ScmInvCountingListMaterial x;
	private ScmInvCountingListMaterial2 y;
	
	public ScmInvCountingListMaterial getX() {
		return x;
	}
	
	public void setX(ScmInvCountingListMaterial x) {
		this.x = x;
	}

	public ScmInvCountingListMaterial2 getY() {
		return y;
	}

	public void setY(ScmInvCountingListMaterial2 y) {
		this.y = y;
	}

}

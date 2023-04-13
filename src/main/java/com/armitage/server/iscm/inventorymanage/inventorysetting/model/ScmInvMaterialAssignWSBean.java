package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvMaterialAssignWSBean")
public class ScmInvMaterialAssignWSBean extends BaseWSBean {
	
	private ScmInvMaterialAssign x;
	private ScmInvMaterialAssign2 y;
	
	public ScmInvMaterialAssign getX() {
		return x;
	}
	
	public void setX(ScmInvMaterialAssign x) {
		this.x = x;
	}

	public ScmInvMaterialAssign2 getY() {
		return y;
	}

	public void setY(ScmInvMaterialAssign2 y) {
		this.y = y;
	}

}

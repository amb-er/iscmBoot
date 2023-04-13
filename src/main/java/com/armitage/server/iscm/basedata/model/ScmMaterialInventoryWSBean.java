package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmMaterialInventoryWSBean")
public class ScmMaterialInventoryWSBean extends BaseWSBean {
	
	private ScmMaterialInventory2 x;
	
	public ScmMaterialInventory2 getX() {
		return x;
	}
	
	public void setX(ScmMaterialInventory2 x) {
		this.x = x;
	}

}
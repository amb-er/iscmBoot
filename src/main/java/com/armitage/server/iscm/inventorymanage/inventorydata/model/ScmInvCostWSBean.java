package com.armitage.server.iscm.inventorymanage.inventorydata.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCostWSBean")
public class ScmInvCostWSBean extends BaseWSBean {
	
	private ScmInvCost x;
	
	public ScmInvCost getX() {
		return x;
	}
	
	public void setX(ScmInvCost x) {
		this.x = x;
	}

}

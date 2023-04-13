package com.armitage.server.iscm.inventorymanage.inventorydata.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvBalWSBean")
public class ScmInvBalWSBean extends BaseWSBean {
	
	private ScmInvBal x;
	
	public ScmInvBal getX() {
		return x;
	}
	
	public void setX(ScmInvBal x) {
		this.x = x;
	}

}

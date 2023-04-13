package com.armitage.server.iscm.inventorymanage.inventorysetting.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvAccreditWhWSBean")
public class ScmInvAccreditWhWSBean extends BaseWSBean {
	
	private ScmInvAccreditWh x;
	private ScmInvAccreditWh2 y;
	
	public ScmInvAccreditWh getX() {
		return x;
	}
	
	public void setX(ScmInvAccreditWh x) {
		this.x = x;
	}

	public ScmInvAccreditWh2 getY() {
		return y;
	}

	public void setY(ScmInvAccreditWh2 y) {
		this.y = y;
	}

}

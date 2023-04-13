package com.armitage.server.iscm.inventorymanage.countbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCountingTableEntryWSBean")
public class ScmInvCountingTableEntryWSBean extends BaseWSBean {
	
	private ScmInvCountingTableEntry x;
	private ScmInvCountingTableEntry2 y;
	
	public ScmInvCountingTableEntry getX() {
		return x;
	}
	
	public void setX(ScmInvCountingTableEntry x) {
		this.x = x;
	}

	public ScmInvCountingTableEntry2 getY() {
		return y;
	}

	public void setY(ScmInvCountingTableEntry2 y) {
		this.y = y;
	}

}
package com.armitage.server.iscm.inventorymanage.countbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCountingTableWSBean")
public class ScmInvCountingTableWSBean extends BaseWSBean {
	
	private ScmInvCountingTable x;
	private ScmInvCountingTable2 y;
	private ScmInvCountingTableEntry2 z;
	
	public ScmInvCountingTableEntry2 getZ() {
		return z;
	}

	public void setZ(ScmInvCountingTableEntry2 z) {
		this.z = z;
	}

	public ScmInvCountingTable getX() {
		return x;
	}
	
	public void setX(ScmInvCountingTable x) {
		this.x = x;
	}

	public ScmInvCountingTable2 getY() {
		return y;
	}

	public void setY(ScmInvCountingTable2 y) {
		this.y = y;
	}

}

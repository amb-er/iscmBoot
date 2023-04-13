package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurReceiveEntryWSBean")
public class ScmPurReceiveEntryWSBean extends BaseWSBean {
	
	private ScmPurReceiveEntry x;
	private ScmPurReceiveEntry2 y;
	private ScmPurReceive2 z;
	private ScmPurReceiveEntryAdvQuery a;
	
	public ScmPurReceiveEntry getX() {
		return x;
	}
	
	public void setX(ScmPurReceiveEntry x) {
		this.x = x;
	}

	public ScmPurReceiveEntry2 getY() {
		return y;
	}

	public void setY(ScmPurReceiveEntry2 y) {
		this.y = y;
	}

	public ScmPurReceive2 getZ() {
		return z;
	}

	public void setZ(ScmPurReceive2 z) {
		this.z = z;
	}

	public ScmPurReceiveEntryAdvQuery getA() {
		return a;
	}

	public void setA(ScmPurReceiveEntryAdvQuery a) {
		this.a = a;
	}

}

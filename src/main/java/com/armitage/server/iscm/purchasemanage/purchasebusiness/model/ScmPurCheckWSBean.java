package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmPurCheckWSBean")
public class ScmPurCheckWSBean extends BaseWSBean {
	
	private ScmPurCheck2 x;
	private ScmPurReceiveEntry2 y;
	private ScmPurCheckAdvQuery z;
	
	public ScmPurCheck2 getX() {
		return x;
	}
	
	public void setX(ScmPurCheck2 x) {
		this.x = x;
	}

	public ScmPurReceiveEntry2 getY() {
		return y;
	}

	public void setY(ScmPurReceiveEntry2 y) {
		this.y = y;
	}

	public ScmPurCheckAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmPurCheckAdvQuery z) {
		this.z = z;
	}
	
}

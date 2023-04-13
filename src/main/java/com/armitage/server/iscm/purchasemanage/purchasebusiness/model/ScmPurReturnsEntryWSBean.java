
package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurReturnsEntryWSBean")
public class ScmPurReturnsEntryWSBean extends BaseWSBean {
	
	private ScmPurReturnsEntry x;
	private ScmPurReturnsEntry2 y;
	private ScmPurReturns2 z;
	
	public ScmPurReturnsEntry getX() {
		return x;
	}
	
	public void setX(ScmPurReturnsEntry x) {
		this.x = x;
	}

	public ScmPurReturnsEntry2 getY() {
		return y;
	}

	public void setY(ScmPurReturnsEntry2 y) {
		this.y = y;
	}

	public ScmPurReturns2 getZ() {
		return z;
	}

	public void setZ(ScmPurReturns2 z) {
		this.z = z;
	}

}
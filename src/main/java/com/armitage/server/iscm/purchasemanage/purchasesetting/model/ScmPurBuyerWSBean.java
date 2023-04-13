
package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurBuyerWSBean")
public class ScmPurBuyerWSBean extends BaseWSBean {
	
	private ScmPurBuyer x;
	
	private ScmPurGroup y;
	
	private ScmPurBuyer2 z;
	private ScmPurBuyerOrg2 a;
	public ScmPurBuyer getX() {
		return x;
	}
	
	public void setX(ScmPurBuyer x) {
		this.x = x;
	}

	public ScmPurGroup getY() {
		return y;
	}

	public void setY(ScmPurGroup y) {
		this.y = y;
	}

	public ScmPurBuyer2 getZ() {
		return z;
	}

	public void setZ(ScmPurBuyer2 z) {
		this.z = z;
	}

	public ScmPurBuyerOrg2 getA() {
		return a;
	}

	public void setA(ScmPurBuyerOrg2 a) {
		this.a = a;
	}

	
}
package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2; 

@XmlRootElement(name = "ScmPurOrderEntryWSBean")
public class ScmPurOrderEntryWSBean extends BaseWSBean {
	
	private ScmPurOrderEntry x;
	private ScmPurOrderEntry2 y;
	private ScmPurOrderEntryAdvQuery z;
	private ScmPurOrder2 a;
	private ScmPurPrice2 b;
	
	public ScmPurOrderEntry getX() {
		return x;
	}
	
	public void setX(ScmPurOrderEntry x) {
		this.x = x;
	}

	public ScmPurOrderEntry2 getY() {
		return y;
	}

	public void setY(ScmPurOrderEntry2 y) {
		this.y = y;
	}

	public ScmPurOrderEntryAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmPurOrderEntryAdvQuery z) {
		this.z = z;
	}

	public ScmPurOrder2 getA() {
		return a;
	}

	public void setA(ScmPurOrder2 a) {
		this.a = a;
	}

	public ScmPurPrice2 getB() {
		return b;
	}

	public void setB(ScmPurPrice2 b) {
		this.b = b;
	}

}

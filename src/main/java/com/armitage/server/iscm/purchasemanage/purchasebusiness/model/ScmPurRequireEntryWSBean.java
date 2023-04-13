package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;

@XmlRootElement(name = "ScmPurRequireEntryWSBean")
public class ScmPurRequireEntryWSBean extends BaseWSBean {
	
	private ScmPurRequireEntry x;
	private ScmPurRequireEntry2 y;
	private ScmPurRequireEntryAdvQuery z;
	private ScmPurRequire2 a;
	private ScmPurOrder2 b;
	private ScmPurPrice2 c;
	private ScmDataCollectionStepState2 d;
	
	public ScmPurRequireEntry getX() {
		return x;
	}
	
	public void setX(ScmPurRequireEntry x) {
		this.x = x;
	}

	public ScmPurRequireEntry2 getY() {
		return y;
	}

	public void setY(ScmPurRequireEntry2 y) {
		this.y = y;
	}

	public ScmPurRequireEntryAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmPurRequireEntryAdvQuery z) {
		this.z = z;
	}

	public ScmPurRequire2 getA() {
		return a;
	}

	public void setA(ScmPurRequire2 a) {
		this.a = a;
	}

	public ScmPurOrder2 getB() {
		return b;
	}

	public void setB(ScmPurOrder2 b) {
		this.b = b;
	}

	public ScmPurPrice2 getC() {
		return c;
	}

	public void setC(ScmPurPrice2 c) {
		this.c = c;
	}

	public ScmDataCollectionStepState2 getD() {
		return d;
	}

	public void setD(ScmDataCollectionStepState2 d) {
		this.d = d;
	}

}

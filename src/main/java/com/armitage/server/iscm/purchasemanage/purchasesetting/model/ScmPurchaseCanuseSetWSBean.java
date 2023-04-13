
package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2; 

@XmlRootElement(name = "scmPurchaseCanuseSetWSBean")
public class ScmPurchaseCanuseSetWSBean extends BaseWSBean {
	
	private ScmPurchaseCanuseSet x;
	private ScmPurchaseCanuseSetOrg2 y;
	private ScmPurchaseCanuseSetMC2 z;
	private ScmPurchaseCanuseSetBizType2 w;
	private ScmPurRequire2 a;
	private ScmPurRequireEntry2 b;
	
	public ScmPurchaseCanuseSet getX() {
		return x;
	}
	
	public void setX(ScmPurchaseCanuseSet x) {
		this.x = x;
	}

	public ScmPurchaseCanuseSetOrg2 getY() {
		return y;
	}

	public void setY(ScmPurchaseCanuseSetOrg2 y) {
		this.y = y;
	}

	public ScmPurchaseCanuseSetMC2 getZ() {
		return z;
	}

	public void setZ(ScmPurchaseCanuseSetMC2 z) {
		this.z = z;
	}

	public ScmPurchaseCanuseSetBizType2 getW() {
		return w;
	}

	public void setW(ScmPurchaseCanuseSetBizType2 w) {
		this.w = w;
	}

	public ScmPurRequire2 getA() {
		return a;
	}

	public void setA(ScmPurRequire2 a) {
		this.a = a;
	}

	public ScmPurRequireEntry2 getB() {
		return b;
	}

	public void setB(ScmPurRequireEntry2 b) {
		this.b = b;
	}

}
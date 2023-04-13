
package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;

@XmlRootElement(name = "scmPurSupplyInfoWSBean")
public class ScmPurSupplyInfoWSBean extends BaseWSBean {
	
	private ScmPurSupplyInfo2 x;
	private ScmPurSupplyRecOrg y;
	private ScmPurSupplyInfoAdvQuery a;
	private ScmPurPriceQuery q;
	
	public ScmPurSupplyInfo2 getX() {
		return x;
	}

	public void setX(ScmPurSupplyInfo2 x) {
		this.x = x;
	}

	public ScmPurSupplyRecOrg getY() {
		return y;
	}

	public void setY(ScmPurSupplyRecOrg y) {
		this.y = y;
	}

	public ScmPurSupplyInfoAdvQuery getA() {
		return a;
	}

	public void setA(ScmPurSupplyInfoAdvQuery a) {
		this.a = a;
	}

	public ScmPurPriceQuery getQ() {
		return q;
	}

	public void setQ(ScmPurPriceQuery q) {
		this.q = q;
	}
	
}
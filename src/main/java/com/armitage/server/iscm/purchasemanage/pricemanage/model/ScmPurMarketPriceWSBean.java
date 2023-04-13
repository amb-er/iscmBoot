
package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmPurPriceEntryWSBean")
public class ScmPurMarketPriceWSBean extends BaseWSBean {
	
	private ScmPurMarketPrice a;
	private ScmPurMarketPrice2 b;
	private ScmPurMarketPriceEntry x;
	private ScmPurMarketPriceEntry2 y;
	public ScmPurMarketPrice getA() {
		return a;
	}
	public void setA(ScmPurMarketPrice a) {
		this.a = a;
	}
	public ScmPurMarketPrice2 getB() {
		return b;
	}
	public void setB(ScmPurMarketPrice2 b) {
		this.b = b;
	}
	public ScmPurMarketPriceEntry getX() {
		return x;
	}
	public void setX(ScmPurMarketPriceEntry x) {
		this.x = x;
	}
	public ScmPurMarketPriceEntry2 getY() {
		return y;
	}
	public void setY(ScmPurMarketPriceEntry2 y) {
		this.y = y;
	}
	
}
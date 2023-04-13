
package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmMaterialPriceWSBean")
public class ScmMaterialPriceWSBean extends BaseWSBean {
	
	private ScmPurPriceAllQuery a;
	private ScmPurPriceAll x;
	private ScmPurPriceQuery y;
	private ScmMaterialPrice z;
	private ScmInvPriceQuery b;
	private ScmPurSupplyInfoQuery c;
	public ScmPurPriceAllQuery getA() {
		return a;
	}
	public void setA(ScmPurPriceAllQuery a) {
		this.a = a;
	}
	public ScmPurPriceAll getX() {
		return x;
	}
	public void setX(ScmPurPriceAll x) {
		this.x = x;
	}
	public ScmPurPriceQuery getY() {
		return y;
	}
	public void setY(ScmPurPriceQuery y) {
		this.y = y;
	}
	public ScmMaterialPrice getZ() {
		return z;
	}
	public void setZ(ScmMaterialPrice z) {
		this.z = z;
	}
	public ScmInvPriceQuery getB() {
		return b;
	}
	public void setB(ScmInvPriceQuery b) {
		this.b = b;
	}
	public ScmPurSupplyInfoQuery getC() {
		return c;
	}
	public void setC(ScmPurSupplyInfoQuery c) {
		this.c = c;
	}

}
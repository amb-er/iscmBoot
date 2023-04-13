
package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2; 

@XmlRootElement(name = "ScmPurPriceWSBean")
public class ScmPurPriceWSBean extends BaseWSBean {
	
	private ScmPurPrice x;
	private ScmPurPrice2 y;
	private ScmPurPriceEntry2 z;
	private ScmPurPriceImPort a;
	private CommonAuditParams b;
	private ScmPurPriceAdvQuery c;
	private ScmPurPriceAssign2 d;
	private ScmPurRequireEntry2 e;
	private ScmPurOrderEntry2 f;
	private ScmSupplierRegInvitation g;
	private ScmPurPricePrepareEntry2 h;
	private ScmMaterialPrePrice scmMaterialPrePrice;
	private ScmPurPriceQuery scmPurPriceQuery;
	
	public ScmPurPrice getX() {
		return x;
	}
	
	public void setX(ScmPurPrice x) {
		this.x = x;
	}

	public ScmPurPrice2 getY() {
		return y;
	}

	public void setY(ScmPurPrice2 y) {
		this.y = y;
	}

	public ScmPurPriceEntry2 getZ() {
		return z;
	}

	public void setZ(ScmPurPriceEntry2 z) {
		this.z = z;
	}

    public ScmPurPriceImPort getA() {
        return a;
    }

    public void setA(ScmPurPriceImPort a) {
        this.a = a;
    }

	public CommonAuditParams getB() {
		return b;
	}

	public void setB(CommonAuditParams b) {
		this.b = b;
	}

	public ScmPurPriceAdvQuery getC() {
		return c;
	}

	public void setC(ScmPurPriceAdvQuery c) {
		this.c = c;
	}

	public ScmPurPriceAssign2 getD() {
		return d;
	}

	public void setD(ScmPurPriceAssign2 d) {
		this.d = d;
	}

	public ScmPurRequireEntry2 getE() {
		return e;
	}

	public void setE(ScmPurRequireEntry2 e) {
		this.e = e;
	}

	public ScmPurOrderEntry2 getF() {
		return f;
	}

	public void setF(ScmPurOrderEntry2 f) {
		this.f = f;
	}

	public ScmSupplierRegInvitation getG() {
		return g;
	}

	public void setG(ScmSupplierRegInvitation g) {
		this.g = g;
	}

	public ScmPurPricePrepareEntry2 getH() {
		return h;
	}

	public void setH(ScmPurPricePrepareEntry2 h) {
		this.h = h;
	}

	public ScmMaterialPrePrice getScmMaterialPrePrice() {
		return scmMaterialPrePrice;
	}

	public void setScmMaterialPrePrice(ScmMaterialPrePrice scmMaterialPrePrice) {
		this.scmMaterialPrePrice = scmMaterialPrePrice;
	}

	public ScmPurPriceQuery getScmPurPriceQuery() {
		return scmPurPriceQuery;
	}

	public void setScmPurPriceQuery(ScmPurPriceQuery scmPurPriceQuery) {
		this.scmPurPriceQuery = scmPurPriceQuery;
	}

}
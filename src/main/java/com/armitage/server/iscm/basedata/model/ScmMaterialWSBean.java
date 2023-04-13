
package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.ifbc.costcard.model.ScmMaterialOrGroupAdvQuery;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;

@XmlRootElement(name = "ScmMaterialWSBean")
public class ScmMaterialWSBean extends BaseWSBean {
	
	private ScmMaterial x;
	private ScmMaterial2 y;
	private ScmMaterialUnitRelation2 z;
	private ScmMaterialPurchase2 a;
	private ScmMaterialInventory2 b;
	private ScmMaterialCompanyInfo c;
	private ScmMaterialOrGroupAdvQuery f;
	private ScmMaterialAdvQuery g;
	private ScmMaterialAdd h;
	private ScmDataCollectionStepState2 i;
	private ScmBaseAttachment k;
	private ScmMaterialGroupStandard2 p;
	
	public ScmMaterial getX() {
		return x;
	}
	
	public void setX(ScmMaterial x) {
		this.x = x;
	}

	public ScmMaterial2 getY() {
		return y;
	}

	public void setY(ScmMaterial2 y) {
		this.y = y;
	}

	public ScmMaterialUnitRelation2 getZ() {
		return z;
	}

	public void setZ(ScmMaterialUnitRelation2 z) {
		this.z = z;
	}

	public ScmMaterialPurchase2 getA() {
		return a;
	}

	public void setA(ScmMaterialPurchase2 a) {
		this.a = a;
	}

	public ScmMaterialInventory2 getB() {
		return b;
	}

	public void setB(ScmMaterialInventory2 b) {
		this.b = b;
	}

	public ScmMaterialCompanyInfo getC() {
		return c;
	}

	public void setC(ScmMaterialCompanyInfo c) {
		this.c = c;
	}

	public ScmMaterialOrGroupAdvQuery getF() {
		return f;
	}

	public void setF(ScmMaterialOrGroupAdvQuery f) {
		this.f = f;
	}

	public ScmMaterialAdvQuery getG() {
		return g;
	}

	public void setG(ScmMaterialAdvQuery g) {
		this.g = g;
	}

	public ScmMaterialAdd getH() {
		return h;
	}

	public void setH(ScmMaterialAdd h) {
		this.h = h;
	}

	public ScmDataCollectionStepState2 getI() {
		return i;
	}

	public void setI(ScmDataCollectionStepState2 i) {
		this.i = i;
	}


	public ScmBaseAttachment getK() {
		return k;
	}

	public void setK(ScmBaseAttachment k) {
		this.k = k;
	}

	public ScmMaterialGroupStandard2 getP() {
		return p;
	}

	public void setP(ScmMaterialGroupStandard2 p) {
		this.p = p;
	}

}
package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams; 

@XmlRootElement(name = "ScmPurQuotationWSBean")
public class ScmPurQuotationWSBean extends BaseWSBean {
	
	private ScmPurQuotation x;
	private ScmPurQuotation2 y;
	private ScmPurQuotationEntry2 z;
	private ScmPurQuotationAdd a;
	private ScmPurQuotationAdvQuery b;
	private CommonAuditParams c;

	public ScmPurQuotation getX() {
		return x;
	}
	
	public void setX(ScmPurQuotation x) {
		this.x = x;
	}

	public ScmPurQuotation2 getY() {
		return y;
	}

	public void setY(ScmPurQuotation2 y) {
		this.y = y;
	}

	public ScmPurQuotationEntry2 getZ() {
		return z;
	}

	public void setZ(ScmPurQuotationEntry2 z) {
		this.z = z;
	}

	public ScmPurQuotationAdd getA() {
		return a;
	}

	public void setA(ScmPurQuotationAdd a) {
		this.a = a;
	}

	public ScmPurQuotationAdvQuery getB() {
		return b;
	}

	public void setB(ScmPurQuotationAdvQuery b) {
		this.b = b;
	}

	public CommonAuditParams getC() {
		return c;
	}

	public void setC(CommonAuditParams c) {
		this.c = c;
	}

}

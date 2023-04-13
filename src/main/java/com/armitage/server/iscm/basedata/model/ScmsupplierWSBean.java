package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmsupplierWSBean")
public class ScmsupplierWSBean extends BaseWSBean {
	
	private Scmsupplier x;
	private Scmsupplier2 y;
	private ScmSupplierUnified z;
	private Scmsupplierpurchaseinfo2 a;
	private Scmsuppliercompanyinfo2 b;
	private ScmSupplierPlatFormUser c;
	private ScmIndustryGroupQualifyType2 e;
	private ScmQualifieInfo2 f;
	
	public Scmsupplier2 getY() {
		return y;
	}

	public void setY(Scmsupplier2 y) {
		this.y = y;
	}

	public Scmsupplier getX() {
		return x;
	}
	
	public void setX(Scmsupplier x) {
		this.x = x;
	}

	public ScmSupplierUnified getZ() {
		return z;
	}

	public void setZ(ScmSupplierUnified z) {
		this.z = z;
	}

	public Scmsupplierpurchaseinfo2 getA() {
		return a;
	}

	public void setA(Scmsupplierpurchaseinfo2 a) {
		this.a = a;
	}

	public Scmsuppliercompanyinfo2 getB() {
		return b;
	}

	public void setB(Scmsuppliercompanyinfo2 b) {
		this.b = b;
	}

	public ScmSupplierPlatFormUser getC() {
		return c;
	}

	public void setC(ScmSupplierPlatFormUser c) {
		this.c = c;
	}

	public ScmIndustryGroupQualifyType2 getE() {
		return e;
	}

	public void setE(ScmIndustryGroupQualifyType2 e) {
		this.e = e;
	}

	public ScmQualifieInfo2 getF() {
		return f;
	}

	public void setF(ScmQualifieInfo2 f) {
		this.f = f;
	}

}
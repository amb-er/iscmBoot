package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment2; 

@XmlRootElement(name = "scmSupplierQualifieInfoBillWSBean")
public class ScmSupplierQualifieInfoBillWSBean extends BaseWSBean {
	
	private ScmSupplierQualifieInfoBill x;
	private ScmSupplierQualifieInfoBillEntry2 y;
	private CommonAuditParams z;
	private ScmBaseAttachment a;
	private ScmSupplierQualifieInfoBill2 b;
	private ScmBaseAttachment2 c;
	
	public ScmSupplierQualifieInfoBill getX() {
		return x;
	}
	public void setX(ScmSupplierQualifieInfoBill x) {
		this.x = x;
	}
	public ScmSupplierQualifieInfoBillEntry2 getY() {
		return y;
	}
	public void setY(ScmSupplierQualifieInfoBillEntry2 y) {
		this.y = y;
	}
	public CommonAuditParams getZ() {
		return z;
	}
	public void setZ(CommonAuditParams z) {
		this.z = z;
	}
	public ScmBaseAttachment getA() {
		return a;
	}
	public void setA(ScmBaseAttachment a) {
		this.a = a;
	}
	public ScmSupplierQualifieInfoBill2 getB() {
		return b;
	}
	public void setB(ScmSupplierQualifieInfoBill2 b) {
		this.b = b;
	}
	public ScmBaseAttachment2 getC() {
		return c;
	}
	public void setC(ScmBaseAttachment2 c) {
		this.c = c;
	}
	
}
package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams; 

@XmlRootElement(name = "ScmInvMaterialRequestBillWSBean")
public class ScmInvMaterialRequestBillWSBean extends BaseWSBean {
	
	private ScmInvMaterialRequestBill x;
	private ScmInvMaterialRequestBill2 Y;
	private ScmInvMaterialRequestBillEntry z;
	private ScmInvMaterialRequestBillEntry2 w;
	private ScmInvMaterialRequestBillAdvQuery a;
	private CommonAuditParams b;
	private ScmInvMaterialDrillResult c;
	
	public ScmInvMaterialRequestBill getX() {
		return x;
	}
	public void setX(ScmInvMaterialRequestBill x) {
		this.x = x;
	}
	public ScmInvMaterialRequestBill2 getY() {
		return Y;
	}
	public void setY(ScmInvMaterialRequestBill2 y) {
		Y = y;
	}
	public ScmInvMaterialRequestBillEntry getZ() {
		return z;
	}
	public void setZ(ScmInvMaterialRequestBillEntry z) {
		this.z = z;
	}
	public ScmInvMaterialRequestBillEntry2 getW() {
		return w;
	}
	public void setW(ScmInvMaterialRequestBillEntry2 w) {
		this.w = w;
	}
	public ScmInvMaterialRequestBillAdvQuery getA() {
		return a;
	}
	public void setA(ScmInvMaterialRequestBillAdvQuery a) {
		this.a = a;
	}
	public CommonAuditParams getB() {
		return b;
	}
	public void setB(CommonAuditParams b) {
		this.b = b;
	}
	public ScmInvMaterialDrillResult getC() {
		return c;
	}
	public void setC(ScmInvMaterialDrillResult c) {
		this.c = c;
	}
	

}
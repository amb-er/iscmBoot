package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams; 

@XmlRootElement(name = "ScmInvMaterialReqBillWSBean")
public class ScmInvMaterialReqBillWSBean extends BaseWSBean {
	
	private ScmInvMaterialReqBill x;
	private ScmInvMaterialReqBill2 y;
	private ScmInvMaterialReqBillEntry2 z;
	private ScmInvMaterialReqBillAdvQuery a;
	private CommonAuditParams b;
	private ScmInvMaterialDrillResult c;

	public ScmInvMaterialDrillResult getC() {
		return c;
	}

	public void setC(ScmInvMaterialDrillResult c) {
		this.c = c;
	}

	public ScmInvMaterialReqBill getX() {
		return x;
	}
	
	public void setX(ScmInvMaterialReqBill x) {
		this.x = x;
	}

	public ScmInvMaterialReqBill2 getY() {
		return y;
	}

	public void setY(ScmInvMaterialReqBill2 y) {
		this.y = y;
	}

	public ScmInvMaterialReqBillEntry2 getZ() {
		return z;
	}

	public void setZ(ScmInvMaterialReqBillEntry2 z) {
		this.z = z;
	}

	public ScmInvMaterialReqBillAdvQuery getA() {
		return a;
	}

	public void setA(ScmInvMaterialReqBillAdvQuery a) {
		this.a = a;
	}

	public CommonAuditParams getB() {
		return b;
	}

	public void setB(CommonAuditParams b) {
		this.b = b;
	}

}

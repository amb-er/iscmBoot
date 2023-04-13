package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvMaterialRequestBillEntryWSBean")
public class ScmInvMaterialRequestBillEntryWSBean extends BaseWSBean {
	
	private ScmInvMaterialRequestBill x;
	private ScmInvMaterialRequestBill2 Y;
	private ScmInvMaterialRequestBillEntry z;
	private ScmInvMaterialRequestBillEntry2 w;
	
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
}
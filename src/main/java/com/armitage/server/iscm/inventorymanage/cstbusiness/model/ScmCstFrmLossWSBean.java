package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

@XmlRootElement(name = "ScmCstFrmLossWSBean")
public class ScmCstFrmLossWSBean extends BaseWSBean {
    
    private ScmCstFrmLoss x;
    private ScmCstFrmLoss2 y;
    private ScmCstFrmLossEntry z;
    private ScmCstFrmLossEntry2 a;
    private CommonAuditParams b;
    private ScmCstFrmLossCostQuery c;
    
	public ScmCstFrmLoss getX() {
		return x;
	}
	public void setX(ScmCstFrmLoss x) {
		this.x = x;
	}
	public ScmCstFrmLoss2 getY() {
		return y;
	}
	public void setY(ScmCstFrmLoss2 y) {
		this.y = y;
	}
	public ScmCstFrmLossEntry getZ() {
		return z;
	}
	public void setZ(ScmCstFrmLossEntry z) {
		this.z = z;
	}
	public ScmCstFrmLossEntry2 getA() {
		return a;
	}
	public void setA(ScmCstFrmLossEntry2 a) {
		this.a = a;
	}
	public CommonAuditParams getB() {
		return b;
	}
	public void setB(CommonAuditParams b) {
		this.b = b;
	}
	public ScmCstFrmLossCostQuery getC() {
		return c;
	}
	public void setC(ScmCstFrmLossCostQuery c) {
		this.c = c;
	}
	
}
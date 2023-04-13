package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;

@XmlRootElement(name = "ScmPurReceiveWSBean")
public class ScmPurReceiveWSBean extends BaseWSBean {
	
	private ScmPurReceive x;
	private ScmPurReceive2 w;
	private ScmPurReceiveEntry2 y;
	private ScmPurOrderEntry2 z;
	private ScmInvPurInWarehsBill2 a;
	private ScmPurReceiveAdvQuery b;
	private CommonAuditParams c;
	private ScmPurBillDrillResult d;
	
	public ScmPurOrderEntry2 getZ() {
		return z;
	}

	public void setZ(ScmPurOrderEntry2 z) {
		this.z = z;
	}

	public ScmPurReceive getX() {
		return x;
	}
	
	public void setX(ScmPurReceive x) {
		this.x = x;
	}

	public ScmPurReceiveEntry2 getY() {
		return y;
	}

	public void setY(ScmPurReceiveEntry2 y) {
		this.y = y;
	}

    public ScmPurReceive2 getW() {
        return w;
    }

    public void setW(ScmPurReceive2 w) {
        this.w = w;
    }

	public ScmInvPurInWarehsBill2 getA() {
		return a;
	}

	public void setA(ScmInvPurInWarehsBill2 a) {
		this.a = a;
	}

	public ScmPurReceiveAdvQuery getB() {
		return b;
	}

	public void setB(ScmPurReceiveAdvQuery b) {
		this.b = b;
	}

	public CommonAuditParams getC() {
		return c;
	}

	public void setC(CommonAuditParams c) {
		this.c = c;
	}

	public ScmPurBillDrillResult getD() {
		return d;
	}

	public void setD(ScmPurBillDrillResult d) {
		this.d = d;
	}
    
}

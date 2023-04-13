package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams; 

@XmlRootElement(name = "ScmInvSaleIssueBillWSBean")
public class ScmInvSaleIssueBillWSBean extends BaseWSBean {
	
	private ScmInvSaleIssueBill x;
	private ScmInvSaleIssueBillEntry y;
	private ScmInvSaleIssueBill2 z;
	private ScmInvSaleIssueBillEntry2 w;
	private ScmInvSaleIssueBillAdvQuery a;
	private ScmDataCollectionStepState2 b;
	private CommonAuditParams s;
	
	public ScmInvSaleIssueBill getX() {
		return x;
	}
	
	public void setX(ScmInvSaleIssueBill x) {
		this.x = x;
	}

    public ScmInvSaleIssueBillEntry getY() {
        return y;
    }

    public void setY(ScmInvSaleIssueBillEntry y) {
        this.y = y;
    }

    public ScmInvSaleIssueBill2 getZ() {
        return z;
    }

    public void setZ(ScmInvSaleIssueBill2 z) {
        this.z = z;
    }

    public ScmInvSaleIssueBillEntry2 getW() {
        return w;
    }

    public void setW(ScmInvSaleIssueBillEntry2 w) {
        this.w = w;
    }

	public ScmInvSaleIssueBillAdvQuery getA() {
		return a;
	}

	public void setA(ScmInvSaleIssueBillAdvQuery a) {
		this.a = a;
	}

	public ScmDataCollectionStepState2 getB() {
		return b;
	}

	public void setB(ScmDataCollectionStepState2 b) {
		this.b = b;
	}

	public CommonAuditParams getS() {
		return s;
	}

	public void setS(CommonAuditParams s) {
		this.s = s;
	}

}
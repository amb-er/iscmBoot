package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iaps.daily.model.Apinvoice;
import com.armitage.server.iaps.daily.model.Apinvoice2;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult; 

@XmlRootElement(name = "ScmInvPurInWarehsBillWSBean")
public class ScmInvPurInWarehsBillWSBean extends BaseWSBean {
	
	private ScmInvPurInWarehsBill x;
	private ScmInvPurInWarehsBill2 y;
	private ScmInvPurInWarehsBillEntry2 z;
	private ScmInvPurInWarehsBillAdvQuery a;
	private Apinvoice2 b;
	private CommonAuditParams c;
	private ScmDataCollectionStepState2 d;
	private ScmPurBillDrillResult e;
	
	public ScmInvPurInWarehsBill getX() {
		return x;
	}
	
	public void setX(ScmInvPurInWarehsBill x) {
		this.x = x;
	}

	public ScmInvPurInWarehsBill2 getY() {
		return y;
	}

	public void setY(ScmInvPurInWarehsBill2 y) {
		this.y = y;
	}

	public ScmInvPurInWarehsBillEntry2 getZ() {
		return z;
	}

	public void setZ(ScmInvPurInWarehsBillEntry2 z) {
		this.z = z;
	}

	public ScmInvPurInWarehsBillAdvQuery getA() {
		return a;
	}

	public void setA(ScmInvPurInWarehsBillAdvQuery a) {
		this.a = a;
	}

    public Apinvoice2 getB() {
        return b;
    }

    public void setB(Apinvoice2 b) {
        this.b = b;
    }

	public CommonAuditParams getC() {
		return c;
	}

	public void setC(CommonAuditParams c) {
		this.c = c;
	}

	public ScmDataCollectionStepState2 getD() {
		return d;
	}

	public void setD(ScmDataCollectionStepState2 d) {
		this.d = d;
	}

	public ScmPurBillDrillResult getE() {
		return e;
	}

	public void setE(ScmPurBillDrillResult e) {
		this.e = e;
	}

	
}

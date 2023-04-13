package com.armitage.server.iscm.common.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;

@XmlRootElement(name = "scmSyncTaskInfoWSBean")
public class ScmSyncTaskInfoWSBean extends BaseWSBean{
	private ScmSyncTaskInfo2 a;
	private ScmPurOrder2 b; 
	private ScmInvPurInWarehsBill2 c;
	private ScmSyncTaskInfoAdvQuery d;
	
	public ScmSyncTaskInfo2 getA() {
		return a;
	}

	public void setA(ScmSyncTaskInfo2 a) {
		this.a = a;
	}

	public ScmPurOrder2 getB() {
		return b;
	}

	public void setB(ScmPurOrder2 b) {
		this.b = b;
	}

	public ScmInvPurInWarehsBill2 getC() {
		return c;
	}

	public void setC(ScmInvPurInWarehsBill2 c) {
		this.c = c;
	}

	public ScmSyncTaskInfoAdvQuery getD() {
		return d;
	}

	public void setD(ScmSyncTaskInfoAdvQuery d) {
		this.d = d;
	}
	
}

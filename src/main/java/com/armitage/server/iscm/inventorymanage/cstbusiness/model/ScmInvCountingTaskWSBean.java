package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountInvTaskDiff;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterial2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListWarehouse2; 

@XmlRootElement(name = "ScmInvCountingTaskWSBean")
public class ScmInvCountingTaskWSBean extends BaseWSBean {
    
    private ScmInvCountingTask x;
    private ScmInvCountingTask2 y;
    private ScmInvCountingListWarehouse2 z;
    private ScmInvCountingListMaterial2 a;
    private ScmInvCountingListUserOrg2 b;
    private ScmInvCountCostTaskDiff c;
    private ScmInvCountInvTaskDiff d;
    private ScmInvCountingTaskAdvQuery f;
    private ScmInvCountingListMaterialGroup2 g;
    public ScmInvCountingListUserOrg2 getB() {
		return b;
	}

	public void setB(ScmInvCountingListUserOrg2 b) {
		this.b = b;
	}

	public ScmInvCountingListMaterial2 getA() {
		return a;
	}

	public void setA(ScmInvCountingListMaterial2 a) {
		this.a = a;
	}

	public ScmInvCountingTask getX() {
        return x;
    }
    
    public void setX(ScmInvCountingTask x) {
        this.x = x;
    }

    public ScmInvCountingTask2 getY() {
        return y;
    }

    public void setY(ScmInvCountingTask2 y) {
        this.y = y;
    }

	public ScmInvCountingListWarehouse2 getZ() {
		return z;
	}

	public void setZ(ScmInvCountingListWarehouse2 z) {
		this.z = z;
	}

	public ScmInvCountCostTaskDiff getC() {
		return c;
	}

	public void setC(ScmInvCountCostTaskDiff c) {
		this.c = c;
	}

	public ScmInvCountInvTaskDiff getD() {
		return d;
	}

	public void setD(ScmInvCountInvTaskDiff d) {
		this.d = d;
	}

	public ScmInvCountingTaskAdvQuery getF() {
		return f;
	}

	public void setF(ScmInvCountingTaskAdvQuery f) {
		this.f = f;
	}

	public ScmInvCountingListMaterialGroup2 getG() {
		return g;
	}

	public void setG(ScmInvCountingListMaterialGroup2 g) {
		this.g = g;
	}
    
}

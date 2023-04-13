package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCountingCostCenterWSBean")
public class ScmInvCountingCostCenterWSBean extends BaseWSBean {
    
    private ScmInvCountingCostCenter x;
    private ScmInvCountingCostCenter2 y;
    private ScmInvCountingCostCenterEntry2 z;
    private ScmInvCountingCostCenterProduct2 q;
    
    public ScmInvCountingCostCenter getX() {
        return x;
    }
    
    public void setX(ScmInvCountingCostCenter x) {
        this.x = x;
    }

    public ScmInvCountingCostCenter2 getY() {
        return y;
    }

    public void setY(ScmInvCountingCostCenter2 y) {
        this.y = y;
    }

    public ScmInvCountingCostCenterEntry2 getZ() {
        return z;
    }

    public void setZ(ScmInvCountingCostCenterEntry2 z) {
        this.z = z;
    }

	public ScmInvCountingCostCenterProduct2 getQ() {
		return q;
	}

	public void setQ(ScmInvCountingCostCenterProduct2 q) {
		this.q = q;
	}

    

}
package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCostConsumeWSBean")
public class ScmInvCostConsumeWSBean extends BaseWSBean {
	
	private ScmInvCostConsume x;
	private ScmInvCostConsume2 y;
	private ScmInvCostConsumeEntry2 z;
	private ScmInvCostConsumeImPort a;
	private ScmInvCostConsumeQuery b;
	public ScmInvCostConsume getX() {
		return x;
	}
	
	public void setX(ScmInvCostConsume x) {
		this.x = x;
	}

    public ScmInvCostConsume2 getY() {
        return y;
    }

    public void setY(ScmInvCostConsume2 y) {
        this.y = y;
    }

    public ScmInvCostConsumeEntry2 getZ() {
        return z;
    }

    public void setZ(ScmInvCostConsumeEntry2 z) {
        this.z = z;
    }

    public ScmInvCostConsumeImPort getA() {
        return a;
    }

    public void setA(ScmInvCostConsumeImPort a) {
        this.a = a;
    }

	public ScmInvCostConsumeQuery getB() {
		return b;
	}

	public void setB(ScmInvCostConsumeQuery b) {
		this.b = b;
	}

    
 
}
package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvSaleIssueBillEntryWSBean")
public class ScmInvSaleIssueBillEntryWSBean extends BaseWSBean {
		
	private ScmInvSaleIssueBillEntry x;
	private ScmInvSaleIssueBillEntry2 y;
	private ScmInvSaleIssueDetailAdvQuery z;
	public ScmInvSaleIssueBillEntry getX() {
		return x;
	}
	
	public void setX(ScmInvSaleIssueBillEntry x) {
		this.x = x;
	}

    public ScmInvSaleIssueBillEntry2 getY() {
        return y;
    }

    public void setY(ScmInvSaleIssueBillEntry2 y) {
        this.y = y;
    }

	public ScmInvSaleIssueDetailAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmInvSaleIssueDetailAdvQuery z) {
		this.z = z;
	}

}
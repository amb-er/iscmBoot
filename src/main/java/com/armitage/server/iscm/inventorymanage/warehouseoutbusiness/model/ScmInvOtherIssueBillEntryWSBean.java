package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmInvOtherIssueBillEntryWSBean")
public class ScmInvOtherIssueBillEntryWSBean extends BaseWSBean{
	private ScmInvOtherIssueBillEntry x;
	private ScmInvOtherIssueBillEntry2 y;
	private ScmInvOtherIssueDetailAdvQuery z;
	public ScmInvOtherIssueBillEntry getX() {
		return x;
	}
	
	public void setX(ScmInvOtherIssueBillEntry x) {
		this.x = x;
	}

	public ScmInvOtherIssueBillEntry2 getY() {
		return y;
	}

	public void setY(ScmInvOtherIssueBillEntry2 y) {
		this.y = y;
	}

	public ScmInvOtherIssueDetailAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmInvOtherIssueDetailAdvQuery z) {
		this.z = z;
	}
	
}

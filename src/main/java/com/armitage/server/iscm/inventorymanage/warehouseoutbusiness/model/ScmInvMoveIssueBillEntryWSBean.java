package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmInvMoveIssueBillEntryWSBean")
public class ScmInvMoveIssueBillEntryWSBean extends BaseWSBean{
	private ScmInvMoveIssueBillEntry x;
	private ScmInvMoveIssueBillEntry2 y;
	private ScmInvMoveIssueDetailAdvQuery z;
	public ScmInvMoveIssueBillEntry getX() {
		return x;
	}
	
	public void setX(ScmInvMoveIssueBillEntry x) {
		this.x = x;
	}

	public ScmInvMoveIssueBillEntry2 getY() {
		return y;
	}

	public void setY(ScmInvMoveIssueBillEntry2 y) {
		this.y = y;
	}

	public ScmInvMoveIssueDetailAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmInvMoveIssueDetailAdvQuery z) {
		this.z = z;
	}
	
}

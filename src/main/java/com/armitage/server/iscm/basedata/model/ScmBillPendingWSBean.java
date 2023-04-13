package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmBillPendingWSBean")
public class ScmBillPendingWSBean extends BaseWSBean {
	
	private ScmBillPending x;
	private ScmBillPendingAdvQuery y;
	
	public ScmBillPending getX() {
		return x;
	}
	
	public void setX(ScmBillPending x) {
		this.x = x;
	}

	public ScmBillPendingAdvQuery getY() {
		return y;
	}

	public void setY(ScmBillPendingAdvQuery y) {
		this.y = y;
	}

}
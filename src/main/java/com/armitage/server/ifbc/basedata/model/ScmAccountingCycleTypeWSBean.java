package com.armitage.server.ifbc.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmAccountingCycleTypeWSBean")
public class ScmAccountingCycleTypeWSBean extends BaseWSBean {
	
	private ScmAccountingCycleTypeToOrg2 x;
	private ScmAccountingCycleType2 y;

	public ScmAccountingCycleTypeToOrg2 getX() {
		return x;
	}

	public void setX(ScmAccountingCycleTypeToOrg2 x) {
		this.x = x;
	}

	public ScmAccountingCycleType2 getY() {
		return y;
	}

	public void setY(ScmAccountingCycleType2 y) {
		this.y = y;
	}

}
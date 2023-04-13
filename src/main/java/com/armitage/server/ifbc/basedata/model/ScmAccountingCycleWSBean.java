package com.armitage.server.ifbc.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmAccountingCycleWSBean")
public class ScmAccountingCycleWSBean extends BaseWSBean {
	
	private ScmAccountingCycle x;
	private ScmAccountingCycleType y;

	public ScmAccountingCycle getX() {
		return x;
	}

	public void setX(ScmAccountingCycle x) {
		this.x = x;
	}

	public ScmAccountingCycleType getY() {
		return y;
	}

	public void setY(ScmAccountingCycleType y) {
		this.y = y;
	}

}
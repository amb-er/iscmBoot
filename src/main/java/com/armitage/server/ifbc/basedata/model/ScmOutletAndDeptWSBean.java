package com.armitage.server.ifbc.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmOutletAndDeptWSBean")
public class ScmOutletAndDeptWSBean extends BaseWSBean {
	
	private ScmProductionDept2 x;
	
	public ScmProductionDept2 getX() {
		return x;
	}
	
	public void setX(ScmProductionDept2 x) {
		this.x = x;
	}

}
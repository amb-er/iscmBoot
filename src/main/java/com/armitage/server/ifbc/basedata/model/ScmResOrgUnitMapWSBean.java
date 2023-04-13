package com.armitage.server.ifbc.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmResOrgUnitMapWSBean")
public class ScmResOrgUnitMapWSBean extends BaseWSBean {
	
	private ScmResOrgUnitMap x;

	public ScmResOrgUnitMap getX() {
		return x;
	}

	public void setX(ScmResOrgUnitMap x) {
		this.x = x;
	}


}
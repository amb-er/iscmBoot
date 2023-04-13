package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmCostCategoryWSBean")
public class ScmCostCategoryWSBean extends BaseWSBean {
	
	private ScmCostCategory x;
	
	public ScmCostCategory getX() {
		return x;
	}
	
	public void setX(ScmCostCategory x) {
		this.x = x;
	}

}
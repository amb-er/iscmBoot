package com.armitage.server.ifbc.costcard.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmItemCostPriceLogWSBean")
public class ScmItemCostPriceLogWSBean extends BaseWSBean {
	
	private ScmItemCostPriceLog y;
	private ScmItemCostPriceLogDetail2 z;
	
	public ScmItemCostPriceLog getY() {
		return y;
	}

	public void setY(ScmItemCostPriceLog y) {
		this.y = y;
	}

	public ScmItemCostPriceLogDetail2 getZ() {
		return z;
	}

	public void setZ(ScmItemCostPriceLogDetail2 z) {
		this.z = z;
	}

}
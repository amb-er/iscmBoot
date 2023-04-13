package com.armitage.server.ifbc.costcard.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmItemCostPriceWSBean")
public class ScmItemCostPriceWSBean extends BaseWSBean {
	
	private ScmItemCostPrice2 x;
	private ScmItemCostPriceLog y;
	private ScmItemCostPriceLogDetail2 z;
	
	public ScmItemCostPrice2 getX() {
		return x;
	}
	
	public void setX(ScmItemCostPrice2 x) {
		this.x = x;
	}

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
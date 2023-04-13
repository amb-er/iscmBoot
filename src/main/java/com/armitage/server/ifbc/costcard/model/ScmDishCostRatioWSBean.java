package com.armitage.server.ifbc.costcard.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmDishCostRatioWSBean")
public class ScmDishCostRatioWSBean extends BaseWSBean {
	
	private ScmDishCostRatio x;
	private ScmDishCostRatio2 y;
	private ScmCostCard2 z;
	
	public ScmDishCostRatio getX() {
		return x;
	}
	
	public void setX(ScmDishCostRatio x) {
		this.x = x;
	}

	public ScmDishCostRatio2 getY() {
		return y;
	}

	public void setY(ScmDishCostRatio2 y) {
		this.y = y;
	}

	public ScmCostCard2 getZ() {
		return z;
	}

	public void setZ(ScmCostCard2 z) {
		this.z = z;
	}

}

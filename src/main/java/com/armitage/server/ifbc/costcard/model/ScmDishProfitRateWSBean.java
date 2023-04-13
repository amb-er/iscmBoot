package com.armitage.server.ifbc.costcard.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmDishProfitRateWSBean")
public class ScmDishProfitRateWSBean extends BaseWSBean {
	
	private ScmDishProfitRate x;
	private ScmCostCard2 y;
	
	public ScmDishProfitRate getX() {
		return x;
	}
	
	public void setX(ScmDishProfitRate x) {
		this.x = x;
	}

	public ScmCostCard2 getY() {
		return y;
	}

	public void setY(ScmCostCard2 y) {
		this.y = y;
	}

}

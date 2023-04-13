package com.armitage.server.ifbc.costcard.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmCostCardWSBean")
public class ScmCostCardWSBean extends BaseWSBean {
	
	private ScmCostCard x;
	private ScmCostCard2 y;
	private ScmCostCardDetail2 z;
	private ScmCostCardAdvQuery a;
	private ScmDishReplaceCostCard b;
	
	public ScmCostCard getX() {
		return x;
	}
	
	public void setX(ScmCostCard x) {
		this.x = x;
	}

	public ScmCostCard2 getY() {
		return y;
	}

	public void setY(ScmCostCard2 y) {
		this.y = y;
	}

	public ScmCostCardDetail2 getZ() {
		return z;
	}

	public void setZ(ScmCostCardDetail2 z) {
		this.z = z;
	}

	public ScmCostCardAdvQuery getA() {
		return a;
	}

	public void setA(ScmCostCardAdvQuery a) {
		this.a = a;
	}

	public ScmDishReplaceCostCard getB() {
		return b;
	}

	public void setB(ScmDishReplaceCostCard b) {
		this.b = b;
	}

}

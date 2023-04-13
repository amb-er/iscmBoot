package com.armitage.server.ifbc.costcard.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmCookCostCardWSBean")
public class ScmCookCostCardWSBean extends BaseWSBean {
	
	private ScmCookCostCard x;
	private ScmCookCostCard2 x1;
	private ScmCookCostCardDetail2 y;
	private ScmCookCostCardAdvQuery a;
	private ScmCostCard2 b;
	
	public ScmCookCostCardAdvQuery getA() {
		return a;
	}

	public void setA(ScmCookCostCardAdvQuery a) {
		this.a = a;
	}

	public ScmCookCostCard2 getX1() {
		return x1;
	}

	public void setX1(ScmCookCostCard2 x1) {
		this.x1 = x1;
	}

	public ScmCookCostCard getX() {
		return x;
	}
	
	public void setX(ScmCookCostCard x) {
		this.x = x;
	}

	public ScmCookCostCardDetail2 getY() {
		return y;
	}

	public void setY(ScmCookCostCardDetail2 y) {
		this.y = y;
	}

	public ScmCostCard2 getB() {
		return b;
	}

	public void setB(ScmCostCard2 b) {
		this.b = b;
	}

}
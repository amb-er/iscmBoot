package com.armitage.server.ifbc.costcard.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmDishReplaceCostCardWSBean")
public class ScmDishReplaceCostCardWSBean extends BaseWSBean {
	
	private ScmDishReplaceCostCard x;
	private ScmDishReplaceCostCardDetail y;
	public ScmDishReplaceCostCard getX() {
		return x;
	}
	public void setX(ScmDishReplaceCostCard x) {
		this.x = x;
	}
	public ScmDishReplaceCostCardDetail getY() {
		return y;
	}
	public void setY(ScmDishReplaceCostCardDetail y) {
		this.y = y;
	}
	

}

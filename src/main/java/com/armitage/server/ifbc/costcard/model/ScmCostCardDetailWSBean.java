package com.armitage.server.ifbc.costcard.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmCostCardDetailWSBean")
public class ScmCostCardDetailWSBean extends BaseWSBean {
	
	private ScmCostCardDetail x;
	private ScmCostCardDetail2 y;
	
	public ScmCostCardDetail getX() {
		return x;
	}
	
	public void setX(ScmCostCardDetail x) {
		this.x = x;
	}

	public ScmCostCardDetail2 getY() {
		return y;
	}

	public void setY(ScmCostCardDetail2 y) {
		this.y = y;
	}

}

package com.armitage.server.ifbc.costcard.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmCostCardPriceWSBean")
public class ScmCostCardPriceWSBean extends BaseWSBean {
	
	private ScmCostCardPrice x;
	
	public ScmCostCardPrice getX() {
		return x;
	}
	
	public void setX(ScmCostCardPrice x) {
		this.x = x;
	}

}
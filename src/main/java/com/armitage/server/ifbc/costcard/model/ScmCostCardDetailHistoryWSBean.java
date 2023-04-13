package com.armitage.server.ifbc.costcard.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmCostCardDetailHistoryWSBean")
public class ScmCostCardDetailHistoryWSBean extends BaseWSBean {
	
	private ScmCostCardDetailHistory x;
	
	public ScmCostCardDetailHistory getX() {
		return x;
	}
	
	public void setX(ScmCostCardDetailHistory x) {
		this.x = x;
	}

}

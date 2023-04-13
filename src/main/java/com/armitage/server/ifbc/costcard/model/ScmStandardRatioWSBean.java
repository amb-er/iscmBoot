package com.armitage.server.ifbc.costcard.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmStandardRatioWSBean")
public class ScmStandardRatioWSBean extends BaseWSBean {
	
	private ScmStandardRatio2 x;

	public ScmStandardRatio2 getX() {
		return x;
	}

	public void setX(ScmStandardRatio2 x) {
		this.x = x;
	}

}
package com.armitage.server.ifbc.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPriceUpdSetWSBean")
public class ScmPriceUpdSetWSBean extends BaseWSBean {
	
	private ScmPriceUpdSet x;

	public ScmPriceUpdSet getX() {
		return x;
	}

	public void setX(ScmPriceUpdSet x) {
		this.x = x;
	}


}
package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmBrandInfoWSBean")
public class ScmBrandInfoWSBean extends BaseWSBean {
	
	private ScmBrandInfo x;
	
	public ScmBrandInfo getX() {
		return x;
	}
	
	public void setX(ScmBrandInfo x) {
		this.x = x;
	}

}
package com.armitage.server.ifbm.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "fbmItemWSBean")
public class FbmItemWSBean extends BaseWSBean {
	
	private FbmItem x;
	
	public FbmItem getX() {
		return x;
	}
	
	public void setX(FbmItem x) {
		this.x = x;
	}

}
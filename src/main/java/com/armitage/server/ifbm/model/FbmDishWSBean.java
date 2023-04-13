package com.armitage.server.ifbm.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "fbmDishWSBean")
public class FbmDishWSBean extends BaseWSBean {
	
	private FbmDish2 x;
	
	public FbmDish2 getX() {
		return x;
	}
	
	public void setX(FbmDish2 x) {
		this.x = x;
	}

}
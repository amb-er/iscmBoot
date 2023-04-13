package com.armitage.server.ifbm.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "fbmDishPrcWSBean")
public class FbmDishPrcWSBean extends BaseWSBean {
	
	private FbmDishPrc2 x;
	
	public FbmDishPrc2 getX() {
		return x;
	}
	
	public void setX(FbmDishPrc2 x) {
		this.x = x;
	}

}
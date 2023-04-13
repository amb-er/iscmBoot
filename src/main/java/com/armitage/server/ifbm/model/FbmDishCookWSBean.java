package com.armitage.server.ifbm.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "FbmDishCookWSBean")
public class FbmDishCookWSBean extends BaseWSBean {
	
	private FbmDishCook x;
	
	public FbmDishCook getX() {
		return x;
	}
	
	public void setX(FbmDishCook x) {
		this.x = x;
	}

}

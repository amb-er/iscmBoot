package com.armitage.server.ifbm.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "FbmCookTypeWSBean")
public class FbmCookTypeWSBean extends BaseWSBean {
	
	private FbmCookType x;
	
	public FbmCookType getX() {
		return x;
	}
	
	public void setX(FbmCookType x) {
		this.x = x;
	}

}

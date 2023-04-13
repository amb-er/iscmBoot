package com.armitage.server.ifbm.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "fbmStatWSBean")
public class FbmStatWSBean extends BaseWSBean {
	
	private FbmStat x;
	
	public FbmStat getX() {
		return x;
	}
	
	public void setX(FbmStat x) {
		this.x = x;
	}

}
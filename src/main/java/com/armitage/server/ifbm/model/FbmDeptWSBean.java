package com.armitage.server.ifbm.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "fbmDeptWSBean")
public class FbmDeptWSBean extends BaseWSBean {
	
	private FbmDept x;
	
	public FbmDept getX() {
		return x;
	}
	
	public void setX(FbmDept x) {
		this.x = x;
	}

}
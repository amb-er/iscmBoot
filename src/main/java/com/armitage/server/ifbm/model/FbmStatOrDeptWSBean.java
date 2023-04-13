package com.armitage.server.ifbm.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "fbmStatOrDeptWSBean")
public class FbmStatOrDeptWSBean extends BaseWSBean {
	
	private FbmStatOrDept x;
	
	public FbmStatOrDept getX() {
		return x;
	}
	
	public void setX(FbmStatOrDept x) {
		this.x = x;
	}

}
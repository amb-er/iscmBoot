package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmSupplierEventWSBean")
public class ScmSupplierEventWSBean extends BaseWSBean {
	
	private ScmSupplierEvent x;
	private ScmSupplierEvent2 y;
	
	public ScmSupplierEvent getX() {
		return x;
	}
	
	public void setX(ScmSupplierEvent x) {
		this.x = x;
	}

	public ScmSupplierEvent2 getY() {
		return y;
	}

	public void setY(ScmSupplierEvent2 y) {
		this.y = y;
	}

}

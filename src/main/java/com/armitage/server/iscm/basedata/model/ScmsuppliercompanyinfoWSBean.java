package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmsuppliercompanyinfoWSBean")
public class ScmsuppliercompanyinfoWSBean extends BaseWSBean {
	
	private Scmsuppliercompanyinfo x;
	private Scmsuppliercompanyinfo2 y;
	
	public Scmsuppliercompanyinfo getX() {
		return x;
	}
	
	public void setX(Scmsuppliercompanyinfo x) {
		this.x = x;
	}

	public Scmsuppliercompanyinfo2 getY() {
		return y;
	}

	public void setY(Scmsuppliercompanyinfo2 y) {
		this.y = y;
	}

}
package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmmaterialGroupWSBean")
public class ScmMaterialGroupWSBean extends BaseWSBean {
	
	private ScmMaterialGroup x;
	private ScmMaterialGroup2 y;
	private ScmMaterialGroupAdvQuery z;
	
	public ScmMaterialGroup getX() {
		return x;
	}
	
	public void setX(ScmMaterialGroup x) {
		this.x = x;
	}

	public ScmMaterialGroup2 getY() {
		return y;
	}

	public void setY(ScmMaterialGroup2 y) {
		this.y = y;
	}

	public ScmMaterialGroupAdvQuery getZ() {
		return z;
	}

	public void setZ(ScmMaterialGroupAdvQuery z) {
		this.z = z;
	}

}
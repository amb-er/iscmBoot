package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmMaterialGroupStandardWSBean")
public class ScmMaterialGroupStandardWSBean extends BaseWSBean {
	
	private ScmMaterialGroupStandard x;
	private ScmMaterialGroupStandard2 y;
	private ScmMaterial2 z;
	
	public ScmMaterialGroupStandard getX() {
		return x;
	}
	
	public void setX(ScmMaterialGroupStandard x) {
		this.x = x;
	}

	public ScmMaterialGroupStandard2 getY() {
		return y;
	}

	public void setY(ScmMaterialGroupStandard2 y) {
		this.y = y;
	}

	public ScmMaterial2 getZ() {
		return z;
	}

	public void setZ(ScmMaterial2 z) {
		this.z = z;
	}

}

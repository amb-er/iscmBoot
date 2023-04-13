package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmMaterialCompanyInfoWSBean")
public class ScmMaterialCompanyInfoWSBean extends BaseWSBean {
	
	private ScmMaterialCompanyInfo x;
	
	public ScmMaterialCompanyInfo getX() {
		return x;
	}
	
	public void setX(ScmMaterialCompanyInfo x) {
		this.x = x;
	}

}
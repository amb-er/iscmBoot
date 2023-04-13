package com.armitage.server.iscm.basedata.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmSupplierQualifyTypeWSBean")
public class ScmSupplierQualifyTypeWSBean extends BaseWSBean {
	
	private ScmSupplierQualifyType x;
	
	public ScmSupplierQualifyType getX() {
		return x;
	}
	
	public void setX(ScmSupplierQualifyType x) {
		this.x = x;
	}

}

package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmPurchaseTypeWSBean")
public class ScmPurchaseTypeWSBean extends BaseWSBean {
	
	private ScmPurchaseType2 x;
	
	public ScmPurchaseType2 getX() {
		return x;
	}
	
	public void setX(ScmPurchaseType2 x) {
		this.x = x;
	}

}
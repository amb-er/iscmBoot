package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmMaterialPurchaseWSBean")
public class ScmMaterialPurchaseWSBean extends BaseWSBean {
	
	private ScmMaterialPurchase2 x;
	
	public ScmMaterialPurchase2 getX() {
		return x;
	}
	
	public void setX(ScmMaterialPurchase2 x) {
		this.x = x;
	}

}
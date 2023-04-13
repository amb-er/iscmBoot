package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurPricePrepareEntryWSBean")
public class ScmPurPricePrepareEntryWSBean extends BaseWSBean {
	
	private ScmPurPricePrepareEntry x;
	private ScmPurPricePrepareEntry2 y;
	
	public ScmPurPricePrepareEntry getX() {
		return x;
	}
	
	public void setX(ScmPurPricePrepareEntry x) {
		this.x = x;
	}

	public ScmPurPricePrepareEntry2 getY() {
		return y;
	}

	public void setY(ScmPurPricePrepareEntry2 y) {
		this.y = y;
	}

}

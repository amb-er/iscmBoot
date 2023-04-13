package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurQuotationEntryWSBean")
public class ScmPurQuotationEntryWSBean extends BaseWSBean {
	
	private ScmPurQuotationEntry x;
	private ScmPurQuotationEntry2 y;
	
	public ScmPurQuotationEntry getX() {
		return x;
	}
	
	public void setX(ScmPurQuotationEntry x) {
		this.x = x;
	}

	public ScmPurQuotationEntry2 getY() {
		return y;
	}

	public void setY(ScmPurQuotationEntry2 y) {
		this.y = y;
	}

}

package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmPurDeliveryWSBean")
public class ScmPurDeliveryWSBean extends BaseWSBean {
	
	private ScmPurDelivery x;
	private ScmPurDeliveryAdvQuery y;

	public ScmPurDelivery getX() {
		return x;
	}

	public void setX(ScmPurDelivery x) {
		this.x = x;
	}

	public ScmPurDeliveryAdvQuery getY() {
		return y;
	}

	public void setY(ScmPurDeliveryAdvQuery y) {
		this.y = y;
	}
	
}

package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmInvSalePriceentryWSBean")
public class ScmInvSalePriceentryWSBean extends BaseWSBean{
	private ScmInvSalePriceentry x;
	private ScmInvSalePriceentry2 y;

	public ScmInvSalePriceentry getX() {
		return x;
	}

	public void setX(ScmInvSalePriceentry x) {
		this.x = x;
	}

	public ScmInvSalePriceentry2 getY() {
		return y;
	}

	public void setY(ScmInvSalePriceentry2 y) {
		this.y = y;
	}
}

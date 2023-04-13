package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmInvSalePriceWSBean")
public class ScmInvSalePriceWSBean extends BaseWSBean{
	private ScmInvSalePrice x;
	private ScmInvSalePrice2 z;
	private ScmInvSalePriceentry2 y;
	private ScmInvSalePriceAdd a;
	private ScmInvSalePriceAdvQuery b;
	public ScmInvSalePriceentry2 getY() {
		return y;
	}

	public void setY(ScmInvSalePriceentry2 y) {
		this.y = y;
	}

	public ScmInvSalePrice getX() {
		return x;
	}

	public void setX(ScmInvSalePrice x) {
		this.x = x;
	}

    public ScmInvSalePrice2 getZ() {
        return z;
    }

    public void setZ(ScmInvSalePrice2 z) {
        this.z = z;
    }

	public ScmInvSalePriceAdd getA() {
		return a;
	}

	public void setA(ScmInvSalePriceAdd a) {
		this.a = a;
	}

	public ScmInvSalePriceAdvQuery getB() {
		return b;
	}

	public void setB(ScmInvSalePriceAdvQuery b) {
		this.b = b;
	}
	
}

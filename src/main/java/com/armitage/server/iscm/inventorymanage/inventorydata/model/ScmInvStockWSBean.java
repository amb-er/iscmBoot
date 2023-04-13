package com.armitage.server.iscm.inventorymanage.inventorydata.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvStockWSBean")
public class ScmInvStockWSBean extends BaseWSBean {
	
	private ScmInvStock x;
	private ScmInvStock2 y;
	
	public ScmInvStock getX() {
		return x;
	}
	
	public void setX(ScmInvStock x) {
		this.x = x;
	}

    public ScmInvStock2 getY() {
        return y;
    }

    public void setY(ScmInvStock2 y) {
        this.y = y;
    }

}

package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmSupplierConfirmDataWSBean")
public class ScmSupplierConfirmDataWSBean extends BaseWSBean {
	
	private ScmSupplierConfirmData2 x;
	private ScmSupplierConfirmOrReplyDataAdvQuery y;
	
	public ScmSupplierConfirmData2 getX() {
		return x;
	}
	
	public void setX(ScmSupplierConfirmData2 x) {
		this.x = x;
	}

	public ScmSupplierConfirmOrReplyDataAdvQuery getY() {
		return y;
	}

	public void setY(ScmSupplierConfirmOrReplyDataAdvQuery y) {
		this.y = y;
	}

}

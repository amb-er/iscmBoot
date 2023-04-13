package com.armitage.server.iscm.basedata.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmSupplierReplyDataWSBean")
public class ScmSupplierReplyDataWSBean extends BaseWSBean {
	
	private ScmSupplierReplyData x;
	
	public ScmSupplierReplyData getX() {
		return x;
	}
	
	public void setX(ScmSupplierReplyData x) {
		this.x = x;
	}

}

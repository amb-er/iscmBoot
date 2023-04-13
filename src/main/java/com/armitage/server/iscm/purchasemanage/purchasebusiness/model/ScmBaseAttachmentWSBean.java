
package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "scmBaseAttachmentWSBean")
public class ScmBaseAttachmentWSBean extends BaseWSBean {
	
	private ScmBaseAttachment x;
	
	public ScmBaseAttachment getX() {
		return x;
	}
	
	public void setX(ScmBaseAttachment x) {
		this.x = x;
	}

}
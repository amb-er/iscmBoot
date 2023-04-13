
package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurGroupWSBean")
public class ScmPurGroupWSBean extends BaseWSBean {
	
	private ScmPurGroup x;
	
	public ScmPurGroup getX() {
		return x;
	}
	
	public void setX(ScmPurGroup x) {
		this.x = x;
	}

}
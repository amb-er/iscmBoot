
package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurREReuseWSBean")
public class ScmPurREReuseWSBean extends BaseWSBean {
	
	private ScmPurREReuse x;
	
	public ScmPurREReuse getX() {
		return x;
	}
	
	public void setX(ScmPurREReuse x) {
		this.x = x;
	}

}
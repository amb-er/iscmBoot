
package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmPurPriceEntryWSBean")
public class ScmPurPriceEntryWSBean extends BaseWSBean {
	
	private ScmPurPriceEntry x;
	private ScmPurPriceEntry2 y;
	
	public ScmPurPriceEntry getX() {
		return x;
	}
	
	public void setX(ScmPurPriceEntry x) {
		this.x = x;
	}

	public ScmPurPriceEntry2 getY() {
		return y;
	}

	public void setY(ScmPurPriceEntry2 y) {
		this.y = y;
	}

	
}
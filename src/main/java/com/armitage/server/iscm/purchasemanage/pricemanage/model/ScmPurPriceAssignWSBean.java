
package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeImPort;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams; 

@XmlRootElement(name = "ScmPurPriceAssignWSBean")
public class ScmPurPriceAssignWSBean extends BaseWSBean {
	
	private ScmPurPriceAssign x;
	private ScmPurPriceAssign2 y;
	
	public ScmPurPriceAssign getX() {
		return x;
	}
	public void setX(ScmPurPriceAssign x) {
		this.x = x;
	}
	public ScmPurPriceAssign2 getY() {
		return y;
	}
	public void setY(ScmPurPriceAssign2 y) {
		this.y = y;
	}
	
}
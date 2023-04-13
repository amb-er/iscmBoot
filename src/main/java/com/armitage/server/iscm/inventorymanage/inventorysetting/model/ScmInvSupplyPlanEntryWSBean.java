
package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvSupplyPlanEntryWSBean")
public class ScmInvSupplyPlanEntryWSBean extends BaseWSBean {
	
	private ScmInvSupplyPlanEntry x;
	private ScmInvSupplyPlanEntry2 y;
	
	public ScmInvSupplyPlanEntry2 getY() {
		return y;
	}

	public void setY(ScmInvSupplyPlanEntry2 y) {
		this.y = y;
	}

	public ScmInvSupplyPlanEntry getX() {
		return x;
	}
	
	public void setX(ScmInvSupplyPlanEntry x) {
		this.x = x;
	}

}
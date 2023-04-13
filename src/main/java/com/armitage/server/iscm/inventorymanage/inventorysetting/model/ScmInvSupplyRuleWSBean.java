
package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2; 

@XmlRootElement(name = "ScmInvSupplyRuleWSBean")
public class ScmInvSupplyRuleWSBean extends BaseWSBean {
	
	private ScmInvSupplyRule x;
	private ScmInvSupplyRuleEntry2 y;
	private ScmInvSupplyPlanEntry2 z;
	private ScmInvSupplyPlan2 o;
	
	
	public ScmInvSupplyPlanEntry2 getZ() {
		return z;
	}

	public void setZ(ScmInvSupplyPlanEntry2 z) {
		this.z = z;
	}

	public ScmInvSupplyPlan2 getO() {
		return o;
	}

	public void setO(ScmInvSupplyPlan2 o) {
		this.o = o;
	}

	public ScmInvSupplyRuleEntry2 getY() {
		return y;
	}

	public void setY(ScmInvSupplyRuleEntry2 y) {
		this.y = y;
	}

	public ScmInvSupplyRule getX() {
		return x;
	}
	
	public void setX(ScmInvSupplyRule x) {
		this.x = x;
	}

}
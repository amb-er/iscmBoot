
package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2; 

@XmlRootElement(name = "ScmInvSupplyPlanWSBean")
public class ScmInvSupplyPlanWSBean extends BaseWSBean {
	
	private ScmInvSupplyPlan x;
	private ScmInvSupplyPlanEntry Y;
	private ScmInvSupplyPlan2 z;
	private ScmInvSupplyPlanEntry2 G;
	private ScmInvSupplyRule M;
	private ScmPurRequireEntry2 N;
	
	public ScmInvSupplyPlan2 getZ() {
		return z;
	}

	public void setZ(ScmInvSupplyPlan2 z) {
		this.z = z;
	}

	public ScmInvSupplyPlanEntry2 getG() {
		return G;
	}

	public void setG(ScmInvSupplyPlanEntry2 g) {
		G = g;
	}

	public ScmInvSupplyRule getM() {
		return M;
	}

	public void setM(ScmInvSupplyRule m) {
		M = m;
	}

	public ScmPurRequireEntry2 getN() {
		return N;
	}

	public void setN(ScmPurRequireEntry2 n) {
		N = n;
	}

	public ScmInvSupplyPlanEntry getY() {
		return Y;
	}

	public void setY(ScmInvSupplyPlanEntry y) {
		Y = y;
	}

	public ScmInvSupplyPlan getX() {
		return x;
	}
	
	public void setX(ScmInvSupplyPlan x) {
		this.x = x;
	}

}
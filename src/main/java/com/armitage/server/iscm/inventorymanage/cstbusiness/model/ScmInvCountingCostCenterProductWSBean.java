package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCountingCostCenterProductWSBean")
public class ScmInvCountingCostCenterProductWSBean extends BaseWSBean {
	
	private ScmInvCountingCostCenterProduct x;
	private ScmInvCountingCostCenterProduct2 y;
	
	public ScmInvCountingCostCenterProduct getX() {
		return x;
	}
	
	public void setX(ScmInvCountingCostCenterProduct x) {
		this.x = x;
	}

	public ScmInvCountingCostCenterProduct2 getY() {
		return y;
	}

	public void setY(ScmInvCountingCostCenterProduct2 y) {
		this.y = y;
	}

}
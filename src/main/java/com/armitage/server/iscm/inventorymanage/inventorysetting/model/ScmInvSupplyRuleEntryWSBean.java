
package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvSupplyRuleEntryWSBean")
public class ScmInvSupplyRuleEntryWSBean extends BaseWSBean {
	
	
	private ScmInvSupplyRuleEntry2 y;
	
	public ScmInvSupplyRuleEntry2 getY() {
		return y;
	}

	public void setY(ScmInvSupplyRuleEntry2 y) {
		this.y = y;
	}


}
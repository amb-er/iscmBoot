package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCountingListUserOrgWSBean")
public class ScmInvCountingListUserOrgWSBean extends BaseWSBean {
	
	private ScmInvCountingListUserOrg x;
	
	public ScmInvCountingListUserOrg getX() {
		return x;
	}
	
	public void setX(ScmInvCountingListUserOrg x) {
		this.x = x;
	}

}
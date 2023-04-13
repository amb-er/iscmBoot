package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvMoveBillEntryWSBean")
public class ScmInvMoveBillEntryWSBean extends BaseWSBean {
	
	private ScmInvMoveBillEntry x;
	private ScmInvMoveBillEntry2 y;
	
	public ScmInvMoveBillEntry getX() {
		return x;
	}
	
	public void setX(ScmInvMoveBillEntry x) {
		this.x = x;
	}

    public ScmInvMoveBillEntry2 getY() {
        return y;
    }

    public void setY(ScmInvMoveBillEntry2 y) {
        this.y = y;
    }
 
}
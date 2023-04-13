package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCostConsumeEntryWSBean")
public class ScmInvCostConsumeEntryWSBean extends BaseWSBean {
	
	private ScmInvCostConsumeEntry x;
	private ScmInvCostConsumeEntry2 y;
	
	public ScmInvCostConsumeEntry getX() {
		return x;
	}
	
	public void setX(ScmInvCostConsumeEntry x) {
		this.x = x;
	}

    public ScmInvCostConsumeEntry2 getY() {
        return y;
    }

    public void setY(ScmInvCostConsumeEntry2 y) {
        this.y = y;
    }
	

}
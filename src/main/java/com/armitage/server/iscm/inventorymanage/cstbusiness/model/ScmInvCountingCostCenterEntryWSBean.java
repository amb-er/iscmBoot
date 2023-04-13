package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvCountingCostCenterEntryWSBean")
public class ScmInvCountingCostCenterEntryWSBean extends BaseWSBean {
    
    private ScmInvCountingCostCenterEntry x;
    private ScmInvCountingCostCenterEntry2 y;
    
    public ScmInvCountingCostCenterEntry getX() {
        return x;
    }
    
    public void setX(ScmInvCountingCostCenterEntry x) {
        this.x = x;
    }

    public ScmInvCountingCostCenterEntry2 getY() {
        return y;
    }

    public void setY(ScmInvCountingCostCenterEntry2 y) {
        this.y = y;
    }

    
}

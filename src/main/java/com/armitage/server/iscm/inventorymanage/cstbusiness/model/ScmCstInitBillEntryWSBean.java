package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmCstInitBillEntryWSBean")
public class ScmCstInitBillEntryWSBean extends BaseWSBean {
    
    private ScmCstInitBillEntry x;
    private ScmCstInitBillEntry2 y;
    
    public ScmCstInitBillEntry getX() {
        return x;
    }
    
    public void setX(ScmCstInitBillEntry x) {
        this.x = x;
    }

    public ScmCstInitBillEntry2 getY() {
        return y;
    }

    public void setY(ScmCstInitBillEntry2 y) {
        this.y = y;
    }
    
}

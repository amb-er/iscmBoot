package com.armitage.server.iscm.inventorymanage.internaltrans.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmInvSaleOrderEntryWSBean")
public class ScmInvSaleOrderEntryWSBean extends BaseWSBean {
	
    private ScmInvSaleOrderEntry x;
	private ScmInvSaleOrderEntry2 y;
    private ScmInvSaleOrder z;
    private ScmInvSaleOrder2 w;
	
	public ScmInvSaleOrderEntry getX() {
		return x;
	}
	
	public void setX(ScmInvSaleOrderEntry x) {
		this.x = x;
	}

    public ScmInvSaleOrderEntry2 getY() {
        return y;
    }

    public void setY(ScmInvSaleOrderEntry2 y) {
        this.y = y;
    }

    public ScmInvSaleOrder getZ() {
        return z;
    }

    public void setZ(ScmInvSaleOrder z) {
        this.z = z;
    }

    public ScmInvSaleOrder2 getW() {
        return w;
    }

    public void setW(ScmInvSaleOrder2 w) {
        this.w = w;
    }

}
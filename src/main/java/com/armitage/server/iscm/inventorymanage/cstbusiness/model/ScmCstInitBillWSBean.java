package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmCstInitBillWSBean")
public class ScmCstInitBillWSBean extends BaseWSBean {
    
    private ScmCstInitBill x;
    private ScmCstInitBill2 y;
    private ScmCstInitBillEntry2 z;
    private ScmCstInitBillImPort a;
    private ScmCstInitBillQuery b;
    public ScmCstInitBill getX() {
        return x;
    }
    
    public void setX(ScmCstInitBill x) {
        this.x = x;
    }

    public ScmCstInitBill2 getY() {
        return y;
    }

    public void setY(ScmCstInitBill2 y) {
        this.y = y;
    }

    public ScmCstInitBillEntry2 getZ() {
        return z;
    }

    public void setZ(ScmCstInitBillEntry2 z) {
        this.z = z;
    }

	public ScmCstInitBillImPort getA() {
		return a;
	}

	public void setA(ScmCstInitBillImPort a) {
		this.a = a;
	}

	public ScmCstInitBillQuery getB() {
		return b;
	}

	public void setB(ScmCstInitBillQuery b) {
		this.b = b;
	}
	
}
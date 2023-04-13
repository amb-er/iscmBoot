package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmCstFrmLossEntryWSBean")
public class ScmCstFrmLossEntryWSBean extends BaseWSBean {
    
    private ScmCstFrmLossEntry x;
    private ScmCstFrmLossEntry2 y;
    
	public ScmCstFrmLossEntry getX() {
		return x;
	}
	public void setX(ScmCstFrmLossEntry x) {
		this.x = x;
	}
	public ScmCstFrmLossEntry2 getY() {
		return y;
	}
	public void setY(ScmCstFrmLossEntry2 y) {
		this.y = y;
	}
    
}

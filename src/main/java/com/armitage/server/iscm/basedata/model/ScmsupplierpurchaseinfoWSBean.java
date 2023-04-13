package com.armitage.server.iscm.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmsupplierpurchaseinfoWSBean")
public class ScmsupplierpurchaseinfoWSBean extends BaseWSBean {
	
	private Scmsupplierpurchaseinfo x;
	private Scmsupplierpurchaseinfo2 y;
	
	public Scmsupplierpurchaseinfo getX() {
		return x;
	}
	
	public void setX(Scmsupplierpurchaseinfo x) {
		this.x = x;
	}

	public Scmsupplierpurchaseinfo2 getY() {
		return y;
	}

	public void setY(Scmsupplierpurchaseinfo2 y) {
		this.y = y;
	}

}
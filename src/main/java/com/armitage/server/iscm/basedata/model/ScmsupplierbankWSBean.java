package com.armitage.server.iscm.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmsupplierbankWSBean")
public class ScmsupplierbankWSBean extends BaseWSBean {
	
	private Scmsupplierbank x;
	
	public Scmsupplierbank getX() {
		return x;
	}
	
	public void setX(Scmsupplierbank x) {
		this.x = x;
	}

}
package com.armitage.server.iscm.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmsuppliergroupWSBean")
public class ScmsuppliergroupWSBean extends BaseWSBean {
	
	private Scmsuppliergroup x;
	private Scmsuppliergroup2 y;
	
	public Scmsuppliergroup getX() {
		return x;
	}
	
	public void setX(Scmsuppliergroup x) {
		this.x = x;
	}
	public Scmsuppliergroup2 getY() {
		return y;
	}

	public void setY(Scmsuppliergroup2 y) {
		this.y = y;
	}

}
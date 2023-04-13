package com.armitage.server.iscm.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmsuppliergroupdetailWSBean")
public class ScmsuppliergroupdetailWSBean extends BaseWSBean {
	
	private Scmsuppliergroupdetail x;
	
	public Scmsuppliergroupdetail getX() {
		return x;
	}
	
	public void setX(Scmsuppliergroupdetail x) {
		this.x = x;
	}

}
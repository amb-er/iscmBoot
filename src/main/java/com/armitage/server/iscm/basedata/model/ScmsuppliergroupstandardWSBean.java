package com.armitage.server.iscm.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmsuppliergroupstandardWSBean")
public class ScmsuppliergroupstandardWSBean extends BaseWSBean {
	
	private Scmsuppliergroupstandard x;
	
	public Scmsuppliergroupstandard getX() {
		return x;
	}
	
	public void setX(Scmsuppliergroupstandard x) {
		this.x = x;
	}

}
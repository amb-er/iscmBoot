package com.armitage.server.iscm.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmsupplierlinkmanWSBean")
public class ScmsupplierlinkmanWSBean extends BaseWSBean {
	
	private Scmsupplierlinkman x;
	
	public Scmsupplierlinkman getX() {
		return x;
	}
	
	public void setX(Scmsupplierlinkman x) {
		this.x = x;
	}

}
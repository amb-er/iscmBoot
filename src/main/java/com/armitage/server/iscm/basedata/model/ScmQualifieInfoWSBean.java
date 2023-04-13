
package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmQualifieInfoWSBean")
public class ScmQualifieInfoWSBean extends BaseWSBean {
	
	private ScmQualifieInfo x;
	
	private ScmQualifieInfo2 y;
	
	public ScmQualifieInfo getX() {
		return x;
	}
	
	public void setX(ScmQualifieInfo x) {
		this.x = x;
	}

	public ScmQualifieInfo2 getY() {
		return y;
	}

	public void setY(ScmQualifieInfo2 y) {
		this.y = y;
	}

}
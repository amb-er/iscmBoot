package com.armitage.server.quartz.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "SynctTaskLogWSBean")
public class ScmSyncTaskLogWSBean extends BaseWSBean {
	
	private ScmSyncTaskLog x;
	private ScmSyncTaskLogAdvQuery y;
	
	public ScmSyncTaskLog getX() {
		return x;
	}
	
	public void setX(ScmSyncTaskLog x) {
		this.x = x;
	}

	public ScmSyncTaskLogAdvQuery getY() {
		return y;
	}

	public void setY(ScmSyncTaskLogAdvQuery y) {
		this.y = y;
	}

}

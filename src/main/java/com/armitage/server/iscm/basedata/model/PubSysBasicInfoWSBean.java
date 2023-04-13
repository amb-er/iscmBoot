package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "PubSysBasicInfoWSBean")
public class PubSysBasicInfoWSBean extends BaseWSBean {
	
	private PubSysBasicInfo x;
	
	public PubSysBasicInfo getX() {
		return x;
	}
	
	public void setX(PubSysBasicInfo x) {
		this.x = x;
	}

}
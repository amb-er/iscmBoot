package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmMeasureUnitGroupWSBean")
public class ScmMeasureUnitGroupWSBean extends BaseWSBean {
	
	private ScmMeasureUnitGroup x;
	
	public ScmMeasureUnitGroup getX() {
		return x;
	}
	
	public void setX(ScmMeasureUnitGroup x) {
		this.x = x;
	}

}

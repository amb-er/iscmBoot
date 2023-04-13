package com.armitage.server.iscm.basedata.model;


import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmIndustryGroupWSBean")
public class ScmIndustryGroupWSBean extends BaseWSBean {
	
	private ScmIndustryGroup x;
	private ScmIndustryGroupQualifyType2 y;
	
	public ScmIndustryGroup getX() {
		return x;
	}
	
	public void setX(ScmIndustryGroup x) {
		this.x = x;
	}

	public ScmIndustryGroupQualifyType2 getY() {
		return y;
	}

	public void setY(ScmIndustryGroupQualifyType2 y) {
		this.y = y;
	}

}

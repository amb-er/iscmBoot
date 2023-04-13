
package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmIndustryGroupQualifyTypeWSBean")
public class ScmIndustryGroupQualifyTypeWSBean extends BaseWSBean {
	
	private ScmIndustryGroupQualifyType x;
	
	
	public ScmIndustryGroupQualifyType getX() {
		return x;
	}
	
	public void setX(ScmIndustryGroupQualifyType x) {
		this.x = x;
	}

}
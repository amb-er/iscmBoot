
package com.armitage.server.iscm.basedata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "ScmMeasureUnitWSBean")
public class ScmMeasureUnitWSBean extends BaseWSBean {
	
	private ScmMeasureUnit x;
	
	public ScmMeasureUnit getX() {
		return x;
	}
	
	public void setX(ScmMeasureUnit x) {
		this.x = x;
	}

}
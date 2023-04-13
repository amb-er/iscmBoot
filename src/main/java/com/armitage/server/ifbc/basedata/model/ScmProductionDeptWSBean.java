package com.armitage.server.ifbc.basedata.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseWSBean; 

@XmlRootElement(name = "ScmProductionDeptWSBean")
public class ScmProductionDeptWSBean extends BaseWSBean {
	
	private ScmProductionDept x;
	private ScmProductionDeptMapping2 y;
	
	public ScmProductionDept getX() {
		return x;
	}
	
	public void setX(ScmProductionDept x) {
		this.x = x;
	}

	public ScmProductionDeptMapping2 getY() {
		return y;
	}

	public void setY(ScmProductionDeptMapping2 y) {
		this.y = y;
	}

}
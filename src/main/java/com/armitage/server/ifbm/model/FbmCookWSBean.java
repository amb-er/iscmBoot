package com.armitage.server.ifbm.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2; 

@XmlRootElement(name = "FbmCookWSBean")
public class FbmCookWSBean extends BaseWSBean {
	
	private FbmCook x;
	private FbmCook2 y;
	private ScmCostCard2 z;
	
	public FbmCook getX() {
		return x;
	}
	
	public void setX(FbmCook x) {
		this.x = x;
	}

	public FbmCook2 getY() {
		return y;
	}

	public void setY(FbmCook2 y) {
		this.y = y;
	}

	public ScmCostCard2 getZ() {
		return z;
	}

	public void setZ(ScmCostCard2 z) {
		this.z = z;
	}

}
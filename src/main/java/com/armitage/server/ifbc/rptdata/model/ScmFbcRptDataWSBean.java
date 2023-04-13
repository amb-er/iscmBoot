package com.armitage.server.ifbc.rptdata.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2; 

@XmlRootElement(name = "scmFbcRptDataWSBean")
public class ScmFbcRptDataWSBean extends BaseWSBean {
	
	private ScmFbcRptData x;
	private ScmDataCollectionStepState2 y;
	
	public ScmFbcRptData getX() {
		return x;
	}

	public void setX(ScmFbcRptData x) {
		this.x = x;
	}

	public ScmDataCollectionStepState2 getY() {
		return y;
	}

	public void setY(ScmDataCollectionStepState2 y) {
		this.y = y;
	}


}
package com.armitage.server.iscm.common.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmDataCollectionRegWSBean")
public class ScmDataCollectionRegWSBean extends BaseWSBean{
	private ScmDataCollectionReg2 a;
	private ScmDataCollectionStepState b;
	private ScmDataCollectionStepState2 c;
	
	public ScmDataCollectionReg2 getA() {
		return a;
	}

	public void setA(ScmDataCollectionReg2 a) {
		this.a = a;
	}

	public ScmDataCollectionStepState getB() {
		return b;
	}

	public void setB(ScmDataCollectionStepState b) {
		this.b = b;
	}

	public ScmDataCollectionStepState2 getC() {
		return c;
	}

	public void setC(ScmDataCollectionStepState2 c) {
		this.c = c;
	}
	
}

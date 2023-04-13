package com.armitage.server.iscm.common.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "scmConfirmRuleWSBean")
public class ScmConfirmRuleWSBean extends BaseWSBean{
	private ScmConfirmRule a;

	public ScmConfirmRule getA() {
		return a;
	}

	public void setA(ScmConfirmRule a) {
		this.a = a;
	}
	
}

package com.armitage.server.iscm.common.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "taskCodeWSBean")
public class TaskCodeWSBean extends BaseWSBean{
	private TaskCode a;

	public TaskCode getA() {
		return a;
	}

	public void setA(TaskCode a) {
		this.a = a;
	}

}

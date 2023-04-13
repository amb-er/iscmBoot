package com.armitage.server.iscm.common.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseWSBean;

@XmlRootElement(name = "taskSettingWSBean")
public class TaskSettingWSBean extends BaseWSBean{
	private TaskSetting2 a;

	public TaskSetting2 getA() {
		return a;
	}

	public void setA(TaskSetting2 a) {
		this.a = a;
	}
	
}

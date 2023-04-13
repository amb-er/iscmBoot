package com.armitage.server.iscm.activityTask.audit.model;

import com.alibaba.fastjson.annotation.JSONField;

public class ScmPurRequireUploadOACreator {
	
	@JSONField(name = "LoginName")
	public String LoginName;

	@JSONField(name = "LoginName") 
	public String getLoginName() {
		return LoginName;
	}

	@JSONField(name = "LoginName") 
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	
}

package com.armitage.server.api.business.security.result;

import io.swagger.annotations.ApiModel;

import java.util.List;

import com.armitage.server.user.model.Module2;
import com.armitage.server.user.model.ModuleOperation;

@ApiModel(value="UsrPrivResult",description="用户权限结果")
public class UsrPrivResult {
	private List<Module2> modulelist;// 功能模块
	private List<ModuleOperation> moduleOperationList;// 功能操作
	private List<Module2> usrFavoriteList;// 用户快捷功能
	public List<Module2> getModulelist() {
		return modulelist;
	}
	public void setModulelist(List<Module2> modulelist) {
		this.modulelist = modulelist;
	}
	public List<ModuleOperation> getModuleOperationList() {
		return moduleOperationList;
	}
	public void setModuleOperationList(List<ModuleOperation> moduleOperationList) {
		this.moduleOperationList = moduleOperationList;
	}
	public List<Module2> getUsrFavoriteList() {
		return usrFavoriteList;
	}
	public void setUsrFavoriteList(List<Module2> usrFavoriteList) {
		this.usrFavoriteList = usrFavoriteList;
	}
}

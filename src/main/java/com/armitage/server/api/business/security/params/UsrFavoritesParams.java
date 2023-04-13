package com.armitage.server.api.business.security.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrFavoritesParams",description="用户快捷功能保存参数对象")
public class UsrFavoritesParams {
	@ApiModelProperty(value="用户编号",dataType="String",example="001",required=true)
	private String usrCode;

	@ApiModelProperty(value="功能编号",dataType="List",example="",required=true)
	private List<Long> moduleIdList;

	public String getUsrCode() {
		return usrCode;
	}

	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}

	public List<Long> getModuleIdList() {
		return moduleIdList;
	}

	public void setModuleIdList(List<Long> moduleIdList) {
		this.moduleIdList = moduleIdList;
	}

}

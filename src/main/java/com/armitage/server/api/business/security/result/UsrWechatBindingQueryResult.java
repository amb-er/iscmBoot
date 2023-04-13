package com.armitage.server.api.business.security.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrWechatBindingQueryResult",description="公众号绑定查询结果")
public class UsrWechatBindingQueryResult {
	
	@ApiModelProperty(value="用户编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String userCode;
	@ApiModelProperty(value="用户名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String userName;
	@ApiModelProperty(value="管理单元编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String controlUnitNo;
	
	@ApiModelProperty(value="管理单元名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String controlUnitName;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String controlUnitNo) {
		this.controlUnitNo = controlUnitNo;
	}

	public String getControlUnitName() {
		return controlUnitName;
	}

	public void setControlUnitName(String controlUnitName) {
		this.controlUnitName = controlUnitName;
	}

	
}

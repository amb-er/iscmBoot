package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrWechatBindingQueryParams",description="公众号解绑查询对象")
public class UsrWechatBindingQueryParams {
	@ApiModelProperty(value="微信用户标识",dataType="String",example="",required=true)
	private String wechatUserId;

	@ApiModelProperty(value="微信公众号码",dataType="String",example="",required=true)
	private String wechatAccount;

	public String getWechatUserId() {
		return wechatUserId;
	}

	public void setWechatUserId(String wechatUserId) {
		this.wechatUserId = wechatUserId;
	}

	public String getWechatAccount() {
		return wechatAccount;
	}

	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}

}

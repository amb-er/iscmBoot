package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrWechatBindingParams",description="公众号绑定参数对象")
public class UsrWechatBindingParams {
	@ApiModelProperty(value="用户编号",dataType="String",example="001",required=true)
	private String usrCode;

	@ApiModelProperty(value="密码",dataType="String",example="",required=true)
	private String pass;

	@ApiModelProperty(value="微信公众号码",dataType="String",example="",required=true)
	private String wechatAccount;

	@ApiModelProperty(value="微信用户标识",dataType="String",example="",required=true)
	private String wechatUserId;

	public String getUsrCode() {
		return usrCode;
	}

	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getWechatAccount() {
		return wechatAccount;
	}

	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}

	public String getWechatUserId() {
		return wechatUserId;
	}

	public void setWechatUserId(String wechatUserId) {
		this.wechatUserId = wechatUserId;
	}

}

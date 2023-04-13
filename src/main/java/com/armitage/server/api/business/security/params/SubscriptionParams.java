package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SubscriptionParams",description="查询公众号设置信息参数对象")
public class SubscriptionParams {
	@ApiModelProperty(value="平台组织编码",dataType="String",example="00000027",required=true)
	private String platFormNo;
	
	@ApiModelProperty(value="公众号",dataType="String",required=true)
	private String publicNo;

	public String getPlatFormNo() {
		return platFormNo;
	}
	public void setPlatFormNo(String platFormNo) {
		this.platFormNo = platFormNo;
	}
	public String getPublicNo() {
		return publicNo;
	}
	public void setPublicNo(String publicNo) {
		this.publicNo = publicNo;
	}

}

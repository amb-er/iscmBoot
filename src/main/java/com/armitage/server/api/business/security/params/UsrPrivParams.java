package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrPrivParams",description="用户授权获取参数")
public class UsrPrivParams {
	@ApiModelProperty(value="用户编号",dataType="String",example="001",required=true)
	private String usrCode;

	@ApiModelProperty(value="产品号",dataType="String",example="iSCM",required=true)
	private String productCode;
	
	public String getUsrCode() {
		return usrCode;
	}
	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}

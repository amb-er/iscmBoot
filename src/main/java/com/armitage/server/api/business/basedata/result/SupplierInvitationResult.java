package com.armitage.server.api.business.basedata.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierResult",description="返回结果集")
public class SupplierInvitationResult {
	
	@ApiModelProperty(value="供应商编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorCode;
	
	@ApiModelProperty(value="邀请码内容",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String invitationContent;

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getInvitationContent() {
		return invitationContent;
	}

	public void setInvitationContent(String invitationContent) {
		this.invitationContent = invitationContent;
	}

}

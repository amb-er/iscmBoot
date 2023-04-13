package com.armitage.server.api.business.attachment.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="AttachmentByBillTypeParams",description="根据单据类型查询附件参数对象")
public class AttachmentByBillTypeParams {
	@ApiModelProperty(value="单据类型",dataType="String",example="ScmPurPrice",required=true)
	private String billType;
	
	@ApiModelProperty(value="单据编码",dataType="String",example="PM00000001",required=true)
	private String billNo;

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	
}

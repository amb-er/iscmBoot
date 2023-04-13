package com.armitage.server.api.business.invsaleorder.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationAuditParams",description="审批参数对象")
public class InvSaleOrderAuditParams {
	@ApiModelProperty(value="订单号",dataType="String",example="123",required=true)
	private String otNo;
	
	@ApiModelProperty(value="审批意见",dataType="String",example="agree",required=true)
	private String opinion;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;

	public String getOtNo() {
		return otNo;
	}

	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getOpinionRemarks() {
		return opinionRemarks;
	}

	public void setOpinionRemarks(String opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}
}

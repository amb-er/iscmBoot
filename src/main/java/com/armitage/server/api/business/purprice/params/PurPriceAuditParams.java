package com.armitage.server.api.business.purprice.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurPriceAuditParams",description="审批参数对象")
public class PurPriceAuditParams {
	@ApiModelProperty(value="定价单号",dataType="String",example="123",required=true)
	private String pmNo;
	
	@ApiModelProperty(value="审批意见",dataType="String",example="agree",required=true)
	private String opinion;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;

	public String getPmNo() {
		return pmNo;
	}

	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
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

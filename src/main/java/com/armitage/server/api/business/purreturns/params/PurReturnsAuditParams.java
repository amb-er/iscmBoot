package com.armitage.server.api.business.purreturns.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReturnsAuditParams",description="审批参数对象")
public class PurReturnsAuditParams {
	@ApiModelProperty(value="退货申请号",dataType="String",example="123",required=true)
	private String rtNo;
	
	@ApiModelProperty(value="审批意见",dataType="String",example="agree",required=true)
	private String opinion;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;

	public String getRtNo() {
		return rtNo;
	}

	public void setRtNo(String rtNo) {
		this.rtNo = rtNo;
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

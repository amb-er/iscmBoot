package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="QualifieBillAuditParams",description="审批参数对象")
public class QualifieBillAuditParams {
	@ApiModelProperty(value="申请单号",dataType="String",example="123",required=true)
	private String billNo;
	
	@ApiModelProperty(value="审批意见",dataType="String",example="agree",required=true)
	private String opinion;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;
	
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
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

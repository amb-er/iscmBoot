package com.armitage.server.api.business.invmaterialreq.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqAuditParams",description="审批参数对象")
public class InvMaterialReqAuditParams {
	@ApiModelProperty(value="领料申请单号",dataType="String",example="123",required=true)
	private String reqNo;
	
	@ApiModelProperty(value="审批意见",dataType="String",example="agree",required=true)
	private String opinion;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;
	
	@ApiModelProperty(value="单据明细",dataType="List<InvMaterialReqAuditDetailParams>",example="123",required=false)
	private List<InvMaterialReqAuditDetailParams> detailList;

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
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

	public List<InvMaterialReqAuditDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvMaterialReqAuditDetailParams> detailList) {
		this.detailList = detailList;
	}
}

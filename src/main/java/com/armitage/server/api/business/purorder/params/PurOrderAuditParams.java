package com.armitage.server.api.business.purorder.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderAuditParams",description="审批参数对象")
public class PurOrderAuditParams {
	@ApiModelProperty(value="订货单号",dataType="String",example="123",required=true)
	private String poNo;
	
	@ApiModelProperty(value="审批意见",dataType="String",example="agree",required=true)
	private String opinion;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;
	
	@ApiModelProperty(value="单据明细",dataType="List<PurOrderAuditDetailParams>",example="123",required=false)
	private List<PurOrderAuditDetailParams> detailList;

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
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

	public List<PurOrderAuditDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PurOrderAuditDetailParams> detailList) {
		this.detailList = detailList;
	}
}

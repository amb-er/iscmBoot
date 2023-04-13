package com.armitage.server.api.business.purrequire.params;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireAuditParams",description="审批参数对象")
public class PurRequireAuditParams {
	@ApiModelProperty(value="请购单号",dataType="String",example="123",required=true)
	private String prNo;
	
	@ApiModelProperty(value="审批意见",dataType="String",example="agree",required=true)
	private String opinion;
	
	@ApiModelProperty(value="审批备注",dataType="String",example="同意",required=false)
	private String opinionRemarks;
	
	@ApiModelProperty(value="单据明细",dataType="List<PurRequireAuditDetailParams>",example="123",required=false)
	private List<PurRequireAuditDetailParams> detailList;
	
	
	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
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

	public List<PurRequireAuditDetailParams> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PurRequireAuditDetailParams> detailList) {
		this.detailList = detailList;
	}
}

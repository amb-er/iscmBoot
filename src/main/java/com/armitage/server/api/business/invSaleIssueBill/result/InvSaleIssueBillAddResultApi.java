package com.armitage.server.api.business.invSaleIssueBill.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "InvSaleIssueBillAddResultApi", description = "返回结果集")
public class InvSaleIssueBillAddResultApi extends ResultApi {
	@ApiModelProperty(value = "销售出库单号", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String otNo;
	
	@ApiModelProperty(value = "第三方单号", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String otherNo;
	
	@ApiModelProperty(value="单据明细",dataType="List")
	private List<InvSaleIssueBillItemAddResult> detailList;

	public String getOtNo() {
		return otNo;
	}

	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}

	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}

	public List<InvSaleIssueBillItemAddResult> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvSaleIssueBillItemAddResult> detailList) {
		this.detailList = detailList;
	}
	
	
}

package com.armitage.server.api.business.invmaterialreqbill.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "InvMaterialReqBillAddResultApi", description = "返回结果集")
public class InvMaterialReqBillAddResultApi extends ResultApi {
	@ApiModelProperty(value = "领料出库单号", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String otNo;
	
	@ApiModelProperty(value="单据明细",dataType="List")
	private List<InvMaterialReqBillItemAddResult> detailList;

	public String getOtNo() {
		return otNo;
	}

	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}

	public List<InvMaterialReqBillItemAddResult> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvMaterialReqBillItemAddResult> detailList) {
		this.detailList = detailList;
	}
	
	
}

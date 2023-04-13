package com.armitage.server.api.business.invmaterialreqbill.result;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.armitage.server.api.common.ResultApi;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="InvMaterialReqBillEditResultApi",description="领料出库单修改返回结果")
public class InvMaterialReqBillEditResultApi extends ResultApi{
	@ApiModelProperty(value = "领料出库单号", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String otNo;
	
	@ApiModelProperty(value="单据明细",dataType="List")
	private List<InvMaterialReqBillItemEditResult> detailList;
	
	public String getOtNo() {
		return otNo;
	}

	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}

	public List<InvMaterialReqBillItemEditResult> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InvMaterialReqBillItemEditResult> detailList) {
		this.detailList = detailList;
	}
	
}

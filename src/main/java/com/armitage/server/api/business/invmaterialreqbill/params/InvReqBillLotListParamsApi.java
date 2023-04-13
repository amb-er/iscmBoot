package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.PageNum;
import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvReqBillLotListParamsApi")
public class InvReqBillLotListParamsApi extends RequestParams{

	@ApiModelProperty(required = true)
	public ApiLogicSymbol logicSymbol;
	
	@ApiModelProperty(required = true)
	public PageNum pageNum;
	
	@ApiModelProperty(required = true)
	private InvReqBillLotListParams params;

	public InvReqBillLotListParams getParams() {
		return params;
	}

	public void setParams(InvReqBillLotListParams params) {
		this.params = params;
	}

	public ApiLogicSymbol getLogicSymbol() {
		return logicSymbol;
	}

	public void setLogicSymbol(ApiLogicSymbol logicSymbol) {
		this.logicSymbol = logicSymbol;
	}

	public PageNum getPageNum() {
		return pageNum;
	}

	public void setPageNum(PageNum pageNum) {
		this.pageNum = pageNum;
	}
}

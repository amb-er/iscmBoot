package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.PageNum;
import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReqPersonParamsApi")
public class PurReqPersonParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	public ApiLogicSymbol logicSymbol;
	
	@ApiModelProperty(required = true)
	public PageNum pageNum;
	
	@ApiModelProperty(required = true)
	private PurReqPersonParams params;

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
	public PurReqPersonParams getParams() {
		return params;
	}
	public void setParams(PurReqPersonParams params) {
		this.params = params;
	}
}

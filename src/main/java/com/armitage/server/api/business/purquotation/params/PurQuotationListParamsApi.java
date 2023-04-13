package com.armitage.server.api.business.purquotation.params;

import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.PageNum;
import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationListParamsApi")
public class PurQuotationListParamsApi extends RequestParams {
    @ApiModelProperty(required = true)
    public ApiLogicSymbol logicSymbol;
    @ApiModelProperty(required = true)
    public PageNum pageNum;
	@ApiModelProperty(required = true)
	private PurQuotationListParams params;
	
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

	public PurQuotationListParams getParams() {
		return params;
	}

	public void setParams(PurQuotationListParams params) {
		this.params = params;
	}
	
}

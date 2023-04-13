package com.armitage.server.api.business.costcounttask.params;

import com.armitage.server.api.common.PageNum;
import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="DeptListParamsApi")
public class DeptListParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private DeptListParams params;
	
	@ApiModelProperty(required = true)
	public PageNum pageNum;

	public DeptListParams getParams() {
		return params;
	}

	public void setParams(DeptListParams params) {
		this.params = params;
	}

	public PageNum getPageNum() {
		return pageNum;
	}

	public void setPageNum(PageNum pageNum) {
		this.pageNum = pageNum;
	}	
	
}

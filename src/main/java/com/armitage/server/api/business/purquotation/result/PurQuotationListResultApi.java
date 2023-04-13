package com.armitage.server.api.business.purquotation.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationListResultApi",description="返回结果集")
public class PurQuotationListResultApi extends ResultApi {
	@ApiModelProperty(value="查询返回结果集",dataType="List")
    private List<PurQuotationListResult> resultList;

	public List<PurQuotationListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurQuotationListResult> resultList) {
		this.resultList = resultList;
	}
}

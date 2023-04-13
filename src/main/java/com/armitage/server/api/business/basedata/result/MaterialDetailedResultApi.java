package com.armitage.server.api.business.basedata.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="MaterialDetailedResultApi",description="返回结果集")
public class MaterialDetailedResultApi extends ResultApi {
	private List<MaterialDetailedResult> resultList;

	public List<MaterialDetailedResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<MaterialDetailedResult> resultList) {
		this.resultList = resultList;
	}


}

package com.armitage.server.api.business.datasync.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="ScmScmSupplierQueryResultApi")
public class ScmSupplierQueryResultApi extends ResultApi {

	private List<ScmSupplierQueryResult> resultList;

	public List<ScmSupplierQueryResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<ScmSupplierQueryResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

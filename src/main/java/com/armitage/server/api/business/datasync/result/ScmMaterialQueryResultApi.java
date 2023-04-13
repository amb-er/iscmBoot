package com.armitage.server.api.business.datasync.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="ScmScmMaterialQueryResultApi")
public class ScmMaterialQueryResultApi extends ResultApi {

	private List<ScmMaterialQueryResult> resultList;

	public List<ScmMaterialQueryResult> getResultList() {
		return resultList;
	}
 
	public void setResultList(List<ScmMaterialQueryResult> resultList) {
		this.resultList = resultList;
	}
	
}

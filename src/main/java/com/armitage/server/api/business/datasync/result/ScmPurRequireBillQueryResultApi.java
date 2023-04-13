package com.armitage.server.api.business.datasync.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

public class ScmPurRequireBillQueryResultApi extends ResultApi {

	
	private List<ScmPurRequireBillQueryResult> resultList;

	public List<ScmPurRequireBillQueryResult> getResultList() {
		return resultList;
	}
 
	public void setResultList(List<ScmPurRequireBillQueryResult> resultList) {
		this.resultList = resultList;
	}
	
}

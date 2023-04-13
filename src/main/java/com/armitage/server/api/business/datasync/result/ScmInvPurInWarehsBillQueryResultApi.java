package com.armitage.server.api.business.datasync.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

public class ScmInvPurInWarehsBillQueryResultApi extends ResultApi {

	private List<ScmInvPurInWarehsBillQueryResult> resultList;

	public List<ScmInvPurInWarehsBillQueryResult> getResultList() {
		return resultList;
	}
 
	public void setResultList(List<ScmInvPurInWarehsBillQueryResult> resultList) {
		this.resultList = resultList;
	}
	
}

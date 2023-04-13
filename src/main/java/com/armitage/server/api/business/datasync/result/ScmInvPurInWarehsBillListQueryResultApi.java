package com.armitage.server.api.business.datasync.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

public class ScmInvPurInWarehsBillListQueryResultApi extends ResultApi {

	private List<ScmInvPurInWarehsBillListQueryResult> resultList;

	public List<ScmInvPurInWarehsBillListQueryResult> getResultList() {
		return resultList;
	}
 
	public void setResultList(List<ScmInvPurInWarehsBillListQueryResult> resultList) {
		this.resultList = resultList;
	}
	
}

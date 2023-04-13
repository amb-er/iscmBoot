package com.armitage.server.api.business.datareport.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="DeptConsumeEntryDataResultApi")
public class DeptConsumeEntryDataResultApi extends ResultApi {
	private List<DeptConsumeEntryDataResult> resultList;

	public List<DeptConsumeEntryDataResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<DeptConsumeEntryDataResult> resultList) {
		this.resultList = resultList;
	}
	
}

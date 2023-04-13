package com.armitage.server.api.business.purrequire.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurRequireListResultApi",description="返回结果集")
public class PurRequireListResultApi extends ResultApi {
	private List<PurRequireListResult> resultList;

	public List<PurRequireListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurRequireListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

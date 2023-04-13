package com.armitage.server.api.business.purreceive.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="PurRecRateListResultApi",description="返回结果集")
public class PurRecRateListResultApi extends ResultApi {
	private List<PurRecRateListResult> resultList;

	public List<PurRecRateListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurRecRateListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

package com.armitage.server.api.business.purorder.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurOrderResultApi",description="返回结果集")
public class PurOrderOAResultApi extends ResultApi {
	private List<PurOrderOAResult> resultList;

	public List<PurOrderOAResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurOrderOAResult> resultList) {
		this.resultList = resultList;
	}
	
}

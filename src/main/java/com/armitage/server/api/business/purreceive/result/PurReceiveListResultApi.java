package com.armitage.server.api.business.purreceive.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="PurReceiveListResultApi",description="返回结果集")
public class PurReceiveListResultApi extends ResultApi {
	private List<PurReceiveListResult> resultList;

	public List<PurReceiveListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurReceiveListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

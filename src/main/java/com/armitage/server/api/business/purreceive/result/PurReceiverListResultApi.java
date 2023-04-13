package com.armitage.server.api.business.purreceive.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="PurReceiverListResultApi",description="返回结果集")
public class PurReceiverListResultApi extends ResultApi {
	private List<PurReceiverListResult> resultList;

	public List<PurReceiverListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurReceiverListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="SubscriptionResultApi",description="查询平台信息返回结果")
public class SubscriptionResultApi extends ResultApi {
	private List<SubscriptionResult> resultList;

	public List<SubscriptionResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<SubscriptionResult> resultList) {
		this.resultList = resultList;
	}

	
}

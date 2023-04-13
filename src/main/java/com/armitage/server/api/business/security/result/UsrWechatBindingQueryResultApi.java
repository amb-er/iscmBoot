package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="UsrWechatBindingQueryResultApi",description="公众号绑定查询结果")
public class UsrWechatBindingQueryResultApi extends ResultApi {
	private List<UsrWechatBindingQueryResult> resultList;

	public List<UsrWechatBindingQueryResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<UsrWechatBindingQueryResult> resultList) {
		this.resultList = resultList;
	}

}

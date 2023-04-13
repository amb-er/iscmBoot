package com.armitage.server.api.business.purorder.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurOrderListResultApi",description="返回结果集")
public class PurOrderListResultApi extends ResultApi {
	private List<PurOrderListResult> resultList;

	public List<PurOrderListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurOrderListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

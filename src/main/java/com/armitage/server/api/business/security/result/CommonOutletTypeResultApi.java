package com.armitage.server.api.business.security.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="CommonOutletTypeResultApi",description="查询可用产品返回结果")
public class CommonOutletTypeResultApi extends ResultApi {
	private List<CommonOutletTypeResult> resultList;

	public List<CommonOutletTypeResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CommonOutletTypeResult> resultList) {
		this.resultList = resultList;
	}

	
}

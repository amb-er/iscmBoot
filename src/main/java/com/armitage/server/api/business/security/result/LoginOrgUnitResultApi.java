package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="LoginOrgUnitResultApi",description="用户可登录组织结果")
public class LoginOrgUnitResultApi extends ResultApi {
	private List<LoginOrgUnitResult> resultList;

	public List<LoginOrgUnitResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<LoginOrgUnitResult> resultList) {
		this.resultList = resultList;
	}

	
}

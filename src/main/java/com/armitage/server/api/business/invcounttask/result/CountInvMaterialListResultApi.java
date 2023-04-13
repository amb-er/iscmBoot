package com.armitage.server.api.business.invcounttask.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="CountInvMaterialListResultApi",description="返回结果集")
public class CountInvMaterialListResultApi extends ResultApi {
	private List<CountInvMaterialListResult> resultList;

	public List<CountInvMaterialListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CountInvMaterialListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

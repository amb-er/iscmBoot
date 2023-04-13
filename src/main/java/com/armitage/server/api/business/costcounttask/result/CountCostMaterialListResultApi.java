package com.armitage.server.api.business.costcounttask.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="CountCostMaterialListResultApi",description="返回结果集")
public class CountCostMaterialListResultApi extends ResultApi {
	private List<CountCostMaterialListResult> resultList;

	public List<CountCostMaterialListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CountCostMaterialListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

package com.armitage.server.api.business.basedata.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="MaterialClassListResultApi",description="返回结果集")
public class MaterialClassListResultApi extends ResultApi {
	private List<MaterialClassListResult> resultList;

	public List<MaterialClassListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<MaterialClassListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

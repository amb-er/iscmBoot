package com.armitage.server.api.business.basedata.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="MaterialListResultApi",description="返回结果集")
public class MaterialListResultApi extends ResultApi {
	private List<MaterialListResult> resultList;

	public List<MaterialListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<MaterialListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

package com.armitage.server.api.business.purrequire.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReqMaterialListResultApi",description="返回结果集")
public class PurReqMaterialListResultApi extends ResultApi {
	private List<PurReqMaterialListResult> resultList;

	public List<PurReqMaterialListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurReqMaterialListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

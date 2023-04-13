package com.armitage.server.api.business.invmaterialreq.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="InvReqMaterialListResultApi",description="返回结果集")
public class InvReqMaterialListResultApi extends ResultApi {
	private List<InvReqMaterialListResult> resultList;

	public List<InvReqMaterialListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvReqMaterialListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

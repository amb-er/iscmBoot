package com.armitage.server.api.business.invmaterialreq.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="InvMaterialReqListResultApi")
public class InvMaterialReqListResultApi extends ResultApi {
	private List<InvMaterialReqListResult> resultList;

	public List<InvMaterialReqListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqListResult> resultList) {
		this.resultList = resultList;
	}
	
}

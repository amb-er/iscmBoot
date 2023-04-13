package com.armitage.server.api.business.invmaterialreq.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="InvMaterialReqDeptResultApi")
public class InvMaterialReqDeptResultApi extends ResultApi {
	private List<InvMaterialReqDeptResult> resultList;

	public List<InvMaterialReqDeptResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqDeptResult> resultList) {
		this.resultList = resultList;
	}
	
}

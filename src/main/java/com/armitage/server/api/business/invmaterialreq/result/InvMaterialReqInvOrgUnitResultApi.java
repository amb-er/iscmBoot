package com.armitage.server.api.business.invmaterialreq.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="InvMaterialReqInvOrgUnitResultApi")
public class InvMaterialReqInvOrgUnitResultApi extends ResultApi {
	private List<InvMaterialReqInvOrgUnitResult> resultList;

	public List<InvMaterialReqInvOrgUnitResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqInvOrgUnitResult> resultList) {
		this.resultList = resultList;
	}
	
}

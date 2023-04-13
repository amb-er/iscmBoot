package com.armitage.server.api.business.basedata.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="InvMaterialReqWareHouseResultApi")
public class InvMaterialReqWareHouseResultApi extends ResultApi {
	private List<InvMaterialReqWareHouseResult> resultList;

	public List<InvMaterialReqWareHouseResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqWareHouseResult> resultList) {
		this.resultList = resultList;
	}
	
}

package com.armitage.server.api.business.datareport.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="SupSupplyOfMaterialDetailsResultApi")
public class SupSupplyOfMaterialDetailsResultApi extends ResultApi {
	private List<SupSupplyOfMaterialDetailsResult> resultList;

	public List<SupSupplyOfMaterialDetailsResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<SupSupplyOfMaterialDetailsResult> resultList) {
		this.resultList = resultList;
	}

}

package com.armitage.server.api.business.purreceive.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReceiveWareHousesResultApi",description="返回结果集")
public class PurReceiveWareHousesResultApi extends ResultApi {
	private List<PurReceiveWareHousesResult> resultList;

	public List<PurReceiveWareHousesResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurReceiveWareHousesResult> resultList) {
		this.resultList = resultList;
	}

}

package com.armitage.server.api.business.purreceive.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="PurRecSupplierListResultApi",description="返回结果集")
public class PurRecSupplierListResultApi extends ResultApi {
	private List<PurRecSupplierListResult> resultList;

	public List<PurRecSupplierListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurRecSupplierListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

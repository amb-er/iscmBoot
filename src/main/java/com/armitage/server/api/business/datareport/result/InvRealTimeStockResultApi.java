package com.armitage.server.api.business.datareport.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="InvRealTimeStockResultApi")
public class InvRealTimeStockResultApi extends ResultApi {
	private List<InvRealTimeStockResult> resultList;

	public List<InvRealTimeStockResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvRealTimeStockResult> resultList) {
		this.resultList = resultList;
	}
	
}

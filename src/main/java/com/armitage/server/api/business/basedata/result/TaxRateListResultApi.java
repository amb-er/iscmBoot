package com.armitage.server.api.business.basedata.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="TaxRateListResultApi",description="返回结果集")
public class TaxRateListResultApi extends ResultApi {
	private List<TaxRateListResult> resultList;

	public List<TaxRateListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<TaxRateListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

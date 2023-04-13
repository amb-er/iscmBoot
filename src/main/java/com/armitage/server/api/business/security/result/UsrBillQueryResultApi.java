package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="UsrBillQueryResultApi",description="用户待(已)审单据查询结果")
public class UsrBillQueryResultApi extends ResultApi {
	private List<UsrBillQueryResult> resultList;

	public List<UsrBillQueryResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<UsrBillQueryResult> resultList) {
		this.resultList = resultList;
	}

}

package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="UsrPendingBillTypeQueryResultApi",description="用户待(已)审单据类型查询结果")
public class UsrPendingBillTypeQueryResultApi extends ResultApi {
	private List<UsrPendingBillTypeQueryResult> resultList;

	public List<UsrPendingBillTypeQueryResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<UsrPendingBillTypeQueryResult> resultList) {
		this.resultList = resultList;
	}

}

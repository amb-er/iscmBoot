package com.armitage.server.api.business.basedata.result;

import java.util.List;
import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="QualifieBillListResultApi",description="返回结果集")
public class QualifieBillListResultApi extends ResultApi {
	private List<QualifieBillListResult> resultList;

	public List<QualifieBillListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<QualifieBillListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}

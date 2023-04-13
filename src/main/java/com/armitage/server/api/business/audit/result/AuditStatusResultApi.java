package com.armitage.server.api.business.audit.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="AuditStatusResultApi",description="返回结果集")
public class AuditStatusResultApi extends ResultApi {
	private List<AuditStatusResult> resultList;

	public List<AuditStatusResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<AuditStatusResult> resultList) {
		this.resultList = resultList;
	}
	
}

package com.armitage.server.api.business.basedata.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="QualifieBillResultApi",description="返回结果集")
public class QualifieBillResultApi extends ResultApi {
	private QualifieBillResult result;

	public QualifieBillResult getResult() {
		return result;
	}

	public void setResult(QualifieBillResult result) {
		this.result = result;
	}
	
}

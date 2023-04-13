package com.armitage.server.api.business.attachment.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="AttachmentByBillTypeResultApi",description="返回结果集")
public class AttachmentByBillTypeResultApi extends ResultApi {

	private List<AttachmentByBillTypeResult> resultList;

	public List<AttachmentByBillTypeResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<AttachmentByBillTypeResult> resultList) {
		this.resultList = resultList;
	}
	
}

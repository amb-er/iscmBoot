package com.armitage.server.api.business.basedata.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="supplierInvitationResultApi",description="获取邀请码返回结果")
public class SupplierInvitationResultApi extends ResultApi {
	private SupplierInvitationResult result;

	public SupplierInvitationResult getResult() {
		return result;
	}

	public void setResult(SupplierInvitationResult result) {
		this.result = result;
	}

}

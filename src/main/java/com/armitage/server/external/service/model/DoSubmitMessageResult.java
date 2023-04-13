package com.armitage.server.external.service.model;

import com.armitage.server.common.base.model.CommonResponse;

public class DoSubmitMessageResult extends CommonResponse {

	public static final String FN_TRANSID = "transId";
	
	private long transId;
	
	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public DoSubmitMessageResult() {
		
	}

}

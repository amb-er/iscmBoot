package com.armitage.server.iscm.webservice.model;

import com.armitage.server.common.base.model.CommonRequest;

public class UpdateRequireStatusParams extends CommonRequest {
    private String purRequireId;
    private String result;
    
	public String getPurRequireId() {
		return purRequireId;
	}
	public void setPurRequireId(String purRequireId) {
		this.purRequireId = purRequireId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}

package com.armitage.server.api.business.attachment.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="AttachmentParams",description="根据附件ID获取附件参数对象")
public class AttachmentParams {

	@ApiModelProperty(value="附件ID",dataType="String",example="1",required=true)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

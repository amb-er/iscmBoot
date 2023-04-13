package com.armitage.server.api.business.attachment.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="AttachmentByBillTypeResult",description="返回结果集resultList")
public class AttachmentByBillTypeResult {

	@ApiModelProperty(value="附件ID",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long id;
	
	@ApiModelProperty(value="附件名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String name;
	
	@ApiModelProperty(value="附件类型",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

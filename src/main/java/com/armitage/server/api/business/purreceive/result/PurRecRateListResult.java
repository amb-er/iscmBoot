package com.armitage.server.api.business.purreceive.result;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="PurRecRateListResult",description="返回结果集resultList")
public class PurRecRateListResult {
	@ApiModelProperty(value="编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String code;

	@ApiModelProperty(value="名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String name;

	@ApiModelProperty(value="值",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal values;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValues() {
		return values;
	}

	public void setValues(BigDecimal values) {
		this.values = values;
	}

	
}

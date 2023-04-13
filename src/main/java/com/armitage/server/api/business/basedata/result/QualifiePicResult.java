package com.armitage.server.api.business.basedata.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="QualifiePicResult",description="资质图片结果集resultList")
public class QualifiePicResult {

	@ApiModelProperty(value="图片ID",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String picData;

	public String getPicData() {
		return picData;
	}

	public void setPicData(String picData) {
		this.picData = picData;
	}


}

package com.armitage.server.api.business.basedata.result;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="QualifieBillDetailResult",description="返回结果集resultList")
public class QualifieBillDetailResult {
	@ApiModelProperty(value="行号",dataType="Int")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int lineId;

	@ApiModelProperty(value="资质类型名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String typeName;
	
	@ApiModelProperty(value="资质图片明细",dataType="List")
	private List<QualifiePicResult> qualifiePicResultList;

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<QualifiePicResult> getQualifiePicResultList() {
		return qualifiePicResultList;
	}

	public void setQualifiePicResultList(List<QualifiePicResult> qualifiePicResultList) {
		this.qualifiePicResultList = qualifiePicResultList;
	}

}

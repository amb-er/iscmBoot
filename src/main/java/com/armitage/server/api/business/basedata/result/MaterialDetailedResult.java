package com.armitage.server.api.business.basedata.result;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="MaterialDetailedResult",description="返回结果集")
public class MaterialDetailedResult {
	@ApiModelProperty(value="管理单元",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String controlUnitNo;
	
	@ApiModelProperty(value="物资类别编码",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String classCode;

	@ApiModelProperty(value="物资类别名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String className;
	
	private List<MaterialDetailedList> detailedList;


	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String controlUnitNo) {
		this.controlUnitNo = controlUnitNo;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<MaterialDetailedList> getDetailedList() {
		return detailedList;
	}

	public void setDetailedList(List<MaterialDetailedList> detailedList) {
		this.detailedList = detailedList;
	}


}

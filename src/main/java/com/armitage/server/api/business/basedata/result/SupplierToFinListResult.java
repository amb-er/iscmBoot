package com.armitage.server.api.business.basedata.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="SupplierToFinListResult",description="返回结果集")
public class SupplierToFinListResult {
	@ApiModelProperty(value="管理单元",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String controlUnitNo;
	
	@ApiModelProperty(value="分组编码",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String classCode;

	@ApiModelProperty(value="分组名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String className;
	
	private List<SupplierToFinListDetailResult> resultList;

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

	public List<SupplierToFinListDetailResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<SupplierToFinListDetailResult> resultList) {
		this.resultList = resultList;
	}
}

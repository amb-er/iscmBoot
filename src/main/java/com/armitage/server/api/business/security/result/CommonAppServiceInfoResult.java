package com.armitage.server.api.business.security.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="CommonAppServiceInfoResult",description="应用服务结果")
public class CommonAppServiceInfoResult {
	@ApiModelProperty(value="服务编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String code;
	
	@ApiModelProperty(value="服务名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String name;

	@ApiModelProperty(value="服务地址",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String url;
	
	@ApiModelProperty(value="产品编码",dataType="Boolean")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private boolean permit;
	
	@ApiModelProperty(value="备注",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String remarks;

	@ApiModelProperty(value="应用服务参数",dataType="List")
	private List<CommonAppServiceInfoDetailResult> detailList;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isPermit() {
		return permit;
	}

	public void setPermit(boolean permit) {
		this.permit = permit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<CommonAppServiceInfoDetailResult> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<CommonAppServiceInfoDetailResult> detailList) {
		this.detailList = detailList;
	}

}
